package com.learn.chapter09;

import com.learn.chapter09.mapper.StudentMapper;
import com.learn.chapter09.pojo.Student;
import com.learn.chapter09.util.PageUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Homejim
 * Date 2018-9-4
 * Time 20:40
 */
public class StudentMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("chapter09/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 没有实现分页，实现的插件工功能
    @Test
    public void testInsertStudent() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

            Student student1 = new Student();
            student1.setName("李四");
            student1.setPhone("13366666666");

            studentMapper.insertStudent(student1);
            sqlSession.commit();

            // 从下标offset开始查询，2条
            PageUtil.setPagingParam(1, 2);
            List<Student> students = sqlSession.selectList("selectAll");
            for (int i = 0; i < students.size(); i++) {
                System.out.println(students.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

  /**
   * 测试 SqlSession 的 selectAll 功能，查询所有数据。
   */
  @Test
    public void testSelectList() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            // 从下标offset开始查询，2条
            PageUtil.setPagingParam(1, 2);
            List<Student> students = sqlSession.selectList("selectAll");
            for (int i = 0; i < students.size(); i++) {
                System.out.println(students.get(i));
            }

            System.out.println("======================================");

            List<Student> students2 = sqlSession.selectList("selectAll");
            for (int i = 0; i < students2.size(); i++) {
                System.out.println(students2.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

  /**
   * 获取一段时间内的用户，使用 Map 传递参数
   */
  @Test
    public void testSelectBtweenCreatedTimeMap() {
        Map<String, Object> params = new HashMap<>();

        // 开始时间
        Calendar bTime = Calendar.getInstance();
        // month 是从0~11， 所以9月是8
        bTime.set(2018, Calendar.AUGUST, 29); // 8月29号
        params.put("bTime", bTime.getTime());

        // 结束时间
        Calendar eTime = Calendar.getInstance();
        eTime.set(2018,Calendar.SEPTEMBER,2); // 9月2号
        params.put("eTime", eTime.getTime());
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();

            StudentMapper studentMapper = (StudentMapper) sqlSession.getMapper(StudentMapper.class);
            List<Student> students = studentMapper.selectBetweenCreatedTime(params);
            for (int i = 0; i < students.size(); i++) {
                System.out.println(students.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    } /* Output:
    Student{studentId=1, name='孙悟空', phone='13155556666', email='123547', sex=49, locked=49, gmtCreated=Sat Sep 01 21:46:56 CST 2018, gmtModified=Sat Nov 06 21:47:02 CST 2021}
    Student{studentId=2, name='唐生', phone='13255557777', email='652135', sex=49, locked=49, gmtCreated=Sun Sep 02 09:50:16 CST 2018, gmtModified=Thu Nov 04 21:50:11 CST 2021}
    *///~

    /**
     * 获取一段时间内的用户，使用 注解 传递参数
     */
    @Test
    public void testSelectBtweenCreatedTimeAnno() {
        Map<String, Object> params = new HashMap<>();

        Calendar bTime = Calendar.getInstance();
        // month 是从0~11， 所以9月是8
        bTime.set(2018, Calendar.AUGUST, 29);

        Calendar eTime = Calendar.getInstance();
        eTime.set(2018,Calendar.SEPTEMBER,2);

        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();

            StudentMapper studentMapper = (StudentMapper) sqlSession.getMapper(StudentMapper.class);
            List<Student> students = studentMapper.selectBetweenCreatedTimeAnno(bTime.getTime(), eTime.getTime());
            for (int i = 0; i < students.size(); i++) {
                System.out.println(students.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    } /* Output:
    Student{studentId=1, name='孙悟空', phone='13155556666', email='123547', sex=49, locked=49, gmtCreated=Sat Sep 01 21:46:56 CST 2018, gmtModified=Sat Nov 06 21:47:02 CST 2021}
    Student{studentId=2, name='唐生', phone='13255557777', email='652135', sex=49, locked=49, gmtCreated=Sun Sep 02 09:50:16 CST 2018, gmtModified=Thu Nov 04 21:50:11 CST 2021}
    *///~
}
