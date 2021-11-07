package com.learn.chapter03.mapper;

import com.learn.chapter03.bean.Role;

import java.util.List;
import java.util.Map;


public interface RoleMapper {

    public List<Role> findRoleByMap(Map<String, String> params);

    /**
     * 查询角色
     */
    Role getRole(Long id);

    /**
     * 查询角色
     */
    Role findRole(String roleName);

    /**
     * 新增角色
     */
    int insertRole(Role role);

    /**
     * 删除角色表的数据
     */
    int deleteRole(Long id);

}
