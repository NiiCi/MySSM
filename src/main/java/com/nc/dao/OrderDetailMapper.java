package com.nc.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import com.nc.entity.OrderDetail;

@Component
public interface OrderDetailMapper {
	public int addOrderDetail(OrderDetail orderDetail);
	public int updateOrderDetail(OrderDetail orderDetail);
	public int delOrderDetail(int id);
	public int getCount(String orderID);
	public List<OrderDetail> queryDetailByOrderID (int orderID);
}
