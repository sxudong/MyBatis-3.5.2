package com.learn.chapter04;

import com.learn.chapter04.collection.mapper.StudentMapper;
import com.learn.chapter04.collection.pojo.Student;
import com.learn.chapter04.collection.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


/**
 * 4.7.4.2 一对多级联
 */
public class collectionTests {

    private static Logger logger = LogManager.getLogger(collectionTests.class);

    @Test
    public void test(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            Student student = studentMapper.getStudent(1);

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
Student(id=1, cnName=张三, sex=男, selfCardNo=1000, note=fdsafsdahj,
    studentSelfcard=StudentSelfCard(
        id=1, studentId=1, nativeStr=本地, issueDate=Sun Jul 02 00:00:00 CST 2017, endDate=Fri Jul 07 00:00:00 CST 2017, note=备注信息
    ),
    studentLectureList=[
        StudentLecture(id=1, studentId=1,lecture=Lecture(id=1, lectureName=语文, note=语文备注), grade=100.00, note=语文),
        StudentLecture(id=2, studentId=1, lecture=Lecture(id=2, lectureName=数学, note=数学备注), grade=90.00, note=数学),
        StudentLecture(id=3, studentId=1, lecture=Lecture(id=3, lectureName=英语, note=英语备注), grade=89.00, note=英语)
    ]
)
执行成功!
*///:~