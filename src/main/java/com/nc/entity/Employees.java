package com.nc.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component("employees")
public class Employees implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int empID;
	private String emp_name;
	private String hire_date;
	private Double salary;
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Employees() {
		super();
	}
	public Employees(int empID) {
		super();
		this.empID = empID;
	}
	public Employees(int empID, String emp_name) {
		super();
		this.empID = empID;
		this.emp_name = emp_name;
	}
	
}

