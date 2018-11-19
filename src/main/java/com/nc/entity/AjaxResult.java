package com.nc.entity;

import java.util.List;

public class AjaxResult {
	private int pageCount;
	private int pageIndex;
	private List<User> userList;
	private List<Admin> adminList;
	private List<Categorys> cgList;
	private List<Providers> pvList;
	private List<Products> productList;
/*	private List<Orders> orderList;
	private List<OrderDetail> odeList;*/
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Categorys> getCgList() {
		return cgList;
	}
	public void setCgList(List<Categorys> cgList) {
		this.cgList = cgList;
	}
	public List<Providers> getPvList() {
		return pvList;
	}
	public void setPvList(List<Providers> pvList) {
		this.pvList = pvList;
	}
	public List<Products> getProductList() {
		return productList;
	}
	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}
/*	public List<Orders> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	public List<OrderDetail> getOdeList() {
		return odeList;
	}
	public void setOdeList(List<OrderDetail> odeList) {
		this.odeList = odeList;
	}*/
	public List<Admin> getAdminList() {
		return adminList;
	}
	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}
}
