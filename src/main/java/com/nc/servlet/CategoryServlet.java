//package com.nc.servlet;
//
//
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.google.gson.Gson;
//import com.nc.entity.Categorys;
//import com.nc.service.CategoryService;
//
//
//
///**
// * Servlet implementation class CategoryServlet
// */
//@WebServlet("/CategoryServlet")
//public class CategoryServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private int pageIndex = 1;
//	CategoryService categoryService;
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public CategoryServlet() {
//        super();
//    }
//
//	/**
//	 * @see Servlet#init(ServletConfig)
//	 */
//    @Override
//	public void init() throws ServletException {
//		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//		categoryService = (CategoryService) web.getBean("categoryService");
//	}
//
//	/**
//	 * @see Servlet#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		String type = request.getParameter("type");
//		PrintWriter out= response.getWriter();
//		Gson gson = new Gson();
//		//查询所有类别
//		System.out.println(type);
//		if(type!=null&&type.equals("getCate")){
//			CategoryService cateService=new CategoryService();
//			List<Categorys> cglist=cateService.queryCates();
//			out.write(gson.toJson(cglist));
//		}
//		else if (id==null && name == null) {
//			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
//			int pageSize = 2;
//	/*		//获取总记录数
//			int count = cateService.getCount();
//			//计算总页数
//			int pageCount = count%pageSize==0?count/pageSize:(count/pageSize+1);*/
//			//根据当前页数查询数据
//			/*List<Categorys> cgList = cateService.queryByPage(pageIndex, pageSize);
//			AjaxResult cgrs = new AjaxResult();
//			cgrs.setPageCount(pageCount);
//			cgrs.setPageIndex(pageIndex);
//			cgrs.setCgList(cgList);*/
//			PageHelper pageHelper = new PageHelper();
//			pageHelper.startPage(pageIndex, pageSize);
//			List<Categorys> list = categoryService.queryAll(); 
//			PageInfo<Categorys> pageInfo = new PageInfo<>(list,3);
//			//将 ar 数组转换成json字符串,并传到ajax中
//			String json = gson.toJson(pageInfo);
//			out.write(json);
//		
//			
//		}else if(id==null && name!= null){
//			boolean flag = categoryService.queryByName(name);
//			System.out.println(name);
//			if (flag) {
//				out.write("error");
//			}else{
//				out.write("success");
//			}
//		}else{
//			int sid = Integer.parseInt(id);
//			System.out.println("类别ID ------: " + sid);
//			int row = categoryService.deleteCategory(sid);
//			System.out.println("删除执行结果-----: " + row);
//			if (row > 0) {
//				out.print("success");
//			}else{
//				out.print("error");
//			}
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out= response.getWriter();
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		String desc = request.getParameter("desc");
//		int row = 0;
//	
//		if (id.equals("")) {
//			row = categoryService.addCategory(new Categorys(name,desc));
//		}else{
//			int sid = Integer.parseInt(request.getParameter("id"));
//			row = categoryService.updateCategory(new Categorys(sid,name,desc));
//		}
//		if (row > 0) {
//			out.write("success");
//		}else{
//			out.write("error");
//		}
//	}
//
//}
