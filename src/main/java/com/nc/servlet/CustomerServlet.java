package com.nc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.nc.entity.Customers;
import com.nc.service.CustomerService;


/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService cusService;
    public CustomerServlet() {
        super();
       
    }
	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		cusService = (CustomerService) web.getBean("customerService");
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();
		Gson gson=new Gson();
		String cusname = request.getParameter("cusname");
		System.out.println("cusname: "+cusname);
		//查询所有雇员
		String type=request.getParameter("type");
		if(type!=null&&type.equals("getCus")){
			List<Customers> cuslist=cusService.queryCus();
			out.write(gson.toJson(cuslist));
		}else{
			int cusID = cusService.checkCus(cusname);
			System.out.println("cusID: "+cusID);
			if (cusID > 0) {
				out.write(cusID+"");
			}else{
				out.write("error");
			}
		}
	}

}
