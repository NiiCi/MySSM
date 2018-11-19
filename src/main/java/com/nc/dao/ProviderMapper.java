package com.nc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nc.entity.Providers;


/*@Repository("providerDao")*/
public interface ProviderMapper {
	
	public int addProvider (Providers pv);
	public int updateProvider (Providers pv);
	public int deleteProvider (int id);
	/*public boolean queryProviderByName (String name);*/
	public boolean checkPvid(int id);
	public int getCount();
	public List<Providers> queryAll();
	public List<Providers> queryByPage(int pageIndex,int pageSize);
	public List<Providers> queryProvider();
}
