package com.chaoxing.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.config.SysConfig;
import com.chaoxing.oa.entity.page.PComboBox;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PShebao;
import com.chaoxing.oa.entity.page.PShebaoType;
import com.chaoxing.oa.entity.page.Pwages;
import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.service.EmployeeInfoServiceI;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeInfoController {
	private EmployeeInfoServiceI employeeInfoService;
	
	public EmployeeInfoServiceI getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoServiceI employeeInfoServiceI) {
		this.employeeInfoService = employeeInfoServiceI;
	}

	@RequestMapping(value = "/renshiUser")
	@ResponseBody
	public Map<String, Object> getRenshiUserName(QueryForm queryForm){
		Map<String, Object> userInfos = employeeInfoService.getRenshiUserName(queryForm);
		return userInfos;
	}
	
	//TODO 方法貌似弃用了,有时间整理一下删除掉
	@RequestMapping(value = "/getQueryForm")
	public ModelAndView getQueryForm(HttpSession session, Model model){
		if(session.getAttribute(ResourceUtil.getSessionInfoName()) != null){
			Map<String, Object> results = employeeInfoService.getQueryForm();
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("/components/queryform");
			modelView.addObject("levels", results.get("levels"));
			modelView.addObject("companys", results.get("companys"));
			return modelView;
		}
		return null;
	}
	
	@RequestMapping(value = "/getFourThLevel")
	@ResponseBody
	public Json getFourthLevel(){
		Json result = new Json();
		List<PComboBox> lists = employeeInfoService.getForthLevel();
		if(lists.size()>0){
			result.setSuccess(true);
			result.setMsg("查询成功！");
			result.setObj(lists);
		}else{
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("查询失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/getOStruct")
	@ResponseBody
	public List<POStructs> getOStruct(){
		List<POStructs> lists = employeeInfoService.getOStruct();
		return lists;
	}
	
	@RequestMapping(value = "/getLevel")
	@ResponseBody
	public List<PLevel> getLevel(){
		List<PLevel> lists = employeeInfoService.getLevel();
		return lists;
	}
	
	@RequestMapping(value = "/getCompany")
	@ResponseBody
	public List<PCompany> getCompany(){
		return employeeInfoService.getCompany();
	}
	
	@RequestMapping(value = "/getInsuranceCompany")
	@ResponseBody
	public List<PComboBox> getInsuranceCompany(){
		return employeeInfoService.getInsuranceCompany();
	}
	
	@RequestMapping(value = "/getWagesList")
	@ResponseBody
	public List<Pwages> getWagesList(QueryForm queryForm){
//		Map<String, Object> result = new HashMap<String, Object>();
		List<Pwages> wagesList = employeeInfoService.getWagesList(queryForm.getId());
//		result.put("total", wagesList.size());
//		result.put("rows", wagesList);
		return wagesList;
	}
	
	@RequestMapping(value = "/getWages")
	@ResponseBody
	public Pwages getWages(QueryForm queryForm){
		return employeeInfoService.getWages(queryForm.getId());
	}
	
	@RequestMapping(value = "/updateWages")
	@ResponseBody
	public Json updateWages(Pwages pwages){
		Json result = new Json();
		if(employeeInfoService.updateWages(pwages)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}
		return result;
	}
	
	@RequestMapping(value = "/addWages")
	@ResponseBody
	public Json addWages(Pwages pwages){
		Json result = new Json();
		if(employeeInfoService.addWages(pwages)!=0){
			result.setSuccess(true);
			result.setMsg("添加成功！");
		}else{
			result.setMsg("添加失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteWages")
	@ResponseBody
	public Json deleteWages(Pwages pwages){
		Json result = new Json();
		if(employeeInfoService.deleteWages(pwages)!=0){
			result.setSuccess(true);
			result.setMsg("删除成功！");
		}else{
			result.setMsg("删除失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/getAllShebaoRadio")
	@ResponseBody
	public Map<String, Object>  getAllShebaoRadio(QueryForm queryform){
		Map<String, Object> pshebaos = employeeInfoService.getAllShebaoRadio(queryform);
		return pshebaos;
	}
	
	@RequestMapping(value = "/getShebaoRadioByCompany")
	@ResponseBody
	public Json getShebaoRadioByCompany(QueryForm queryForm){
		Json result = new Json();
		List<PShebao> pshebaos = employeeInfoService.getShebaoRadioByCompany(queryForm.getCompany());
		if(pshebaos.size() > 0){
			result.setSuccess(true);
			result.setObj(pshebaos);
			result.setMsg("查询成功！");
		}else{
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("没有数据或者查询失败！");
		}
		return result;
	}
	@RequestMapping(value = "/getShebaoRadioByType")
	@ResponseBody
	public Json getShebaoRadioByType(String shebaoType){
		Json result = new Json();
		List<PShebao> pshebaos = employeeInfoService.getShebaoRadioByCompany(shebaoType);
		if(pshebaos.size() > 0){
			result.setSuccess(true);
			result.setObj(pshebaos);
			result.setMsg("查询成功！");
		}else{
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("没有数据或者查询失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/getShebaoType")
	@ResponseBody
	public List<PShebaoType> getShebaoType(){
		return employeeInfoService.getShebaoType();
	}
}
