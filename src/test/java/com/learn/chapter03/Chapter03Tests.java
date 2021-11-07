package com.learn.chapter03;


import com.learn.chapter03.bean.UserSex;
import com.learn.chapter03.enums.Sex;
import com.learn.chapter03.mapper.UserSexMapper;
import com.learn.chapter03.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 测试 learn.chapter3.typeHandler.SexEnumTypeHandler
 */
public class Chapter03Tests {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserSexMapper userSexMapper = sqlSession.getMapper(UserSexMapper.class);

            UserSex user = new UserSex();
            user.setUsername("任他游");
            user.setSex(Sex.FEMALE); // Sex.FEMALE.ordinal() 枚举转换为int的方式
            userSexMapper.insertUser(user);

            List<UserSex> us = userSexMapper.getUserAll();
            for (UserSex userSex : us) {
                System.out.println(userSex);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
/* Output:
DEBUG 2020-08-01 09:26:48,732 org.apache.ibatis.datasource.pooled.PooledDataSource Created connection 1795799895.
DEBUG 2020-08-01 09:26:48,732 org.apache.ibatis.transaction.jdbc.JdbcTransaction Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6b09bb57]
DEBUG 2020-08-01 09:26:48,735 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==>  Preparing: INSERT INTO usersex(username, sex) VALUES (?, ?)
DEBUG 2020-08-01 09:26:48,777 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==> Parameters: 任他游(String), FEMALE(String)
DEBUG 2020-08-01 09:26:48,782 org.apache.ibatis.logging.jdbc.BaseJdbcLogger <==    Updates: 1
DEBUG 2020-08-01 09:26:48,783 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==>  Preparing: SELECT * FROM usersex
DEBUG 2020-08-01 09:26:48,784 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==> Parameters:
DEBUG 2020-08-01 09:26:48,800 org.apache.ibatis.logging.jdbc.BaseJdbcLogger <==      Total: 2
UserSex{id=1, username='任我游', sex=MALE}
UserSex{id=2, username='任他游', sex=FEMALE}
*///~