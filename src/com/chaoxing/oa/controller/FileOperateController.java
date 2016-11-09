package com.chaoxing.oa.controller;

import java.io.UnsupportedEncodingException;
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

import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PSheBaoSummary;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.EmployeeInfoService;
import com.chaoxing.oa.service.ExportExcelService;
import com.chaoxing.oa.util.BarCode128C;
import com.chaoxing.oa.util.FileOperateUtil;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping(value = "/file")
public class FileOperateController {
	private EmployeeInfoService employeeInfoService;
	private ExportExcelService exportExcelService;
	
	
	public ExportExcelService getExportDao() {
		return exportExcelService;
	}
	@Autowired
	public void setExportDao(ExportExcelService exportExcelService) {
		this.exportExcelService = exportExcelService;
	}
	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoService employeeInfoServiceI) {
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
    		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
    		boolean flag = false;
    		if(100 == sessionInfo.getRoleId()){
    			flag = true;
    		}
    		String storeName = exportExcelService.getRenshiQueryExport(renshiEmployeeInfos,flag);  
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
    
    @RequestMapping(value = "/exportKaoqinExcel")
    public ModelAndView exportKaoqinExcel(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findKaoqin(queryForm, session,1);
    	List<PKaoQin> pkaoqins = (List<PKaoQin>) res.get("rows");
    	if(pkaoqins!=null&&pkaoqins.size()>0){
    		String storeName = exportExcelService.getKaoqinExportExcel(pkaoqins);  
    		String realName = "考勤查询结果表.xlsx";  
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
    
    @RequestMapping(value = "/exportMonthWagesExcel")
    public ModelAndView exportMonthWagesExcel(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findMonthWages(queryForm, session,1);
    	List<PMonthWages> pMonthWages = (List<PMonthWages>) res.get("rows");
    	if(pMonthWages!=null&&pMonthWages.size()>0){
    		String storeName = exportExcelService.getMonthWagesExcel(pMonthWages);  
    		String realName = "当月工资查询结果表.xlsx";  
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
    
    @RequestMapping(value = "/exportShebaoCompany")
    public ModelAndView exportShebaoCompany(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.getShebaoCompany(queryForm, session,1);
    	List<PshebaoDetail> pShebaoDetails = (List<PshebaoDetail>) res.get("rows");
    	if(pShebaoDetails!=null&&pShebaoDetails.size()>0){
    		String storeName = exportExcelService.getShebaoCompany(pShebaoDetails);  
    		String realName = "公司社保汇总表.xlsx";  
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
    
    @RequestMapping(value = "/exportOS")
    public ModelAndView exportOStruct(HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findAllStruct(null);
    	List<POStructV> pos = (List<POStructV>) res.get("rows");
    	if(pos!=null&&pos.size()>0){
    		String storeName = exportExcelService.getPOStructExcel(pos);  
    		String realName = "架构表.xlsx";  
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
    
    @RequestMapping(value = "/exportShebaoSummary")
    public ModelAndView exportShebaoSummary(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.getShebaoSummary(queryForm, session,1);
    	List<PSheBaoSummary> pShebaoSummarys = (List<PSheBaoSummary>) res.get("rows");
    	if(pShebaoSummarys!=null&&pShebaoSummarys.size()>0){
    		String storeName = exportExcelService.getShebaoSummary(pShebaoSummarys);  
    		String realName = "社保公司汇总表.xlsx";  
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
    @RequestMapping(value = "/codeImage")
    public ModelAndView getcodeImage(PFahuo pfahuo, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	if(null != pfahuo.getMailno()&&""!=pfahuo.getMailno()&&!"".equals(pfahuo.getOrderid())){
    		try {
				BarCode128C.getCode128CPicture(pfahuo.getMailno(), 22, request, response );
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
}
