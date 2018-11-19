package com.nc.test;

import java.util.Scanner;

import org.junit.Test;

import com.nc.aop.Cal;
import com.nc.aop.CalImp;
import com.nc.dao.StudentMapper;
import com.nc.dao.StudentMapperImp;
import com.nc.entity.Students;

public class ProxyTest {
	@Test
	public void testProxy(){
	/*	Cal cal = new CalImp();
		DymaProxy dp = new DymaProxy();
		cal = (Cal) dp.getProxy(cal);
		System.out.print("�����-----:");
		Scanner input = new Scanner(System.in);
		int num1 = input.nextInt();
		System.out.print("�����-----:");
		int num2 = input.nextInt();
;		cal.add(num1,num2);*/
		Students stu = new Students("aa",99,22);
		StudentMapper mapper = new StudentMapperImp();
		DymaProxy dp = new DymaProxy();
		mapper = (StudentMapper) dp.getProxy(mapper);
		System.out.println(stu);
		System.out.print("���������-----:");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		System.out.print("���������-----:");
		int age = input.nextInt();
		System.out.print("������ɼ�-----:");
		double score = input.nextDouble();
		stu = mapper.updateStu(new Students(name,age,score));
		System.out.println(stu);
	}
}
	