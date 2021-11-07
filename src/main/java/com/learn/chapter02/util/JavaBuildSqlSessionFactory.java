package com.learn.chapter02.util;

import com.learn.chapter02.mapper.RoleMapper;
import com.learn.chapter02.pojo.Role;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * 《深入浅出Mybatis》第2章 Mybatis入门 P18
 *  用 Java 的方式构建 SqlSessionFactory
 */
public class JavaBuildSqlSessionFactory {

	public static SqlSessionFactory getSqlSessionFactory(){
		// 构建数据库连接池
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setDriver("com.mysql.cj.jdbc.Driver" ) ;
		dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		// 构建数据库事务方式
		TransactionFactory transactionFactory = new JdbcTransactionFactory() ;
		// 创建了数据库运行环境
		Environment environment = new Environment("development", transactionFactory,dataSource) ;
		// 构建 configuration 对象
		Configuration configuration = new Configuration(environment);
		// 注册一个 MyBatis 上下文别名
		configuration.getTypeAliasRegistry( ).registerAlias("role", Role.class);
		// 加入一个映射器
		configuration.addMapper(RoleMapper.class) ;
		// 使用 sqlSessionFactoryBuilder 构建 sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration) ;
		return sqlSessionFactory;
	}
}
