package com.learn.chapter04.otherCascade.pojo;


import java.util.Date;

public class StudentSelfCard {
    private Integer id;
    private Integer studentId;
    private String nativeStr;
    private Date issueDate;
    private Date endDate;
    private String note;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setNativeStr(String nativeStr) {
        this.nativeStr = nativeStr;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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