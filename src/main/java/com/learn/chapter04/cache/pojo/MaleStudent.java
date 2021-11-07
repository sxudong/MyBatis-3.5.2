package com.learn.chapter04.cache.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 男学生
 */
public class MaleStudent extends Student implements Serializable {
    private List<StudentHealthMale> studentHealthMaleList = null;

    public List<StudentHealthMale> getStudentHealthMaleList() {
        return studentHealthMaleList;
    }

    public void setStudentHealthMaleList(List<StudentHealthMale> studentHealthMaleList) {
        this.studentHealthMaleList = studentHealthMaleList;
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
                ", studentHealthMaleList=" + studentHealthMaleList +
                '}';
    }
}
