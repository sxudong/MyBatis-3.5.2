package com.learn.chapter04.lazyLoad.mapper;

import com.learn.chapter04.lazyLoad.pojo.StudentHealthFemale;

public interface StudentHealthFemaleMapper {

    StudentHealthFemale findStudentHealthFemaleByStudentId(Integer id);
}
