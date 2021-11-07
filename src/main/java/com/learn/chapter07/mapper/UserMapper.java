package com.learn.chapter07.mapper;

import com.learn.chapter07.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 5.5 foreach
     */
    List<User> findUserBySex(@Param("sexList") List<Integer> list);
}
