package com.nc.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.nc.entity.Pers;
import com.nc.entity.Role_Pers;
import com.nc.entity.Roles;
import com.nc.service.PerService;
import com.nc.service.RoleService;

@Controller
public class RoleController {
	@Autowired
	RoleService roleSerivce;
	@Autowired
	PerService perService;
	int row = 0;
	@RequestMapping("/roleList")
	public Object roleList(){
		return "roleList";
	}

	@RequestMapping(value="getRole",method=RequestMethod.GET)
	@ResponseBody
	public Object getRole(){
		List<Roles> roleList = roleSerivce.queryRole();
		return roleList;
	}
	
	@RequestMapping(value="roleController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryRole(@RequestParam int pageIndex){
		System.out.println("pageIndex"+pageIndex);
		int pageSize = 3;
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Roles> roleList = roleSerivce.queryRole();
		PageInfo<Roles> pageInfo = new PageInfo<>(roleList, 3);
		return pageInfo;
	}
	@RequestMapping(value="roleController",method=RequestMethod.POST)
	@ResponseBody
	public Object addRole(@Valid Roles roles ,BindingResult br,HttpServletRequest requeset) throws ClassNotFoundException{
		if (!br.hasErrors()) {
			//添加角色
			row = roleSerivce.addRole(roles);
			int roleid = roleSerivce.queryRoleidByName(roles.getRoleName());
			String[] perid = requeset.getParameterValues("perid");
			if (perid.length != 0) {
				List<Role_Pers> role_perList = new ArrayList<>();
				for (String string : perid) {
					Role_Pers role_pers = new Role_Pers();
					role_pers.setRoleid(roleid);
					role_pers.setPerid(Integer.parseInt(string));;
					System.out.println("roleid: "+roles.getRoleid());
					System.out.println("perid:"+Integer.parseInt(string));
					role_perList.add(role_pers);
				}
				perService.addRolePers(role_perList);
				for (Role_Pers role_per : role_perList) {
					System.out.println(role_per.getRoleid());
				}
			}
			System.out.println("----影响行数: "+row);
			if (row > 0) {
				return "success";
			}
		}else{
			Class c = Class.forName("com.nc.entity.Roles");
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
	@RequestMapping(value="roleController/{updateFlag}",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateRole(@PathVariable String updateFlag,@Valid Roles roles,BindingResult br,HttpServletRequest request) throws ClassNotFoundException{
		System.out.println("updateFlag------: "+updateFlag);
		if (updateFlag.equals("updatePer")) {
				String[] perid = request.getParameterValues("perid"); 
				List<Role_Pers> role_perList = new ArrayList<>();
				for (String string : perid) {
					Role_Pers role_per = new Role_Pers();
					role_per.setRoleid(roles.getRoleid());
					role_per.setPerid(Integer.parseInt(string));
					System.out.println("roleid: "+roles.getRoleid());
					System.out.println("perid:"+Integer.parseInt(string));
					role_perList.add(role_per);
				}
				perService.deleteRolePers(roles.getRoleid());
				row = perService.addRolePers(role_perList);
				System.out.println("----影响行数: "+row);
				if (row > 0) {
					return "success";
				}
			}else if (updateFlag.equals("updateInfo")) {
				if (!br.hasErrors()) {
					row = roleSerivce.updateRole(roles);
					if (row > 0 ) {
						return "success";
					}
				}
			}else if(updateFlag.equals("updateAll")){
				if (!br.hasErrors()) {
					//调用修改用户信息方法
					row = roleSerivce.updateRole(roles);
					String[] perid = request.getParameterValues("perid"); 
					List<Role_Pers> role_perList = new ArrayList<>();
					for (String string : perid) {
						Role_Pers role_per = new Role_Pers();
						role_per.setRoleid(roles.getRoleid());
						role_per.setPerid(Integer.parseInt(string));
						System.out.println("roleid: "+roles.getRoleid());
						System.out.println("perid:"+Integer.parseInt(string));
						role_perList.add(role_per);
					}
					perService.deleteRolePers(roles.getRoleid());
					perService.addRolePers(role_perList);
					System.out.println("----影响行数: "+row);
					if (row > 0) {
						return "success";
					}
			}
		}else{
			Class c = Class.forName("com.nc.entity.Admin");
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
	@RequestMapping(value="roleController/{roleid}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteRole(@PathVariable int roleid){
		try {
			row = roleSerivce.deleteRole(roleid);
			perService.deleteRolePers(roleid);
			if (row > 0) {
				return "success";
			}
		} catch (Exception e) {
			return "error";
		}
		return null;
	}
	
	@RequestMapping(value="roleController/{roleid}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryPersByRoleid(@PathVariable int roleid){
		List<Pers> perList = perService.queryPersByRoleid(roleid);
		for (Pers pers : perList) {
			System.out.println(pers.getPerDesc());
		}
		return perList;
	}
}
