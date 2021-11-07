package com.learn.chapter09.useEnum;

/**
 * 《深入浅出 MyBatis 技术原理与实战》 9.7 在映射中使用枚举 颜色枚举类
 *  P247
 **/
public enum Color {
    RED(1,"红"),
    YELLOW(2,"黄"),
    BLUE(3,"蓝");

    Color(int code,String name){
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Color getEnumByCode(int code){
        for (Color color : Color.values()) {
            if (color.code == code){
                return color;
            }
        }
        return null;
    }
}
