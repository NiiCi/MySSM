package com.nc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AdviceAround implements MethodInterceptor{
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("-----SpringAOP����֪ͨ��ִ��ǰ��ʾ-----");
		Object result = invocation.proceed();
		System.out.println("-----SrpingAOP����֪ͨ��ִ�к���ʾ-----");
		return result;
	}
}
