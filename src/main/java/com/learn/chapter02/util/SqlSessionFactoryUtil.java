package com.learn.chapter02.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SqlSession 工具类
 *
 * 本例中的 SqlSessionFactory 的唯一责任就是为我们 创建SqlSession，所以采用"单便模式"。
 * 我们往往把它构造方法构有化，并给一个静态（static）方法，让其返回唯一单例。而在多线程
 * 环境初始化单例，往往需要线程锁以避免类对象被多次初始化。
 */
public class SqlSessionFactoryUtil {
    // SqlSessionFactory对象
	private static SqlSessionFactory sqlSessionFactory = null;

	// 类线程锁
	private static final Class<SqlSessionFactoryUtil> CLASS_LOCK = SqlSessionFactoryUtil.class;

	// 私有化构造参数
	private SqlSessionFactoryUtil() { // 目的是避免使用者用 new 的方式去创建多个对象
	}

	/**
	 * 构建 SqlSessionFactory
	 */
	public static SqlSessionFactory initSqlSessionFactory() {
		String resource = "chapter02/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			/*
			 * 这里创建了一个 XML 输入流，用 SqlSessionFactoryBuilder().build() 读取 XML 的信息来创建
			 * SessionFactory 对象。
			 * MyBatis 的解析程序会将 mybatis-config.xml 文件配置的信息解析到 Configuration类对象 里面,
			 * 然后利用 SqlSessionFactoryBuilder() 读取这个对象为我们创建 SessionFactory。
			 */
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE, null, e);
		}
		synchronized (CLASS_LOCK) { // 加锁目的是避免在多线程环境中，多次初始化造成对象的不唯一
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			}
		}
		return sqlSessionFactory;
	}

	/**
	 * 打开 SqlSession
	 */
	public static SqlSession openSession() {
		if (sqlSessionFactory == null) {
			initSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}
}
