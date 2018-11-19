package com.nc.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component("orders")
public class Orders implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderID;
	private String order_date;
	private Customers cus;
	private Employees emp;
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public Customers getCus() {
		return cus;
	}
	public void setCus(Customers cus) {
		this.cus = cus;
	}
	public Employees getEmp() {
		return emp;
	}
	public void setEmp(Employees emp) {
		this.emp = emp;
	}
	public Orders() {
		super();
	}
	public Orders(String order_date, Customers cus, Employees emp) {
		super();
		this.order_date = order_date;
		this.cus = cus;
		this.emp = emp;
	}
	public Orders(int orderID, String order_date, Customers cus, Employees emp) {
		super();
		this.orderID = orderID;
		this.order_date = order_date;
		this.cus = cus;
		this.emp = emp;
	}
	
}
