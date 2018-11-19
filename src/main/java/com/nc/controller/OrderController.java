package com.nc.controller;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nc.entity.Customers;
import com.nc.entity.Employees;
import com.nc.entity.Orders;
import com.nc.entity.Role_Admin;
import com.nc.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	int pageIndex = 1;
	int row = 0;
	@RequestMapping("/orderList")
	public Object orderList(){
		return "orderList";
	}
	@RequestMapping(value="orderController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryAll(HttpServletRequest request) {
		String id = request.getParameter("id");
		String cusID = request.getParameter("cusID");
		String empID = request.getParameter("empID");
		String date = request.getParameter("date");
		String newDate = null;
		System.out.println("cusID-----: " + cusID);
		System.out.println("empID-----: " + empID);
		System.out.println("date-----: " + date);
		System.out.println("第一次输出结束------ ");
		if (cusID == null || cusID.equals("请选择")) {
			cusID = String.valueOf(0);
		}
		if (empID == null || empID.equals("请选择")) {
			empID = String.valueOf(0);
		}
		if (date == null || date.equals("")) {
			date = null;
		} else {
			newDate = "%" + date.trim() + "%";
		}
		System.out.println("cusID-----: " + cusID);
		System.out.println("empID-----: " + empID);
		System.out.println("date-----: " + newDate);
		System.out.println("第二次输出结束-------");
		pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		System.out.println(pageIndex);
		Customers cus = new Customers(Integer.parseInt(cusID));
		Employees emp = new Employees(Integer.parseInt(empID));
		int pageSize = 3;
		// 根据当前页数查询数据
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Orders> list = orderService.queryAll(new Orders(newDate, cus, emp));
		PageInfo<Orders> pageInfo = new PageInfo<>(list, 3);
		return pageInfo;
	}
	
	@RequestMapping(value="/orderController",method=RequestMethod.POST)
	@ResponseBody
	public Object addOrder(HttpServletRequest request){
		String odID = request.getParameter("odID");
		String cusID = request.getParameter("cusID");
	/*	String cusName = request.getParameter("cusName");*/
		String odDate = request.getParameter("odDate");
		String empID = request.getParameter("empID");
	/*	String empName = request.getParameter("empName");*/
		if (cusID != null && empID != null) {
			int newCusID = Integer.parseInt(cusID);
			int newEmpID = Integer.parseInt(empID);
			Employees emp = new Employees(newEmpID);
			Customers cus = new Customers(newCusID);
			//添加订单
			row = orderService.addOrder(new Orders(odDate,cus,emp));
			System.out.println("----影响行数: "+row);
			if (row > 0) {
				return "success";
			}
		}
		return null;
	}
	
	@RequestMapping(value="/orderController",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateOrder(HttpServletRequest request){
		String odID = request.getParameter("odID");
		String cusID = request.getParameter("cusID");
	/*	String cusName = request.getParameter("cusName");*/
		String odDate = request.getParameter("odDate");
		String empID = request.getParameter("empID");
	/*	String empName = request.getParameter("empName");*/
		if (cusID != null && empID != null) {
			int newCusID = Integer.parseInt(cusID);
			int newEmpID = Integer.parseInt(empID);
			Employees emp = new Employees(newEmpID);
			Customers cus = new Customers(newCusID);
			//添加订单
			row = orderService.updateOrder(new Orders(Integer.parseInt(odID), odDate, cus, emp));
			System.out.println("----影响行数: "+row);
			if (row > 0) {
				return "success";
			}
		}
		return null;
	}
	@RequestMapping(value="/orderController/{orderID}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteOrder(@PathVariable int orderID){
		row = orderService.deleteOrder(orderID);
		if (row > 0) {
			return "success";
		}
		return null;
	}
	
}
