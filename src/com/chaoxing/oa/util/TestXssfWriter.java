package com.chaoxing.oa.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.chaoxing.oa.entity.page.common.ExportModelVo;

public class TestXssfWriter extends XssfWriter {

	@Override
	public void generate(Map<String, Object> query) throws Exception {
		List<ExportModelVo> heads=getHeaders();
		genrateHeader(heads);
		 long start = System.currentTimeMillis(); // 记录起始时间
	        for (int rr = 0; rr < 40; rr++) {  
	        	insertRow(rr);
	            for (int cc = 0; cc < 10; cc++) {  
	            	createCell(cc,("测试" + rr + "," + cc));
	            }  
	        } 
	     long end = System.currentTimeMillis();  
		 System.out.println("运行了" + (end - start)/1000 + "秒");

	}

	protected void genrateHeader(List<ExportModelVo> heads) throws IOException {
		insertRow(0);
		for(int i=0;i<heads.size();i++){
			createCell(i,heads.get(i).getFieldName());
		}
		endRow();
	}

	protected List<ExportModelVo> getHeaders() {
		List<ExportModelVo> list=new ArrayList<ExportModelVo>();
		
		ExportModelVo epo1=new ExportModelVo();
		epo1.setFieldId("username");
		epo1.setFieldName("姓名");
		epo1.setDicName("");
		list.add(epo1);
		
		ExportModelVo epo2=new ExportModelVo();
		epo2.setFieldId("firstLevel");
		epo2.setFieldName("公司");
		epo2.setDicName("");
		list.add(epo2);
		
		ExportModelVo epo3=new ExportModelVo();
		epo3.setFieldId("secondLevel");
		epo3.setFieldName("部门");
		epo3.setDicName("");
		list.add(epo3);
		
		ExportModelVo epo4=new ExportModelVo();
		epo4.setFieldId("thirdLevel");
		epo4.setFieldName("岗位");
		epo4.setDicName("");
		list.add(epo4);
		
		ExportModelVo epo5=new ExportModelVo();
		epo5.setFieldId("fourthLevel");
		epo5.setFieldName("小组");
		epo5.setDicName("");
		list.add(epo5);
		
		ExportModelVo epo6=new ExportModelVo();
		epo6.setFieldId("position");
		epo6.setFieldName("职位");
		epo6.setDicName("");
		list.add(epo1);
		
		ExportModelVo epo7=new ExportModelVo();
		epo7.setFieldId("sex");
		epo7.setFieldName("性别");
		epo7.setDicName("");
		list.add(epo7);
		
		ExportModelVo epo9=new ExportModelVo();
		epo9.setFieldId("company");
		epo9.setFieldName("公司名称");
		epo9.setDicName("");
		list.add(epo9);
		
		ExportModelVo epo10=new ExportModelVo();
		epo10.setFieldId("insuranceCompany");
		epo10.setFieldName("保险公司");
		epo10.setDicName("");
		list.add(epo10);
		
		ExportModelVo epo11=new ExportModelVo();
		epo11.setFieldId("degree");
		epo11.setFieldName("学历");
		epo11.setDicName("");
		list.add(epo11);
		
		ExportModelVo epo12=new ExportModelVo();
		epo12.setFieldId("workPlace");
		epo12.setFieldName("工作地点");
		epo12.setDicName("");
		list.add(epo12);
		
		ExportModelVo epo13=new ExportModelVo();
		epo13.setFieldId("earlyEntryDate");
		epo13.setFieldName("早期入职时间");
		epo13.setDicName("");
		list.add(epo13);
		
		ExportModelVo epo14=new ExportModelVo();
		epo14.setFieldId("hiredate");
		epo14.setFieldName("入职时间");
		epo14.setDicName("");
		list.add(epo14);
		
		ExportModelVo epo15=new ExportModelVo();
		epo15.setFieldId("zhuanzhengTime");
		epo15.setFieldName("转正时间");
		epo15.setDicName("");
		list.add(epo15);
		
		ExportModelVo epo16=new ExportModelVo();
		epo16.setFieldId("leaveTime");
		epo16.setFieldName("离职时间");
		epo16.setDicName("");
		list.add(epo16);
		
		ExportModelVo epo17=new ExportModelVo();
		epo17.setFieldId("phoneNumber");
		epo17.setFieldName("联系方式");
		epo17.setDicName("");
		list.add(epo17);
		
		ExportModelVo epo18=new ExportModelVo();
		epo18.setFieldId("maritalStatus");
		epo18.setFieldName("婚姻状况");
		epo18.setDicName("");
		list.add(epo18);
		
		return list;
	}

	public static void main(String[] args) {
		XssfWriter writer = new TestXssfWriter();
		String fileName;
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("style", "gettestExcel");
		try {
			fileName = writer.process("testExcel", null);
			System.out.println(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
