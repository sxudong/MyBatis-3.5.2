package com.learn.chapter04.cache.mapper;

import com.learn.chapter04.cache.pojo.StudentHealthMale;

public interface StudentHealthMaleMapper {
    StudentHealthMale findStudentHealthMaleByStudentId(Integer id);
}