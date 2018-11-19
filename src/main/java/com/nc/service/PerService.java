package com.nc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.dao.PerMapper;
import com.nc.entity.Pers;
import com.nc.entity.Role_Pers;
@Service
public class PerService {
	@Autowired
	PerMapper perMapper;
	public List<Pers> queryPersByRoleid(int roleid){
		return perMapper.queryPersByRoleid(roleid);
	}
	public List<Pers> queryPersAll(){
		return perMapper.queryPersAll();
	}
	public int addPer(Pers pers){
		return perMapper.addPer(pers);
	}
	public int updatePer(Pers pers){
		return perMapper.updatePer(pers);
	}
	public int deletePer(int perid){
		return perMapper.deletePer(perid);
	}
	public int addRolePers(List<Role_Pers> perList){
		return perMapper.addRolePers(perList);
	}
	public int deleteRolePers(int roleid){
		return perMapper.deleteRolePers(roleid);
	}
}
