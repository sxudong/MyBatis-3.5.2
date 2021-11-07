package com.learn.chapter03.enums;

/**
 * 性别枚举
 */
public enum Sex {
    /**
     * 数据字典
     */
    MALE(1, "男"),
    /**
     * 数据字典
     */
    FEMALE(2, "女");

    private int id;
    private String name;

    Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * 数据处理
     * 根据ID返回相应的枚举名称
     */
    public static Sex getSex(int id) {
        if(id == 1) {
            return MALE;
        } else if (id == 2) {
            return FEMALE;
        }
        return null;
    }

    /**
     * 用于返回数据处理
     */
//    public static Sex getSex(String name) {
//        if(name.equals("MALE")) {
//            return MALE;
//        } else if (name.equals("FEMALE")) {
//            return FEMALE;
//        }
//        return null;
//    }
}

