package com.nc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nc.entity.Employees;
/*
@Repository("employeeDao")*/
public interface EmployeeMapper {
	public List<Employees> queryEmp ();

	public int checkEmp(String name);
}
