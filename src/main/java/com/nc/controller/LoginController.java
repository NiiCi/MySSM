package com.nc.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mysql.fabric.Response;
import com.nc.entity.Admin;
import com.nc.entity.Nav;
import com.nc.entity.Pers;
import com.nc.entity.Roles;
import com.nc.service.AdminService;
import com.nc.service.NavService;

@Controller
@SessionAttributes("navList")
public class LoginController {
	@Autowired
	AdminService adminService;
	@Autowired
	NavService navService;
	//���ʻ� ���ı���������ʾ
	@RequestMapping("/changeLanguage")
	public String changeLanguage(Locale locale){
		System.out.println("����----: "+locale.getCountry()+" ,����----: "+locale.getLanguage());
		return "login";
	}
	@RequestMapping("/")
	public String login(){
		System.out.println("-------�����¼ҳ��");
//		return "index";
		return "login";
	}	
	@RequestMapping("/index")
	public String relogin(){
		System.out.println("����------��ҳ");
		return "index";
	}
	
	public Object getNav(@RequestParam String admin_name){
		List<Roles> roleList = navService.queryRolesByName(admin_name);
		List<Nav> navList = new ArrayList<>();
		for (Roles roles : roleList) {
			Nav nav = new Nav();
			nav.setRoleDesc(roles.getRoleDesc());
			nav.setRoleCode(roles.getRoleCode());
			System.out.println("-----"+roles.getRoleDesc());
			List<Pers> perList = navService.queryPersByRoleid(roles.getRoleid(),admin_name);
			for (Pers pers : perList) {
				System.out.println("-----"+pers.getPerDesc());
			}
			nav.setPers(perList);
			navList.add(nav);
		}
		return navList;
	}
	
	//shiro ��½��֤
	@RequestMapping("/userLogin")
	
	public Object shiroLogin(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws IOException{
//		// ����shiro.ini �ļ�
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		// ��ȡ SecurityManager ��ȫ����������
//		SecurityManager securityManager = factory.getInstance();
//		// ��SecurityManagerʵ���󶨸�SecurityUtils,����һ��ȫ�����ã�����һ�μ���
//		SecurityUtils.setSecurityManager(securityManager);
		// ��ȡ��ǰ����,���Զ��󶨵���ǰ�߳�
		Subject currentUser = SecurityUtils.getSubject();
		// ����session����
		Session session = currentUser.getSession();
		String name = request.getParameter("admin_name");
		String password = request.getParameter("admin_password");
		String check = request.getParameter("remeberpwd");
		String autoLogin = request.getParameter("autoLogin");
		boolean flag = autoLogin == null? false:true;
		System.out.println("flag-----: "+flag);
		System.out.println("check-----: "+check);
		//����û�û�б���֤��
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(name,password);		
			System.out.println("name-----: "+name);
			System.out.println("password-----: "+password);
			// �Զ���¼
			token.setRememberMe(flag);
			try {
				// ����login����,���������֤,���Զ�ί�и� SecurityManager.login �������е�¼
				currentUser.login(token);
//				// cookie��֧�����ģ�����Ҫ����һ�����ô洢��ʽ
				String utfName = URLEncoder.encode(name, "utf-8");
				//��¼�ɹ���,���Խ��û���������洢��Cookie��
				Cookie nameCookie = new Cookie("nameCookie", utfName);
				Cookie pwdCookie = new Cookie("pwdCookie", password);
				Cookie checkCookie = new Cookie("checkCookie", check);
				//����ʱ��
				nameCookie.setMaxAge(60*60*24);
				pwdCookie.setMaxAge(60*60*24);
				checkCookie.setMaxAge(60*60*24);
				//��ͻ�������Cookie
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
				response.addCookie(checkCookie);
				session.setAttribute("name", name);
				//��ʽ������ʱ��
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(session.getStartTimestamp());
				session.setAttribute("accessTime", date);
				System.out.println("�������ʱ��-----: "+date);
			} catch (AuthenticationException ae) {
				System.err.println(token.getUsername()+" :�û������������-----: ");
				session.setAttribute("err", "�û������������");
				return "login";
			}
		}
		//����ȡ���ĵ�����Ϣ,���浽map��,������ǰ�˵�requestScope���з��ʵ�
		map.put("navList", getNav(name));
		return "index";
	}
	
//	public Object login(HttpServletRequest request,HttpServletResponse response) throws IOException{
//
//		String name = request.getParameter("admin_name");
//		System.out.println("�û���-----: " +name);
//		// cookie��֧�����ģ�����Ҫ����һ�����ô洢��ʽ
//		String utfName = URLEncoder.encode(name, "utf-8");
//		String password = request.getParameter("admin_password");
//		String check = request.getParameter("remeberpwd");
//		String autoLogin = request.getParameter("autoLogin");
//		
//		System.out.println("����-----: " +password);
//		System.out.println("autoLogin----: "+autoLogin);
//		System.out.println("check--------: "+check);
//		HttpSession session = request.getSession();
//		if (name != null && password != null) {
//			Admin admin = adminService.AdminLogin(name, password);
//			System.out.println(admin);
//			if (admin != null) {
//				//��¼�ɹ���,���Խ��û���������洢��Cookie��
//				Cookie nameCookie = new Cookie("nameCookie", utfName);
//				Cookie pwdCookie = new Cookie("pwdCookie", password);
//				
				//����ʱ��
//				nameCookie.setMaxAge(60*60*24);
//				pwdCookie.setMaxAge(60*60*24);
				//��ͻ�������Cookie
//				response.addCookie(nameCookie);
//				response.addCookie(pwdCookie);
//				
//				if (check != null) {
//					Cookie checkCookie = new Cookie("checkCookie", check);
//					checkCookie.setMaxAge(60*60*24);
//					response.addCookie(checkCookie);
//				}
//				if (autoLogin != null) {
//					Cookie checkAuto = new Cookie("checkAuto",autoLogin);
//					checkAuto.setMaxAge(60*60*24);
//					response.addCookie(checkAuto);
//				}
//				// ���session��������
//				session.setAttribute("name", name);
//				session.setAttribute("isLogin", true);
//				
//				// ��¼�ɹ�,ת������ҳ
//				/*request.getRequestDispatcher("index.jsp").forward(request, response);*/
//				return "index";
//			}else{
//				request.getSession().setAttribute("err", "�û������������");
//				/*response.sendRedirect("login.jsp");*/
//				return "redirect:relogin";
//			}
//		}else{
//			/*response.sendRedirect("login.jsp");*/
//			return "redirect:relogin";
//		}
//	}
}