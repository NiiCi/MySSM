package com.nc.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nc.dao.CustomerMapper;
import com.nc.dao.EmployeeMapper;
import com.nc.entity.Employees;
import com.nc.util.MybatisUtil;
@Service
public class EmployeeService {
	/*SqlSession session = MybatisUtil.getSession();
	EmployeeDao mapper = MybatisUtil.getSession().getMapper(EmployeeDao.class);*/
/*	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeMapper mapper = (EmployeeMapper) ctx.getBean("employeeMapper");*/
	@Autowired
	EmployeeMapper employeeMapper;
	public List<Employees> queryEmp(){
		/*session.commit();*/
		return employeeMapper.queryEmp();
	}
	public int checkEmp(String name){
		return employeeMapper.checkEmp(name);
	}
}
