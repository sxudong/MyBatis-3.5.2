package com.learn.chapter08.dao.mapper;

import com.learn.chapter08.dao.pojo.UserPO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    /**
     * 创建
     * @param userPO POJO
     * @return 响应记录数
     */
    int insert(UserPO userPO);

    /**
     * 更新
     * @param userPO POJO
     * @return 响应记录数
     */
    int update(UserPO userPO);

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
    UserPO findOne(Integer id);

    /**
     * 批量查询
     * @param userName 用户名称
     * @param rowBounds 分页条件
     * @return POJO 集合
     */
    List<UserPO> list(String userName, RowBounds rowBounds);

}
