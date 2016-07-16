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

    HashMap<String, Object> update(User user);

    HashMap<String, Object> save(User user);

    HashMap<String, Object> delete(User user);

    void addTemp_scoreLogList(HashMap<String, Object> temp_score, User user);
}
