package com.learn.chapter04.annotation.dao;

import com.learn.chapter04.annotation.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    List<Role> findRoleByAnnotation(@Param("roleName") String rolename, @Param("note") String note);
}