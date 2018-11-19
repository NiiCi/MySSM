package com.nc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nc.entity.Role_Admin;
import com.nc.entity.Roles;

public interface RoleMapper {
	public List<Roles> queryRole();
	public int queryRoleidByName(String roleName);
	public int addRole(Roles roles);
	public int updateRole(Roles roles);
	public int deleteRole(int roleid);
	public int addAdminRole(@Param("roleList") List<Role_Admin> role);
	
	//根据用户id 删除该用户角色
	public int deleteAdminRole(int admin_id);
}
