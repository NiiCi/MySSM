package com.nc.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] params, Object proxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("----«∞÷√Õ®÷™≤‚ ‘----");
	}
	
}
