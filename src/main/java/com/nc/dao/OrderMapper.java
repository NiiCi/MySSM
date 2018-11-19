package com.nc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nc.entity.Orders;

/*@Repository("orderDao")*/
public interface OrderMapper {
	public int addOrder(Orders order);
	public int updateOrder(Orders order);
	public int deleteOrder(int orderID);
	public int getCount(Orders order);
	public List<Orders> queryAll(Orders order);
}
