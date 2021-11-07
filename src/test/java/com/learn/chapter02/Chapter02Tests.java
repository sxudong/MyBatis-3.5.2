package com.learn.chapter02;


import com.learn.chapter02.mapper.RoleMapper;
import com.learn.chapter02.pojo.Role;
import com.learn.chapter02.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

/**
 * 测试
 */
public class Chapter02Tests {
  @Test
  public void test() {

    SqlSession sqlSession = null; // 定义SqlSession
    try {
      sqlSession = SqlSessionFactoryUtil.openSession(); // 打开 SqlSession 会话
      RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class); // 获取映射器Mapper
      Role role = new Role();
      role.setId(1L);
      role.setRoleName("testName");
      role.setNote("testNote");

//      roleMapper.insertRole(role); // 插入
      roleMapper.deleteRole(1L); // 删除
      sqlSession.commit(); // 提交事务

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      sqlSession.rollback(); // 回滚
    } finally {
      if (sqlSession != null) {
        sqlSession.close(); // 关闭资源
      }
    }
  }
}
