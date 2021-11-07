package com.learn.chapter03.typeHandler;

import com.learn.chapter03.enums.Sex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * 自定义枚举类 SexEnumTypeHandler
 */
//@MappedTypes(Sex.class) // 注解在这不起作用,不用也能正常运行
//@MappedJdbcTypes(JdbcType.VARCHAR) // 注解在这不起作用,不用也能正常运行
public class SexEnumTypeHandler implements TypeHandler<Sex> {

    private Logger log = Logger.getLogger("SexEnumTypeHandler.class");

    /**
     * 插入数据库的数据转换
     * 在 UserMapper.xml中定义sex字段插入时的数据转换
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, Sex sex, JdbcType jdbcType) throws SQLException {
        log.info("使用自定义 EnumTypeHandler 转换数据后 插入数据库");
        ps.setInt(i, sex.getId());   // 插入int类型
        //ps.setString(i, sex.name()); // 插入String类型 Enum.name()得到名称
        //ps.setString(i, sex.getName()); // 插入属性值（男/女）
    }

    /**
     * 返回的数据转换
     * ResultSet 列名获取字符串
     * 在 UserMapper.xml中resultMap定义返回时的数据 sex字段 的数据转换
     */
    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        log.info("使用自定义 EnumTypeHandler 处理返回数据");
        int id = rs.getInt(columnName);
        return Sex.getSex(id);

//        String name = rs.getString(columnName);
//        return Sex.getSex(name);

        // 源码方式
//        String name = rs.getString(columnName);
//        return name == null ? null : Enum.valueOf(Sex.class, name);
    }

    /**
     * ResultSet 下标获取字符串
     */
    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        log.info("使用自定义 EnumTypeHandler 处理返回数据");
        int id = rs.getInt(columnIndex);
        return Sex.getSex(id);
    }

    /**
     * CallableStatement 存储过程
     */
    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return Sex.getSex(id);
    }
}
