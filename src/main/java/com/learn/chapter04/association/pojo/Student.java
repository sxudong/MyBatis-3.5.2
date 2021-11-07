package com.learn.chapter04.association.pojo;


/**
 * 一对一级联
 * 学生 和 学生证 是一对一的关系
 */
public class Student {
    private Integer id;
    private String cnName;
    private SexEnum sex;
    private String selfCardNo;
    private String note;
    private StudentSelfCard studentSelfcard; // 学生证

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getSelfCardNo() {
        return selfCardNo;
    }

    public void setSelfCardNo(String selfCardNo) {
        this.selfCardNo = selfCardNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StudentSelfCard getStudentSelfcard() {
        return studentSelfcard;
    }

    public void setStudentSelfcard(StudentSelfCard studentSelfcard) {
        this.studentSelfcard = studentSelfcard;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", cnName='" + cnName + '\'' +
                ", sex=" + sex +
                ", selfCardNo='" + selfCardNo + '\'' +
                ", note='" + note + '\'' +
                ", studentSelfcard=" + studentSelfcard +
                '}';
    }
}
