package com.learn.chapter04;

import com.learn.chapter04.curd.mapper.RoleMapper;
import com.learn.chapter04.curd.mapper.UserMapper;
import com.learn.chapter04.curd.pojo.Role;
import com.learn.chapter04.curd.pojo.RoleParam;
import com.learn.chapter04.curd.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 4.7.2 :
 * 使用Map存储结果集
 * 4.7.3 :
 * 使用POJO存储结果集
 *
 * 增删改查(curd): 创建(Create)、更新(Update)、读取(Retrieve) 和 删除(Delete) 查询基本使用
 */
public class curdTests {

    private static Logger logger = LogManager.getLogger(curdTests.class.getName());

    @Test
    public void useMap传递参数(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            Map<String, String> paramsMap = new HashMap<>(2);
            paramsMap.put("roleName", "me");
            paramsMap.put("note", "te");

            List<Role> roleByMap = roleMapper.findRoleByMap(paramsMap);
            roleByMap.forEach(x -> System.out.println(x.toString()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }

    @Test
    public void 使用注解传递参数(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<Role> roleByAnnotation = roleMapper.findRoleByAnnotation("me", "te");
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

    @Test
    public void 使用JavaBean传递参数(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            RoleParam paramJavaBean = new RoleParam();
            paramJavaBean.setRoleName("me");
            paramJavaBean.setNote("te");

            List<Role> roleByParams = roleMapper.findRoleByParams(paramJavaBean);
            roleByParams.forEach(x -> System.out.println(x.toString()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }

    @Test
    public void insert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            Role param = new Role();
            param.setRoleName("me12");
            param.setNote("te22");

            Long num = roleMapper.insertRole(param);
            System.out.println(num);

            System.out.println(param.getId());
            sqlSession.commit();
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

    @Test
    public void updateAndDelete(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role param = new Role();
            param.setId(2);
            param.setRoleName("12222");
            param.setNote("3333");

            roleMapper.updateRole(param);
            roleMapper.selectAllData().forEach(x -> System.out.println(x.toString()));
            System.out.println("-------------------------------------------------------------->");

            roleMapper.deleteRole(2);
            roleMapper.selectAllData().forEach(x -> System.out.println(x.toString()));
            System.out.println("-------------------------------------------------------------->");
            //sqlSession.commit();
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


    @Test
    public void select1() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            String role = roleMapper.getRole(5).toString();
            System.out.println(role);
            System.out.println("-------------------------------------------------------------->");

            roleMapper.findRoleByNote(5).forEach((y, z) -> System.out.println(y + "--->" + z));
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
    } /* Output:
    Opening JDBC Connection
    Created connection 1754894440.
    Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@68999068]
    ==>  Preparing: select id,role_name,note from t_role where id=?
    ==> Parameters: 5(Integer)
    <==      Total: 1
    Role(id=5, roleName=testName34, note=4)
    -------------------------------------------------------------->
    ==>  Preparing: select id,role_name,note from t_role where id=?
    ==> Parameters: 5(Integer)
    <==      Total: 1
    role_name--->testName34
    note--->4
    id--->5
    -------------------------------------------------------------->
    Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@68999068]
    Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@68999068]
    Returned connection 1754894440 to pool.
    执行成功!
    *///:~

    @Test
    public void select2(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            System.out.println(userMapper.countFirstName("z"));
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
