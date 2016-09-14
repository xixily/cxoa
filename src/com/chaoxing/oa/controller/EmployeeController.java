package com.chaoxing.oa.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.management.Query;
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

import sun.print.resources.serviceui;

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
	
	@RequestMapping(value = "/queryJiagou")
	@ResponseBody
	public List<POStructs> queryJiagou(QueryForm queryform){
		System.out.println(queryform);
		List<POStructs> lists = employeeInfoService.getOStruct(queryform,0);
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
		List<Pwages> wagesList = null ;
		if(queryForm.getId()!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			wagesList = employeeInfoService.getWagesList(queryForm.getId(),sessionInfo,ifSecret);
		}
		return wagesList;
	}
	
	@RequestMapping(value = "/getWages")
	@ResponseBody
	public Pwages getWages(QueryForm queryForm){
		Pwages pwages = null;
		if(queryForm!=null&&queryForm.getId()!=0){
			pwages = employeeInfoService.getWages(queryForm.getId());
		}
		return pwages;
	}
	
	@RequestMapping(value = "/updateGridWages")
	@ResponseBody
	public Json updatePartWages(Pwages pwage){
		Json result = new Json();
		if(pwage!=null&&pwage.getId()!=null&&pwage.getId()!=0){
			Pwages target = employeeInfoService.getWages(pwage.getId());
			if(target!=null){
				target.setRadix(pwage.getRadix());
				target.setCompany(pwage.getCompany());
				target.setHouseholdType(pwage.getHouseholdType());
				target.setRubaoTime(pwage.getRubaoTime());
				if(pwage.getIdentityCard()!=null){
					target.setIdentityCard(pwage.getIdentityCard());
				}
				if(pwage.getAccountBank()!=null){
					target.setAccountBank(pwage.getAccountBank());
				}
				if(pwage.getAccount()!=null){
					target.setAccount(pwage.getAccount());
				}
				if(caculateWages(target)==1){
					if(employeeInfoService.updateWages(target)!=0){
						result.setSuccess(true);
						result.setMsg("<strong>"+ target.getUsername() + ":" +target.getId() +"</strong>更新成功！");
					}else{
						result.setMsg("更新失败！");
					}
				}else{
					result.setMsg("社保计算失败！");
				}
			}else{
				result.setMsg("没有<strong>"+ pwage.getUsername() + ":" +pwage.getId() + "</strong>该条工资信息！");
			}
		}
		return result;
	}
	
	public int caculateWages(Pwages pwage){
		if(pwage.getRadix()==null){
			pwage.setRadix(0d);
		}
		Double oradix = pwage.getRadix();
		String company = pwage.getCompany();
		String houseHoldType = pwage.getHouseholdType();
		List<PShebao> shebaoCompany = employeeInfoService.getShebaoRadioByCompany(company);
		BigDecimal rRadix;
		if(pwage.getRadix()==0){
			pwage.setSubEndowmentIinsurance(0d);
			pwage.setSubHouseIinsurance(0d);
			pwage.setSubMedicare(0d);
			pwage.setSubUnemployedInsurance(0d);
			pwage.setcBirthIinsurance(0d);
			pwage.setcEndowmentIinsurance(0d);
			pwage.setcHouseIinsurance(0d);
			pwage.setcInjuryInsurance(0d);
			pwage.setcMedicare(0d);
			pwage.setcUnemployedInsurance(0d);
			return 1;
		}else if(shebaoCompany!=null&&shebaoCompany.size()>0){
			for (PShebao pShebao : shebaoCompany) {//社保计算
				Double radix = oradix;
				radix = radix<pShebao.getRadixMin()?pShebao.getRadixMin():(radix>pShebao.getRadixMax()?pShebao.getRadixMax():radix);
				rRadix = new BigDecimal(radix.toString());
				if(pShebao.getShebaoType().equals("养老保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubEndowmentIinsurance(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcEndowmentIinsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("失业保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubUnemployedInsurance(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcUnemployedInsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("工伤保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setcInjuryInsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("生育保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setcBirthIinsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("医疗保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubMedicare(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcMedicare(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("公积金")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubHouseIinsurance(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcHouseIinsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}
			}
			return 1;
		}
		return 0;
	}
	
	@RequestMapping(value = "/updateWages")
	@ResponseBody
	public Json updateWages(Pwages pwages, HttpSession session){
		//TODO 修改策略，更新的时候只更改部分字段。
		Json result = new Json();
		if(pwages.getId()!=null&&pwages.getId()!=0){
			QueryForm queryForm = new QueryForm();
			queryForm.setId(pwages.getEmployeeId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
				if(employeeInfoService.updateWages(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}
			}
		}else{
			result.setMsg("没有找到该条记录！");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateWagesRadix")
	@ResponseBody
	public Json updateWagesRadix(Pwages pwages){
		Json result = new Json();
		if(pwages.getId()!=0){
			if(employeeInfoService.updateWagesRadix(pwages)!=0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setMsg("更新失败！");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/addWages")
	@ResponseBody
	public Json addWages(Pwages pwages, HttpSession session){
		Json result = new Json();
//		if(pwages.getId()!=null&&pwages.getId()!=0){
			QueryForm queryForm = new QueryForm();
			queryForm.setId(pwages.getEmployeeId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
				if(employeeInfoService.addWages(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("添加成功！");
				}else{
					result.setMsg("添加失败！");
				}
			}else{
				result.setMsg("对不起，您没有删除权限~！");
			}
//		}else{
//			result.setMsg("没有找到该条记录！");
//		}
		
		return result;
	}
	
	@RequestMapping(value = "/deleteWages")
	@ResponseBody
	public Json deleteWages(Pwages pwages, HttpSession session ){
		Json result = new Json();
		if(pwages.getId()!=null&&pwages.getId()!=0){
			QueryForm queryForm = new QueryForm();
			queryForm.setId(pwages.getEmployeeId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
				if(employeeInfoService.deleteWages(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("删除成功！");
				}else{
					result.setMsg("删除失败！");
				}
			}else{
				result.setMsg("对不起，您没有删除权限~！");
			}
		}else{
			result.setMsg("没有找到该条记录！");
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
		if(queryForm.getCompany()==null){
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("没有公司名称！");
			return result;
		}
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
	public Json getShebaoRadioByType(String company){
		Json result = new Json();
		if(company!=null&&!company.equals("")){
			List<PShebao> pshebaos = employeeInfoService.getShebaoRadioByCompany(company);
			if(pshebaos.size() > 0){
				result.setSuccess(true);
				result.setObj(pshebaos);
				result.setMsg("查询成功！");
			}else{
				result.setErrorCode(SysConfig.REQUEST_ERROR);
				result.setMsg("没有数据或者查询失败！");
			}
		}else{
			result.setMsg("公司名称为空！~");
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
		if(pshebao.getSid()!=0){
			if(employeeInfoService.updateShebao(pshebao) != 0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setMsg("更新失败！");
			}
		}else{
			result.setMsg("没有id");
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
		if(pshebao.getCompany()!=null){
			if(employeeInfoService.addShebao(pshebao)!=0){
				result.setSuccess(true);
				result.setMsg("增加成功！");
			}else{
				result.setMsg("添加失败！");
			}
		}else{
			result.setMsg("添加社保比例，至少要有公司名称~！");
		}
		return result;
	}
	@RequestMapping(value = "/deleteShebao")
	@ResponseBody
	public Json deleteShebao(PShebao pshebao){
		Json result = new Json();
		if(pshebao.getSid()!=0){
			if(employeeInfoService.deleteShebao(pshebao)!=0){
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}else{
				result.setMsg("删除失败！");
			}
		}else{
			result.setMsg("没有该条记录~~!");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryShebaoSummary")
	@ResponseBody
	public Map<String, Object> queryShebaoSummary(QueryForm queryForm, HttpSession session){
		Map<String, Object> shebaoSummaries = employeeInfoService.getShebaoSummary(queryForm, session);
		return shebaoSummaries;
	}
	
	@RequestMapping(value = "/getShebaoCompany")
	@ResponseBody
	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session){
		if(queryForm!=null&&queryForm.getCompany()!=null){
			Map<String, Object> shebaoCompanys = employeeInfoService.getShebaoCompany(queryForm, session);
			return shebaoCompanys;
		}
		return null;
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
	
	@RequestMapping(value = "/deleteKaoqin")
	@ResponseBody
	public Json deleteKaoqin(PKaoQin pkaoqin){
		Json result = new Json();
		if(pkaoqin.getId()!=0){
			if(employeeInfoService.deleteKaoqin(pkaoqin)>0){
				result.setSuccess(true);
				result.setMsg("删除成功！~");
			}else{
				result.setMsg("删除失败！~");
			}
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
	public Json updateMonthWages(PMonthWages pmonthWages, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Json result = new Json();
		PMonthWages target = employeeInfoService.getMonthWages(pmonthWages.getId());
		if(sessionInfo.getRoleId()!=0&&sessionInfo.getRoleId()!=100){
			target.setChuqinDay(pmonthWages.getChuqinDay());
			target.setZhuanzhengChaeDay(pmonthWages.getZhuanzhengChaeDay());
			target.setFakuan(pmonthWages.getFakuan());
			target.setJiangjin(pmonthWages.getJiangjin());
			target.setBufaSalary(pmonthWages.getBufaSalary());
			target.setShiJiaHour(pmonthWages.getShiJiaHour());
			target.setBingJiaHour(pmonthWages.getBingJiaHour());
			target.setKuangGongHour(pmonthWages.getKuangGongHour());
			target.setChidaoYingkouDay(pmonthWages.getChidaoYingkouDay());
			target.setKaoQinremarks(pmonthWages.getKaoQinremarks());
		}else{
			target = pmonthWages;
		}
		caculateWages(target);
		if(employeeInfoService.updateMonthWages(pmonthWages, sessionInfo)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}else{
			result.setMsg("更新失败！");
		}
		return result;
	}
	private void caculateWages(PMonthWages pmonthWages) {
		Double salary = pmonthWages.getSalary();
		Float chuqin = pmonthWages.getChuqinDay();
		double chaeDay = pmonthWages.getZhuanzhengChaeDay()!=null ? pmonthWages.getZhuanzhengChaeDay():0;
		double zhuanzhengChae = 0.00;
		double lishiSalary = pmonthWages.getLishiSalary()!=null ? pmonthWages.getLishiSalary():0;
		double baomiSub = pmonthWages.getSecrecySubsidy()!=null?pmonthWages.getSecrecySubsidy():0;
		double lunchSub = pmonthWages.getLunchSubsidy()!=null?pmonthWages.getLunchSubsidy():0;
		double tongxunSub = pmonthWages.getCommunicationSubsidy()!=null?pmonthWages.getCommunicationSubsidy():0;
		double jiangjin = pmonthWages.getJiangjin()!=null?pmonthWages.getJiangjin():0;
		double bufaSalary = pmonthWages.getBufaSalary()!=null?pmonthWages.getBufaSalary():0;
		double fakuan = pmonthWages.getFakuan()!=null?pmonthWages.getFakuan():0;
		float shijia = (pmonthWages.getShiJiaHour()!=null)? pmonthWages.getShiJiaHour():0;
		//计算考勤总额
		Double bingjia = 0.0;
		if(pmonthWages.getSickLleaveTotal()<=24){
			bingjia =  ((pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal())>24?(pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal() -24):0);
		}else{
			bingjia =(double)pmonthWages.getBingJiaHour();
		}
		Float kuanggong = pmonthWages.getKuangGongHour();
		Float chidaoYingkou = pmonthWages.getChidaoYingkouDay();
		if(chidaoYingkou<=3){
			chidaoYingkou = chidaoYingkou * 20;
		}else{
			chidaoYingkou = chidaoYingkou * 50 - 150;
		}
		if(lishiSalary!=0){
			zhuanzhengChae = (salary - lishiSalary)/21 * (21 - chaeDay);
		}
		float kaoqinTotal = (float) (salary*shijia/168 + bingjia*salary/336 + kuanggong*salary/56 + chidaoYingkou + zhuanzhengChae) ;
		//计算社保代扣总额
		float subTotal = (float) (pmonthWages.getSubEndowmentIinsurance()+pmonthWages.getSubHouseIinsurance()+pmonthWages.getSubMedicare()+pmonthWages.getSubUnemployedInsurance());
		//应发工资
		float yingfa = (float) (salary*chuqin/21 -kaoqinTotal-subTotal + baomiSub + lunchSub + tongxunSub+jiangjin+bufaSalary-fakuan);
		//个税
		float selfTax = 0;
		if((yingfa - 3500)<=0){
			selfTax = 0;
		}else if(0<(yingfa-3500)&&(yingfa-3500)<=1500){
			selfTax = (float) ((yingfa-3500)*0.03);
		}else if((yingfa-3500)<=4500){
			selfTax = (float) ((yingfa-3500)*0.1 - 105);
		}else if((yingfa - 3500)<=9000){
			selfTax = (float) ((yingfa-3500)*0.2 - 555);
		}else if((yingfa - 3500)<=35000){
			selfTax = (float) ((yingfa-3500)*0.25 - 1005);
		}else if((yingfa - 3500)<=55000){
			selfTax = (float) ((yingfa-3500)*0.30 - 2755);
		}else if((yingfa - 3500)<=80000){
			selfTax = (float) ((yingfa-3500)*0.35 - 5505);
		}else{
			selfTax = (float) ((yingfa-3500)*0.45 - 13505);
		}
		pmonthWages.setSubTotal(subTotal);
		pmonthWages.setKaoqinTotal(kaoqinTotal);
		pmonthWages.setYingfaTotal(yingfa);
		pmonthWages.setSelfTax(selfTax);
		pmonthWages.setShifaTotal(yingfa-selfTax);
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
		if(pwagesDate.getDate()!=null){
			int savaNum = employeeInfoService.deleteWagesDate(pwagesDate);
			if(savaNum>0){
				result.setSuccess(true);
				result.setMsg(String.valueOf(savaNum));
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/generateKaoqin")
	@ResponseBody
	public Json generateKaoqin(){
		Json result = new Json();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
		Calendar cal = Calendar.getInstance();
		String afterDate = df.format(cal.getTime());
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
		System.out.println(date);
		int savaNum = employeeInfoService.generateKaoqin(date,preDate,afterDate);
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
