package com.learn.chapter03.objectFactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 标题：自定义 ObjectFactory
 * 描述：继承 DefaultObjectFactory 来简化编程。
 *      当 MyBatis 在构建一个结果返回的时候，都会使用 ObjectFactory（对象工厂）去构建 POJO,
 *      在 MyBatis 中可以定制自已的对象工厂。一般使用默认的即可，默认的 ObjectFactory 是由
 *      org.apache.ibatis.reflection.factory.DefaultObjectFactory 来提供服务。
 *
 * 注意：大部分情况下，我们不需要使用自已配置的 ObjectFactory，使用系统默认的即可。
 **/
public class MyObjectFactory extends DefaultObjectFactory {

    private static final long serialVersionUID = -6421137313378981965L;
    Logger log = Logger.getLogger("MyObjectFactory.class");

    @Override
    public <T> T create(Class<T> type) {
        log.info("使用定制对象工厂的 create方法 构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        log.info("使用定制对象工厂的 create方法 构建列表对象");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("使用定制对象工厂的 setProperties方法 设置属性");
        super.setProperties(properties);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
/*
信息: 使用定制对象工厂的 setProperties方法 设置属性
八月 01, 2020 2:31:32 下午 learn.chapter3.typeHandler.SexEnumTypeHandler setParameter
信息: 使用自定义 EnumTypeHandler 转换数据后 插入数据库
DEBUG 2020-08-01 14:31:32,334 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==> Parameters: zhangsan(String), 张三(String), 2020-08-01 14:31:32.031(Timestamp), 1(Integer), zhangsan@163.com(String), 18888886666(String), test SexEnumTypeHandler(String)
DEBUG 2020-08-01 14:31:32,340 org.apache.ibatis.logging.jdbc.BaseJdbcLogger <==    Updates: 1
DEBUG 2020-08-01 14:31:32,342 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==>  Preparing: select id, user_name, cnname, birthday, sex, email, mobile, note from t_user where id=?
DEBUG 2020-08-01 14:31:32,342 org.apache.ibatis.logging.jdbc.BaseJdbcLogger ==> Parameters: 1(Long)
八月 01, 2020 2:31:32 下午 learn.chapter3.objectFactory.MyObjectFactory create
信息: 使用定制对象工厂的 create方法 构建单个对象
八月 01, 2020 2:31:32 下午 learn.chapter3.objectFactory.MyObjectFactory create
信息: 使用定制对象工厂的 create方法 构建列表对象
八月 01, 2020 2:31:32 下午 learn.chapter3.objectFactory.MyObjectFactory create
信息: 使用定制对象工厂的 create方法 构建单个对象
八月 01, 2020 2:31:32 下午 learn.chapter3.objectFactory.MyObjectFactory create
信息: 使用定制对象工厂的 create方法 构建列表对象
DEBUG 2020-08-01 14:31:32,365 org.apache.ibatis.logging.jdbc.BaseJdbcLogger <==      Total: 1
MALE
 */
