package com.nc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nc.controller.LoginController;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("-------进入拦截器: "+request.getRequestURI());
		//获取请求相对路径
		String path = request.getRequestURI();
		//截取根路径
		String contextPath = request.getContextPath();
		System.out.println("contextPath-----: "+contextPath);
		//截取具体的请求路径
		String subPath = path.substring(path.indexOf("/", 1), path.length());
		
		System.out.println(subPath);
		boolean flag = subPath.equals("/relogin");
		System.out.println("路径判断-----: "+flag);
		HttpSession session = request.getSession();
		boolean loginFlag = session.getAttribute("isLogin")==null? false:true;
		System.out.println("是否已登录-----: "+loginFlag);
		
		if (loginFlag == true) {
			return true;
		}else{
			if (flag==false) {
				response.sendRedirect("relogin");
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			System.out.println("------postHandle------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
