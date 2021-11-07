package com.learn.chapter03.mapper;

import com.learn.chapter03.bean.UserSex;

import java.util.List;

public interface UserSexMapper {
    List<UserSex> getUserAll();
    UserSex getUser(Long id);
    int insertUser(UserSex user);
}
