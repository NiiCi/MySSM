package com.nc.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nc.dao.OrderMapper;
import com.nc.entity.Orders;
import com.nc.util.MybatisUtil;
@Service
public class OrderService {
	/*SqlSession session = MybatisUtil.getSession();
	OrderDao mapper = MybatisUtil.getSession().getMapper(OrderDao.class);*/
	/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	OrderMapper mapper = (OrderMapper) ctx.getBean("orderMapper");*/
	@Autowired
	OrderMapper orderMapper;
	public int addOrder(Orders order){
		return orderMapper.addOrder(order);
	}
	public int updateOrder(Orders order){
		return orderMapper.updateOrder(order);
	}
	public int deleteOrder(int orderID){
		return orderMapper.deleteOrder(orderID);
	}
	public int getCount(Orders order){
		return orderMapper.getCount(order);
	}
	public List<Orders> queryAll(Orders order){
		/*session.commit();*/
		return orderMapper.queryAll(order);
	}
}
