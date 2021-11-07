package com.learn.chapter08.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 集成 MyBatis
 * 描述：MyBatis 相关配置
 * 使用 @EnableTransactionManagement // 开启自动事务控制
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.learn.chapter08.dao.mapper.RoleMapper")
public class MyBatisConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        // application.properties
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_all?characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    //mybatis的配置
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(new ClassPathResource("chapter08/mybatis.cfg.xml"));
        return factoryBean;
    }

    // mybatis的配置 https://www.cnblogs.com/hhhshct/p/9688079.html
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); //mybatis-plus插件类
//        sqlSessionFactoryBean.setDataSource(dataSource()); //数据源
//        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mappers/*.xml"));
//        sqlSessionFactoryBean.setTypeAliasesPackage("powerx.io.model"); //别名，让*Mpper.xml实体类映射可以不加上具体包名
//        return sqlSessionFactoryBean;
//    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // 扫描包下的所有 Mapper.java
        configurer.setBasePackage("com.learn.chapter08.dao");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        configurer.setAnnotationClass(Repository.class);
        return configurer;
    }

}
