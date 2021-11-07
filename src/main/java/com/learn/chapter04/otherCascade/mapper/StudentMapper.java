package com.learn.chapter04.otherCascade.mapper;

import com.learn.chapter04.otherCascade.pojo.Student;

import java.util.List;


public interface StudentMapper {
    Student getStudent(Integer id);

    List<Student> findAllStudent();
}
