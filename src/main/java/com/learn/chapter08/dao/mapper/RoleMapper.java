package com.learn.chapter08.dao.mapper;

import com.learn.chapter08.dao.pojo.Role;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleMapper {
    /**
     * 创建
     * @param role POJO
     * @return 响应记录数
     */
    int insert(Role role);

    /**
     * 更新
     * @param role POJO
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
     * @return POJO
     */
    Role findOne(Integer id);

    /**
     * 批量查询
     * @param roleName 角色名称
     * @param rowBounds 分页条件
     * @return POJO 集合
     */
    List<Role> list(String roleName, RowBounds rowBounds);

}
