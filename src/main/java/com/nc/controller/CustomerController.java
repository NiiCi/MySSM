package com.nc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nc.entity.Customers;
import com.nc.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@RequestMapping(value="/customerController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryCus(){
		//查询所有雇员
			List<Customers> cusList=customerService.queryCus();
			for (Customers customers : cusList) {
				customers.getCustomer_name();
			}
			return cusList;
	}
	
	//校验顾客是否存在
	@RequestMapping(value="/customerController/{cusName}",method=RequestMethod.GET)
	@ResponseBody
	public Object checkCus(@PathVariable String cusName){
		int cusID = customerService.checkCus(cusName);
		System.out.println("cusID: " + cusID);
		if (cusID > 0) {
			return cusID;
		} else {
			return "error";
		}
	}
}
