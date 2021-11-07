package com.learn.chapter03.mapper;

import com.learn.chapter03.bean.User;

/**
 * <br>
 * 标题：User DAO<br>
 * 描述：t_user 表 DAO 接口<br>
 *
 * @author zc
 * @date 2018/04/19
 **/
public interface UserMapper {

    /**
     * 查询角色
     *
     * @param id
     * @return
     */
    User getUser(Integer id);

    /**
     * 新增角色
     * @param user
     * @return
     */
    int insertUser(User user);

    int insertUser2(User user);

    int insertUser3(User user);
}
