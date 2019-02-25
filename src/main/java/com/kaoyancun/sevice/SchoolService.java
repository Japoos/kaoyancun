package com.kaoyancun.sevice;


import com.kaoyancun.dao.SchoolDao;
import com.kaoyancun.entity.Major;
import com.kaoyancun.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    SchoolDao schoolDao;

    public List<School> getSchools(String province){
        return schoolDao.getSchool(province);
    }


    public List<Major> getMajorBySchoolCode(String schoolCode){ return schoolDao.getMajor(schoolCode);}


    ////    根据专业名称调取院校专业
    public List<Major> getMajorByMajorName(String majorName,Integer schoolCode)
    {
        List<Major> majors = schoolDao.getMajorByMajorName(majorName,schoolCode);
        return majors;
    }

    //根据院校代码、院系代码、专业代码调取专业表ID
    public Integer getMajorIdBySCFCMC(Integer schoolCode,Integer facultyCode,Integer majorCode)
    {
       return schoolDao.getMajorIdBySCFCMC(schoolCode,facultyCode,majorCode);
    }
}
