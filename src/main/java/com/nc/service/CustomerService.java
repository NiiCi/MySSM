package com.nc.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nc.dao.CustomerMapper;
import com.nc.entity.Customers;
import com.nc.util.MybatisUtil;
@Service
public class CustomerService {
	/*SqlSession session = MybatisUtil.getSession();
	CustomerDao mapper = MybatisUtil.getSession().getMapper(CustomerDao.class);*/
	/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	CustomerMapper mapper = (CustomerMapper) ctx.getBean("customerMapper");*/
	@Autowired
	CustomerMapper customerMapper;
	public List<Customers> queryCus(){
		/*session.commit();*/
		return customerMapper.queryCus();
	}
	public int checkCus(String name){
		return customerMapper.checkCus(name);
	}
}
