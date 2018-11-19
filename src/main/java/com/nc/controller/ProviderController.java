package com.nc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.google.gson.Gson;
import com.nc.entity.Categorys;
import com.nc.entity.Providers;
import com.nc.service.ProviderService;

@Controller
public class ProviderController {
	@Autowired
	ProviderService providerService;
	private int pageIndex =1;
	int row = 0;
	Gson gson = new Gson();
	
	@RequestMapping("/providerList")
	public String provider(){
		return "providerList";
	}
	
	@RequestMapping(value="/providerController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryProvider(@RequestParam int pageIndex){
		int pageSize = 2;
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Providers> list = providerService.queryAll();
		PageInfo pageInfo = new PageInfo<>(list);
		return pageInfo;
	}	
	
	@RequestMapping(value="/providerController/{type}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryProviders(@PathVariable String type){
		List<Providers> pvlist=providerService.queryAll();
		return pvlist;
	}
	
	@RequestMapping(value="/providerController",method=RequestMethod.POST)
	@ResponseBody
	public Object addProvider(@Valid Providers provider,BindingResult br) throws ClassNotFoundException{
		if (!br.hasErrors()) {
			row = providerService.addProvider(provider);
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
	@RequestMapping(value="/providerController",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateProvider(@Valid Providers provider,BindingResult br) throws ClassNotFoundException{
		if (!br.hasErrors()) {
			row = providerService.updateProvider(provider);
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
	
	@RequestMapping(value="/providerController/{providerID}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteProvider (@PathVariable int providerID){
		row = providerService.deleteProvider(providerID);
		if (row > 0) {
			return "success";
		}else{
			return "error";
		}
	}
}
