package com.kaoyancun.entity;

public class Faculty {

    private Integer facultyID;
    private Integer schoolCode;
    private Integer facultyCode;
    private String facultyName;

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public Integer getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Integer getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(Integer facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
