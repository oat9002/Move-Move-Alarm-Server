package movealarm.kmitl.net.user.service.impl;

import movealarm.kmitl.net.common.Converter;
import movealarm.kmitl.net.common.StatusDescription;
import movealarm.kmitl.net.user.entity.User;
import movealarm.kmitl.net.user.repository.UserRepository;
import movealarm.kmitl.net.user.repository.UserScoreRepository;
import movealarm.kmitl.net.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by oat on 7/16/16.
 */

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserScoreRepository userScoreRepository;

    @Autowired
    private UserScoreService userScoreService;

    @Autowired
    private Converter converter;

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
            user.setCreatedDate(new Date());
            User temp = userRepository.save(user);
            if(temp == null)
                return StatusDescription.createProcessStatus(false, "Cannot save due to a database error.");
            return StatusDescription.createProcessStatus(true);
        }

        HashMap<String, Object> addScoreLogStatus = userScoreService.addScoreLogToDatabase(user); //add score log to database
        if(addScoreLogStatus != null) //if an error has occurred while adding score logs to the database
            return addScoreLogStatus; //break saving process and return error description

        user.setTemp_scoreLogList(null);
        return StatusDescription.createProcessStatus((Boolean)update(user).get("status"));
    }

    @Override
    public  HashMap<String, Object>  delete(User user) {
        try {
            userRepository.delete(user);
            return StatusDescription.createProcessStatus(true);
        }
        catch (Exception e) {
            return StatusDescription.createProcessStatus(false, "Due to database error.");
        }
    }

    @Override
    public void addTemp_scoreLogList(HashMap<String, Object> temp_score, User user) {
        ArrayList<HashMap<String, Object>> temp = user.getTemp_scoreLogList();
        temp.add(temp_score);
        user.setTemp_scoreLogList(temp);
    }

}
