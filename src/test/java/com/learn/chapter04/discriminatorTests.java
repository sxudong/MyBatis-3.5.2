package com.learn.chapter04;

import com.learn.chapter04.discriminator.mapper.StudentMapper;
import com.learn.chapter04.discriminator.pojo.Student;
import com.learn.chapter04.discriminator.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 4.7.4.3 discriminator 鉴别器级联
 */
public class discriminatorTests {

    private static Logger logger = LogManager.getLogger(discriminatorTests.class);

    @Test
    public void test() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            Student student = studentMapper.getStudent(2);

            System.out.println(student.toString());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
/* Output:
Opening JDBC Connection
Created connection 858952163.
Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@333291e3]
==>  Preparing: select id,cnname,sex,note,selfcard_no from t_student where id=?
==> Parameters: 2(Integer)
====>  Preparing: SELECT id, student_id, check_date, heart, liver, spleen, lung, kidney, uterus, note FROM t_student_health_female where student_id=?
====> Parameters: 2(Integer)
<====      Total: 1
====>  Preparing: select id,student_id,native,issue_date,end_date,note from t_student_selfcard where student_id=?
====> Parameters: 2(Integer)
<====      Total: 1
====>  Preparing: select id,student_id,lecture_id,grade,note from t_student_lecture where student_id=?
====> Parameters: 2(Integer)
======>  Preparing: select id,lecture_name as lectureName,note from t_lecture where id=?
======> Parameters: 1(Integer)
<======      Total: 1
======>  Preparing: select id,lecture_name as lectureName,note from t_lecture where id=?
======> Parameters: 2(Integer)
<======      Total: 1
======>  Preparing: select id,lecture_name as lectureName,note from t_lecture where id=?
======> Parameters: 3(Integer)
<======      Total: 1
<====      Total: 3
<==      Total: 1
MaleStudent{id=2, cnName='李四', sex=女, selfCardNo='1001', note='测试备注', studentSelfcard=StudentSelfCard(id=2, studentId=2, nativeStr=测试, issueDate=Sun Jul 02 00:00:00 CST 2017, endDate=Fri Jul 07 00:00:00 CST 2017, note=备注信息2), studentLectureList=[StudentLecture(id=4, studentId=2, lecture=Lecture(id=1, lectureName=语文, note=语文备注), grade=75.00, note=语文), StudentLecture(id=5, studentId=2, lecture=Lecture(id=2, lectureName=数学, note=数学备注), grade=80.00, note=数学), StudentLecture(id=6, studentId=2, lecture=Lecture(id=3, lectureName=英语, note=英语备注), grade=65.00, note=英语)], studentHealthMaleList=[StudentHealthFemale(id=2, student_id=2, check_date=2017-01-01, heart=10, liver=20, spleen=30, lung=40, kidney=50, uterus=60, note=70)]}
Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@333291e3]
Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@333291e3]
Returned connection 858952163 to pool.
执行成功!
*///:~