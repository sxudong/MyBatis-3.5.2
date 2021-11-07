package com.learn.chapter07;

import com.learn.chapter07.mapper.RoleMapper;
import com.learn.chapter07.pojo.Role;
import com.learn.chapter07.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 第 7 章  Plugin 插件
 */
public class pluginTests {
    private static final Logger log = LogManager.getLogger(pluginTests.class);

    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

        Role role = new Role();
        role.setRoleName("lym");
        role.setNote("wj");
        roleMapper.insertRole(role);

        log.info("执行成功!");
    }
}
/* Output:
自定义插件 mysql
调用生成代理对象
before......
调用生成代理对象
调用生成代理对象
调用生成代理对象
after......
执行成功!
*///:~