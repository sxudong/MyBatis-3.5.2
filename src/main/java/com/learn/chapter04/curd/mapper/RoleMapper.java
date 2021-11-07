package com.learn.chapter04.curd.mapper;

import com.learn.chapter04.curd.pojo.Role;
import com.learn.chapter04.curd.pojo.RoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    // 4.2.4.1 使用 map 传递参数
    List<Role> findRoleByMap(Map<String, String> params);

    // 4.2.4.2 使用 注解 传递参数
    List<Role> findRoleByAnnotation(@Param("roleName") String rolename, @Param("note") String note);

    // 4.2.4.3 使用 JavaBean 传递参数
    List<Role> findRoleByParams(RoleParam params);

    Long insertRole(Role role);

    Role getRole(Integer id);

    Map<Object, Object> findRoleByNote(Integer id);

    int updateRole(Role role);

    int deleteRole(Integer id);

    List<Role> selectAllData();
}
