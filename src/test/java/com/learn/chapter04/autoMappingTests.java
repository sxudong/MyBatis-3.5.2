package com.learn.chapter04;

import com.learn.chapter04.autoMapping.dao.RoleDao;
import com.learn.chapter04.autoMapping.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 *
 */
public class autoMappingTests {
    private static Logger logger = LogManager.getLogger(autoMappingTests.class.getName());

    @Test
    public void test() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleDao userDao = sqlSession.getMapper(RoleDao.class);
            System.out.println(userDao.getRole(2L));
            //roleMapper.deleteRole(1L);
            //sqlSession.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            //sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
