package com.learn.chapter04;

import com.learn.chapter04.association.mapper.StudentMapper;
import com.learn.chapter04.association.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 4.7.4.1 association 一对一级联
 * P93
 */
public class associationTests {
    private static Logger logger = LogManager.getLogger(associationTests.class);

    @Test
    public void test() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            System.out.println(studentMapper.getStudent(1));
            System.out.println(studentMapper.getStudent(2));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
