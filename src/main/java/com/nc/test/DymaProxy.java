package com.nc.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class DymaProxy implements InvocationHandler{
	//真实对象
	Object obj;

	//获取代理对象
	public Object getProxy(Object obj){
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader()
				,obj.getClass().getInterfaces(), this);
	}
	//重写invoke对象
	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		System.out.println("动态代理对象执行的方法-----: "+method.getName()+" " + Arrays.toString(params));
		return method.invoke(obj, params);
	}

}

