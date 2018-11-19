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
import com.nc.entity.Providers;
import com.nc.service.ProviderService;



/**
 * Servlet implementation class ProviderServlet
 */
@WebServlet("/ProviderServlet")
public class ProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int pageIndex = 1;
    ProviderService pvService;
    Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProviderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		pvService = (ProviderService) web.getBean("providerService");
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
		/*String name = request.getParameter("name");*/
		String type = request.getParameter("type");
		PrintWriter out= response.getWriter();
			//查询所有供应商
			System.out.println(type);
			if(type!=null&&type.equals("getPd")){
				List<Providers> pvlist=pvService.queryAll();
				out.write(gson.toJson(pvlist));
			}
			else if (id==null){
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = 2;
			/*//获取总记录数
			int count = pvService.getCount();
			//计算总页数
			int pageCount = count%pageSize==0?count/pageSize:(count/pageSize+1);*/
			//根据当前页数查询数据
			PageHelper pageHelper = new PageHelper();
			pageHelper.startPage(pageIndex, pageSize);
			List<Providers> list = pvService.queryAll();
			PageInfo pageInfo = new PageInfo<>(list);
		/*	List<Providers> pvList = pvService.queryByPage(pageIndex, pageSize);
			AjaxResult pvrs = new AjaxResult();
			pvrs.setPageCount(pageCount);
			pvrs.setPageIndex(pageIndex);
			pvrs.setPvList(pvList);	*/
			
			//将 ar 数组转换成json字符串,并传到ajax中
			String json = gson.toJson(pageInfo);
			System.out.println(json);
			out.write(json);
			
			
		}/*else if(id==null && name!= null){
			boolean flag = pvService.queryByName(name);
			System.out.println(name);
			if (flag) {
				out.write("error");
			}else{
				out.write("success");
			}
		}*/else{
			int sid = Integer.parseInt(id);
			System.out.println(sid);
			int row = pvService.deleteProvider(sid);
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String tel  = request.getParameter("tel");
		String acc = request.getParameter("acc");
		String email = request.getParameter("email");
		
		System.out.println("id" + id);
		int row = 0;
		if (id.equals("")) {
			row = pvService.addProvider(new Providers(name,addr,tel,acc,email));
		}
		else{
			System.out.println("进入修改");
			int sid = Integer.parseInt(request.getParameter("id"));
			row = pvService.updateProvider(new Providers(sid,name,addr,tel,acc,email));
		}
		if (row > 0) {
			out.write("success");
		}else{
			out.write("error");
		}
	}

}
