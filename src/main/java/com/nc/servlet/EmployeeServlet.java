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
import com.nc.entity.Employees;
import com.nc.service.EmployeeService;


/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeService empService;
 
    public EmployeeServlet() {
        super();
        
    }


	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		empService = (EmployeeService) web.getBean("employeeService");
	}


	public void destroy() {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();
		Gson gson=new Gson();
		//查询所有雇员
		String type=request.getParameter("type");
		String empname = request.getParameter("empname");
		if(type!=null&&type.equals("getEmps")){
			List<Employees> emplist=empService.queryEmp();
			out.write(gson.toJson(emplist));
		}else{
			int empID = empService.checkEmp(empname);
			System.out.println("empID: "+empID);
			if (empID > 0) {
				out.write(empID+"");
			}else{
				out.write("error");
			}
		}
	}

}
