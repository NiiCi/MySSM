package com.nc.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMember;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.junit.Test;

import com.nc.entity.Students;


public class StuReflect {
	@Test
	public void test(){
		Class students = Students.class;
		try {
			//javassist 获取反射对象
			/*ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.get(students.getName());
			CtMethod[] cm = cc.getDeclaredMethods();
			for (CtMethod m : cm) {
				//获取方法
				 MethodInfo methodInfo = m.getMethodInfo();
				
				 //获取方法参数名
				 CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
				
				 LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
				int pos = Modifier.isStatic(m.getModifiers())?0:1;
				 String[] names = new String[m.getParameterTypes().length];
				 CtClass[] parameterTypes = m.getParameterTypes();
				 for (int i = 0; i < names.length; i++) {
					names[i] = attr.variableName(i + 1);
					
					System.out.println(Modifier.toString(m.getModifiers())
							+" "+
							m.getReturnType().getName().replace("java.lang.", "")
							+" "+
							m.getName()
							+"("+
							parameterTypes[i]+
							names[i]
							+")");
				}
			}*/
			
			
			Class class1 = Class.forName("com.nc.entity.Students");
			Students stu = (Students) class1.newInstance();
			stu.setName("aa");
			stu.setAge(20);
			stu.setScore(99);
			//反射获取属性
			/*Field[] field = class1.getDeclaredFields();
			for (Field f : field) {
				System.out.println(Modifier.toString(f.getModifiers())
						+" "+
						f.getType().getName().replace("java.lang.", "")
						+" "+
						f.getName());
			}
			System.out.println("stu"+stu.toString());*/
			
			//反射获取方法
			Method method = class1.getDeclaredMethod("setName", String.class);
			
			/*for (Method m : method) {
				System.out.println(Modifier.toString(m.getModifiers())
						+" "+
						m.getReturnType().getName().replace("java.lang.", "")
						+" "+
						m.getName()
						+"("+
						Arrays.toString(m.getParameters()).replace("java.lang.", "").replace("[", "").replace("]", "")
						+")");
			}*/
			Object[] num= {"bb"};
			method.invoke(stu,num);
			System.out.println("stu"+stu.toString());
		} catch (Exception e) {
			System.out.println("异常信息-----: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
