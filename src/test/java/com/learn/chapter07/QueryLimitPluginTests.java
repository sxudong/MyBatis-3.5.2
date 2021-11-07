package com.learn.chapter07;

import com.learn.chapter07.mapper.RoleMapper;
import com.learn.chapter07.pojo.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QueryLimitPluginTests {
    private static final Logger log = LogManager.getLogger(QueryLimitPluginTests.class);

    @Test
    public void test() throws IOException {
        // 读取配置信息
        InputStream inputStream = Resources.getResourceAsStream("chapter07/mybatis-queryLimitPlugin.xml");
        // 根据配置信息，创建SqlSession工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // SqlSession工厂创建SqlSession
        SqlSession sqlSession = factory.openSession();

        // 获取接口的代理对象
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

//        Role role = new Role();
//        role.setRoleName("johnny");
//        role.setNote("wong");
//        roleDao.insertRole(role);

        List<Role> allRole = roleMapper.findAllRole();
        allRole.iterator().forEachRemaining(System.out::println);
        //sqlSession.commit();

        log.info("执行成功!");
    }
}
/* Output:
调用生成代理对象...
调用生成代理对象...
调用生成代理对象...
调用生成代理对象...
23:15:25.230 [main] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:159) ==>  Preparing: select * from (select * from t_role) Table_Name_xxx limit 3
23:15:25.260 [main] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:159) ==> Parameters:
23:15:25.273 [main] TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger.trace(BaseJdbcLogger.java:165) <==    Columns: id, role_name, note
23:15:25.273 [main] TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger.trace(BaseJdbcLogger.java:165) <==        Row: 2, 21111, 533211
23:15:25.275 [main] TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger.trace(BaseJdbcLogger.java:165) <==        Row: 3, melkjfdsalkj, testNode2
23:15:25.275 [main] TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger.trace(BaseJdbcLogger.java:165) <==        Row: 4, 更新之后, 更新备注
23:15:25.276 [main] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug(BaseJdbcLogger.java:159) <==      Total: 3
Role(id=2, roleName=null, note=533211)
Role(id=3, roleName=null, note=testNode2)
Role(id=4, roleName=null, note=更新备注)
*///:~
