package com.nc.controller;


import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nc.entity.Admin;
import com.nc.entity.Role_Admin;
import com.nc.entity.Roles;
import com.nc.entity.UploadResult;
import com.nc.service.AdminService;
import com.nc.service.NavService;
import com.nc.service.RoleService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	NavService navService;
	@Autowired
	RoleService roleService;
	int row = 0;
/*	Gson gson = new Gson();*/
	/*@RequiresRoles("superAdmin")*/
	@RequestMapping("/adminList")
	public String admin(){
		return "adminList";
	}
	/*
	 * ������excel����,excelView ΪExcelView���bean��
	 */
	@RequestMapping("/download")
	public String download(Map<String, Object> map){
		List<Admin> admin =  adminService.queryAll();
		map.put("admin", admin);
		return "excelView";
	}

	/*�������ļ�����*/
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam MultipartFile uploadfile){
		//�жϲ�Ϊ��ʱ
		if (!uploadfile.isEmpty()&&uploadfile.getOriginalFilename().endsWith(".xls")) {
			System.out.println("---�ļ���: "+uploadfile.getOriginalFilename());
			
			try {
				HSSFWorkbook hssf = new HSSFWorkbook(uploadfile.getInputStream());
				//�Ϸ����ݱ��浽������
				List<Admin> adminList = new ArrayList<>();
				//�Ƿ����ݴ�����Ϣ���浽������
				Map<Integer, String> errors = new HashMap<>();
				
				//��ȡ������ĳ���
				int length = hssf.getNumberOfSheets();
				System.out.println("���������-----: "+length);
				for (int i = 0; i < length; i++) {
					//ͨ���±� ��ȡÿ��������
					HSSFSheet sheet = hssf.getSheetAt(i);
					System.out.println("-----�м�¼������:"+(sheet.getPhysicalNumberOfRows()));
					System.out.println("-----���һ��: "+sheet.getLastRowNum());
					for (int j = 1; j <=sheet.getLastRowNum(); j++) {
						
						HSSFRow row = sheet.getRow(j);
						Admin admin = new Admin();
						
					/*	System.out.println("----"+row.getCell(2).getStringCellValue());*/
						/*admin.setAdmin_id(((int)row.getCell(0).getNumericCellValue()));*/
						row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
						admin.setAdmin_id(row.getCell(0) == null? 0:(int)row.getCell(0).getNumericCellValue());
						admin.setAdmin_name(row.getCell(1).getStringCellValue());
						admin.setAdmin_password(row.getCell(2).getStringCellValue());
						admin.setAdmin_date(row.getCell(3).getStringCellValue());
						//ͨ���±� ��ȡÿ��
					/*	for (int k = 0; k < row.getLastCellNum(); k++) {
							//ͨ���±� ��ȡÿ��
							HSSFCell cell = row.getCell(k);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							System.out.print(cell);
						}
						System.out.println();*/
						//��֤���ݺϷ���
						String isUse = check(admin);
						System.out.println("-----isUse: "+isUse);
						if (isUse.length()==0) {
							adminList.add(admin);
						}else{
							errors.put(j, isUse);
						}
					}
				}
				
				//��������(ID�ظ��������,�Ƿ����ݲ������)
			
				adminService.addAdminList(adminList);
				UploadResult ur = new UploadResult();
				ur.setOkCount(adminList.size());
				ur.setErrCount(errors.size());
				ur.setErrors(errors);
				return ur;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String check(Admin admin){
		String result = "";
		result=(admin.getAdmin_name().matches("^[\u4e00-\u9fa5]{2,6}$"))? result:result+"�û�������Ϊ����,����Ϊ2��6���ַ�";
		result=(admin.getAdmin_password().matches("^[\\w!@#$%^&*()_+~]{6,16}"))? result:result+"���볤��Ϊ6��16λ";
		result=(admin.getAdmin_date().matches("([0-9]{4})-(0?[0-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])"))? result:result+"���ڸ�ʽΪyyyy-mm-dd";
		return result;
	}
	
	@RequestMapping(value="/adminController",method=RequestMethod.GET)
	@ResponseBody
	public Object queryAdmin(@RequestParam int pageIndex){
		//ÿҳ��ѯ������
		int pageSize = 2;
		// ���ݵ�ǰҳ����ѯ����
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageIndex, pageSize);
		List<Admin> adminList = adminService.queryAll();
		for (Admin admin : adminList) {
			admin.setAdmin_password(admin.getAdmin_password().substring(0, 8)+"...");
		}
		PageInfo<Admin> pageInfo = new PageInfo<>(adminList, 3);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		System.out.println(session.getLastAccessTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(session.getLastAccessTime());
		session.setAttribute("accessTime", date);
		System.out.println(date);
		return pageInfo;
	}
	
	
	//���һ������
	@RequestMapping(value="/adminController",method=RequestMethod.POST)
	@ResponseBody
	public Object addAdmin (@Valid Admin admin, BindingResult br,HttpServletRequest request) throws ClassNotFoundException{
		if (!br.hasErrors()) {
			SimpleHash sh = new SimpleHash("MD5", admin.getAdmin_password(), admin.getAdmin_name(), 1000);
			admin.setAdmin_password(sh.toString());
			//����û�
			row = adminService.addAdmin(admin);
			//ͨ���û�����ѯ�û���Ϣ,ȡ������ӵ��û���admin_id
			admin = adminService.queryAdminByName(admin.getAdmin_name());
			String[] roleid = request.getParameterValues("roleid"); 
			if (roleid.length != 0) {
				List<Role_Admin> role_adminList = new ArrayList<>();
				for (String string : roleid) {
					Role_Admin role_admin = new Role_Admin();
					role_admin.setAdmin_id(admin.getAdmin_id());
					role_admin.setRoleid(Integer.parseInt(string));
					System.out.println("admin_id: "+admin.getAdmin_id());
					System.out.println("roleid:"+Integer.parseInt(string));
					role_adminList.add(role_admin);
				}
				roleService.addAdminRole(role_adminList);
				for (Role_Admin role_Admin : role_adminList) {
					System.out.println(role_Admin.getRoleid());
				}
			}
			System.out.println("----Ӱ������: "+row);
			if (row > 0) {
				return "success";
			}
		}else{
			Class c = Class.forName("com.nc.entity.Admin");
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
	
	@RequestMapping(value="/adminController/{updateFlag}",method=RequestMethod.PUT)
	@ResponseBody
	public Object updateAdmin (@PathVariable String updateFlag,@Valid Admin admin,BindingResult br,HttpServletRequest request) throws ClassNotFoundException{
			System.out.println("updateFlag------: "+updateFlag);
			if (updateFlag.equals("updateRole")) {
			/*	Admin adminList = adminService.queryAdminByName(admin.getAdmin_name());*/
				
				String[] roleid = request.getParameterValues("roleid");
				if (roleid == null) {
					row =roleService.deleteAdminRole(admin.getAdmin_id());
				}
				
				else{
					List<Role_Admin> role_adminList = new ArrayList<>();
					for (String string : roleid) {
						Role_Admin role_admin = new Role_Admin();
						role_admin.setAdmin_id(admin.getAdmin_id());
						role_admin.setRoleid(Integer.parseInt(string));
						System.out.println("admin_id: "+admin.getAdmin_id());
						System.out.println("roleid:"+Integer.parseInt(string));
						role_adminList.add(role_admin);
					}
					row =roleService.deleteAdminRole(admin.getAdmin_id());
					roleService.addAdminRole(role_adminList);
				}
				System.out.println("----Ӱ������: "+row);
				if (row > 0) {
					return "success";
				}
			}else if (updateFlag.equals("updateInfo")) {
				if (!br.hasErrors()) {
					SimpleHash sh = new SimpleHash("MD5", admin.getAdmin_password(), admin.getAdmin_name(), 1000);
					admin.setAdmin_password(sh.toString());
					row = adminService.updateAdmin(admin);
					if (row > 0 ) {
						return "success";
					}
				}
			}else if(updateFlag.equals("updateAll")){
				if (!br.hasErrors()) {
					SimpleHash sh = new SimpleHash("MD5", admin.getAdmin_password(), admin.getAdmin_name(), 1000);
					admin.setAdmin_password(sh.toString());
					//�����޸��û���Ϣ����
					row = adminService.updateAdmin(admin);
					/*Admin adminList = adminService.queryAdminByName(admin.getAdmin_name());*/
				
					String[] roleid = request.getParameterValues("roleid"); 
					List<Role_Admin> role_adminList = new ArrayList<>();
					for (String string : roleid) {
						Role_Admin role_admin = new Role_Admin();
						role_admin.setAdmin_id(admin.getAdmin_id());
						role_admin.setRoleid(Integer.parseInt(string));
						System.out.println("admin_id: "+admin.getAdmin_id());
						System.out.println("roleid:"+Integer.parseInt(string));
						role_adminList.add(role_admin);
					}
					roleService.deleteAdminRole(admin.getAdmin_id());
					roleService.addAdminRole(role_adminList);
					System.out.println("----Ӱ������: "+row);
					if (row > 0) {
						return "success";
					}
			}
		}else{
			Class c = Class.forName("com.nc.entity.Admin");
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
	@RequestMapping(value="/adminController/{admin_name}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryAdminRole(@PathVariable String admin_name){		
		List<Roles> roleList = navService.queryRolesByName(admin_name);
		for (Roles roles : roleList) {
			System.out.println("------:"+roles.getRoleName());
		}
		return roleList;
	}
	
	@RequestMapping(value="/adminController/{admin_id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object delAdmin(@PathVariable int admin_id){
		try {
			System.out.println(admin_id);
			int row = adminService.deleteAdmin(admin_id);
			roleService.deleteAdminRole(admin_id);
			System.out.println(row);
			if (row > 0) {
				return "success";
			}
		} catch (Exception e) {
			return "error";
		}
		return null;
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public Object ExceptionThrow(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		map.put("error", "��û�и�Ȩ��,����ϵ����Ա");
		System.out.println("��û�и�Ȩ��,����ϵ����Ա");
		return "index";
	}
}
