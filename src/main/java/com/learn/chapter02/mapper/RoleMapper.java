package com.learn.chapter02.mapper;

import com.learn.chapter02.pojo.Role;
import org.apache.ibatis.annotations.Select;

/**
 * 映射器
 * 映射器是由 Java接口 和 XML文件（或注解）共同组成的。它的作用如下：
 * - 定义参数类型。
 * - 描述缓存。
 * - 描述SQL语句
 * - 定义“查询结果”和“POJO”的映射关系
 *
 * 一个映射器的实现方式有两种：
 * （1）通过XML文件方式
 * （2）通过代码方式实现，在Configuration里面注册 Mapper接口。
 *
 * 使用 XML文件配置方式 实现 Mapper。它由一个Java接口和一个XML文件构成。
 * 第一步：给出Java接口。
 * 第二步：给出一个映射 XML 文件。
 */
public interface RoleMapper {

	int insertRole(Role role);

	Role getRole(Long id);

	int deleteRole(Long id);

	/**
	 * Java注解方式 实现Mapper
	 * p24
	 */
	@Select(value = "select id, role_name as roleName, note from t_role where id = #{id}")
	Role getRole2(Long id);
}

