package com.kaoyancun.dao;


import com.kaoyancun.entity.Major;
import com.kaoyancun.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolDao {

    @Select("select * from kyc_School,kyc_schoolInfo where kyc_schoolInfo.schoolCode = kyc_School.schoolCode and provinceName =#{province}")
    List<School> getSchool(String province);

    @Select("select * from kyc_Faculty,kyc_Major where kyc_Faculty.facultyID = kyc_Major.facultyId and schoolCode = #{schoolCode}")
    List<Major> getMajor(String schoolCode);

    //根据专业名称调取院校专业

    @Select("select * from kyc_Major,kyc_Faculty where kyc_Major.facultyId = kyc_Faculty.facultyId and schoolCode=#{schoolCode} and majorName LIKE  CONCAT('%',#{majorName},'%')")
    List<Major> getMajorByMajorName(@Param("majorName") String majorName,@Param("schoolCode") Integer schoolCode);

    @Select("select majorId from kyc_Faculty,kyc_Major where  kyc_Faculty.facultyID=kyc_Major.facultyId and schoolCode =#{schoolCode} and facultyCode=#{facultyCode} and majorCode=#{majorCode}")
    Integer getMajorIdBySCFCMC(@Param("schoolCode")Integer schoolCode,@Param("facultyCode")Integer facultyCode,@Param("majorCode")Integer majorCode);

}
