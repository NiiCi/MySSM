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
			System.out.println("����������");
		}

		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			request.setCharacterEncoding("UTF-8");
			System.out.println("�����¼��֤filter");
			//����ת��
			HttpServletRequest  httpRequest = (HttpServletRequest) request;
			HttpServletResponse  httpResponse = (HttpServletResponse) response;
			HttpSession session = httpRequest.getSession();
			
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			String remeberpwd = request.getParameter("remeberpwd");
			String autoLogin = request.getParameter("autoLogin");
			//�������·��
			String path = httpRequest.getRequestURI();
			String targetPath = path.substring(path.indexOf("/", 1),path.length());
			
			System.out.println(path);
			Object loginFlag = session.getAttribute("isLogin");
		
			boolean flag = path.equals("/login.jsp");
			boolean autoFlag = autoLogin.equals("on");
			System.out.println("�Ƿ��ס����------: " + remeberpwd);
			System.out.println("�Ƿ��Զ���¼------: " + autoLogin);
			System.out.println("�Ƿ��ѵ�¼-------: " + loginFlag);
			System.out.println("����·��-----: " + path);
			System.out.println("��ȡ�������·��-----: " + targetPath);
			System.out.println("����·���ж��Ƿ��ǵ�¼ҳ�� ----: " + flag);
			System.out.println("�û���-----: " + name);
			System.out.println("����-----: " + password);
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
			System.out.println("��������ʼ��");
		}

}*/
