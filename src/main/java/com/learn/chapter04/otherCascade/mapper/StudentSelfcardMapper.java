package com.learn.chapter04.otherCascade.mapper;

import com.learn.chapter04.otherCascade.pojo.StudentSelfCard;

public interface StudentSelfcardMapper {

    StudentSelfCard findStudentSelfcardByStudentId(Integer studentId);
}
