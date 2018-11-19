package com.nc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.dao.NavMapper;
import com.nc.entity.Pers;
import com.nc.entity.Roles;
@Service
public class NavService {
	@Autowired
	NavMapper navMapper;
	public List<Roles> queryRolesByName(String admin_name){
		return navMapper.queryRolesByName(admin_name);
	}
	public List<Pers> queryPersByRoleid(int roleid,String admin_name){
		return navMapper.queryPersByRoleid(roleid,admin_name);
	}
	public List<Pers> queryAllPers(String admin_name){
		return navMapper.queryAllPers(admin_name);
	}
}
