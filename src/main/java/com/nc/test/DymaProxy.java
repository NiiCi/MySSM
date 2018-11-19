package com.nc.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class DymaProxy implements InvocationHandler{
	//��ʵ����
	Object obj;

	//��ȡ�������
	public Object getProxy(Object obj){
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader()
				,obj.getClass().getInterfaces(), this);
	}
	//��дinvoke����
	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		System.out.println("��̬�������ִ�еķ���-----: "+method.getName()+" " + Arrays.toString(params));
		return method.invoke(obj, params);
	}

}

