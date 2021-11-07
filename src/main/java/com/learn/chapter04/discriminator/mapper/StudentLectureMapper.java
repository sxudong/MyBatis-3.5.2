package com.learn.chapter04.discriminator.mapper;

import com.learn.chapter04.discriminator.pojo.StudentLecture;

import java.util.List;

public interface StudentLectureMapper {

    List<StudentLecture> findStudentLectureByStudentId(Integer id);

}
