package com.learn.chapter09.mapper;

import com.learn.chapter09.pagePlugin.MyPage;
import com.learn.chapter09.pagePlugin.PageParams;
import com.learn.chapter09.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int insertUser(User user);

    int batchInsertUser(@Param("users")List<User> users);

    //使用自定义的分页插件
    List<User> getAllUserByPage(MyPage myPage);
    List<User> queryAllUserByPage(Map<String, Object> map);

    //使用《深入浅出MyBatis》中的代码测试
    List<User> queryAllUserByPage(PageParams pageParams);

    int batchDeleteUser(@Param("list")List<Integer> list);
}
