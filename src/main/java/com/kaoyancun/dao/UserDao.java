package com.kaoyancun.dao;

import com.kaoyancun.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    //查找All
    @Select("select * from kyc_user")
    List<Users> getUserAll();

    //根据ID查找
    @Select("select * from kyc_user where userId = #{userId}")
    List<Users> getUserAllById(int userId);


    //判断用户是否存在
    @Select("select count(userTel) from kyc_user where userTel = #{userTel}")
    Integer boolUser(String userTel);


    //插入返回ID
    @Insert("INSERT INTO kyc_user(userName,userTel,userQq) VALUES (#{userName},#{userTel},#{userQq})")
    @Options(useGeneratedKeys=true, keyProperty="userId")
    void insertUser(Users users);


}
