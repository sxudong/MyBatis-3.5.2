package com.learn.chapter04.collection.mapper;

import com.learn.chapter04.collection.pojo.StudentSelfCard;

public interface StudentSelfcardMapper {

    StudentSelfCard findStudentSelfcardByStudentId(Integer studentId);
}
