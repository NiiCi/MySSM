package com.nc.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/*@Component*/
/*@Aspect*/
public class AdviceBefore implements MethodBeforeAdvice{
	/*private Log log = LogFactory.getLog(getClass());*/
	/*@Before("execution(* Cal.add(..))")*/
/*	public void beforeAdivce(){
		System.out.println("-------前置通知显示-------");
	
	}
	@After("execution(* Cal.add(..))")
	public void afterAdvice(){
		System.out.println("-------后置通知显示-------");
	}*/
	@Override
	public void before(Method method, Object[] params, Object param) throws Throwable {
		System.out.println("-----SpringAop前置通知显示-----");
	}
}
