package com.nc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nc.dao.CategoryMapper;
import com.nc.entity.Categorys;
@Service
public class CategoryService {
	/*SqlSession session = MybatisUtil.getSession();
	CategoryDao mapper = session.getMapper(CategoryDao.class);*/
/*	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	CategoryMapper mapper = (CategoryMapper) ctx.getBean("categoryMapper");*/
	@Autowired
	CategoryMapper categoryMapper;
	public int addCategory (Categorys cg){
		return categoryMapper.addCategory(cg);
	}
	public int updateCategory (Categorys cg){
		return categoryMapper.updateCategory(cg);
	}
	public int deleteCategory (int id){
		return categoryMapper.deleteCategory(id);
	}
	public Categorys queryCategoryById (int id){
		return categoryMapper.queryCategoryById(id);
	}
	public boolean checkCgid(int id){
		return categoryMapper.checkCgid(id);
	}
	
	/*public List<Categorys> queryByPage(int pageIndex,int pageSize){
		return mapper.quer
	}*/
	public boolean queryByName(String name){
		return categoryMapper.queryByName(name);
	}
	public int getCount(){
		return categoryMapper.getCount();
	}
	public List<Categorys> queryCates(){
		return categoryMapper.queryCates();
	}
	public List<Categorys> queryAll(){
		/*session.commit();*/
		return categoryMapper.queryAll();
	}

}
