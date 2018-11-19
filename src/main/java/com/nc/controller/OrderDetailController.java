package com.nc.controller;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nc.entity.OrderDetail;
import com.nc.service.OrderDetailService;

@Controller
public class OrderDetailController {
	@Autowired
	OrderDetailService orderDetailService;
	
	int row = 0;

	@RequestMapping(value="/orderDetailController",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateOrderDetail(@Valid OrderDetail orderDetail,BindingResult br){
		int id = orderDetail.getId();
		int quantity = orderDetail.getQuantity();
		double discount = orderDetail.getDiscount();
		System.out.println("id-----: "+id);
		System.out.println("quantity-----: "+quantity);
		System.out.println("discount-----: "+discount);
		row = orderDetailService.updateOrderDetail(orderDetail);
		if (row > 0) {
			return "success";
		}
		return null;
	}
	
	@RequestMapping(value="/orderDetailController",method=RequestMethod.POST)
	@ResponseBody
	public Object addOrderDetail(@Valid OrderDetail orderDetail,BindingResult br){
		System.out.println("_________: "+orderDetail.getPd().getProductID());
		try {
			if (!br.hasErrors()) {
				row = orderDetailService.addOrderDetail(orderDetail);
				System.out.println("----影响行数: " + row);
				if (row > 0) {
					return "success";
				}
			} else {
				Class c = Class.forName("com.nc.entity.OrderDetail");
				Field[] field = c.getDeclaredFields();
				List<String> msg = new ArrayList<>();
				for (Field f : field) {
					System.out.println(f.getName());
					if (br.getFieldError(f.getName()) == null) {
						msg.add(" ");
					} else {
						msg.add(br.getFieldError(f.getName()).getDefaultMessage());
					}
				}
				return msg;
			} 
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@RequestMapping(value="/orderDetailController/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object delOrderDetail(@PathVariable int id){
		System.out.println("所要删除的id-----: "+id);
		row = orderDetailService.delOrderDetail(id);
		if (row > 0) {
			return "success";
		}
		return null;
	}
	
	@RequestMapping(value="/orderDetailController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryDetailByOrderID (@RequestParam int orderID){
		List<OrderDetail> odeList = orderDetailService.queryDetailByOrderID(orderID);
		return odeList;
	}
}
