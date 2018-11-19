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
	//国际化 更改本地语言显示
	@RequestMapping("/changeLanguage")
	public String changeLanguage(Locale locale){
		System.out.println("国家----: "+locale.getCountry()+" ,语言----: "+locale.getLanguage());
		return "login";
	}
	@RequestMapping("/")
	public String login(){
		System.out.println("-------进入登录页面");
//		return "index";
		return "login";
	}	
	@RequestMapping("/index")
	public String relogin(){
		System.out.println("进入------首页");
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
	
	//shiro 登陆认证
	@RequestMapping("/userLogin")
	
	public Object shiroLogin(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws IOException{
//		// 加载shiro.ini 文件
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		// 获取 SecurityManager 安全管理器对象
//		SecurityManager securityManager = factory.getInstance();
//		// 将SecurityManager实例绑定给SecurityUtils,这是一个全局设置，设置一次即可
//		SecurityUtils.setSecurityManager(securityManager);
		// 获取当前对象,会自动绑定到当前线程
		Subject currentUser = SecurityUtils.getSubject();
		// 创建session对象
		Session session = currentUser.getSession();
		String name = request.getParameter("admin_name");
		String password = request.getParameter("admin_password");
		String check = request.getParameter("remeberpwd");
		String autoLogin = request.getParameter("autoLogin");
		boolean flag = autoLogin == null? false:true;
		System.out.println("flag-----: "+flag);
		System.out.println("check-----: "+check);
		//如果用户没有被认证过
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(name,password);		
			System.out.println("name-----: "+name);
			System.out.println("password-----: "+password);
			// 自动登录
			token.setRememberMe(flag);
			try {
				// 调用login方法,进行身份验证,会自动委托给 SecurityManager.login 方法进行登录
				currentUser.login(token);
//				// cookie不支持中文，所以要进行一个设置存储格式
				String utfName = URLEncoder.encode(name, "utf-8");
				//登录成功后,可以将用户名和密码存储到Cookie中
				Cookie nameCookie = new Cookie("nameCookie", utfName);
				Cookie pwdCookie = new Cookie("pwdCookie", password);
				Cookie checkCookie = new Cookie("checkCookie", check);
				//设置时长
				nameCookie.setMaxAge(60*60*24);
				pwdCookie.setMaxAge(60*60*24);
				checkCookie.setMaxAge(60*60*24);
				//向客户端设置Cookie
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
				response.addCookie(checkCookie);
				session.setAttribute("name", name);
				//格式化日期时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(session.getStartTimestamp());
				session.setAttribute("accessTime", date);
				System.out.println("进入访问时间-----: "+date);
			} catch (AuthenticationException ae) {
				System.err.println(token.getUsername()+" :用户名或密码错误-----: ");
				session.setAttribute("err", "用户名或密码错误");
				return "login";
			}
		}
		//将获取到的导航信息,保存到map中,可以在前端的requestScope域中访问到
		map.put("navList", getNav(name));
		return "index";
	}
	
//	public Object login(HttpServletRequest request,HttpServletResponse response) throws IOException{
//
//		String name = request.getParameter("admin_name");
//		System.out.println("用户名-----: " +name);
//		// cookie不支持中文，所以要进行一个设置存储格式
//		String utfName = URLEncoder.encode(name, "utf-8");
//		String password = request.getParameter("admin_password");
//		String check = request.getParameter("remeberpwd");
//		String autoLogin = request.getParameter("autoLogin");
//		
//		System.out.println("密码-----: " +password);
//		System.out.println("autoLogin----: "+autoLogin);
//		System.out.println("check--------: "+check);
//		HttpSession session = request.getSession();
//		if (name != null && password != null) {
//			Admin admin = adminService.AdminLogin(name, password);
//			System.out.println(admin);
//			if (admin != null) {
//				//登录成功后,可以将用户名和密码存储到Cookie中
//				Cookie nameCookie = new Cookie("nameCookie", utfName);
//				Cookie pwdCookie = new Cookie("pwdCookie", password);
//				
				//设置时长
//				nameCookie.setMaxAge(60*60*24);
//				pwdCookie.setMaxAge(60*60*24);
				//向客户端设置Cookie
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
//				// 添加session到服务器
//				session.setAttribute("name", name);
//				session.setAttribute("isLogin", true);
//				
//				// 登录成功,转发到首页
//				/*request.getRequestDispatcher("index.jsp").forward(request, response);*/
//				return "index";
//			}else{
//				request.getSession().setAttribute("err", "用户名或密码错误");
//				/*response.sendRedirect("login.jsp");*/
//				return "redirect:relogin";
//			}
//		}else{
//			/*response.sendRedirect("login.jsp");*/
//			return "redirect:relogin";
//		}
//	}
}