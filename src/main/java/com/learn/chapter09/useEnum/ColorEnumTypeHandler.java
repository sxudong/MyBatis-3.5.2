package com.learn.chapter09.useEnum;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 标题：在映射中使用枚举类
 * 代码清单 9-46：颜色的 typeHandler p248
 **/
public class ColorEnumTypeHandler implements TypeHandler<Color> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Color color, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,color.getCode());
    }

    @Override
    public Color getResult(ResultSet rs, String columnName) throws SQLException {
        int result = rs.getInt(columnName);
        return Color.getEnumByCode(result);
    }

    @Override
    public Color getResult(ResultSet rs, int columnIndex) throws SQLException {
        int result = rs.getInt(columnIndex);
        return Color.getEnumByCode(result);
    }

    @Override
    public Color getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int result = cs.getInt(columnIndex);
        return Color.getEnumByCode(result);
    }
}
