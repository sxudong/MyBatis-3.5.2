package com.learn.chapter08.service;

import com.learn.chapter08.dao.pojo.Role;

import java.util.List;

/**
 * 标题：角色服务<br>
 * 描述：角色服务<br>
 * 时间：2018/05/24<br>
 *
 * @author zc
 **/
public interface RoleService {

    /**
     * 创建
     * @param role PO
     * @return 响应记录数
     */
    int insert(Role role);

    /**
     * 更新
     * @param role PO
     * @return 响应记录数
     */
    int update(Role role);

    /**
     * 删除
     * @param id 主键
     * @return 响应记录数
     */
    int delete(Integer id);

    /**
     * 单个查询
     * @param id 主键
     * @return PO
     */
    Role findOne(Integer id);

    /**
     * 批量查询
     * @param roleName 角色名称
     * @param start 开始条数
     * @param limit 每页条数
     * @return PO 集合
     */
    List<Role> list(String roleName, int start, int limit);

}
