package com.nc.entity;

import java.io.Serializable;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Component("products")
public class Products implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productID;
	@Size(max=20,min=2)
	private String product_name;
	private double income_price;
	private Providers pv;
	private double sales_price;
	private int quantity;
	private Categorys cg;
	private String income_time;
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID){
		this.productID = productID;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getIncome_price() {
		return income_price;
	}
	public void setIncome_price(double income_price) {
		this.income_price = income_price;
	}
	public Providers getPv() {
		return pv;
	}
	public void setPv(Providers pv) {
		this.pv = pv;
	}
	public double getSales_price() {
		return sales_price;
	}
	public void setSales_price(double sales_price) {
		this.sales_price = sales_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Categorys getCg() {
		return cg;
	}
	public void setCg(Categorys cg) {
		this.cg = cg;
	}
	public String getIncome_time() {
		return income_time;
	}
	public void setIncome_time(String income_time) {
		this.income_time = income_time;
	}
	public Products() {
		super();
	}
	public Products(String product_name, double income_price, Providers pv,double sales_price,int quantity,
			Categorys cg, String income_time) {
		super();
		this.product_name = product_name;
		this.income_price = income_price;
		this.pv = pv;
		this.sales_price = sales_price;
		this.quantity = quantity;
		this.cg = cg;
		this.income_time = income_time;
	}
	public Products(int productID, String product_name, double income_price, Providers pv, double sales_price,
			int quantity,Categorys cg, String income_time) {
		super();
		this.productID = productID;
		this.product_name = product_name;
		this.income_price = income_price;
		this.pv = pv;
		this.sales_price = sales_price;
		this.quantity = quantity;
		this.cg = cg;
		this.income_time = income_time;
	}
	

}
