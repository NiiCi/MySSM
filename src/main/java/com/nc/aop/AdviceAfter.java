package com.nc.aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

public class AdviceAfter implements AfterReturningAdvice{
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("-----SpringAOP����֪ͨ��ʾ-----");
	}
}