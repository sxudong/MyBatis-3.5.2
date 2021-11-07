package com.learn.chapter05;

import com.learn.chapter05.mapper.RoleMapper;
import com.learn.chapter05.mapper.UserMapper;
import com.learn.chapter05.pojo.Role;
import com.learn.chapter05.pojo.User;
import com.learn.chapter05.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 第 5 章 动态SQL
 */
public class dynamicSqlTests {
    private static final Logger log = LogManager.getLogger(dynamicSqlTests.class);

    SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();

    /**
     * 5.2 if
     */
    @Test
    public void test1(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

        System.out.println("---------5.2----------->");
        roleMapper.findRolesIf("test").forEach(x -> System.out.println(x.toString()));
        System.out.println("---------5.2end----------->");
    }

    /**
     * 5.3 choose,when,otherwise元素
     */
    @Test
    public void test2(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

        System.out.println("---------5.3----------->");
        roleMapper.findChooseWhenOtherWise("4", "test").forEach(x -> System.out.println(x.toString()));
        roleMapper.findChooseWhenOtherWise("", "test").forEach(x -> System.out.println(x.toString()));
        System.out.println("---------5.3end----------->");
    }

    /**
     * 5.4 trim,where,set元素
     */
    @Test
    public void test3(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

        /** where */
        List<Role> roleList = roleMapper.findWhere("test");
        System.out.println(roleList);

        /** trim */
        List<Role> roles = roleMapper.findRoleTrim("更新");
        System.out.println(roles);

        Role role = new Role();
        role.setId(4);
        role.setRoleName("更新之后");
        role.setNote("更新备注");
        /** set */
        int i = roleMapper.updateRole(role);
        System.out.println(i);
        sqlSession.commit();
    }

    /**
     * 5.5 foreach
     */
    @Test
    public void test4(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findUserBySex(Arrays.asList(0, 1, 2));
        userList.forEach(x -> System.out.println(x.toString()));
    }

    /**
     * 5.6 test属性
     */
    @Test
    public void test5(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roleList = roleMapper.getRoleTest("y");
        System.out.println(roleList);
    }

    /**
     * 5.7 使用Bind元素.
     */
    @Test
    public void test6(){
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> roleList = roleMapper.findRoleMulBind("更新", "更新");
        System.out.println(roleList);
    }
}
