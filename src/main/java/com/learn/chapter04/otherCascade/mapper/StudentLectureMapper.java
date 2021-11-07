package com.learn.chapter04.otherCascade.mapper;

import com.learn.chapter04.otherCascade.pojo.StudentLecture;

import java.util.List;


public interface StudentLectureMapper {

    List<StudentLecture> findStudentLectureByStudentId(Integer id);

}
