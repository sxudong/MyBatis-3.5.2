package com.learn.chapter08.dao.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 标题：表 t_role
 * 描述：表 t_role
 * 时间：2018/05/17
 **/
@Alias("role")
public class Role implements Serializable {

    private static final long serialVersionUID = -5650546738695963728L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String note;

    @Override
    public String toString() {
        return "RolePO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    /**
     * 获取 主键
     *
     * @return id 主键
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 角色名称
     *
     * @return roleName 角色名称
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 设置 角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取 备注
     *
     * @return note 备注
     */
    public String getNote() {
        return this.note;
    }

    /**
     * 设置 备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }
}
