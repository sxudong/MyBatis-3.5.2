package com.learn.chapter04;

import com.learn.chapter04.annotation.dao.RoleDao;
import com.learn.chapter04.annotation.pojo.Role;
import com.learn.chapter04.annotation.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * 4.2.4.2 使注解的方式传递参数
 */
public class annotationTests {

    private static Logger logger = LogManager.getLogger(annotationTests.class);

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();

            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            List<Role> roleByAnnotation = roleDao.findRoleByAnnotation("me", "te");
            roleByAnnotation.forEach(x -> System.out.println(x.toString()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
/* Output:
Role(id=2, roleName=metst, note=testNode1)
Role(id=3, roleName=melkjfdsalkj, note=testNode2)
执行成功!
*///~