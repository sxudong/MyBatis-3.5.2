package com.learn.chapter04.association.mapper;

import com.learn.chapter04.association.pojo.StudentSelfCard;

public interface StudentSelfcardMapper {
    StudentSelfCard findStudentSelfcardByStudentId(Integer studentId);
}
