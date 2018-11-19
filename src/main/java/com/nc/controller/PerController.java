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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nc.entity.Pers;
import com.nc.service.PerService;

@Controller
public class PerController {
	@Autowired
	PerService perService;
	int row = 0;
	
	@RequestMapping("/perList")
	public Object perList(){
		return "perList";
	}
	
	@RequestMapping(value="/perController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryPersAll(){
		List<Pers> perList = perService.queryPersAll();
		return perList;
	}
	

	@RequestMapping(value="/perController/{pageIndex}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryPers(@PathVariable int pageIndex){
		int pageSize = 3;
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Pers> perList = perService.queryPersAll();
		PageInfo<Pers> pageInfo = new PageInfo<>(perList, 3);
		return pageInfo;
	}
	
	@RequestMapping(value="/perController",method=RequestMethod.POST)
	@ResponseBody
	public Object addPer(@Valid Pers pers,BindingResult br) throws ClassNotFoundException{
		//�󶨽�����ж�û���쳣
		if (!br.hasErrors()) {
			//���Ȩ��
			System.out.println("isNav------: "+pers.getIsNav());
			row = perService.addPer(pers);
			System.out.println("----Ӱ������: "+row);
			if (row > 0) {
				return "success";
			}
		}else{
			//�󶨽�������쳣,ͨ�����佫�쳣��Ϣ�Ľ��ͨ��Ajax���ص�ǰ��
			Class c = Class.forName("com.nc.entity.Pers");
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
	
	@RequestMapping(value="/perController",method=RequestMethod.PUT)
	@ResponseBody
	public Object updatePer(@Valid Pers pers,BindingResult br) throws ClassNotFoundException{
		//�󶨽�����ж�û���쳣
		if (!br.hasErrors()) {
			// �޸�Ȩ��
			row = perService.updatePer(pers);
			System.out.println("----Ӱ������: " + row);
			if (row > 0) {
				return "success";
			}
		} else {
			// �󶨽�������쳣,ͨ�����佫�쳣��Ϣ�Ľ��ͨ��Ajax���ص�ǰ��
			Class c = Class.forName("com.nc.entity.Pers");
			Field[] field = c.getDeclaredFields();
			List<String> msg = new ArrayList<>();
			for (Field f : field) {
				System.out.println(f.getName());
				if (br.getFieldError(f.getName()) == null) {
					msg.add(" ");
				} else {
					msg.add(br.getFieldError(f.getName()).getDefaultMessage());
				}
			}
			return msg;
		}
		return null;
		
	}
	
	@RequestMapping(value="/perController/{perid}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deletePer(@PathVariable int perid){
		try {
			row = perService.deletePer(perid);
			if (row > 0) {
				return "success";
			}
		} catch (Exception e) {
			return "error";
		}
		return null;
	}
}
