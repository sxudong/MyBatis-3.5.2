package com.learn.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 代码清单 3-29：使用自定义数据源
 * 在 mybatis-conf.xml 中配置：<datasource type="xxx.xxx.DbcpDataSourceFactory">
 */
public class DbcpDataSourceFactory extends BasicDataSource implements DataSourceFactory {

    private Properties props = null;

    @Override
    public void setProperties(Properties properties) {
        this.props = properties;
    }

    @Override
    public DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);}
        catch (Exception ex){
            ex.printStackTrace();
        }
        return dataSource;
    }
}
