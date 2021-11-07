package com.learn.chapter04.cache.mapper;

import com.learn.chapter04.cache.pojo.StudentHealthFemale;

public interface StudentHealthFemaleMapper {
    StudentHealthFemale findStudentHealthFemaleByStudentId(Integer id);
}