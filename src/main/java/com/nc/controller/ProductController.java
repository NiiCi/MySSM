package com.nc.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.Binder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nc.entity.Products;
import com.nc.entity.Providers;
import com.nc.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	int row = 0;
	@RequestMapping("/productList")
	public String product(){
		return "productList";
	}
	
	@RequestMapping(value="/productController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryProducts(@RequestParam int pageIndex){
		int pageSize = 2;
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Products> pdList = productService.queryAll();
		PageInfo<Products> pageInfo = new PageInfo<>(pdList,2); 
		return pageInfo;
	}
	
	@RequestMapping(value="/productController/{getFlag}",method=RequestMethod.GET)
	@ResponseBody
	public Object getPdByName(@RequestParam String product_name,@PathVariable String getFlag){
		
		System.out.println("product_name: " + product_name);
		System.out.println("getFlag: "+getFlag);
		if (getFlag.equals("getPdByName")) {
			List<Products> pdList = productService.getPdByName("%"+product_name+"%");
			return pdList;
		}else{
			System.out.println("++++++++++++++++++++");
			List<Products> pdList = productService.getPvByName(product_name);
			return pdList;
		}
	}
	
	@RequestMapping(value="/productController/categoryID",method=RequestMethod.GET)
	@ResponseBody
	public Object getPdByCgId(@RequestParam int categoryID){
		System.out.println("--------------------");
		System.out.println("categoryID: " + categoryID);
		List<Products> pdList = productService.getPdByCgId(categoryID);
		for (Products products : pdList) {
			System.out.println(products.getProductID());
		}
		return pdList;
	}
	
	
	
	/*@RequestMapping(value="/productController/{categoryID}",method=RequestMethod.GET)
	@ResponseBody
	public Object getPdByCgId(@PathVariable int categoryID){
		List<Products> pdList = productService.getPdByCgId(categoryID);
		return pdList;
	}*/
	
/*	@RequestMapping(value="/productController/{product_name}",method=RequestMethod.GET)
	@ResponseBody
	public Object getPvByname(@PathVariable String product_name){
		List<Providers> pvList = productService.getPvByname(product_name);
		return pvList;
	}*/
	
	@RequestMapping(value="/productController/{productID}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteProducts(@PathVariable int productID){
		try {
			row = productService.deleteProduct(productID);
			if (row > 0) {
				return "success";
			}
		} catch (Exception e) {
			System.out.println("É¾³ýÊ§°Ü!");
			return "error";
		}
		return null;
	}
	
	@RequestMapping(value="/productController",method=RequestMethod.POST)
	@ResponseBody
	public Object addProducts(@Valid Products product,BindingResult br) throws ClassNotFoundException{
		System.out.println(product.getPv().getProviderID());
		System.out.println(product.getCg().getCategoryID());
		if (!br.hasErrors()) {
			row = productService.addProduct(product);
			if (row > 0) {
				return "success";
			}
		}else{
			Class c = Class.forName("com.nc.entity.Products");
			Field[] field = c.getDeclaredFields();
			List<String> msg = new ArrayList<>();
			for (Field f : field) {
				System.out.println(f.getName());
				if (br.getFieldError(f.getName()) == null) {
					msg.add(" ");
				}else{
					msg.add(br.getFieldError(f.getName()).getDefaultMessage());
				}
			}
			return msg;
		}
		return null;
	}
	
	@RequestMapping(value="/productController",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateProducts(@Valid Products product,BindingResult br) throws ClassNotFoundException{
//		System.out.println(product.getPv().getProviderID());
//		System.out.println(product.getCg().getCategoryID());
		
		if (!br.hasErrors()) {
			row = productService.updateProduct(product);
			if (row > 0) {
				return "success";
			}
		}else{
			Class c = Class.forName("com.nc.entity.Products");
			Field[] field = c.getDeclaredFields();
			List<String> msg = new ArrayList<>();
			for (Field f : field) {
				System.out.println(f.getName());
				if (br.getFieldError(f.getName()) == null) {
					msg.add(" ");
				}else{
					msg.add(br.getFieldError(f.getName()).getDefaultMessage());
				}
			}
			return msg;
		}
		return null;
	}
}
