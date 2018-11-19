package com.nc.servlet;
/*package com.nc.controller;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.nc.service.AdminService;



*//**
 * Servlet implementation class LoginServlet
 *//*
@RequestMapping("/")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminService adminService;
    public LoginServlet() {
        super();
    }
	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		adminService = (AdminService) web.getBean("adminService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		// cookie��֧�����ģ�����Ҫ����һ�����ô洢��ʽ
		String utfName = URLEncoder.encode(name, "utf-8");
		String password = request.getParameter("password");
		String check = request.getParameter("remeberpwd");
		String autoLogin = request.getParameter("autoLogin");
		System.out.println("�û���-----: " +name);
		System.out.println("����-----: " +password);
		System.out.println("autoLogin----: "+autoLogin);
		System.out.println("check--------: "+check);
		HttpSession session = request.getSession();
		UserService userService = new UserService();
		List<User> userList = userService.queryUser();
		if (name != null && password != null) {
			if (adminService.AdminLogin(name, password)) {
				//��¼�ɹ���,���Խ��û���������洢��Cookie��
				Cookie nameCookie = new Cookie("nameCookie", utfName);
				Cookie pwdCookie = new Cookie("pwdCookie", password);
				//����ʱ��
				nameCookie.setMaxAge(60*60*24);
				pwdCookie.setMaxAge(60*60*24);
				//��ͻ�������Cookie
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
				
				if (check != null) {
					Cookie checkCookie = new Cookie("checkCookie", check);
					checkCookie.setMaxAge(60*60*24);
					response.addCookie(checkCookie);
				}
				if (autoLogin != null) {
					Cookie checkAuto = new Cookie("checkAuto",autoLogin);
					checkAuto.setMaxAge(60*60*24);
					response.addCookie(checkAuto);
				}
				// ���session��������
				session.setAttribute("name", name);
				session.setAttribute("isLogin", true);
				
				// ��¼�ɹ�,ת������ҳ
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				request.getSession().setAttribute("err", "�û������������");
				response.sendRedirect("login.jsp");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
	}
	public void destroy() {
		
	}

}*/
