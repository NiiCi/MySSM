package com.nc.aop;

import org.apache.catalina.tribes.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class CheckAOP {
	@Before(" execution(* Cal.*(..)) || execution(* com.nc.dao.CardMapper.*(..))")
	public void checkNum(JoinPoint joinPoint) throws IllegalAccessException {
		System.out.println("-----AspectJǰ��֪ͨ,������֤��ʾ-----" + joinPoint.getSignature().getName() + ":"
				+ Arrays.toString(joinPoint.getArgs()));
		for (Object items : joinPoint.getArgs()) {
			double item = Double.parseDouble(items.toString());
			if ( item < 0 ) {
				throw new IllegalAccessException("�쳣��Ϣ-----: "+item+" ���ָ�ʽ�쳣");
			}else{
				System.out.println(item+" ������֤�ɹ�");
			}
		};
	}
	@AfterThrowing(pointcut="execution(* Cal.*(..)) || execution(* com.nc.dao.CardMapper.*(..))",throwing="e")
	public void numThrowingAdivce(JoinPoint joinPoint,Throwable e){
		for (Object items : joinPoint.getArgs()) {
			double item = Double.parseDouble(items.toString());
			if ( item < 0 ) {
				System.out.println(e.getMessage());
			}
		};
	}
}

