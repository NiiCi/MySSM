package com.nc.servlet;
/*package com.nc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.nc.entity.Admin;
import com.nc.service.AdminService;



*//**
 * Servlet implementation class AdminServlet
 *//*

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageIndex = 1;
	AdminService adminService;
	public AdminServlet() {
        super();
    }
	@Override
	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		adminService = (AdminService) web.getBean("adminService");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		PrintWriter out= response.getWriter();
		Gson gson = new Gson();
		if (id==null && name == null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = 2;
			//获取总记录数
			int count = adminService.getCount();
			//计算总页数
			int pageCount = count%pageSize==0?count/pageSize:(count/pageSize+1);
			//根据当前页数查询数据
			PageHelper pageHelper = new PageHelper();
			pageHelper.startPage(pageIndex, pageSize);
			List<Admin> adminList = adminService.queryByPage((pageIndex-1)*pageSize, pageSize);
			List<Admin> adminList = adminService.queryAll();
			PageInfo<Admin> pageInfo = new PageInfo<>(adminList,3);
			String json = gson.toJson(pageInfo);
			System.out.println(json);
			out.write(json);
			
			AjaxResult ar = new AjaxResult();
			ar.setPageCount(pageCount);
			ar.setPageIndex(pageIndex);
			ar.setAdminList(adminList);	
			
			//将 ar 数组转换成json字符串,并传到ajax中
			
			
		}else if(id==null && name!= null){
			List<Admin> list = adminService.queryAdminByName(name);
			System.out.println(name);
			if (list.size() > 0) {
				out.write("error");
			}else{
				out.write("success");
			}
		}else{
			int sid = Integer.parseInt(id);
			System.out.println(sid);
			int row = adminService.deleteAdmin(sid);
			System.out.println(row);
			if (row > 0) {
				out.print("success");
			}else{
				out.print("error");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String date = request.getParameter("date");
		int row = 0;
		if (id.equals("")) {
			row = adminService.addAdmin(new Admin(name,pwd,date));
		}else{
			int sid = Integer.parseInt(id);
			row = adminService.updateAdmin(new Admin(sid,name,pwd,date));
		}
		System.out.println(row);
		if (row > 0) {
			out.write("success");
		}else{
			out.write("error");
		}
	}

}*/
