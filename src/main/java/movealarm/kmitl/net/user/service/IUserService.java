package movealarm.kmitl.net.user.service;

import java.util.HashMap;

/**
 * Created by oat on 7/16/16.
 */
public interface IUserService {

    HashMap<String, Object> getValues(); //get all values from mode

    public HashMap<String, Object> getGeneralValues(); //get only common values
}
