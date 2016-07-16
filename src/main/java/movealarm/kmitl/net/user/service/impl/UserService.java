package movealarm.kmitl.net.user.service.impl;

import movealarm.kmitl.net.common.StatusDescription;
import movealarm.kmitl.net.user.entity.User;
import movealarm.kmitl.net.user.repository.UserRepository;
import movealarm.kmitl.net.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static movealarm.kmitl.net.common.ModelCollection.modelCollection;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by oat on 7/16/16.
 */

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public HashMap<String, Object> getValues(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("id", user.getId());
        temp.put("birthday", user.getBirthday());
        temp.put("age", user.getAge());
        temp.put("score", user.getScore());
        temp.put("height", user.getHeight());
        temp.put("weight", user.getWeight());
        temp.put("waistline", user.getWaistline());
        temp.put("bmi", user.getBmi());
        temp.put("email", user.getEmail());
        temp.put("profileImage", user.getProfileImage());
        temp.put("facebookID", user.getFacebookID());
        temp.put("facebookFirstName", user.getFacebookFirstName());
        temp.put("facebookLastName", user.getFacebookLastName());
        if(user.getModifiedDate() == null)
            temp.put("modifiedDate", null);
        else
            temp.put("modifiedDate", sdf.format(user.getModifiedDate()));
        return temp;
    }

    @Override
    public HashMap<String, Object> getGeneralValues(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("id", user.getId());
        temp.put("birthday", user.getBirthday());
        temp.put("age", user.getAge());
        temp.put("score", user.getScore());
        temp.put("height", user.getHeight());
        temp.put("weight", user.getWeight());
        temp.put("waistline", user.getWaistline());
        temp.put("bmi", user.getBmi());
        temp.put("email", user.getEmail());
        temp.put("profileImage", user.getProfileImage());
        temp.put("facebookID", user.getFacebookID());
        temp.put("facebookFirstName", user.getFacebookFirstName());
        temp.put("facebookLastName", user.getFacebookLastName());
        return temp;
    }

    @Override
    public User find(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User[] where(String colName, String operator, String value) {
        return userRepository.where(colName, operator, value);
    }

    @Override
    public User[] where(String colName, String operator, String value, String extraCondition) {
        return userRepository.where(colName, operator, value, extraCondition);
    }

    @Override
    public User[] all() {
        return (User[])userRepository.findAll().toArray();
    }

    @Override
    public HashMap<String, Object> update(User user) {
        User userUpdated = userRepository.saveAndFlush(user);
        if(userUpdated != null) {
            return StatusDescription.createProcessStatus(true);
        }
        else {
            return StatusDescription.createProcessStatus(false);
        }
    }

    @Override
    public HashMap<String, Object> save(User user) {
        if(user.getCreatedDate() == null) {
            HashMap<String, Object> temp = modelCollection.create(this);
            if(temp == null)
                return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");
            user.setId(Integer.parseInt("" + temp.get("id")));
            user.setCreatedDate((Date) temp.get("created_date"));
            return StatusDescription.createProcessStatus(true);
        }

        HashMap<String, Object> addScoreLogStatus = addScoreLogToDatabase(); //add score log to database
        if(addScoreLogStatus != null) //if an error has occurred while adding score logs to the database
            return addScoreLogStatus; //break saving process and return error description

        user.setTemp_scoreLogList(null);
        User
        return StatusDescription.createProcessStatus(userRepository.save(user));
    }

    @Override
    public  HashMap<String, Object>  delete(User user) {
        try {
            userRepository.delete(user);
            return StatusDescription.createProcessStatus(true);
        }
        catch (Exception e) {
            return StatusDescription.createProcessStatus(false);
        }
    }

    @Override
    public HashMap<String, Object> addScoreLogToDatabase(User user) {
        if(temp_scoreLogList.size() != 0) { //if temporary list is not empty
            String[] valuesSet = new String[temp_scoreLogList.size()]; //create array to keep a set of values of each score log

            for(int i = 0; i < temp_scoreLogList.size(); i++) {
                HashMap<String, Object> item = temp_scoreLogList.get(i);
                valuesSet[i] = "" + item.get("id") + ", " + item.get("currentScore") + ", " + item.get("modifiedScore") + ", '" + item.get("description") + "'"; //concat string
            }

            String colNameSet = "user_id, currentScore, modifiedScore, description"; //set of column name of values

            if(!modelCollection.manualInsertDataMultiple("user_score", colNameSet, valuesSet)) { //if inserting data to the database is error
                System.out.println("An error has occurred while adding a score log.");
                return StatusDescription.createProcessStatus(false, "An error has occurred while adding a score log.");
            }

            temp_scoreLogList.clear(); //clear temp score log list
        }

        return null;
    }

    @Override
    public HashMap<String, Object> increaseScore(User user, int score, String description) {
        //score log will not be inserted to the database until method save is called
        HashMap<String, Object> temp_scoreLog = new HashMap<>();
        int changeScore = Math.abs(score); //prevent from improper integer

        this.score += changeScore;
        temp_scoreLog.put("id", id);
        temp_scoreLog.put("currentScore", this.score); //a score after save
        temp_scoreLog.put("modifiedScore", changeScore);
        temp_scoreLog.put("description", description);
        temp_scoreLogList.add(temp_scoreLog); //add to temp

        updateModifiedDate();

        return StatusDescription.createProcessStatus(true);
    }

    @Override
    public HashMap<String, Object> decreaseScore(User user, int score, String description) {
        HashMap<String, Object> temp_scoreLog = new HashMap<>();
        int changeScore = Math.abs(score);

        if(this.score - changeScore < 0)
            return StatusDescription.createProcessStatus(false, "The score cannot be under zero.");

        this.score -= changeScore;
        temp_scoreLog.put("id", id);
        temp_scoreLog.put("currentScore", this.score);
        temp_scoreLog.put("modifiedScore", -changeScore);
        temp_scoreLog.put("description", description);
        temp_scoreLogList.add(temp_scoreLog);

        updateModifiedDate();

        return StatusDescription.createProcessStatus(true);
    }
}
