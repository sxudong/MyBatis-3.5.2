package com.learn.chapter04.lazyLoad.mapper;

import com.learn.chapter04.lazyLoad.pojo.StudentLecture;

import java.util.List;

public interface StudentLectureMapper {

    List<StudentLecture> findStudentLectureByStudentId(Integer id);

}
