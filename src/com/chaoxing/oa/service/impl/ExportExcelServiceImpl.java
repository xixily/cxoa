package com.chaoxing.oa.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.service.ExportExcelService;
import com.chaoxing.oa.util.SXSSFWriter;

@Service("exportExcelService")
public class ExportExcelServiceImpl implements ExportExcelService {

	@Override
	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees) {
		SXSSFWriter sxffWriter = null;
		try {
			sxffWriter = new SXSSFWriter("employeeExcel");
			//文件绝对路径
			String filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("查询结果excel表");
			if(renshiEmployees==null || renshiEmployees.size()==0){
				return null;
			}
			createHeader(sxffWriter);
			for (PRenshiEmployee pRenshiEmployee : renshiEmployees) {
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getUsername());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPosition());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getSex());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getCompany());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getInsuranceCompany());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getDegree());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getWorkPlace());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getEarlyEntryDate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getHiredate());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getZhuanzhengTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getLeaveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getPhoneNumber());
				sxffWriter.createCell();
				sxffWriter.setStringData(pRenshiEmployee.getMaritalStatus());
			}
			sxffWriter.flush();
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				sxffWriter.destroy();
			} catch (IOException e) {
				System.out.println("销毁失败！");
				e.printStackTrace();
			}
		}
		return null;
	}

	private void createHeader(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("职员姓名");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司");
		sxffWriter.createCell();
		sxffWriter.setStringData("部门");
		sxffWriter.createCell();
		sxffWriter.setStringData("岗位");
		sxffWriter.createCell();
		sxffWriter.setStringData("小组");
		sxffWriter.createCell();
		sxffWriter.setStringData("职位");
		sxffWriter.createCell();
		sxffWriter.setStringData("性别");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司名称");
		sxffWriter.createCell();
		sxffWriter.setStringData("保险公司");
		sxffWriter.createCell();
		sxffWriter.setStringData("学历");
		sxffWriter.createCell();
		sxffWriter.setStringData("工作地点");
		sxffWriter.createCell();
		sxffWriter.setStringData("早期入职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("入职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("转正时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("离职时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("联系方式");
		sxffWriter.createCell();
		sxffWriter.setStringData("婚姻状况");
	}

}
