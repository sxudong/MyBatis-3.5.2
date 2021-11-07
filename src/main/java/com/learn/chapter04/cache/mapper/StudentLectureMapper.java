package com.learn.chapter04.cache.mapper;

import com.learn.chapter04.cache.pojo.StudentLecture;

import java.util.List;

public interface StudentLectureMapper {
    List<StudentLecture> findStudentLectureByStudentId(Integer id);
}