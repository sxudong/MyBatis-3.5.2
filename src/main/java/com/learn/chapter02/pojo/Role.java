package com.learn.chapter02.pojo;

import java.io.Serializable;

/**
 * 普通的 JavaBean
 * Plain Ordinary Java Object，简单的Java对象，简称 POJO。
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String roleName;
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", note=" + note + "]";
	}

}
