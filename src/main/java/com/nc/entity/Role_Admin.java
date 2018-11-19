package com.nc.entity;

import org.springframework.stereotype.Component;

@Component
public class Role_Admin {
	private int roleid;
	private int admin_id;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
}
