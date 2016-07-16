package movealarm.kmitl.net.user.service;

import movealarm.kmitl.net.user.entity.User;

import java.util.HashMap;

/**
 * Created by oat on 7/16/16.
 */

public interface IUserService {

    HashMap<String, Object> getValues(User user); //get all values from mode

    HashMap<String, Object> getGeneralValues(User user); //get only common values

    User find(Integer id);

    User[] where(String colName, String operator, String value);

    User[] where(String colName, String operator, String value, String extraCondition);

    User[] all();

    HashMap<String, Object>  update(User user);

    HashMap<String, Object> save(User user);

    HashMap<String, Object>  delete(User user);

    HashMap<String, Object> addScoreLogToDatabase(User user);

    HashMap<String, Object> increaseScore(User user, int score, String description); //this method will create score log and put into temporary log list

    HashMap<String, Object> decreaseScore(User user, int score, String description); //like increaseScore method but decrease
}
