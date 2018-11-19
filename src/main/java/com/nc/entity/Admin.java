package com.nc.entity;

import java.io.Serializable;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Component;
@Component("admin")
public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int admin_id;
	
	private String admin_name;
	@Size(max=255,min=6)
	private String admin_password;
	private String admin_date;
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getAdmin_date() {
		return admin_date;
	}
	public void setAdmin_date(String admin_date) {
		this.admin_date = admin_date;
	}
	public Admin() {
		super();
	}
	
	public Admin(String admin_name, String admin_password, String admin_date) {
		super();
		this.admin_name = admin_name;
		this.admin_password = admin_password;
		this.admin_date = admin_date;
	}
	public Admin(int admin_id, String admin_name, String admin_password, String admin_date) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_password = admin_password;
		this.admin_date = admin_date;
	}
}
