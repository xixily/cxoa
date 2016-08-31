package com.chaoxing.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.chaoxing.oa.entity.page.PHouseholdType;
import com.chaoxing.oa.entity.page.PKaoQin;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.PMonthWages;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PShebao;
import com.chaoxing.oa.entity.page.PShebaoType;
import com.chaoxing.oa.entity.page.PWagesDate;
import com.chaoxing.oa.entity.page.Pwages;
import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.service.EmployeeInfoService;
import com.chaoxing.oa.util.DateUtil;
import com.chaoxing.oa.util.ResourceUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private EmployeeInfoService employeeInfoService;
	
	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoService employeeInfoServiceI) {
		this.employeeInfoService = employeeInfoServiceI;
	}

	@RequestMapping(value = "/renshiUser")
	@ResponseBody
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.getRenshiUserName(queryForm, session);
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
	public List<Pwages> getWagesList(QueryForm queryForm, HttpSession session){
//		Map<String, Object> result = new HashMap<String, Object>();
		if(queryForm.getId()!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
		if(sessionInfo.getRoleId()>1 && employeeInfoService.getUserInfo(queryForm).getIfSecret().equals("on")){
			return null;
		}else{
			List<Pwages> wagesList = employeeInfoService.getWagesList(queryForm.getId());
	//		result.put("total", wagesList.size());
	//		result.put("rows", wagesList);
			return wagesList;
		}
		}
		return null;
	}
	
	@RequestMapping(value = "/getWages")
	@ResponseBody
	public Pwages getWages(QueryForm queryForm){
		return employeeInfoService.getWages(queryForm.getId());
	}
	
	@RequestMapping(value = "/updateWages")
	@ResponseBody
	public Json updateWages(Pwages pwages){
		//TODO 修改策略，更新的时候只更改部分字段。
		Json result = new Json();
		if(employeeInfoService.updateWages(pwages)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}
		return result;
	}
	@RequestMapping(value = "/updateWagesRadix")
	@ResponseBody
	public Json updateWagesRadix(Pwages pwages){
		Json result = new Json();
		if(employeeInfoService.updateWagesRadix(pwages)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}else{
			result.setMsg("更新失败！");
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
	
	@RequestMapping(value = "/getShebao")
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
	
	@RequestMapping(value = "/updateShebao")
	@ResponseBody
	public Json updateShebao(PShebao pshebao){
//		System.out.println("company: " + pshebao.getCompany() + ";shebaoType: " + pshebao.getShebaoType() + ";householdType: " + pshebao.getHouseholdType());
		Json result = new Json();
//		try {
//			String company = new String(pshebao.getCompany().getBytes("ISO8859-1"), "utf-8");
//			String shebaoType = new String(pshebao.getShebaoType().getBytes("ISO8859-1"),"utf-8");
//			String householdType = new String(pshebao.getHouseholdType().getBytes("ISO8859-1"),"utf-8");
//			System.out.println(company);
//			System.out.println(shebaoType);
//			pshebao.setCompany(company);
//			pshebao.setShebaoType(shebaoType);
//			pshebao.setHouseholdType(householdType);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}  
		if(employeeInfoService.updateShebao(pshebao) != 0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}else{
			result.setMsg("更新失败！");
		}
		return result;
	}
	@RequestMapping(value = "/getHouseholdType")
	@ResponseBody
	public List<PHouseholdType> getHouseholdType(){
		return employeeInfoService.getHouseholdType();
	}
	
	@RequestMapping(value = "/addShebao")
	@ResponseBody
	public Json addShebao(PShebao pshebao){
		Json result = new Json();
		if(employeeInfoService.addShebao(pshebao)!=0){
			result.setSuccess(true);
			result.setMsg("增加成功！");
		}else{
			result.setMsg("添加失败！");
		}
		return result;
	}
	@RequestMapping(value = "/deleteShebao")
	@ResponseBody
	public Json deleteShebao(PShebao pshebao){
		Json result = new Json();
		if(employeeInfoService.deleteShebao(pshebao)!=0){
			result.setSuccess(true);
			result.setMsg("删除成功！");
		}else{
			result.setMsg("删除失败！");
		}
		return result;
	}
	@RequestMapping(value = "/queryShebaoSummary")
	@ResponseBody
	public Map<String, Object> queryShebaoSummary(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.getShebaoSummary(queryForm, session);
		return userInfos;
	}
	
	@RequestMapping(value = "/getShebaoCompany")
	@ResponseBody
	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session){
		Map<String, Object> shebaoCompanys = employeeInfoService.getShebaoCompany(queryForm, session);
		return shebaoCompanys;
	}
	
	@RequestMapping(value = "/queryKaoqin")
	@ResponseBody
	public Map<String, Object> queryKaoqin(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.findKaoqin(queryForm, session);
		return userInfos;
	}
	
	@RequestMapping(value = "/updateKaoqin")
	@ResponseBody
	public Json updateKaoqin(PKaoQin pkaoqin){
		Json result = new Json();
		if(employeeInfoService.updateKaoqin(pkaoqin)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}else{
			result.setMsg("更新失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryMonthWages")
	@ResponseBody
	public Map<String, Object> queryMonthWages(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.findMonthWages(queryForm, session);
		return userInfos;
	}
	
	@RequestMapping(value = "/updateMonthWages")
	@ResponseBody
	public Json updateMonthWages(PMonthWages pmonthWages){
		Json result = new Json();
		if(employeeInfoService.updateMonthWages(pmonthWages)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}else{
			result.setMsg("更新失败！");
		}
		return result;
	}
	@RequestMapping(value = "/queryWagesDate")
	@ResponseBody
	public Map<String, Object> queryWagesDate(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.queryWagesDate(queryForm);
		return userInfos;
	}
	
	@RequestMapping(value = "/updateWagesDate")
	@ResponseBody
	public Json updateWagesDates(PWagesDate pwagesDate){
		Json result = new Json();
		int savaNum = employeeInfoService.updateWagesDate(pwagesDate);
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg(String.valueOf(savaNum));
		}
		return result;
	}
	
	@RequestMapping(value = "/generateWagesDates")
	@ResponseBody
	public Json generateWagesDates(Integer year,Integer month){
		Json result = new Json();
		if(year==null||year==0||month==null||month==0){
			return result;
		}
		List<PWagesDate> pwagesDates = DateUtil.getWagesDate(year, month);
		int savaNum = employeeInfoService.updateWagesDates(pwagesDates);
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg(String.valueOf(savaNum));
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteWagesDate")
	@ResponseBody
	public Json deleteWagesDate(PWagesDate pwagesDate){
		Json result = new Json();
		int savaNum = employeeInfoService.deleteWagesDate(pwagesDate);
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg(String.valueOf(savaNum));
		}
		return result;
	}
	
	@RequestMapping(value = "/generateKaoqin")
	@ResponseBody
	public Json generateKaoqin(){
		Json result = new Json();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String date = df.format(cal.getTime());
		cal.add(Calendar.MONTH, -1);
		String preDate = df.format(cal.getTime());
//		int year,month;
//		List<PWagesDate> pwagesDates = null;
//		for (int i = 0; i < 2; i++) {
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DATE, 1);  
//			year = cal.get(Calendar.YEAR);
//			month = cal.get(Calendar.MONTH)+1;
//			String date = df.format(cal.getTime()).substring(0, 7);
//			QueryForm queryForm = new QueryForm();
//			queryForm.setWagesMonth(date);
//			pwagesDates = (List<PWagesDate>) employeeInfoService.queryWagesDate(queryForm).get("rows");
//			int j = 0;
//			if(pwagesDates.size()<28){
//				result.setMsg("请检查"+ date +"的工作日表");
//				return result;
//			}
//			while(cal.get(Calendar.YEAR) == year &&     
//	                cal.get(Calendar.MONTH) < month){
//				if(df.format(cal.getTime())!= pwagesDates.get(j).getDate()){
//					result.setMsg("请检查"+ date +"的工作日表");
//					return result;
//				}
//			}
//		}
		int savaNum = employeeInfoService.generateKaoqin(date,preDate);
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}
		return result;
	}
	
	@RequestMapping(value = "/generateMonthWages")
	@ResponseBody
	public Json generateMonthWages(){
		Json result = new Json();
		int savaNum = employeeInfoService.generateMonthWages();
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}
		return result;
	}
	
	
}
