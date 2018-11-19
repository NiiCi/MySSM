package com.nc.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nc.entity.Employees;
import com.nc.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@RequestMapping(value="/employeeController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryEmp(){
		//查询所有雇员
		List<Employees> empList=employeeService.queryEmp();
		return empList;
	}
	//校验雇员是否存在
	@RequestMapping(value="/employeeController/{empName}",method=RequestMethod.GET)
	@ResponseBody
	public Object checkEmp(@PathVariable String empName){
		System.out.println(empName);	
		int empID = employeeService.checkEmp(empName);
		System.out.println("empID: " + empID);
		if (empID > 0) {
			return empID;
		} else {
			return "error";
		}
		
	}
}
