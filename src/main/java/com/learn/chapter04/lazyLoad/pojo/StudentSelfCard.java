package com.learn.chapter04.lazyLoad.pojo;

import java.util.Date;

public class StudentSelfCard {
    private Integer id;
    private Integer studentId;
    private String nativeStr;
    private Date issueDate;
    private Date endDate;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getNativeStr() {
        return nativeStr;
    }

    public void setNativeStr(String nativeStr) {
        this.nativeStr = nativeStr;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentSelfCard{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", nativeStr='" + nativeStr + '\'' +
                ", issueDate=" + issueDate +
                ", endDate=" + endDate +
                ", note='" + note + '\'' +
                '}';
    }
}
