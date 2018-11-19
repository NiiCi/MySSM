package com.nc.util;

import java.io.InputStream;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nc.dao.AdminMapper;



public class MybatisUtil {
	public static SqlSession getSession(){
			try {
				String resource = "mybatis-config.xml";
				InputStream inputStream = Resources.getResourceAsStream(resource);
				//2. build SqlSessionFactory	
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				//3. a SqlSession from SqlSessionFactory
				SqlSession session = sqlSessionFactory.openSession(true);
				return session;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}