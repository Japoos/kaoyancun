package com.kaoyancun.sevice;


import com.kaoyancun.dao.AchieveDao;
import com.kaoyancun.entity.Achieve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchieveService {

    @Autowired
    AchieveDao achieveDao;
    public Boolean insertAchieve(Achieve achieve){
        achieveDao.insertAchieve(achieve);
        if(achieve.getAchieveId()>0){
            return true;
        }else {return false;}
    }

}
