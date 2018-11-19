package com.nc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.nc.entity.Categorys;
import com.nc.entity.Products;
import com.nc.entity.Providers;
import com.nc.service.ProductService;
import com.nc.util.MybatisUtil;




@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageIndex = 1;
	ProductService pdService;
	Gson gson = new Gson();
   
    public ProductServlet() {
        super();
      
    }

	
	public void init() throws ServletException {
		WebApplicationContext web = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		pdService = (ProductService) web.getBean("productService");
	}

	
	public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pdname = request.getParameter("pdname");
		String cateID = request.getParameter("cateID");
		String gettype = request.getParameter("gettype");
		PrintWriter out= response.getWriter();
	
		if (id==null && pdname == null && cateID == null) {
			System.out.println("进入分页查询-----");
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = 2;
			//获取总记录数
		/*	int count = pdService.getCount();
			//计算总页数
			int pageCount = count%pageSize==0?count/pageSize:(count/pageSize+1);*/
			//根据当前页数查询数据
			PageHelper pageHelper = new PageHelper();
			pageHelper.startPage(pageIndex, pageSize);
			List<Products> list = pdService.queryAll();
			PageInfo pageInfo = new PageInfo<>(list);
			/*List<Products> pdList = pdService.queryUserByPageIndex(pageIndex, pageSize);
			AjaxResult pdrs = new AjaxResult();
			pdrs.setPageCount(pageCount);
			pdrs.setPageIndex(pageIndex);
			pdrs.setProductList(pdList);*/	
			//将 ar 数组转换成json字符串,并传到ajax中
			String json = gson.toJson(pageInfo);
			System.out.println(json);
			out.write(json);
		}else if(pdname!= null && gettype == null){
			List<Products> list = new ArrayList<>();
			list = pdService.getPdByName(pdname);
			String json = gson.toJson(list);
			out.write(json);
		}else if(id != null){
			int sid = Integer.parseInt(id);
			System.out.println(sid);
			int row = pdService.deleteProduct(sid);
			System.out.println(row);
			if (row > 0) {
				out.print("success");
			}else{
				out.print("error");
			}
		}else if(cateID != null){
			int cateId = Integer.parseInt(cateID);
			List<Products> list = new ArrayList<>();
			list = pdService.getPdByCgId(cateId);
			String json = gson.toJson(list);
			out.write(json);
			System.out.println("Json:  "+json);
		}
	/*	else {
			List<Products> list = new ArrayList<>();
			list = pdService.getPvByName(pdname);
			String json = gson.toJson(list);
			out.write(json);
		}*/
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String inprice = request.getParameter("inprice");
		String outprice = request.getParameter("outprice");
		String quan = request.getParameter("quan");
		String pv = request.getParameter("pv");
		String cate = request.getParameter("cate");
		String indate = request.getParameter("indate");
		Double inprices = 0.0;
		Double outprices = 0.0;
		int row = 0;
		int pvs = 0;
		int quans = 0;
		int cates = 0;
		if (inprice != null) {
			inprices = Double.parseDouble(inprice);
		}
		if (pv != null) {
			pvs = Integer.parseInt(pv);
		}
		if (outprice != null) {
			outprices = Double.parseDouble(outprice);
		}
		if (quan != null) {
			quans = Integer.parseInt(quan);
		}
		if (cate != null) {
			cates = Integer.parseInt(cate);
		}
		if (id.equals("") ){
			row = pdService.addProduct(new Products(name, inprices, new Providers(pvs),outprices,quans,new Categorys(cates), indate));
		}
		else{
			int sid = Integer.parseInt(request.getParameter("id"));
			row = pdService.updateProduct(new Products(sid, name, inprices, new Providers(pvs), outprices,quans,new Categorys(cates), indate));
		}
		if (row > 0) {
			out.write("success");
		}else{
			out.write("error");
		}
	}

}