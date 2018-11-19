package com.nc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AdviceAround implements MethodInterceptor{
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("-----SpringAOP环绕通知在执行前显示-----");
		Object result = invocation.proceed();
		System.out.println("-----SrpingAOP环绕通知在执行后显示-----");
		return result;
	}
}
