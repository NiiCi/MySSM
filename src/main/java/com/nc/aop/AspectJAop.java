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
		System.out.println("-----AspectJ前置通知,日志显示-----"
		+joinPoint.getSignature().getName()+":"+Arrays.toString(joinPoint.getArgs()));
	}
	@After("execution(* Cal.del(..))")
	public void afterAdivce(JoinPoint joinPoint){
		System.out.println("-----AspectJ后置通知显示-----"
		+joinPoint.getSignature().getName()+":"+Arrays.toString(joinPoint.getArgs()));
	}
	@AfterThrowing(pointcut="execution(* Cal.*(..))",throwing="e")
	public void throwingAdivce(JoinPoint joinPoint,Throwable e){
		System.out.println("-----AspectJ异常通知显示-----"
		+joinPoint.getSignature().getName() +"方法     异常信息-----: " + e.getMessage());
	}
}
