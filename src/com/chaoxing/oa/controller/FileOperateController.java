package com.chaoxing.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.service.EmployeeInfoServiceI;
import com.chaoxing.oa.service.ExportExcelService;
import com.chaoxing.oa.util.FileOperateUtil;

@Controller
@RequestMapping(value = "/file")
public class FileOperateController {
	private EmployeeInfoServiceI employeeInfoService;
	private ExportExcelService exportExcelService;
	
	
	public ExportExcelService getExportDao() {
		return exportExcelService;
	}
	@Autowired
	public void setExportDao(ExportExcelService exportExcelService) {
		this.exportExcelService = exportExcelService;
	}
	public EmployeeInfoServiceI getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoServiceI employeeInfoServiceI) {
		this.employeeInfoService = employeeInfoServiceI;
	}
	
	 /** 
     * 到上传文件的位置 
     *  
     * @author dengxf 
     * @return 
     */  
    @RequestMapping(value = "to_upload")  
    public ModelAndView toUpload() {  
        return new ModelAndView("fileOperate/upload");  
    }  
  
    /** 
     * 上传文件 
     *  
     * @author dengxf 
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "upload")  
    public ModelAndView upload(HttpServletRequest request) throws Exception {  
  
        Map<String, Object> map = new HashMap<String, Object>();  
  
        // 别名  
        String[] alaises = ServletRequestUtils.getStringParameters(request,"alais");  
  
        String[] params = new String[] { "alais" };  
        Map<String, Object[]> values = new HashMap<String, Object[]>();  
        values.put("alais", alaises);  
  
        List<Map<String, Object>> result = FileOperateUtil.upload(request,  
                params, values);  
  
        map.put("result", result);  
  
        return new ModelAndView("fileOperate/list", map);  
    }  
  
    /** 
     * 下载 
     *  
     * @author dengxf 
     * @param attachment 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "download")  
    public ModelAndView download(HttpServletRequest request,HttpServletResponse response) throws Exception {  
  
        String storeName = "201205051340364510870879724.zip";  
        String realName = "Java设计模式.zip";  
        String contentType = "application/octet-stream";  
  
        FileOperateUtil.download(request, response, storeName, contentType,realName);  
  
        return null;  
    }
    
    @RequestMapping(value = "/exportExcel")
    public ModelAndView exprotRenshiQuery(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.getRenshiUserName(queryForm, session,1);
    	List<PRenshiEmployee> renshiEmployeeInfos = (List<PRenshiEmployee>) res.get("rows");
    	if(renshiEmployeeInfos!=null){
    		String storeName = exportExcelService.getRenshiQueryExport(renshiEmployeeInfos);  
    		String realName = "导出查询结果表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	
    	return null;
    }
}
