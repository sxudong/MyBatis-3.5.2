package com.learn.chapter04.collection.mapper;

import com.learn.chapter04.collection.pojo.StudentLecture;

import java.util.List;

public interface StudentLectureMapper {
    List<StudentLecture> findStudentLectureByStudentId(Integer id);
}