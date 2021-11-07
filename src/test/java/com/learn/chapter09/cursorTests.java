package com.learn.chapter09;

import com.learn.chapter09.mapper.ProcedureMapper;
import com.learn.chapter09.pojo.PageRole;
import com.learn.chapter09.pojo.Role;
import com.learn.chapter09.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;


/**
 * 9.3.2 存储过程游标 p220
 */
public class cursorTests {

    /**
     * Oracle支持这种游标方式返回结果集，MySQL不支持书中的这种方式返回结果集。
     * MySQL存储过程不支持返回多行结果集，可以考虑保存到临时表中。
     */
    @Test
    public void test() {
        SqlSession sqlSession = null;

        try{
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            ProcedureMapper procedureMapper = sqlSession.getMapper(ProcedureMapper.class);

            PageRole pageRole = new PageRole();
            pageRole.setRoleName("role");
            pageRole.setStart(0);
            pageRole.setEnd(5);
            pageRole.setCount(0);
            pageRole.setRoleList(new ArrayList<Role>());

            procedureMapper.findRole(pageRole);
            System.out.println(pageRole.getCount());

            for (Role role: pageRole.getRoleList()) {
                System.out.println("role_no => " + role.getId() + ",role_name => " + role.getRoleName());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
