package com.nc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.nc.entity.Customers;
import com.nc.entity.Employees;
import com.nc.entity.Orders;
import com.nc.service.OrderService;

/**
 * Servlet implementation class OrderServlet
 */

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageIndex = 1;
    OrderService orderService;
	
	Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		orderService = (OrderService) web.getBean("orderService");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String cusID = request.getParameter("cusID");
		String empID = request.getParameter("empID");
		String date = request.getParameter("date");
		System.out.println("cusID-----: " + cusID);
		System.out.println("empID-----: " + empID);
		System.out.println("date-----: " + date);
		System.out.println("第一次输出结束------ ");
		if (cusID == null || cusID.equals("请选择")){
			cusID = String.valueOf(0);
		}
		if(empID == null || empID.equals("请选择")){
			empID = String.valueOf(0);
		}
		if( date == null || date.equals("")){
			date = null;
		}
		System.out.println("cusID-----: " + cusID);
		System.out.println("empID-----: " + empID);
		System.out.println("date-----: " + date);
		System.out.println("第二次输出结束-------");
		/*String name = request.getParameter("name");*/
		PrintWriter out= response.getWriter();
		if (id==null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			System.out.println(pageIndex);
			Customers cus = new Customers(Integer.parseInt(cusID));
			Employees emp = new Employees(Integer.parseInt(empID));
			int pageSize = 2;
	/*		//获取总记录数
			int count = orderService.getCount(new Orders(date, cus, emp));
			//计算总页数
			int pageCount = count%pageSize==0?count/pageSize:(count/pageSize+1);*/
			//根据当前页数查询数据
			PageHelper pageHelper = new PageHelper();
			pageHelper.startPage(pageIndex, pageSize);
			List<Orders> list = orderService.queryAll(new Orders(date, cus, emp));
			PageInfo<Orders> pageInfo = new PageInfo<>(list);
			/*List<Orders> odList = orderService.queryByPage(pageIndex, pageSize,cusName);
			AjaxResult od = new AjaxResult();
			od.setPageCount(pageCount);
			od.setPageIndex(pageIndex);
			od.setOrderList(odList);*/
			
			//将 ar 数组转换成json字符串,并传到ajax中
			String json = gson.toJson(pageInfo);
			out.write(json);
			System.out.println(json);
			
		/*}else if(id==null && name!= null){
			boolean flag = orderService.queryUserByName(name);
			System.out.println(name);
			if (flag) {
				out.write("error");
			}else{
				out.write("success");
			}*/
		}else{
			int sid = Integer.parseInt(id);
			/*System.out.println(sid);*/
			int row = orderService.deleteOrder(sid);
			System.out.println(row);
			if (row > 0) {
				out.print("success");
			}else{
				out.print("error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		String id = request.getParameter("pdid");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		int intsex = Integer.parseInt(sex);
		int row = 0;
		System.out.println(sex);
		if (id.equals("")) {
			row = userService.addUser(new User(name,pwd,intsex));
		}else{
			int sid = Integer.parseInt(request.getParameter("id"));
			row = userService.updateUser(new User(sid,name,pwd,intsex));
		}
		if (row > 0) {
			out.write("success");
		}else{
			out.write("error");
		}*/
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		String odID = request.getParameter("odID");
		String cusID = request.getParameter("cusID");
		String cusName = request.getParameter("cusName");
		String odDate = request.getParameter("odDate");
		String empID = request.getParameter("empID");
		String empName = request.getParameter("empName");
		System.out.println("odID:"+odID);
		int row = 0;
		if (cusID != null && empID != null) {
			int newCusID = Integer.parseInt(cusID);
			int newEmpID = Integer.parseInt(empID);
			if (odID == null) {
				Employees emp = new Employees(newEmpID);
				Customers cus = new Customers(newCusID);
				row = orderService.addOrder(new Orders(odDate,cus,emp));
			}else{
				Employees emp = new Employees(newEmpID,empName);
				Customers cus = new Customers(newCusID,cusName);
				row = orderService.updateOrder(new Orders(Integer.parseInt(odID), odDate, cus, emp));
			}
		}
		if (row > 0) {
			out.write("success");
		}else{
			out.write("error");
		}
		
	
	}

}
