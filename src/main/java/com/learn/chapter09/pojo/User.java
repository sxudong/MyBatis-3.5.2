package com.learn.chapter09.pojo;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String cnname;
    private Integer sex;
    private String mobile;
    private String email;
    private String note;

    public User() {
    }

  public User(String cnname, String mobile, Integer sex) {
    this.cnname = cnname;
    this.mobile = mobile;
    this.sex = sex;
  }

  public static long getSerialVersionUID() {
      return serialVersionUID;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getCnname() {
      return cnname;
    }

    public void setCnname(String cnname) {
      this.cnname = cnname;
    }

    public Integer getSex() {
      return sex;
    }

    public void setSex(Integer sex) {
      this.sex = sex;
    }

    public String getMobile() {
      return mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getNote() {
      return note;
    }

    public void setNote(String note) {
      this.note = note;
    }

  @Override
    public String toString() {
      return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", cnname='" + cnname + '\'' +
        ", sex=" + sex +
        ", mobile='" + mobile + '\'' +
        ", email='" + email + '\'' +
        ", note='" + note + '\'' +
        '}';
    }
}
