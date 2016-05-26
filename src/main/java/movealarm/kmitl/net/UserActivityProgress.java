package movealarm.kmitl.net;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

    //type = 0 => day, type = 1 => week
    public UserActivityProgress(int type) {
        if(type == 0) {
            this.tableName = "userActivity_progress_day";
        }
        else if(type == 1) {
            this.tableName = "userActivity_progress_week";
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
