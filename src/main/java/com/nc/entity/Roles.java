package com.nc.entity;

import java.io.Serializable;

public class Roles implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roleid;
	private String roleName;
	private String roleDesc;
	private String roleCode;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}
