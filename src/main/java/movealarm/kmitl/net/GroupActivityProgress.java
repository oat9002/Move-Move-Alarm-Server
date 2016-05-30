package movealarm.kmitl.net;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class GroupActivityProgress extends Model {
    private double totalExerciseTime = 0;
    private int numberOfAccept = 0;
    private int totalActivity = 0;
    private int neck = 0;
    private int shoulder = 0;
    private int chest_back = 0;
    private int wrist = 0;
    private int waist = 0;
    private int hip_leg_calf = 0;
    private Group group;

    public GroupActivityProgress()
    {
        this.tableName = "groupActivity_progress_week";

        //this.addRequiredField("numberOfAccept");
        //this.addRequiredField("cancelActivity");
        //this.addRequiredField("groupID");
    }



    public static GroupActivityProgress findByGroup(Group group)
    {
        Converter converter = Converter.getInstance();
        ArrayList<HashMap<String, Object>> data = modelCollection.where("groupActivity_progress", "groupID", "=", converter.toString(group.getID()));

        if(data == null || data.size() == 0)
            return null;

        HashMap<String, Object> progressData = data.get(0);

        GroupActivityProgress model = new GroupActivityProgress();
        model.setID(converter.toInt(progressData.get("id")));
        model.setTotalExerciseTime(converter.toDouble(progressData.get("totalExerciseTime")));
        model.setTotalActivity(converter.toInt(progressData.get("totalActivity")));
        model.setNumberOfAccept(converter.toInt(progressData.get("numberOfAccept")));
        model.setNeck(converter.toInt(progressData.get("neck")));
        model.setShoulder(converter.toInt(progressData.get("shoulder")));
        model.setChest_back(converter.toInt(progressData.get("chest_back")));
        model.setWrist(converter.toInt(progressData.get("wrist")));
        model.setWaist(converter.toInt(progressData.get("waist")));
        model.setHip_leg_calf(converter.toInt(progressData.get("hip_leg_calf")));
        model.setCreatedDate((Date)progressData.get("createdDate"));
        model.setModidiedDate((Date)progressData.get("modifiedDate"));

        return model;
    }


    @Override
    public HashMap<String, Object> getValues() //get all values from model
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> temp = new HashMap<>();

        temp.put("totalExerciseTime", totalExerciseTime);
        temp.put("totalActivity", totalActivity);
        temp.put("numberOfAccept", numberOfAccept);
        temp.put("neck", neck);
        temp.put("shoulder", shoulder);
        temp.put("chest_back", chest_back);
        temp.put("wrist", wrist);
        temp.put("waist", waist);
        temp.put("hip_leg_calf", hip_leg_calf);
        temp.put("groupID", group.getID());
        if(modifiedDate != null)
        temp.put("modifiedDate", sdf.format(modifiedDate));
        if(modifiedDate != null)
            temp.put("modifiedDate", sdf2.format(modifiedDate));

        return temp;
    }

    @Override
    public HashMap<String, Object> getGeneralValues() //get only common values
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> temp = new HashMap<>();

        temp.put("totalExerciseTime", totalExerciseTime);
        temp.put("totalActivity", totalActivity);
        temp.put("numberOfAccept", numberOfAccept);
        temp.put("neck", neck);
        temp.put("shoulder", shoulder);
        temp.put("chest_back", chest_back);
        temp.put("wrist", wrist);
        temp.put("waist", waist);
        temp.put("hip_leg_calf", hip_leg_calf);

        return temp;
    }

    @Override
    public HashMap<String, Object> save() //save all progresses
    {
        HashMap<String, Object> requiredFields = checkRequiredFields(); //check if some required fields still not filled
        if (requiredFields != null)
            return requiredFields; //return name of need required fields
        GroupActivityProgress progress_check = GroupActivityProgress.findByGroup(getGroup());
        if (progress_check == null) { //if this model has never been saved before
            HashMap<String, Object> temp = modelCollection.create(this); //create row on the database first and receive id, created date
            if (temp == null) //if an error has occurred while inserting data to the database
                return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");
            id = Integer.parseInt("" + temp.get("id")); //set id and created date of this model
            return StatusDescription.createProcessStatus(true); //return new model
        }

        Calendar createdDate_temp = Calendar.getInstance();
        createdDate_temp.setTime(progress_check.getCreatedDate());
        Calendar today = Calendar.getInstance();
        if(today.YEAR == createdDate_temp.YEAR) {
            if (today.WEEK_OF_YEAR == createdDate_temp.WEEK_OF_YEAR) { //save to db in the same week.
                //set all stuff
                GroupActivityProgress progress = GroupActivityProgress.findByGroup(getGroup());
                setID(progress.getID());
                setTotalExerciseTime(getTotalExerciseTime() + progress.getTotalExerciseTime());
                setTotalActivity(getTotalActivity() + progress.getTotalActivity());
                setNumberOfAccept(getNumberOfAccept() + progress.getNumberOfAccept());
                setNeck(getNeck() + progress.getNeck());
                setShoulder(getShoulder() + progress.getShoulder());
                setChest_back(getChest_back() + progress.getChest_back());
                setWrist(getWrist() + progress.getWrist());
                setWaist(getWaist() + progress.getWaist());
                setHip_leg_calf(getHip_leg_calf() + progress.getHip_leg_calf());
                HashMap<String, Object> status = StatusDescription.createProcessStatus(modelCollection.save(this));
                return status;
            }
        }
        //save to db different week or year(Have to delete old row and create a new one.)
        GroupActivityProgress progress = GroupActivityProgress.findByGroup(getGroup());
        HashMap<String, Object> status = StatusDescription.createProcessStatus(modelCollection.delete(progress));
        HashMap<String, Object> temp = modelCollection.create(this); //create row on the database first and receive id, created date
        if (temp == null) //if an error has occurred while inserting data to the database
            return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");

        id = Integer.parseInt("" + temp.get("id")); //set id and created date of this model
        createdDate = (Date) temp.get("created_date");
        status.put("status_create", true);
        return status; //return new model
    }

    public void setNumberOfAccept(int numberOfAccept)
    {
        this.numberOfAccept = numberOfAccept;
        updateModifiedDate();
    }

    public void setGroup(Group group)
    {
        this.group = group;
        updateModifiedDate();
    }

    public double getTotalExerciseTime() {
        return totalExerciseTime;
    }

    public void setTotalExerciseTime(double totalExerciseTime) {
        this.totalExerciseTime = totalExerciseTime;
        updateModifiedDate();
    }

    public int getNumberOfAccept() {
        return numberOfAccept;
    }

    public int getTotalActivity() {
        return totalActivity;
    }

    public void setTotalActivity(int totalActivity) {
        this.totalActivity = totalActivity;
        updateModifiedDate();
    }

    public int getNeck() {
        return neck;
    }

    public void setNeck(int neck) {
        this.neck = neck;
        updateModifiedDate();
    }

    public int getShoulder() {
        return shoulder;
    }

    public void setShoulder(int shoulder) {
        this.shoulder = shoulder;
        updateModifiedDate();
    }

    public int getChest_back() {
        return chest_back;
    }

    public void setChest_back(int chest_back) {
        this.chest_back = chest_back;
        updateModifiedDate();
    }

    public int getWrist() {
        return wrist;
    }

    public void setWrist(int wrist) {
        this.wrist = wrist;
        updateModifiedDate();
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
        updateModifiedDate();
    }

    public int getHip_leg_calf() {
        return hip_leg_calf;
    }

    public void setHip_leg_calf(int hip_leg_calf) {
        this.hip_leg_calf = hip_leg_calf;
        updateModifiedDate();
    }

    public Group getGroup() {
        return group;
    }

    public void setID(int id) {
        this.id = id;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date date) {
        this.createdDate = date;
        updateModifiedDate();
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModidiedDate(Date date) {
        this.modifiedDate = date;
        updateModifiedDate();
    }

    public void increaseAcceptTime(int time)
    {
        this.numberOfAccept += time;
    }

    /*public void increaseCancelTime(int time)
    {
        this.cancelActivity += time;
    }*/
}
