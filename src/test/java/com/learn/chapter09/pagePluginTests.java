package com.learn.chapter09;

import com.learn.chapter09.mapper.StudentMapper;
import com.learn.chapter09.pagePlugin.PageParams;
import com.learn.chapter09.mapper.UserMapper;
import com.learn.chapter09.pagePlugin.MyPage;
import com.learn.chapter09.pojo.Student;
import com.learn.chapter09.pojo.User;
import com.learn.chapter09.util.PageUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 测试自定义分页插件
 *
 * @Date: 2020/3/3
 */
public class pagePluginTests {

    public static void insetUser(UserMapper userMapper) {
      List<User> users = new ArrayList<>();
      IntStream.range(0, 5).forEach(i -> users.add(new User("张三" + i,"13655556666", i)));
      userMapper.batchInsertUser(users);
    }

  /**
   * 测试书中的《深入浅出MyBatis》9.5.2 插页分页
   * P229
   */
  @Test
    public void testPlugin() throws IOException {
        // 读取配置信息
        InputStream  inputStream = Resources.getResourceAsStream("chapter09/mybatis-plugin.xml");
        // 根据配置信息，创建 SqlSession 工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // SqlSession 工厂创建 SqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//        insetUser(userMapper);
//        sqlSession.commit();

        // 执行相应的接口方法
        PageParams pageParams = new PageParams();
        pageParams.setPage(1);
        pageParams.setPageSize(10);
        pageParams.setUseFlag(true);

        List<User> users = userMapper.queryAllUserByPage(pageParams);
        users.forEach(System.out::println);

        //关闭连接
        sqlSession.close();
    } /* Output:
      开始包装目标对象
      开始包装目标对象
      开始包装目标对象
      开始包装目标对象
      总条数：63
      User{id=1, userName='zhangsan', cnname='张三', sex=0, mobile='1325656546', email='lfkdsj@dlkjf.com', note='fdsafljdsakf'}
      User{id=2, userName='zlkjklfdsa', cnname='李四', sex=1, mobile='1325656546', email='lfkdsj@dlkjf.com', note='fdsafljdsakf'}
      User{id=3, userName='wangwuz', cnname='王五', sex=2, mobile='1265445', email='211321@fdsaf.com', note='2'}
      User{id=39, userName='null', cnname='张三0', sex=1, mobile='null', email='null', note='null'}
      User{id=40, userName='null', cnname='张三1', sex=1, mobile='null', email='null', note='null'}
      User{id=41, userName='null', cnname='张三2', sex=1, mobile='null', email='null', note='null'}
      User{id=42, userName='null', cnname='张三3', sex=1, mobile='null', email='null', note='null'}
      User{id=43, userName='null', cnname='张三4', sex=1, mobile='null', email='null', note='null'}
      User{id=44, userName='null', cnname='张三', sex=null, mobile='13012345671', email='null', note='null'}
      User{id=45, userName='null', cnname='李四', sex=null, mobile='13012345672', email='null', note='null'}
      *///~

    /**
     * 测试的是自定义的 com.learn.chapter09.action.pagePlugin.MyPageInterceptor 分页插件
     * @throws IOException
     */
    @Test
    public void testMyPlugin1() throws IOException {
        // 读取配置信息
        InputStream inputStream = Resources.getResourceAsStream("chapter09/mybatis-myPlugin1.xml");
        // 根据配置信息，创建 SqlSession 工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // SqlSession 工厂创建 SqlSession
        SqlSession sqlSession = factory.openSession();

        //获取接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        insetUser(userMapper); // 批量插入
        //userMapper.batchDeleteUser(Arrays.asList(new Integer[]{21, 22, 23})); // 批量删除
        //sqlSession.commit();

        //执行相应的接口方法
        MyPage myPage = new MyPage();
        myPage.setCurrentPage(1); //查看第几页
        myPage.setPageSize(10);   //每页10条
        List<User> userss = userMapper.getAllUserByPage(myPage);
        userss.forEach(System.out::println);

        //批量插入需要提交，否则数据库里没有数据。
        sqlSession.commit();
        //关闭连接
        sqlSession.close();
    } /* Output:
    开始包装目标对象
    开始包装目标对象
    开始包装目标对象
    开始包装目标对象
    开始包装目标对象
    开始包装目标对象
    开始包装目标对象
    原来的sql：select id, user_name as userName, cnname, sex, mobile, email, note from t_user
    User{id=1, userName='zhangsan', cnname='张三', sex=0, mobile='1325656546', email='lfkdsj@dlkjf.com', note='fdsafljdsakf'}
    User{id=2, userName='zlkjklfdsa', cnname='李四', sex=1, mobile='1325656546', email='lfkdsj@dlkjf.com', note='fdsafljdsakf'}
    User{id=3, userName='wangwuz', cnname='王五', sex=2, mobile='1265445', email='211321@fdsaf.com', note='2'}
    User{id=39, userName='null', cnname='张三0', sex=1, mobile='null', email='null', note='null'}
    User{id=40, userName='null', cnname='张三1', sex=1, mobile='null', email='null', note='null'}
    User{id=41, userName='null', cnname='张三2', sex=1, mobile='null', email='null', note='null'}
    User{id=42, userName='null', cnname='张三3', sex=1, mobile='null', email='null', note='null'}
    User{id=43, userName='null', cnname='张三4', sex=1, mobile='null', email='null', note='null'}
    User{id=44, userName='null', cnname='张三', sex=null, mobile='13012345671', email='null', note='null'}
    User{id=45, userName='null', cnname='李四', sex=null, mobile='13012345672', email='null', note='null'}
    *///~

    /**
     * 测试自定义的 com.learn.chapter09.action.pagePlugin.MyPagePlugin 分页插件
     */
    @Test
    public void testMyPlugin2() throws IOException {
          //读取配置信息
          InputStream  inputStream = Resources.getResourceAsStream("chapter09/mybatis-myPlugin2.xml");
          //根据配置信息，创建SqlSession工厂
          SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
          //SqlSession工厂创建SqlSession
          SqlSession sqlSession = factory.openSession();
          //获取接口的代理对象
          UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//        insetUser(userMapper);
//        sqlSession.commit();

          MyPage myPage = new MyPage();
          myPage.setCurrentPage(3);
          myPage.setPageSize(10);

          Map<String, Object> map = new HashMap<>();
          map.put("page", myPage);

          List<User> users = userMapper.queryAllUserByPage(map);
          users.forEach(System.out::println);

          //关闭连接
          sqlSession.close();
      } /* Output:
      开始包装目标对象
      开始包装目标对象
      开始包装目标对象
      开始包装目标对象
      原来的sql：select user_name as userName, cnname, sex, mobile, email, note from t_user
      User{id=null, userName='null', cnname='张三2', sex=2, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三3', sex=3, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三4', sex=4, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三0', sex=0, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三1', sex=1, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三2', sex=2, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三3', sex=3, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三4', sex=4, mobile='null', email='null', note='null'}
      User{id=null, userName='null', cnname='张三0', sex=0, mobile='13655556666', email='null', note='null'}
      User{id=null, userName='null', cnname='张三1', sex=1, mobile='13655556666', email='null', note='null'}
      *///~

    /**
     * 测试 com.learn.chapter09.pagePlugin.PageInterceptor 插件，使用自己创建的 MappedStatement 。
     * 需要打开配置文件的注释测试。
     */
    @Test
    public void testPageInterceptor() throws IOException {
        // 读取配置信息
        InputStream inputStream = Resources.getResourceAsStream("chapter09/mybatis-config.xml");
        // 根据配置信息，创建SqlSession工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // SqlSession工厂创建SqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取接口的代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

//        Student student1 = new Student();
//        student1.setName("李四");
//        student1.setPhone("13366666666");
//
//        studentMapper.insertStudent(student1);
//        sqlSession.commit();

        // 从下标offset开始查询，2条
        PageUtil.setPagingParam(1, 2);
        List<Student> students = sqlSession.selectList("selectAll");
        for (int i = 0; i < students.size(); i++) {
          System.out.println(students.get(i));
        }

        //关闭连接
        sqlSession.close();
    } /* Output:
    newSQL: select

        student_id, name, phone, email, sex, locked, gmt_created, gmt_modified

        from student limit 1,2
        Student{studentId=2, name='唐生', phone='13255557777', email='652135', sex=49, locked=49, gmtCreated=Mon Nov 01 21:50:07 CST 2021, gmtModified=Thu Nov 04 21:50:11 CST 2021}
        Student{studentId=3, name='八戒', phone='13566669999', email='2351458', sex=49, locked=49, gmtCreated=Wed Nov 03 21:50:39 CST 2021, gmtModified=Fri Nov 05 21:50:43 CST 2021}
    *///~
}

