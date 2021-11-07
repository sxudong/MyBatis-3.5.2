package com.learn.chapter03.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sang on 17-1-13.
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    public static SqlSessionFactory initSqlSessionFactory() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("chapter03/mybatis-conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if(sqlSessionFactory==null)
            initSqlSessionFactory();
        return sqlSessionFactory.openSession();
    }
}
