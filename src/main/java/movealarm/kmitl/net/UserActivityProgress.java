package movealarm.kmitl.net;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;

public class UserActivityProgress extends Model {

    private double totalExerciseTime = 0;
    private int numberOfAccept = 0;
    private int totalActivity = 0;
    private int neck = 0;
    private int shoulder = 0;
    private int chest_back = 0;
    private int wrist = 0;
    private int waist = 0;
    private int hip_leg_calf = 0;
    private User user;
    private int type = -1;

    //type = 0 => day, type = 1 => week
    public UserActivityProgress(int type) {
        if(type == 0) {
            this.tableName = "userActivity_progress_day";
            this.type = 0;
        }
        else if(type == 1) {
            this.tableName = "userActivity_progress_week";
            this.type = 1;
        }
        else {
            System.out.println("Type is not correct.");
        }
    }

    public static UserActivityProgress findByUser(User user, int type) {
        Converter converter = Converter.getInstance();
        ArrayList<HashMap<String,Object>> data;
        if(type == 0) {
            data = modelCollection.where("userActivity_progress_day","userID","=",converter.toString(user.getID()));
        }
        else if(type == 1) {
            data = modelCollection.where("userActivity_progress_week","userID","=",converter.toString(user.getID()));
        }
        else {
            System.out.println("Type is not correct.");
            return null;
        }

        if(data == null || data.size() == 0) {
            return null;
        }

        HashMap<String, Object> model = data.get(0);

        UserActivityProgress progress = new UserActivityProgress(type);
        progress.setTotalExerciseTime(converter.toDouble(model.get("totalExerciseTime")));
        progress.setTotalActivity(converter.toInt(model.get("totalActivity")));
        progress.setNumberOfAccept(converter.toInt(model.get("numberOfAccept")));
        progress.setNeck(converter.toInt(model.get("neck")));
        progress.setShoulder(converter.toInt(model.get("shoulder")));
        progress.setChest_back(converter.toInt(model.get("chest_back")));
        progress.setWrist(converter.toInt(model.get("wrist")));
        progress.setWaist(converter.toInt(model.get("waist")));
        progress.setHip_leg_calf(converter.toInt(model.get("hip_leg_calf")));
        progress.setUser(user);

        return progress;
    }

    @Override
    public HashMap<String, Object> getValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        temp.put("userID", user.getID());

        if(modifiedDate != null)
            temp.put("modifiedDate", sdf.format(modifiedDate));
        if(createdDate != null)
            temp.put("createdDate", sdf.format(createdDate));

        return temp;
    }

    @Override
    public HashMap<String, Object> getGeneralValues() {
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
        temp.put("userID", user.getID());

        return temp;
    }

    @Override
    public HashMap<String, Object> save() //save all progresses
    {
        HashMap<String, Object> requiredFields = checkRequiredFields(); //check if some required fields still not filled
        if(requiredFields != null)
            return requiredFields; //return name of need required fields

        if(createdDate == null) { //if this model has never been saved before
            HashMap<String, Object> temp = modelCollection.create(this); //create row on the database first and receive id, created date
            this.setType(1);
            HashMap<String, Object> temp2 = modelCollection.create(this);
            if(temp == null || temp2 == null) //if an error has occurred while inserting data to the database
                return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");

            id = Integer.parseInt("" + temp.get("id")); //set id and created date of this model
            createdDate = (Date) temp.get("created_date");
            return StatusDescription.createProcessStatus(true); //return new model
        }

        Calendar createdDate_temp = Calendar.getInstance();
        createdDate_temp.setTime(getCreatedDate());
        Calendar today = Calendar.getInstance();
        if(today.YEAR == createdDate_temp.YEAR) {
            if(today.WEEK_OF_YEAR == createdDate_temp.WEEK_OF_YEAR) {
                if(today.DATE == createdDate_temp.DATE) {
                    UserActivityProgress progress = UserActivityProgress.findByUser(getUser(),0);
                    setType(0);
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
                    progress = UserActivityProgress.findByUser(getUser(),1);
                    setType(1);
                    setTotalExerciseTime(getTotalExerciseTime() + progress.getTotalExerciseTime());
                    setTotalActivity(getTotalActivity() + progress.getTotalActivity());
                    setNumberOfAccept(getNumberOfAccept() + progress.getNumberOfAccept());
                    setNeck(getNeck() + progress.getNeck());
                    setShoulder(getShoulder() + progress.getShoulder());
                    setChest_back(getChest_back() + progress.getChest_back());
                    setWrist(getWrist() + progress.getWrist());
                    setWaist(getWaist() + progress.getWaist());
                    setHip_leg_calf(getHip_leg_calf() + progress.getHip_leg_calf());
                    status.put("status_week", modelCollection.save(this));
                    return status;
                }
                else {
                    UserActivityProgress progress = UserActivityProgress.findByUser(getUser(),0);
                    HashMap<String, Object> status = StatusDescription.createProcessStatus(modelCollection.delete(progress));
                    setType(0);
                    HashMap<String, Object> temp = modelCollection.create(this);
                    if(temp == null) //if an error has occurred while inserting data to the database
                        return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");
                    progress = UserActivityProgress.findByUser(getUser(),1);
                    setType(1);
                    setTotalExerciseTime(getTotalExerciseTime() + progress.getTotalExerciseTime());
                    setTotalActivity(getTotalActivity() + progress.getTotalActivity());
                    setNumberOfAccept(getNumberOfAccept() + progress.getNumberOfAccept());
                    setNeck(getNeck() + progress.getNeck());
                    setShoulder(getShoulder() + progress.getShoulder());
                    setChest_back(getChest_back() + progress.getChest_back());
                    setWrist(getWrist() + progress.getWrist());
                    setWaist(getWaist() + progress.getWaist());
                    setHip_leg_calf(getHip_leg_calf() + progress.getHip_leg_calf());
                    status.put("status_save",modelCollection.save(this));
                    return status;
                }
            }
            else {
                UserActivityProgress progress = UserActivityProgress.findByUser(getUser(), 0);
                HashMap<String, Object> status = StatusDescription.createProcessStatus(modelCollection.delete(progress));
                progress = UserActivityProgress.findByUser(getUser(), 1);
                status.put("status_delete week", modelCollection.delete(progress));
                HashMap<String, Object> temp = modelCollection.create(this); //create row on the database first and receive id, created date
                this.setType(1);
                HashMap<String, Object> temp2 = modelCollection.create(this);
                if(temp == null || temp2 == null) //if an error has occurred while inserting data to the database
                    return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");

                id = Integer.parseInt("" + temp.get("id")); //set id and created date of this model
                createdDate = (Date) temp.get("created_date");
                status.put("status_create", true);
                return status; //return new model
            }
        }

        return StatusDescription.createProcessStatus(modelCollection.save(this)); //return saving status if all required process are complete
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if(type == 0) {
            this.type = type;
            this.tableName = "userActivity_progress_day";
        }
        else if(type == 1) {
            this.type = type;
            this.tableName = "userActivity_progress_day";
        }
        else {
            this.type = -1;
            this.tableName = "";
            System.out.println("Invalid type;");
        }
    }

    public double getTotalExerciseTime() {
        return totalExerciseTime;
    }

    public void setTotalExerciseTime(double totalExerciseTime) {
        this.totalExerciseTime = totalExerciseTime;
    }

    public int getNumberOfAccept() {
        return numberOfAccept;
    }

    public void setNumberOfAccept(int numberOfAccept) {
        this.numberOfAccept = numberOfAccept;
    }

    public int getTotalActivity() {
        return totalActivity;
    }

    public void setTotalActivity(int totalActivity) {
        this.totalActivity = totalActivity;
    }

    public int getNeck() {
        return neck;
    }

    public void setNeck(int neck) {
        this.neck = neck;
    }

    public int getShoulder() {
        return shoulder;
    }

    public void setShoulder(int shoulder) {
        this.shoulder = shoulder;
    }

    public int getChest_back() {
        return chest_back;
    }

    public void setChest_back(int chest_back) {
        this.chest_back = chest_back;
    }

    public int getWrist() {
        return wrist;
    }

    public void setWrist(int wrist) {
        this.wrist = wrist;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getHip_leg_calf() {
        return hip_leg_calf;
    }

    public void setHip_leg_calf(int hip_leg_calf) {
        this.hip_leg_calf = hip_leg_calf;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

/*public class UserActivityProgress extends Model {
    private int numberOfAccept = 0;
    private int numberOfCancel = 0;
    private int cancelActivity = 0;
    private User user;
    private Date date;

    public UserActivityProgress()
    {
        this.tableName = "userActivity_progress";

        this.addRequiredField("numberOfAccept");
        this.addRequiredField("cancelActivity");
        this.addRequiredField("userID");
    }

    public static UserActivityProgress findByUser(User user)
    {
        Converter converter = Converter.getInstance();
        ArrayList<HashMap<String, Object>> data = modelCollection.where("userActivity_progress", "userID", "=", converter.toString(user.getID()));

        if(data == null || data.size() == 0)
            return null;

        HashMap<String, Object> progressData = data.get(0);

        UserActivityProgress model = new UserActivityProgress();
        model.id = converter.toInt(progressData.get("id"));
        model.user = user;
        model.numberOfAccept = converter.toInt(progressData.get("numberOfAccept"));
        model.numberOfCancel = converter.toInt(progressData.get("numberOfCancel"));
        model.cancelActivity = converter.toInt(progressData.get("cancelActivity"));
        model.date = (Date) progressData.get("date");
        model.createdDate = (Date) progressData.get("createdDate");
        model.modifiedDate = (Date) progressData.get("modifiedDate");
        return model;
    }

    @Override
    public HashMap<String, Object> getValues() //get all values from model
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> temp = new HashMap<>();

        temp.put("numberOfAccept", numberOfAccept);
        temp.put("numberOfCancel", numberOfCancel);
        temp.put("cancelActivity", cancelActivity);
        temp.put("userID", user.getID());
        if(date != null)
            temp.put("date", sdf.format(date));
        if(modifiedDate != null)
            temp.put("modifiedDate", sdf2.format(modifiedDate));

        return temp;
    }

    @Override
    public HashMap<String, Object> getGeneralValues() //get only common values
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> temp = new HashMap<>();

        temp.put("numberOfAccept", numberOfAccept);
        temp.put("numberOfCancel", numberOfCancel);
        temp.put("cancelActivity", cancelActivity);
        temp.put("user", user.getGeneralValues());
        if(date != null)
            temp.put("date", sdf.format(date));

        return temp;
    }

    public void setNumberOfAccept(int numberOfAccept)
    {
        this.numberOfAccept = numberOfAccept;
        updateModifiedDate();
    }

    public void setNumberOfCancel(int numberOfCancel)
    {
        this.numberOfCancel = numberOfCancel;
        updateModifiedDate();
    }

    public void setCancelActivity(int cancelActivity)
    {
        this.cancelActivity = cancelActivity;
        updateModifiedDate();
    }

    public void setUser(User user)
    {
        this.user = user;
        updateModifiedDate();
    }

    public void setDate(Date date)
    {
        this.date = date;
        updateModifiedDate();
    }
}*/
