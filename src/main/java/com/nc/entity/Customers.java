package com.nc.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component("customers")
public class Customers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerID;
	private String customer_name;
	private String customer_add;
	private String customer_bir;
	private String customer_tel;
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_add() {
		return customer_add;
	}
	public void setCustomer_add(String customer_add) {
		this.customer_add = customer_add;
	}
	public String getCustomer_bir() {
		return customer_bir;
	}
	public void setCustomer_bir(String customer_bir) {
		this.customer_bir = customer_bir;
	}
	public String getCustomer_tel() {
		return customer_tel;
	}
	public void setCustomer_tel(String customer_tel) {
		this.customer_tel = customer_tel;
	}
	public Customers() {
		super();
	}
	public Customers(int customerID) {
		super();
		this.customerID = customerID;
	}
	public Customers(int customerID, String customer_name) {
		super();
		this.customerID = customerID;
		this.customer_name = customer_name;
	}
	
}
