package com.nc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.dao.OrderDetailMapper;
import com.nc.entity.OrderDetail;
@Service
public class OrderDetailService {
	@Autowired
	OrderDetailMapper orderDetailMapper;
	public int addOrderDetail(OrderDetail orderDetail){
		return orderDetailMapper.addOrderDetail(orderDetail);
	}
	public int updateOrderDetail(OrderDetail orderDetail){
		return orderDetailMapper.updateOrderDetail(orderDetail);
	}
	public int delOrderDetail(int id){
		return orderDetailMapper.delOrderDetail(id);
	}
	public List<OrderDetail> queryDetailByOrderID (int orderID){
		return orderDetailMapper.queryDetailByOrderID(orderID);
	}
}
