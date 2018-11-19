package com.nc.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import com.nc.dao.CardMapper;
import com.nc.service.CardService;

public class AopTest {
/*	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			Cal cal = (Cal) ctx.getBean("throwAdviceProxy");
			System.out.println("计算结果-----: " + cal.del(2, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	@Test
	public void testBeforeAdvice(){
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Cal cal = (Cal) ctx.getBean("cal");
		cal.add(1, 1);
		ctx.close();
	}
	@Test
	public void testThrowAdvice(){
		ConfigurableApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Cal cal = (Cal) ctx.getBean("cal");
		cal.del(2, 0);
		ctx.close();
	}
	@Test
	public void testUpdateMoney(){
		try {
			ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			CardService cardService = (CardService) ctx.getBean("cardService");
			cardService.ioMoney(200, 1, 2);
			ctx.close();
		} catch (Exception e) {
			System.out.println("异常信息-----: "+ e.getMessage());
		}
	}	
}
