package com.nc.entity;

import java.io.Serializable;

public class Pers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int perid;
	private String perDesc;
	private String url;
	private int isNav;
	private String perCode;
	public int getPerid() {
		return perid;
	}
	public void setPerid(int perid) {
		this.perid = perid;
	}
	public String getPerDesc() {
		return perDesc;
	}
	public void setPerDesc(String perDesc) {
		this.perDesc = perDesc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsNav() {
		return isNav;
	}
	public void setIsNav(int isNav) {
		this.isNav = isNav;
	}
	public String getPerCode() {
		return perCode;
	}
	public void setPerCode(String perCode) {
		this.perCode = perCode;
	}
	
}
