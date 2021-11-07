package com.learn.chapter07.mapper;

import com.learn.chapter07.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int insertRole(@Param("role") Role role);
    List<Role> findAllRole();
}