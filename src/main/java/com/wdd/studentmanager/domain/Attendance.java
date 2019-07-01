package com.wdd.studentmanager.domain;

/**
 * @Classname Attendance
 * @Description 考勤实体类
 * @Date 2019/7/1 11:52
 * @Created by WDD
 */
public class Attendance {
    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private String type;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
