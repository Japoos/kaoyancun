package com.kaoyancun.dao;


import com.kaoyancun.entity.DispenSingUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DispenSingUserDao {



    @Insert("INSERT INTO kyc_DispenSingUser(userId,dispenSingSchool1,dispenSingSchool2,dispenSingSchool3) VALUES(#{userId},#{dispenSingSchool1},#{dispenSingSchool2},#{dispenSingSchool3})")
    @Options(useGeneratedKeys=true, keyProperty="dispenSingUserID")
    void insertDispenSingUser(DispenSingUser dispenSingUser);
}
