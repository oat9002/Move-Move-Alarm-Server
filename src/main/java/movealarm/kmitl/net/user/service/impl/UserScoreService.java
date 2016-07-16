package movealarm.kmitl.net.user.service.impl;

import movealarm.kmitl.net.common.StatusDescription;
import movealarm.kmitl.net.user.entity.User;
import movealarm.kmitl.net.user.entity.UserScore;
import movealarm.kmitl.net.user.repository.UserScoreRepository;
import movealarm.kmitl.net.user.service.IUserScoreService;
import movealarm.kmitl.net.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by oat on 7/16/16.
 */
@Service
public class UserScoreService implements IUserScoreService {

    @Autowired
    private UserScoreRepository userScoreRepository;

    @Autowired
    private IUserService userService;

    @Override
    public HashMap<String, Object> save(UserScore userScore) {
        UserScore temp = userScoreRepository.save(userScore);
        if(temp != null) {
            return StatusDescription.createProcessStatus(true);
        }
        else {
            return StatusDescription.createProcessStatus(false, "Due to database error.");
        }
    }

    @Override
    public HashMap<String, Object> delete(UserScore userScore) {
        try {
            userScoreRepository.delete(userScore);
            return StatusDescription.createProcessStatus(true);
        }
        catch (Exception e) {
            return StatusDescription.createProcessStatus(false, "Due to database error.");
        }
    }

    @Override
    public HashMap<String, Object> addScoreLogToDatabase(User user) {
        if(user.getTemp_scoreLogList().size() != 0) { //if temporary list is not empty
            String[] valuesSet = new String[user.getTemp_scoreLogList().size()]; //create array to keep a set of values of each score log

            for(int i = 0; i < user.getTemp_scoreLogList().size(); i++) {
                HashMap<String, Object> item = user.getTemp_scoreLogList().get(i);
                UserScore userScore = new UserScore();
                userScore.setUserID((Integer)item.get("id"));
                userScore.setCurrentScore((Integer)item.get("currentScore"));
                userScore.setModifiedScore((Integer)item.get("modifiedScore"));
                userScore.setDescription((String)item.get("description"));
                HashMap<String, Object> status = save(userScore);
                if("false".equals(status.get("status"))) {
                    System.out.println("An error has occurred while adding a score log.");
                    return StatusDescription.createProcessStatus(false, "An error has occurred while adding a score log.");
                }
            }
            user.setTemp_scoreLogList(new ArrayList<>());//clear temp score log list
        }

        return null;
    }

    @Override
    public HashMap<String, Object> increaseScore(User user, int score, String description) {
        //score log will not be inserted to the database until method save is called
        HashMap<String, Object> temp_scoreLog = new HashMap<>();
        int changeScore = Math.abs(score); //prevent from improper integer

        user.setScore(user.getScore() + changeScore);
        temp_scoreLog.put("id", id);
        temp_scoreLog.put("currentScore", user.getScore()); //a score after save
        temp_scoreLog.put("modifiedScore", changeScore);
        temp_scoreLog.put("description", description);
        userService.addTemp_scoreLogList(temp_scoreLog, user); //add to temp

        user.updateModifiedDate();

        return StatusDescription.createProcessStatus(true);
    }

    @Override
    public HashMap<String, Object> decreaseScore(User user, int score, String description) {
        HashMap<String, Object> temp_scoreLog = new HashMap<>();
        int changeScore = Math.abs(score);

        if(user.getScore() - changeScore < 0)
            return StatusDescription.createProcessStatus(false, "The score cannot be under zero.");

        user.setScore(user.getScore() - changeScore);
        temp_scoreLog.put("id", id);
        temp_scoreLog.put("currentScore", user.getScore());
        temp_scoreLog.put("modifiedScore", -changeScore);
        temp_scoreLog.put("description", description);
        userService.addTemp_scoreLogList(temp_scoreLog, user);

        user.updateModifiedDate();

        return StatusDescription.createProcessStatus(true);
    }

}
