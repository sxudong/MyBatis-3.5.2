package com.learn.chapter07.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 *  自定义插件 拦截
 *  代码清单 7-11 实现插件拦截方法 p161
 *
 * 定义插件签名：
 * `@Intercepts注解 说明它是一个拦截器。
 * `@Signature注解 是注册拦截器签名的地方，只有签名满足条件才能拦截。
 *  type：指定要拦截的目标，可以是四大对象中的一个"Executor, StatementHandler, ParameterHandler, ResultSetHandler"。
 *  method：代表要拦截四大对象的某一种接口方法。
 *  args：表示拦截方法的参数列表，你需要根据拦截对象的方法参数设置。
 *
 *  定义完成一个自定义拦截器后，需要在 mybatis-config.xml 配置文件中对该拦截器进行配置。
 */
@Intercepts({@Signature(
        type = Executor.class, // 确定要拦截的对象
        method = "update",     // 确定要拦截的方法
        args = {MappedStatement.class, Object.class} // 拦截方法的参数
)})
public class MyPlugin implements Interceptor {

    private Properties props = null;

    /**
     * 代替拦截对象方法的内容
     * @param invocation 责任链对象
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("before......");
        /*
        如果当前代理的是一个非代理对象,那么它就回调用真实拦截对象的方法,
        如果不是,它会调度下个插件代理对象的invoke方法.
         */
        Object obj = invocation.proceed();
        System.err.println("after......");
        return obj;
    }

    /**
     * 生成对象的代理，这里常用 MyBatis 提供的 Plugin 类的 wrap 方法
     * @param target 被代理对象
     * @return 生成的代理对象
     */
    @Override
    public Object plugin(Object target) {
        // 使用MyBatis提供的Plugin类生成代理对象.
        System.err.println("调用生成代理对象");
        return Plugin.wrap(target, this);
    }

    /**
     * 获取插件配置的属性，我们在 MyBatis 的配置文件里面去配置
     * @param properties 是 MyBatis 配置的参数
     */
    @Override
    public void setProperties(Properties properties) {
        //System.err.println("自定义插件 " + properties.getProperty("dbType"));
        System.err.println("自定义插件 " + properties.get("dbType"));
        this.props = properties;
    }
}
