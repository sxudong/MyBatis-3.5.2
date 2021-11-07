package com.learn.chapter04.lazyLoad.mapper;

import com.learn.chapter04.lazyLoad.pojo.StudentHealthMale;

public interface StudentHealthMaleMapper {

    StudentHealthMale findStudentHealthMaleByStudentId(Integer id);
}
