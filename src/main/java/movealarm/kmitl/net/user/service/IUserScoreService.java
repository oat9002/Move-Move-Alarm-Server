package movealarm.kmitl.net.user.service;

import movealarm.kmitl.net.user.entity.User;
import movealarm.kmitl.net.user.entity.UserScore;

import java.util.HashMap;

/**
 * Created by oat on 7/16/16.
 */
public interface IUserScoreService {
    HashMap<String, Object> save(UserScore user);

    HashMap<String, Object> delete(UserScore user);

    HashMap<String, Object> addScoreLogToDatabase(User user);

    HashMap<String, Object> increaseScore(User user, int score, String description); //this method will create score log and put into temporary log list

    HashMap<String, Object> decreaseScore(User user, int score, String description); //like increaseScore method but decrease
}
