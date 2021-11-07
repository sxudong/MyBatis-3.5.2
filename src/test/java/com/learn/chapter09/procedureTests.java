package com.learn.chapter09;

import com.learn.chapter09.mapper.ProcedureMapper;
import com.learn.chapter09.pojo.ProcedurePojo;
import com.learn.chapter09.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

/**
 * 测试存储过程 int 和 out 参数
 *
 *《深入浅出的MyBatis》9.3 调用存储过程  P218
 */
public class procedureTests {

    @Test
    public void test() {
        SqlSession sqlSession = null;

        try{
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            ProcedureMapper procedureMapper = sqlSession.getMapper(ProcedureMapper.class);
            ProcedurePojo pojo = new ProcedurePojo();
            pojo.setRoleName("role");

            procedureMapper.count(pojo);
            System.out.println(pojo.getRoleName() + "\t" + pojo.getResult() + "\t");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
/* Output:
role	10
*///~
