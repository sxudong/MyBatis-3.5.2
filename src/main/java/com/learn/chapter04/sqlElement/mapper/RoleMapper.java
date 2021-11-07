package com.learn.chapter04.sqlElement.mapper;

import com.learn.chapter04.sqlElement.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * 4.6 sql元素 p88
 *
 * 用 sql 元素定义了 role_columns,它可以很方便地使用
 * include 元素的 refid 属性进行引用，从而达到重用的功能。
 */
public interface RoleMapper {
    Role getRoleCustom(Integer id);

    List<Role> findRoles(Map<String, String> params);

    Role getRole(Integer id);
}
