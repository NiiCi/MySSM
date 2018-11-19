package com.nc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nc.entity.Admin;
import com.nc.entity.Pers;
import com.nc.entity.Roles;

/*@Repository("adminDao")*/
public interface AdminMapper {
	public Admin AdminLogin(@Param("name") String name,@Param("password")String password);
	public int addAdmin(Admin admin);
	public int addAdminList(Map maps);
	public Admin queryAdminByName(String name);
	public int updateAdmin(Admin admin);
	public int getCount();
	public List<Admin> queryByPage(@Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize);
	public int deleteAdmin(int id);
	public List<Admin> queryAll();
	public List<Roles> queryRolesByName(String name);
	public List<Pers> queryAllPers(String name);
}
