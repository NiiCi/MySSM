package com.nc.view;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.nc.entity.Admin;

@Component
public class ExcelView extends AbstractXlsView {

	@Override
	
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "管理员列表.xls";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/ms-excel");
		response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));
		OutputStream outputStream = response.getOutputStream();

		List<Admin> admin = (List<Admin>) model.get("admin");
		//创建Excel 表名
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("管理员列表");
		//设置表的标题
		HSSFRow title = sheet.createRow(0);
		title.createCell(0).setCellValue("编号");
		title.createCell(1).setCellValue("姓名");
		title.createCell(2).setCellValue("密码");
		title.createCell(3).setCellValue("出生日期");
		int index = 1;
		for (Admin a : admin) {
			HSSFRow row = sheet.createRow(index);
		
			row.createCell(0).setCellValue(a.getAdmin_id());
			row.createCell(1).setCellValue(a.getAdmin_name());
			row.createCell(2).setCellValue(a.getAdmin_password());
			row.createCell(3).setCellValue(a.getAdmin_date());
			index++;
			
		}
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

}
