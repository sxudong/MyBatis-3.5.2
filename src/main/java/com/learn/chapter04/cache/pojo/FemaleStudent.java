package com.learn.chapter04.cache.pojo;


import java.io.Serializable;
import java.util.List;

public class FemaleStudent extends Student implements Serializable {
    private List<StudentHealthFemale> studentHealthFemaleList = null;

    public List<StudentHealthFemale> getStudentHealthFemaleList() {
        return studentHealthFemaleList;
    }

    public void setStudentHealthFemaleList(List<StudentHealthFemale> studentHealthFemaleList) {
        this.studentHealthFemaleList = studentHealthFemaleList;
    }

    @Override
    public String toString() {
        return "MaleStudent{" +
                "id=" + super.getId() +
                ", cnName='" + super.getCnName() + '\'' +
                ", sex=" + super.getSex() +
                ", selfCardNo='" + super.getSelfCardNo() + '\'' +
                ", note='" + super.getNote() + '\'' +
                ", studentSelfcard=" + super.getStudentSelfcard() +
                ", studentLectureList=" + super.getStudentLectureList() +
                ", studentHealthMaleList=" + studentHealthFemaleList +
                '}';
    }
}
