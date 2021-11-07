package com.learn.chapter04.cache.mapper;


import com.learn.chapter04.cache.pojo.Student;

import java.util.List;

public interface StudentMapper {
    Student getStudent(Integer id);

    List<Student> findAllStudent();
}
