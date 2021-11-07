package com.learn.chapter04;

import com.learn.chapter04.cache.mapper.StudentMapper;
import com.learn.chapter04.cache.pojo.Student;
import com.learn.chapter04.cache.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 4.8 缓存 cache
 * P113
 */
public class cacheTests {
    private static Logger logger = LogManager.getLogger(cacheTests.class);

    @Test
    public void test() {
        SqlSession sqlSession = null;
        SqlSession sqlSession2 = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            Student student = studentMapper.getStudent(1);
            logger.info("使用同一个sqlSession再执行一次");
            Student student1 = studentMapper.getStudent(1);
            // 请注意,当我们使用二级缓存的时候,sqlSession调用了commit方法后才会生效.
            sqlSession.commit();
            logger.info("现在创建一个新的sqlSession再执行一次");
            sqlSession2 = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentDao1 = sqlSession2.getMapper(StudentMapper.class);
            Student student2 = studentDao1.getStudent(1);
            // 请注意,当我们使用二级缓存的时候,sqlSession调用了commit方法后才会生效
            sqlSession2.commit();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (sqlSession2 != null) {
                sqlSession2.close();
            }
        }
        logger.info("执行成功!");
        /*
         * 显然从头到尾只执行了一次SQL，都是二级缓存中得到我们需要的数据，
         * 这就说明二级缓存是在 SqlSessionFactory 层面所共享的。
         */
    }
}
