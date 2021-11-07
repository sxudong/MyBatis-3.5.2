package com.learn.chapter04.lazyLoad.mapper;

import com.learn.chapter04.lazyLoad.pojo.StudentSelfCard;

public interface StudentSelfcardMapper {

    StudentSelfCard findStudentSelfcardByStudentId(Integer studentId);
}
