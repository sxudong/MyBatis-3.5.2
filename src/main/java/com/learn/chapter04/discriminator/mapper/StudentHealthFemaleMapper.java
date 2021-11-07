package com.learn.chapter04.discriminator.mapper;

import com.learn.chapter04.discriminator.pojo.StudentHealthFemale;

public interface StudentHealthFemaleMapper {

    StudentHealthFemale findStudentHealthFemaleByStudentId(Integer id);
}
