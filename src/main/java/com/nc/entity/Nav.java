package com.nc.entity;

import java.util.List;

public class Nav {
	private String roleDesc;
	private String roleCode;
	private List<Pers> pers;
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
	public List<Pers> getPers() {
		return pers;
	}
	public void setPers(List<Pers> pers) {
		this.pers = pers;
	}
	
}
