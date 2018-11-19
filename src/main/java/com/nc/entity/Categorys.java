package com.nc.entity;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component("categorys")
public class Categorys implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int categoryID;
	@Size(max=6,min=2)
	private String category_name;
	@NotEmpty
	private String category_desc;
	
	private Products pd;
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	public Products getPd() {
		return pd;
	}
	public void setPd(Products pd) {
		this.pd = pd;
	}
	public Categorys() {
		super();
	}
	public Categorys(String category_name, String category_desc) {
		super();
		this.category_name = category_name;
		this.category_desc = category_desc;
	}
	public Categorys(int categoryID, String category_name, String category_desc) {
		super();
		this.categoryID = categoryID;
		this.category_name = category_name;
		this.category_desc = category_desc;
	}
	public Categorys(int categoryID) {
		super();
		this.categoryID = categoryID;
	}
	
	
	
	
}
