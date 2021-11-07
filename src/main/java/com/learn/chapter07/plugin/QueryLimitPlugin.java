package com.learn.chapter07.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * 自定义插件 拦截
 * 标题：编写自定义插件<br>
 * 代码清单 7-13：QueryLimitPlugin 限制返回行数拦截器 p163
 *
 *
 * 定义插件签名：
 * `@Intercepts注解 说明它是一个拦截器。
 * `@Signature注解 是注册拦截器签名的地方，只有签名满足条件才能拦截。
 *  type：指定要拦截的目标，可以是四大对象中的一个"Executor, StatementHandler,
 *        ParameterHandler, ResultSetHandler"。这里拦截的是"StatementHandler"。
 *  method：代表要拦截四大对象的某一种接口方法。这里是"prepare()"
 *  args：表示拦截方法的参数列表，你需要根据拦截对象的方法参数设置。
 *        StatementHandler.prepare(Connection connection)方法中有一个Connection对象
 *
 *  定义完成一个自定义拦截器后，需要在 mybatis-config.xml 配置文件中对该拦截器进行配置。
 */
@Intercepts({@Signature(
        type = StatementHandler.class, // 确定要拦截的对象
        method = "prepare",            // 确定要拦截的方法
        args = {Connection.class, Integer.class} // 拦截方法的参数
)})
public class QueryLimitPlugin implements Interceptor {
    // 限制表中间别名,避免表重名,所以起的怪些.
    private static final String LMT_TABLE_NAME = "Table_Name_xxx";
    // 默认限制查询返回行数
    private int limit;
    // 数据库类型
    private String dbType;

    /**
     * 代替拦截对象方法的内容
     * @param invocation 责任链对象
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 取出拦截对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 7.4 常用工具类——MetaObject
        // 它可以有效读取 或者 修改一些重要的对象的属性
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // 进行绑定
        // 分离代理对象链（由于目标类可能被多个拦截器拦截，从而形成多次代理,
        // 通过循环可以分离出最原始的被代理类，MyBatis 使用的是JDK代理。）
        while (metaObject.hasGetter("h")) {
            Object object = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(object);
        }
        // 分离最后一个代理对象的目标类
        while (metaObject.hasGetter("target")) {
            Object object = metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(object);
        }

        // 取出即将要执行的SQL（使用 MetaObject 工具类获取对象属性）
        // 真实的 StatementHandler 有一个属性 BoundSql,它下面有一个属性sql。
        String sql = (String) metaObject.getValue("delegate.boundSql.sql"); // BoundSql 对象是处理SQL语句用的
        String limitSql;

        // 判断 SQL 是否是 select 语句，如果不是 select 语句，那么就出错了
        // 如果是，则修改它，最多返回 3 行，这里用的是 MySQL 数据库，其它数据库要改写成其它。
        if (sql != null && sql.toLowerCase().trim().indexOf("select") == 0) {
            // 通过 SQL 重写来实现，将参数写入SQL。
            limitSql = "select * from (" + sql + ") " + LMT_TABLE_NAME + " limit " + limit;
            // 使用 MetaObject 工具类修改对象属性，重写要执行的SQL.
            metaObject.setValue("delegate.boundSql.sql", limitSql);
        }
        // 调用原来对象的方法，进入责任链的下一层级
        // 如果当前代理的是一个非代理对象，那么它就回调用真实拦截对象的方法，
        // 如果不是它会调度下个插件代理对象 invoke()方法。
        return invocation.proceed();

        /*
        // 取出即将要执行的SQL.
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");

        // 判断参数是不是MySQL数据库且SQL有没有被插件重写过.
        if ("mysql".equals(this.dbType) && !sql.contains(LMT_TABLE_NAME) && sql.contains("select")) {
            // 去掉前后空格
            sql = sql.trim();
            // 将参数写入SQL.
            limitSql = "select * from (" + sql + ") " + LMT_TABLE_NAME + " limit " + limit;
            //重写要执行的SQL.
            metaObject.setValue("delegate.boundSql.sql", limitSql);
        }
        // 调用原来对象的方法,进入责任链的下一层级.
        return invocation.proceed();
        */
    }

    /**
     * 生成对象的代理，这里常用 MyBatis 提供的 Plugin 类的 wrap 方法
     * @param target 被代理对象
     * @return 生成的代理对象
     */
    @Override
    public Object plugin(Object target) {
        // 使用MyBatis提供的 Plugin类 生成代理对象
        System.out.println("调用生成代理对象...");
        return Plugin.wrap(target, this);
    }

    /**
     * 获取插件配置的属性，我们在 MyBatis 的配置文件里面去配置
     * @param properties 是MyBatis配置的参数
     */
    @Override
    public void setProperties(Properties properties) {
        String strLimit = properties.getProperty("limit","3"); // 限制所有查询的SQL都只能返回 3 行记录。
        this.limit = Integer.parseInt(strLimit);
        this.dbType = properties.getProperty("dbtype");
    }
}
