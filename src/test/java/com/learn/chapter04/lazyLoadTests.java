package com.learn.chapter04;

import com.learn.chapter04.lazyLoad.mapper.StudentMapper;
import com.learn.chapter04.lazyLoad.pojo.Student;
import com.learn.chapter04.lazyLoad.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 4.7.4.5 -- 延迟加载
 *
 *     <!-- 配置 全局延迟加载 -->
 *     <settings>
 *         <!-- 使用 log4j2 打印查询语句 -->
 *         <setting name="logImpl" value="LOG4J2" />
 *         <!-- 按层级输出 -->
 *         <setting name="lazyLoadingEnabled" value="true"/>
 *         <!-- 默认为true按层级输出，false不按层输出，按程序调用要求输出 -->
 *         <setting name="aggressiveLazyLoading" value="false"/>
 *     </settings>
 *
 *     fetchType="lazy" 或 “eager”，没有配置它，默认就是“eager”
 */
public class lazyLoadTests {
    private static Logger logger = LogManager.getLogger(lazyLoadTests.class);

    @Test
    public void test() {
        SqlSession sqlSession = null;
        try {
            // 测试延迟加载
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentDao = sqlSession.getMapper(StudentMapper.class);

            Student student = studentDao.getStudent(1);

            System.out.println(student);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
/* Output:
14:03:51.983 DEBUG org.apache.ibatis.logging.LogFactory 154 setImplementation - Logging initialized using 'class org.apache.ibatis.logging.log4j2.Log4j2Impl' adapter.
14:03:52.054 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 330 forceCloseAll - PooledDataSource forcefully closed/removed all connections.
14:03:52.054 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 330 forceCloseAll - PooledDataSource forcefully closed/removed all connections.
14:03:52.054 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 330 forceCloseAll - PooledDataSource forcefully closed/removed all connections.
14:03:52.055 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 330 forceCloseAll - PooledDataSource forcefully closed/removed all connections.
14:03:52.175 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 142 openConnection - Opening JDBC Connection
14:03:52.355 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 412 popConnection - Created connection 580871917.
14:03:52.356 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 107 setDesiredAutoCommit - Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@229f66ed]
14:03:52.358 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id, cnname, sex, note, selfcard_no from t_student where id=?
14:03:52.393 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 1(Integer)
14:03:52.415 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, cnname, sex, note, selfcard_no
14:03:52.416 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 1, 张三, 1, fdsafsdahj, 1000
14:03:52.463 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [71ms ]
14:03:52.464 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: SELECT id,student_id,check_date,heart,liver,spleen,lung,kidney,prostate,note FROM t_student_health_male where student_id=?
14:03:52.465 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 1(Integer)
14:03:52.466 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, student_id, check_date, heart, liver, spleen, lung, kidney, prostate, note
14:03:52.466 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 1, 1, 2017-01-02, 1, 2, 3, 4, 5, 6, 7
14:03:52.468 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [3ms ]
14:03:52.468 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id,student_id,lecture_id,grade,note from t_student_lecture where student_id=?
14:03:52.469 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 1(Integer)
14:03:52.470 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, student_id, lecture_id, grade, note
14:03:52.471 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 1, 1, 1, 100.00, 语文
14:03:52.473 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 2, 1, 2, 90.00, 数学
14:03:52.474 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 3, 1, 3, 89.00, 英语
14:03:52.475 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 3
执行耗时 : [7ms ]
14:03:52.476 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id,student_id,native,issue_date,end_date,note from t_student_selfcard where student_id=?
14:03:52.477 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 1(Integer)
14:03:52.479 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, student_id, native, issue_date, end_date, note
14:03:52.480 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 1, 1, 本地, 2017-07-02, 2017-07-07, 备注信息
14:03:52.481 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [5ms ]
14:03:52.483 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id,lecture_name as lectureName,note from t_lecture where id=?
14:03:52.483 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 1(Integer)
14:03:52.484 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, lectureName, note
14:03:52.485 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 1, 语文, 语文备注
14:03:52.486 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [3ms ]
14:03:52.487 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id,lecture_name as lectureName,note from t_lecture where id=?
14:03:52.488 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 2(Integer)
14:03:52.489 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, lectureName, note
14:03:52.489 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 2, 数学, 数学备注
14:03:52.490 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [2ms ]
14:03:52.491 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==>  Preparing: select id,lecture_name as lectureName,note from t_lecture where id=?
14:03:52.491 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - ==> Parameters: 3(Integer)
14:03:52.492 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==    Columns: id, lectureName, note
14:03:52.493 TRACE org.apache.ibatis.logging.jdbc.BaseJdbcLogger 148 trace - <==        Row: 3, 英语, 英语备注
14:03:52.495 DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger 142 debug - <==      Total: 1
执行耗时 : [5ms ]
MaleStudent{id=1, cnName='张三', sex=男, selfCardNo='1000', note='fdsafsdahj', studentSelfcard=StudentSelfCard{id=1, studentId=1, nativeStr='本地', issueDate=Sun Jul 02 00:00:00 CST 2017, endDate=Fri Jul 07 00:00:00 CST 2017, note='备注信息'}, studentLectureList=[StudentLecture{id=1, studentId=1, lecture=Lecture{id=1, lectureName='语文', note='语文备注'}, grade=100.00, note='语文'}, StudentLecture{id=2, studentId=1, lecture=Lecture{id=2, lectureName='数学', note='数学备注'}, grade=90.00, note='数学'}, StudentLecture{id=3, studentId=1, lecture=Lecture{id=3, lectureName='英语', note='英语备注'}, grade=89.00, note='英语'}], studentHealthMaleList=[StudentHealthMale{id=1, student_id='1', check_date='2017-01-02', heart='1', liver='2', spleen='3', lung='4', kidney='5', prostate='6', note='7'}]}
14:03:52.497 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 130 resetAutoCommit - Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@229f66ed]
14:03:52.498 DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction 96 close - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@229f66ed]
14:03:52.499 DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource 361 pushConnection - Returned connection 580871917 to pool.
14:03:52.499 INFO  com.learn.chapter04.lazyLoadTests 45 test - 执行成功!
*///~
