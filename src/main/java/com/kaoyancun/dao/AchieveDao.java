package com.kaoyancun.dao;

import com.kaoyancun.entity.Achieve;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface AchieveDao {

    //添加成绩
    @Insert("INSERT INTO kyc_Achieve(userId,politicalAch,forLangAch,matOrMajorOneAch,majorSecAch,majorId) VALUES(#{userId},#{politicalAch},#{forLangAch},#{matOrMajorOneAch},#{majorSecAch},#{majorId})")
    @Options(useGeneratedKeys=true, keyProperty="achieveId")
    void insertAchieve(Achieve achieve);

}
