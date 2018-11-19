package com.nc.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nc.dao.ProviderMapper;
import com.nc.entity.Providers;
import com.nc.util.MybatisUtil;
@Service
public class ProviderService {
/*	SqlSession session = MybatisUtil.getSession();
	ProviderDao mapper = MybatisUtil.getSession().getMapper(ProviderDao.class);*/
	
/*	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	ProviderMapper mapper = (ProviderMapper) ctx.getBean("providerMapper");*/
	@Autowired
	ProviderMapper providerMapper;
	public int addProvider (Providers pv){
		return providerMapper.addProvider(pv);
	}
	public int updateProvider (Providers pv){
		return providerMapper.updateProvider(pv);
	}
	public int deleteProvider (int id){
		return providerMapper.deleteProvider(id);
	}
	/*public boolean queryProviderByName (String name);*/
	public boolean checkPvid(int id){
		return providerMapper.checkPvid(id);
	}
	public int getCount(){
		return providerMapper.getCount();
	}
	public List<Providers> queryAll(){
		
		return providerMapper.queryAll();
	}
	/*public List<Providers> queryByPage(int pageIndex,int pageSize);*/
	/*public List<Providers> queryProvider();*/
}
