package com.kaoyancun.sevice;


import com.kaoyancun.dao.UserDao;
import com.kaoyancun.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserService {

    @Resource
    private UserDao userDaol;

    public long insertUser(Users users) {


        Boolean uBool = boolUser(users.getUserTel());

        if(!uBool){
            userDaol.insertUser(users);
            return  users.getUserId();
        }
        else
        {
            return 0;
        }
    }

    public Boolean boolUser(String userTel) {
        int uBool = userDaol.boolUser(userTel);
        if (uBool > 0) {
            return true;
        } else {
            return false;
        }

    }


}
