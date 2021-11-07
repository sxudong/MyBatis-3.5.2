package com.learn.chapter04;


import com.learn.chapter04.otherCascade.mapper.StudentMapper;
import com.learn.chapter04.otherCascade.pojo.Student;
import com.learn.chapter04.otherCascade.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * 4.7.4.6 -- 另一种级联
 * 级联（Cascade） p110
 *
 * 这里我们看到了一条SQL就能完成映射，但是这条SQL有些复杂。其次我们是否需要在
 * 一次查询就导出这么多数据，这会不会造成资源浪费，同时也给维护带来一定的困难。
 * 这些需要在实际工作中做考量。
 */
public class otherCascadeTests {

    private static Logger logger = LogManager.getLogger(otherCascadeTests.class);

    @Test
    public void test() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentDao = sqlSession.getMapper(StudentMapper.class);
            List<Student> student = studentDao.findAllStudent();
            student.forEach(x -> System.out.println(x));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        logger.info("执行成功!");
    }
}
/* Output:
Student{id=1, cnName='张三', sex=null, selfCardNo='1000', note='fdsafsdahj', studentSelfcard=StudentSelfCard(id=1, studentId=null, nativeStr=null, issueDate=Sun Jul 02 00:00:00 CST 2017, endDate=Fri Jul 07 00:00:00 CST 2017, note=备注信息), studentLectureList=[StudentLecture(id=1, studentId=1, lecture=Lecture(id=1, lectureName=语文, note=语文备注), grade=100.00, note=语文), StudentLecture(id=2, studentId=1, lecture=Lecture(id=2, lectureName=数学, note=数学备注), grade=90.00, note=数学), StudentLecture(id=3, studentId=1, lecture=Lecture(id=3, lectureName=英语, note=英语备注), grade=89.00, note=英语)], studentHealthMaleList=[StudentHealthMale(id=1, studentId=null, checkDate=2017-01-02, heart=1, liver=2, spleen=3, lung=4, kidney=5, prostate=6, note=7)]}
Student{id=2, cnName='李四', sex=null, selfCardNo='1001', note='测试备注', studentSelfcard=StudentSelfCard(id=2, studentId=null, nativeStr=null, issueDate=Sun Jul 02 00:00:00 CST 2017, endDate=Fri Jul 07 00:00:00 CST 2017, note=备注信息2), studentLectureList=[StudentLecture(id=4, studentId=2, lecture=Lecture(id=1, lectureName=语文, note=语文备注), grade=75.00, note=语文), StudentLecture(id=5, studentId=2, lecture=Lecture(id=2, lectureName=数学, note=数学备注), grade=80.00, note=数学), StudentLecture(id=6, studentId=2, lecture=Lecture(id=3, lectureName=英语, note=英语备注), grade=65.00, note=英语)], studentHealthMaleList=[StudentHealthFemale(id=2, studentId=null, checkDate=2017-01-01, heart=10, liver=20, spleen=30, lung=40, kidney=50, uterus=60, note=70)]}
Student{id=3, cnName='王五', sex=null, selfCardNo='1002', note='新测试', studentSelfcard=StudentSelfCard(id=3, studentId=null, nativeStr=null, issueDate=Sun Jul 02 00:00:00 CST 2017, endDate=Fri Jul 07 00:00:00 CST 2017, note=备注信息1), studentLectureList=[StudentLecture(id=null, studentId=3, lecture=null, grade=null, note=null)], studentHealthMaleList=[StudentHealthMale(id=3, studentId=null, checkDate=2017-01-10, heart=9, liver=8, spleen=7, lung=6, kidney=5, prostate=2, note=1)]}
执行成功!
*///:~
