package com.nc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nc.entity.Customers;

/*@Repository("customerDao")*/
public interface CustomerMapper {
	public List<Customers> queryCus();
	public int checkCus(String name);
}
