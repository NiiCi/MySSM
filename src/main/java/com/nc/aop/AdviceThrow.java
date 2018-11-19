package com.nc.aop;

import org.springframework.aop.ThrowsAdvice;

public class AdviceThrow implements ThrowsAdvice{
	public void afterThrowing(Exception e) throws Throwable {
		System.out.println("“Ï≥£–≈œ¢-----: " + e.getMessage());
	}
}
