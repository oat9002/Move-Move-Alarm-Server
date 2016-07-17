package movealarm.kmitl.net.common.service.impl;

import movealarm.kmitl.net.common.service.ICommonService;
import movealarm.kmitl.net.user.entity.User;
import movealarm.kmitl.net.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by oat90 on 7/17/2016.
 */
@Service
public class CommonService implements ICommonService{
    @Autowired
    IUserService userService;

    @Override
    public HashMap<String, Object> getGeneralValues(Object object) {
        if(object instanceof User) {
            return userService.getGeneralValues((User)object);
        }
        else {
            return null;
        }
    }
}
