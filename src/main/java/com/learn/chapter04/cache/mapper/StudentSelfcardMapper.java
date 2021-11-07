package com.learn.chapter04.cache.mapper;

import com.learn.chapter04.cache.pojo.StudentSelfCard;

public interface StudentSelfcardMapper {
    StudentSelfCard findStudentSelfcardByStudentId(Integer studentId);
}