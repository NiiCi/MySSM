package com.nc.controller;


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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.nc.entity.Categorys;
import com.nc.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	int row = 0;
	@RequestMapping("/categoryList")
	public String category(){
		return "categoryList";
	}
	//查询所有类别
	@RequestMapping(value="/categoryController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryAll(@RequestParam int pageIndex){
		int pageSize = 2;
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Categorys> list = categoryService.queryAll(); 
		PageInfo<Categorys> pageInfo = new PageInfo<>(list,3);
		return pageInfo;
	}
		
	@RequestMapping(value="/categoryController/{type}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryCates(@PathVariable String type){
		List<Categorys> cglist=categoryService.queryCates();
		return cglist;
	}
	
//	@RequestMapping("/queryByName/{category_name}")
//	public String queryByName(@RequestParam String category_name){}
//		boolean flag = categoryService.queryByName(category_name);
//		System.out.println(category_name);
//		if (flag) {
//			return "error";
//		}else{
//			return "success";
//		}
//	}

	@RequestMapping(value="/categoryController/{categoryID}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteCategory(@PathVariable int categoryID){
		System.out.println("类别ID ------: " + categoryID);
		int row = categoryService.deleteCategory(categoryID);
		System.out.println("删除执行结果-----: " + row);
		if (row>0) {
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value="/categoryController",method=RequestMethod.POST)
	@ResponseBody
	public Object addCategory(@Valid Categorys categorys,BindingResult br) throws ClassNotFoundException{
		if (!br.hasErrors()) {
			row = categoryService.addCategory(categorys);
			System.out.println("----影响行数: "+row);
			if (row > 0) {
				return "success";
			}
		}else{
			Class c = Class.forName("com.nc.entity.Categorys");
			Field[] field = c.getDeclaredFields();
			List<String> msg = new ArrayList<>();
			for (Field f : field) {
				System.out.println(f.getName());
				if (br.getFieldError(f.getName()) == null) {
					msg.add(" ");
				}else{
					msg.add(br.getFieldError(f.getName()).getDefaultMessage());
				}
			}
			return msg;
		}
		return null;
	}
		
	
	@RequestMapping(value="/categoryController",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateCategory(@Valid Categorys categorys,BindingResult br) throws ClassNotFoundException{
		if (!br.hasErrors()) {
			row = categoryService.updateCategory(categorys);
			if (row > 0) {
				return "success";
			}
		}else{
			Class c = Class.forName("com.nc.entity.Categorys");
			Field[] field = c.getDeclaredFields();
			List<String> msg = new ArrayList<>();
			for (Field f : field) {
				System.out.println(f.getName());
				if (br.getFieldError(f.getName()) == null) {
					msg.add(" ");
				}else{
					msg.add(br.getFieldError(f.getName()).getDefaultMessage());
				}
			}
			return msg;
		}
		return null;
	}
}
