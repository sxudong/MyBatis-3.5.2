package com.learn.chapter04;

import com.learn.chapter04.sqlElement.mapper.RoleMapper;
import com.learn.chapter04.sqlElement.pojo.Role;
import com.learn.chapter04.sqlElement.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 4.6 sql元素 复用，实现一处定义多处引用
 * <sql id=""></sql> <include refid="">
 */
public class sqlElementTests {

    private static Logger logger = LogManager.getLogger(sqlElementTests.class.getName());

    @Test
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleDao = sqlSession.getMapper(RoleMapper.class);

            Role param = new Role();
            param.setId(2);
            param.setRoleName("me111123123459");
            param.setNote("te22upouidateaer");

            System.out.println(roleDao.getRole(5).toString());
            System.out.println("-------------------------------------------------------------->");
            System.out.println(roleDao.getRoleCustom(5).toString());
            System.out.println("-------------------------------------------------------------->");

            Map<String, String> maps = new HashMap<>(2);
            maps.put("roleName", "me");
            maps.put("note", "te");

            roleDao.findRoles(maps).forEach(x -> System.out.println(x.toString()));
            /*
            List<Role> getRoleCustom(Integer id);
            List<Role> findRoles(Map<String,String> params);
            List<Role> getRole(Integer id);
             */
            System.out.println("-------------------------------------------------------------->");
        } catch (Exception e) {
            e.printStackTrace();
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
14:05:21.169 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 142 openConnection - Opening JDBC Connection
14:05:21.424 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 412 popConnection - Created connection 2087885397.
14:05:21.425 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 107 setDesiredAutoCommit - Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@7c729a55]
14:05:21.428 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id, role_name, note from t_role where id=?
14:05:21.518 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 5(Integer)
14:05:21.557 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, role_name, note
14:05:21.558 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 5, testName34, 4
14:05:21.559 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [42ms ]
Role{id=5, roleName='testName34', note='4'}
-------------------------------------------------------------->
14:05:21.561 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select r.id, r.role_name, r.note from t_role r where id=?
14:05:21.562 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 5(Integer)
14:05:21.565 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, role_name, note
14:05:21.566 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 5, testName34, 4
14:05:21.566 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [5ms ]
Role{id=5, roleName='testName34', note='4'}
-------------------------------------------------------------->
14:05:21.567 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id,role_name,note from t_role where role_name like concat('%',?,'%') and note like concat('%',?,'%')
14:05:21.568 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: me(String), te(String)
14:05:21.570 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, role_name, note
14:05:21.571 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 2, metst, testNode1
14:05:21.573 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 3, melkjfdsalkj, testNode2
14:05:21.574 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 12, me11, te22
14:05:21.574 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 14, me11, te22
14:05:21.575 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 16, me12, te22
14:05:21.576 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 5
执行耗时 : [9ms ]
Role{id=2, roleName='metst', note='testNode1'}
Role{id=3, roleName='melkjfdsalkj', note='testNode2'}
Role{id=12, roleName='me11', note='te22'}
Role{id=14, roleName='me11', note='te22'}
Role{id=16, roleName='me12', note='te22'}
-------------------------------------------------------------->
14:05:21.578 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 130 resetAutoCommit - Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@7c729a55]
14:05:21.580 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 96 close - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@7c729a55]
14:05:21.582 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 361 pushConnection - Returned connection 2087885397 to pool.
14:05:21.582 INFO  com.learn.chapter04.sqlElementTests 58 main - 执行成功!
*///~