package com.nc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.dao.RoleMapper;
import com.nc.entity.Role_Admin;
import com.nc.entity.Roles;
@Service
public class RoleService {
	@Autowired
	RoleMapper roleMapper;
	
	public List<Roles> queryRole(){
		return roleMapper.queryRole();
	}
	public int queryRoleidByName(String roleName){
		return roleMapper.queryRoleidByName(roleName);
	}
	public int addRole(Roles roles){
		return roleMapper.addRole(roles);
	}
	public int updateRole(Roles roles){
		return roleMapper.updateRole(roles);
	}
	public int deleteRole(int roleid){
		return roleMapper.deleteRole(roleid);
	}
	public int addAdminRole(List<Role_Admin> role){
		return roleMapper.addAdminRole(role);
	}
	public int deleteAdminRole(int admin_id){
		return roleMapper.deleteAdminRole(admin_id);
	}
}
