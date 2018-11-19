/*package com.nc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

*//**
 * Servlet Filter implementation class LoginFilter
 *//*
@WebFilter("/*")
public class LoginFilter implements Filter {
	    public LoginFilter() {
		     
	    }
		public void destroy() {
			System.out.println("过滤器销毁");
		}

		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			request.setCharacterEncoding("UTF-8");
			System.out.println("进入登录验证filter");
			//类型转换
			HttpServletRequest  httpRequest = (HttpServletRequest) request;
			HttpServletResponse  httpResponse = (HttpServletResponse) response;
			HttpSession session = httpRequest.getSession();
			
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			String remeberpwd = request.getParameter("remeberpwd");
			String autoLogin = request.getParameter("autoLogin");
			//获得请求路径
			String path = httpRequest.getRequestURI();
			String targetPath = path.substring(path.indexOf("/", 1),path.length());
			
			System.out.println(path);
			Object loginFlag = session.getAttribute("isLogin");
		
			boolean flag = path.equals("/login.jsp");
			boolean autoFlag = autoLogin.equals("on");
			System.out.println("是否记住密码------: " + remeberpwd);
			System.out.println("是否自动登录------: " + autoLogin);
			System.out.println("是否已登录-------: " + loginFlag);
			System.out.println("请求路径-----: " + path);
			System.out.println("截取后的请求路径-----: " + targetPath);
			System.out.println("请求路劲判断是否是登录页面 ----: " + flag);
			System.out.println("用户名-----: " + name);
			System.out.println("密码-----: " + password);
			if (flag) {
				chain.doFilter(request,response);
				return;
			}
			if (loginFlag != null && loginFlag.equals(true)){
				chain.doFilter(request,response);
			}else{
				httpResponse.sendRedirect("login.jsp");
			}
		}

		
		public void init(FilterConfig fConfig) throws ServletException {
			System.out.println("过滤器初始化");
		}

}*/
