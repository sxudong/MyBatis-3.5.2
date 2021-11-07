package com.learn.chapter09.mapper;

import com.learn.chapter09.pojo.ColorBean;
import org.apache.ibatis.annotations.Param;

/**
 * 《深入浅出 MyBatis 技术原理与实战》 9.7 在映射中使用枚举 颜色枚举类
 *  P247
 **/
public interface ColorMapper {
    public ColorBean getColor(@Param("id") Integer id);
}
