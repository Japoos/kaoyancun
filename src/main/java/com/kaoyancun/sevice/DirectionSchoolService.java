package com.kaoyancun.sevice;

import com.kaoyancun.dao.DirectionSchoolDao;
import com.kaoyancun.entity.DirectionSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectionSchoolService {


    @Autowired
    DirectionSchoolDao directionSchoolDao;

    public List<DirectionSchool> getDispensingSchool(String schoolName)
    {
        return directionSchoolDao.getDispensingSchool(schoolName);
    }


    public List<String> showProvince()
    {
        return directionSchoolDao.showSevince();
    }


    //根据省份调取学校
    public List<DirectionSchool>  getDispensingSchoolByProvice(String province)
    {
        return directionSchoolDao.getDispensingSchoolByProvice(province);
    }

}
