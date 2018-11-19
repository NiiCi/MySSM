package com.nc.aop;

import org.springframework.stereotype.Repository;

@Repository("cal")
public class CalImp implements Cal {
	int result;
	@Override
	public int add(int num1, int num2){
		result = num1 + num2;
		System.out.println("计算结果-----:" + result);
		return result;
	}

	@Override
	public int del(int num1, int num2) {
		result = num1/num2;
		System.out.println("计算结果-----:" + result);
		return result;
	}

}
