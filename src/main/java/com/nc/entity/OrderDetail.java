package com.nc.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int orderID;
	private Products pd;
	private Categorys cg;
	private Providers pv;
	private int quantity;
	private double discount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Products getPd() {
		return pd;
	}
	public void setPd(Products pd) {
		this.pd = pd;
	}
	public Categorys getCg() {
		return cg;
	}
	public void setCg(Categorys cg) {
		this.cg = cg;
	}
	public Providers getPv() {
		return pv;
	}
	public void setPv(Providers pv) {
		this.pv = pv;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(int id, int quantity, double discount) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public OrderDetail(int orderID, Products pd, int quantity, double discount) {
		super();
		this.orderID = orderID;
		this.pd = pd;
		this.quantity = quantity;
		this.discount = discount;
	}
	public OrderDetail(int id, int orderID, Products pd, Categorys cg, Providers pv, int quantity, double discount) {
		super();
		this.id = id;
		this.orderID = orderID;
		this.pd = pd;
		this.cg = cg;
		this.pv = pv;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	
}