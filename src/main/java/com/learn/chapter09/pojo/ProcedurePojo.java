package com.learn.chapter09.pojo;

/**
 * 代码清单 9-11：存储过程参数POJO
 * 《深入浅出的MyBatis》第9章 实用的场景 P218
 */
public class ProcedurePojo {
    private String roleName = null;
    private int result = 0;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}