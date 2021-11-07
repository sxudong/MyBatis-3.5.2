package com.learn.chapter09.pagePlugin;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 《深入浅出 MyBatis 技术原理与实战》9.5.2 插件分页 P229
 **/
//@Intercepts({
//        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
//})
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {

    private Integer defaultPage;      //默认页码
    private Integer defaultPageSize;  //默认每页条数
    private Boolean defaultUseFlag;   //默认是否启动插件
    private Boolean defaultCheckFlag; //默认是否检测当前页码的正确性

    //实现拦截逻辑
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取目标对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 获取元数据
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        // boolean isSelect = sql.trim().toLowerCase().startsWith("select");
        // 如果不是 select 查询语句，则直接进入下一个操作
        if (!checkSelect(sql)) {
            return invocation.proceed();
        }
        // 获取绑定的sql
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        // 获取参数对象
        Object parameterObject = boundSql.getParameterObject();
        /** 获取传入分页参数 */
        PageParams pageParams = this.getPageParams(parameterObject);
        if(pageParams == null){
          return invocation.proceed();
        }

        // 获取分页参数，获取不到则使用默认值
        Integer pageNum = pageParams.getPage() == null ? this.defaultPage : pageParams.getPage();
        Integer pageSize = pageParams.getPageSize() == null ? this.defaultPageSize : pageParams.getPageSize();
        Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
        Boolean checkFlag = pageParams.getCheckFlag() == null ? this.defaultCheckFlag : pageParams.getCheckFlag();

        // 不使用分页插件
        if(!useFlag){
           return invocation.proceed();
        }
        int total = getTotal(invocation, metaStatementHandler, boundSql);

        // 回填总数到分页数据里
        setTotalToPageParams(pageParams, total, pageSize);
        // 检查当前页码的有效性
        checkPage(checkFlag, pageNum, pageParams.getTotalPage());
        // 修改SQL
        return changeSQL(invocation, metaStatementHandler, boundSql, pageNum, pageSize);
    }

    private void setTotalToPageParams(PageParams pageParams, int total, Integer pageSize) {
        pageParams.setTotal(total);
        //计算总页数
        int totalPage = total % pageSize == 0 ? total/pageSize : total/pageSize + 1;
        pageParams.setTotalPage(totalPage);
    }

  /**
   * 检查当前页码的有效性
   * @param checkFlag
   * @param pageNum
   * @param pageTotal
   * @throws Throwable
   */
    private void checkPage(Boolean checkFlag, Integer pageNum, Integer pageTotal) throws Throwable{
        if(checkFlag){
            // 检查页码 page 是否合法
            if(pageNum > pageTotal){
                throw new Exception("查询失败，查询页码【" + pageNum + "】大于总页数【" + pageTotal + "】!!");
            }
        }
    }

  /**
   * 修改当前查询的SQL
   * @param invocation
   * @param metaStatementHandler
   * @param boundSql
   * @param page
   * @param pageSize
   * @return
   * @throws Exception
   */
    private Object changeSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, Integer page, Integer pageSize) throws Exception{
        // 获取当前需要执行的SQL
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        // 修改 SQL，这里使用的是 MySQL，如果是其他数据库则需要修改
        String newSql = "select * from (" + sql + ") page_table limit ?,?";
        // 修改当前需要执行的SQL
        metaStatementHandler.setValue ( "delegate.boundSql.sql", newSql);
        // 相当于条用 StatementHandler 的 prepare 方法，预编译了当前 SQL 并设置原有的参数，
        // 但是少了两个分页参数，它返回的是一个 PreparedStatement 对象。
        PreparedStatement ps = (PreparedStatement)invocation.proceed();
        // 计算sQL总参数个数
        int count = ps.getParameterMetaData().getParameterCount();
        // 设置两个分页参数
        ps.setInt (count -1, (page - 1) * pageSize);
        ps.setInt(count, pageSize) ;
        return ps;

    }


  /**
   * 判断是否 select 语句
   * @param sql
   * @return
   */
    private boolean checkSelect(String sql) {
      String trimSql = sql.trim();
      int idx = trimSql.toLowerCase().indexOf("select");
      return idx == 0 ;
    }

  /**
   * 获取总数
   * @param ivt
   * @param metaStatementHandler
   * @param boundSql sql
   * @return sql查询总数
   */
    private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql) throws Throwable {
        //获取当前的 mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        //配置对象
        Configuration cfg = mappedStatement.getConfiguration();
        //当前需要执行的SQL
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        // 改写为统计总数的sQL，这里是MySQL数据库，如果是其他的数据库，需要按数据库的SQL规范改写
        String countSql = "select count(*) as total from (" + sql + ") $_paging";
        // 获取拦截方法参数，我们知道是 Connection 对象
        Connection connection = (Connection) ivt.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0 ;
        try {
            // 预编译统计总数 SQL
            ps = connection.prepareStatement(countSql);
            //构建统计总数 BoundSql
            BoundSql countBoundSql = new BoundSql(cfg, countSql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());
            // 构建 MyBatis 的 ParameterHandler 用来设置总数 SQL 的参数
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            // 设置总数 SQL 参数
            handler.setParameters(ps) ;//执行查询
            ResultSet rs = ps.executeQuery ();
            while (rs.next ()) {
              total = rs.getInt("total");
            }
        } finally {
            //这里不能关闭connection，否则后续的sQL就没法继续了
            if (ps != null ) {
              ps.close();
            }
        }
        System.out.println("总条数：" + total);
        return total;
    }

  //返回被拦截对象的代理对象
    @Override
    public Object plugin(Object target) {
        System.out.println("开始包装目标对象");
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    //设置插件配置的参数
    @Override
    public void setProperties(Properties properties) {
      String strDefaultPage = properties.getProperty("default.page",  "1");
      String strDefaultPageSize = properties.getProperty("default.pageSize","10");
      String strDefaultUseFlag = properties.getProperty("default.useFlag","false");
      String strDefaultCheckFlag = properties.getProperty("default.checkFlag", "false");
      this.defaultPage = Integer.parseInt(strDefaultPage);
      this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
      this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
      this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
    }


    /**
     * 获取分页参数，这里支持 Map 和 @Param注解传递参数，或者 POJO 继承 PageParams，
     * 这三种方式都是允许的
     *
     * @param parameterObject  --sql 允许参数
     * @return 分页参数
     */
    private PageParams getPageParams(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        }

        // 支持 Map 参数和 MyBatis 的 @Param 注解参数
        if (parameterObject instanceof Map) {
            // 如果传入的参数是map类型的，则遍历map取出Page对象
            Map<String, Object> parameMap = (Map<String, Object>) parameterObject;
            Set<String> keySet = parameMap.keySet();
            for (String key : keySet) {
                Object value = parameMap.get(key);
                if (value instanceof PageParams) {
                    // 返回 PageParams对象，PageParams对象保存在传起来的 Map对象 的value当中。
                    return (PageParams) value;
                }
            }
        } else if (parameterObject instanceof PageParams) { //继承的方式
            // 如果传入的是 Page 类型，则直接返回该对象
            return (PageParams) parameterObject;
        }

        // 初步判断并没有传入MyPage类型的参数，返回null
        return null;
    }
}
