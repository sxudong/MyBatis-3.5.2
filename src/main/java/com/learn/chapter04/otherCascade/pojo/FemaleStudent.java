package com.learn.chapter04.otherCascade.pojo;


import java.util.List;

public class FemaleStudent extends Student {
    private List<StudentHealthFemale> studentHealthFemaleList = null;

    public List<StudentHealthFemale> getStudentHealthFemaleList() {
        return studentHealthFemaleList;
    }

    public void setStudentHealthFemaleList(List<StudentHealthFemale> studentHealthFemaleList) {
        this.studentHealthFemaleList = studentHealthFemaleList;
    }

    @Override
    public String toString() {
        return "Student{" +
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
