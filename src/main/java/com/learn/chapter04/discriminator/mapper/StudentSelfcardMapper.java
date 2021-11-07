package com.learn.chapter04.discriminator.mapper;

import com.learn.chapter04.discriminator.pojo.StudentSelfCard;

public interface StudentSelfcardMapper {

    StudentSelfCard findStudentSelfcardByStudentId(Integer studentId);
}
