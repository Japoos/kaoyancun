package com.kaoyancun.sevice;

import com.kaoyancun.dao.DispenSingUserDao;
import com.kaoyancun.entity.DispenSingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispenSingUserService {



    @Autowired
    DispenSingUserDao dispenSingUserDao;

    public Boolean insertDispenSingUser(DispenSingUser dispenSingUser)
    {
        dispenSingUserDao.insertDispenSingUser(dispenSingUser);
        if (dispenSingUser.getDispenSingUserID()>0)
        {return true;}else {return false;}
    }


}
