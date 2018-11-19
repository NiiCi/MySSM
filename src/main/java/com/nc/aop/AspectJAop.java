package com.nc.aop;

import org.apache.catalina.tribes.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Order(1)
public class AspectJAop {	
	@Before("execution(* Cal.add(..))")
	public void beforeAdvice(JoinPoint joinPoint){
		System.out.println("-----AspectJǰ��֪ͨ,��־��ʾ-----"
		+joinPoint.getSignature().getName()+":"+Arrays.toString(joinPoint.getArgs()));
	}
	@After("execution(* Cal.del(..))")
	public void afterAdivce(JoinPoint joinPoint){
		System.out.println("-----AspectJ����֪ͨ��ʾ-----"
		+joinPoint.getSignature().getName()+":"+Arrays.toString(joinPoint.getArgs()));
	}
	@AfterThrowing(pointcut="execution(* Cal.*(..))",throwing="e")
	public void throwingAdivce(JoinPoint joinPoint,Throwable e){
		System.out.println("-----AspectJ�쳣֪ͨ��ʾ-----"
		+joinPoint.getSignature().getName() +"����     �쳣��Ϣ-----: " + e.getMessage());
	}
}
