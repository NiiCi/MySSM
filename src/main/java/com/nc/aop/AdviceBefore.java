package com.nc.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/*@Component*/
/*@Aspect*/
public class AdviceBefore implements MethodBeforeAdvice{
	/*private Log log = LogFactory.getLog(getClass());*/
	/*@Before("execution(* Cal.add(..))")*/
/*	public void beforeAdivce(){
		System.out.println("-------ǰ��֪ͨ��ʾ-------");
	
	}
	@After("execution(* Cal.add(..))")
	public void afterAdvice(){
		System.out.println("-------����֪ͨ��ʾ-------");
	}*/
	@Override
	public void before(Method method, Object[] params, Object param) throws Throwable {
		System.out.println("-----SpringAopǰ��֪ͨ��ʾ-----");
	}
}
