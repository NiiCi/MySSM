package com.nc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nc.entity.Pers;
import com.nc.entity.Role_Pers;

public interface PerMapper {
	public List<Pers> queryPersByRoleid(int roleid);
	public List<Pers> queryPersAll();
	public int addPer(Pers pers);
	public int updatePer(Pers pers);
	public int deletePer(int perid);
	public int addRolePers(@Param("perList") List<Role_Pers> role_per);
	public int deleteRolePers(int roleid);
}
