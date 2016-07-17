package movealarm.kmitl.net.user.entity;

import movealarm.kmitl.net.common.AbstractEntity;
import movealarm.kmitl.net.common.StatusDescription;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Moobi on 30-Oct-15.
 */

@Entity
@Table(name = "user")
public class User extends AbstractEntity {
    private String birthday;
    private int age;
    private int score;
    private int height;
    private int weight;
    private int waistline;
    private double bmi;
    private String email;
    private String facebookID;
    private String facebookFirstName;
    private String facebookLastName;
    private int profileImage;

    @Transient
    private ArrayList<HashMap<String, Object>> temp_scoreLogList = new ArrayList<>();
    @Transient
    private ArrayList<HashMap<String, Object>> temp_activityLogList = new ArrayList<>();


    public HashMap<String, Object> setAge(int age)
    {
        if(age < 1) //prevent from improper value
            return StatusDescription.createProcessStatus(false, "Age should not be less than 1.");

        this.age = age;
        updateModifiedDate();

        return StatusDescription.createProcessStatus(true);
    }

    public void setEmail(String email)
    {
        this.email = email;
        updateModifiedDate();
    }

    public void setScore(int score)
    {
        this.score = score;
        updateModifiedDate();
    }

    public ArrayList<HashMap<String, Object>> getTemp_scoreLogList() {
        return temp_scoreLogList;
    }

    public void setTemp_scoreLogList(ArrayList<HashMap<String, Object>> temp_scoreLogList) {
        this.temp_scoreLogList = temp_scoreLogList;
    }

    public ArrayList<HashMap<String, Object>> getTemp_activityLogList() {
        return temp_activityLogList;
    }

    public void setTemp_activityLogList(ArrayList<HashMap<String, Object>> temp_activityLogList) {
        this.temp_activityLogList = temp_activityLogList;
    }

    public void setFacebookID(String facebookID)
    {
        this.facebookID = facebookID;
        updateModifiedDate();
    }

    public void setFacebookFirstName(String facebookFirstName)
    {
        this.facebookFirstName = facebookFirstName;
        updateModifiedDate();
    }

    public void setFacebookLastName(String facebookLastName)
    {
        this.facebookLastName = facebookLastName;
        updateModifiedDate();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        updateModifiedDate();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateModifiedDate();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        updateModifiedDate();
    }

    public int getWaistline() {
        return waistline;
    }

    public void setWaistline(int waistline) {
        this.waistline = waistline;
        updateModifiedDate();
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
        updateModifiedDate();
    }

    public int getAge()
    {
        return age;
    }

    public int getScore()
    {
        return score;
    }

    public String getEmail()
    {
        return email;
    }

    public Object getProfileImage()
    {
        return profileImage;
    }

    public String getFacebookID()
    {
        return facebookID;
    }

    public String getFacebookFirstName()
    {
        return facebookFirstName;
    }

    public String getFacebookLastName()
    {
        return facebookLastName;
    }

    public Date getModifiedDate()
    {
        return modifiedDate;
    }
}