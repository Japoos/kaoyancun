package com.kaoyancun.dao;

import com.kaoyancun.entity.DirectionSchool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DirectionSchoolDao {

    //根据院校名称查询所有信息
    @Select("Select * from kyc_DispensingSchool where schoolName LIKE  CONCAT('%',#{schoolName},'%')")
    List<DirectionSchool> getDispensingSchool(@Param("schoolName") String schoolName);


    //输出所有调剂省份
    @Select("select distinct province from kyc_DispensingSchool")
    List<String> showSevince();

    //根据省份查询院校
    @Select("select * from kyc_DispensingSchool where province =#{province}")
    List<DirectionSchool> getDispensingSchoolByProvice(@Param("province")String province);

}
