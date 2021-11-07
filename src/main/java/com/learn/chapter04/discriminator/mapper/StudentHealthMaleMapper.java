package com.learn.chapter04.discriminator.mapper;

import com.learn.chapter04.discriminator.pojo.StudentHealthMale;

public interface StudentHealthMaleMapper {

    StudentHealthMale findStudentHealthMaleByStudentId(Integer id);
}
