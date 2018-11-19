package com.nc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nc.entity.Pers;
import com.nc.entity.Roles;

public interface NavMapper {
	public List<Roles> queryRolesByName(String admin_name);
	public List<Pers> queryPersByRoleid(@Param("roleid") int roleid,@Param("admin_name") String admin_name);
	public List<Pers> queryAllPers(String admin_name);
}
