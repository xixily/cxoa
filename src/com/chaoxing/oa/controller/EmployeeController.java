package com.chaoxing.oa.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.config.SysConfig;
import com.chaoxing.oa.entity.page.Json;
import com.chaoxing.oa.entity.page.PComboBox;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PHouseholdType;
import com.chaoxing.oa.entity.page.PKaoQin;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.PMonthWages;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PQuickQuery;
import com.chaoxing.oa.entity.page.PShebao;
import com.chaoxing.oa.entity.page.PShebaoType;
import com.chaoxing.oa.entity.page.PSystemConfig;
import com.chaoxing.oa.entity.page.PWagesDate;
import com.chaoxing.oa.entity.page.PshebaoDetail;
import com.chaoxing.oa.entity.page.Pwage_;
import com.chaoxing.oa.entity.page.Pwages;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.entity.page.SF.KuaidiList;
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
	
	/*//TODO 方法貌似弃用了,有时间整理一下删除掉
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
	*/
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
	public Map<String,Object> queryJiagou(QueryForm queryform){
		
		Map<String,Object> osInfo = employeeInfoService.getOStruct(queryform,0);
		return osInfo;
	}
	
	@RequestMapping(value = "/queryStruct")
	@ResponseBody
	public Map<String,Object> queryStruct(QueryForm queryform){
		Map<String,Object> structInfo = employeeInfoService.findStruct(queryform,0);
		return structInfo;
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
//			System.out.println("xml:" + xml);
//			System.out.println("verifyCode:" + verifyCode);
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
		if(queryForm.getId()>0){
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
		PSystemConfig ps = employeeInfoService.getSysconfig(pwage.getCompany(), SysConfig.SHEBAO_SUMMARY);
		if(ps==null || ps.getLocked()==0){
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
		}else{
			result.setMsg("社保公司[" + pwage.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
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
	public Json updateWages(Pwage_ pwages, HttpSession session){
		//TODO 修改策略，更新的时候只更改部分字段。
		Json result = new Json();
//		PSystemConfig ps = employeeInfoService.getSysconfig(pwages.getCompany(), SysConfig.SHEBAO_SUMMARY);
//		if(ps==null || ps.getLocked()==0){
			if(pwages.getId()!=null&&pwages.getId()!=0){
				QueryForm queryForm = new QueryForm();
				queryForm.setId(pwages.getEmployeeId());
				SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
				String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
				if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
					Pwages mw = employeeInfoService.getWages(pwages.getId());
//					pwages.getSubEndowmentIinsurance(m);
					BeanUtils.copyProperties(pwages, mw);
					if(employeeInfoService.updateWages(mw)!=0){
						result.setSuccess(true);
						result.setMsg("更新成功！");
					}else{
						result.setMsg("更新失败！");
					}
				}
			}else{
				result.setMsg("没有找到该条记录！");
			}
//		}else{
//			result.setMsg("社保公司[" + pwages.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
//		}
		return result;
	}
	
	@RequestMapping(value = "/updateShebaoDetail")
	@ResponseBody
	public Json updateShebaoDetail(PshebaoDetail pshebaoDetail, HttpSession session){
		Json result = new Json();
		PSystemConfig ps = employeeInfoService.getSysconfig(pshebaoDetail.getCompany(), SysConfig.SHEBAO_SUMMARY);
		if(ps==null || ps.getLocked()==0){
			if(pshebaoDetail.getId()!=null&&pshebaoDetail.getId()!=0){
				QueryForm queryForm = new QueryForm();
				queryForm.setId(pshebaoDetail.getEmployeeId());
				Pwages pwages = new Pwages();
				BeanUtils.copyProperties(employeeInfoService.getWages(pshebaoDetail.getId()), pwages);
				String username = pwages.getUsername();
				String idcard = pwages.getIdentityCard();
				BeanUtils.copyProperties(pshebaoDetail, pwages);
				pwages.setUsername(username);//username和idcard字段不更新
				pwages.setIdentityCard(idcard);
				if(employeeInfoService.updateWages(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}else{
					result.setMsg("更新失败！");
				}
			}else{
				result.setMsg("没有找到该条记录！");
			}
		}else{
			result.setMsg("社保公司[" + pshebaoDetail.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateWagesRadix")
	@ResponseBody
	public Json updateWagesRadix(PshebaoDetail pwages){
		Json result = new Json();
		PSystemConfig ps = employeeInfoService.getSysconfig(pwages.getCompany(), SysConfig.SHEBAO_SUMMARY);
		if(ps==null || ps.getLocked()==0){
			if(pwages.getId()!=0){
				if(employeeInfoService.updateWagesRadix(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}else{
					result.setMsg("更新失败！");
				}
			}
		}else{
			result.setMsg("社保公司[" + pwages.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/addWages")
	@ResponseBody
	public Json addWages(Pwage_ pwages, HttpSession session){
		Json result = new Json();
//		PSystemConfig ps = employeeInfoService.getSysconfig(pwages.getCompany(), SysConfig.SHEBAO_SUMMARY);
//		if(ps!=null && ps.getLocked()==1&&(pwages.getRadix()!=0||pwages.getSubEndowmentIinsurance()!=0||pwages.getSubMedicare()!=0||
//				pwages.getSubUnemployedInsurance()!=0||pwages.getSubHouseIinsurance()!=0)){
//			result.setMsg("该社保公司已被社保管理员锁定，请您把社保基数置0，或者与社保管理员联系。");
//		}else{
			QueryForm queryForm = new QueryForm();
			queryForm.setId(pwages.getEmployeeId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
				Pwages pw = new Pwages();
				BeanUtils.copyProperties(pwages, pw);
				if(employeeInfoService.addWages(pw)!=0){
					result.setSuccess(true);
					result.setMsg("添加成功！");
				}else{
					result.setMsg("添加失败！");
				}
			}else{
				result.setMsg("对不起，您没有删除权限~！");
			}
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
		Json result = new Json();
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
	
	@RequestMapping(value = "/luckedShebaoSummary")
	@ResponseBody
	public Json luckedShebaoSummary(String company){
		Json result = new Json();
		if(company!=null&&!company.equals("")){
			PSystemConfig ps = new PSystemConfig();
			ps.setName(company);
			ps.setLocked((byte) 1);
			ps.setConfigType("shebaoSummary");
			if(employeeInfoService.updateSysconfig(ps)>0){
				result.setSuccess(true);
				result.setMsg("更新成功！~");
			}
		}else{
			result.setMsg("公司名称为空");
		}
		return result;
	}
	
	@RequestMapping(value = "/unluckeShebaoSummary")
	@ResponseBody
	public Json unluckeShebaoSummary(String company){
		Json result = new Json();
		if(company!=null&&!company.equals("")){
			PSystemConfig ps = new PSystemConfig();
			ps.setName(company);
			ps.setLocked((byte) 0);
			ps.setConfigType("shebaoSummary");
			if(employeeInfoService.updateSysconfig(ps)>0){
				result.setSuccess(true);
				result.setMsg("更新成功！~");
			}
		}else{
			result.setMsg("公司名称为空");
		}
		return result;
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
		if(caculateWages(target)==0){
			if(employeeInfoService.updateMonthWages(target, sessionInfo)!=0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setMsg("更新失败！");
			}
		}else{
			result.setMsg("计算工资失败,可能是转正报表有问题！~");
		}
		return result;
	}
	
	private int caculateWages(PMonthWages pmonthWages) {
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
		String zhuanZhengRport = pmonthWages.getZhuanzhengReport();
		//计算考勤总额
		Double bingjia = 0.0;
		if(pmonthWages.getSickLleaveTotal()==null){
			pmonthWages.setSickLleaveTotal(0d);
		}
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
		if(lishiSalary!=0 && chaeDay>0){
			zhuanzhengChae = (salary - lishiSalary)/21 * (21 - chaeDay);
		}
		float kaoqinTotal = (float) (salary*shijia/168 + bingjia*salary/336 + kuanggong*salary/56 + chidaoYingkou + zhuanzhengChae) ;
		//计算社保代扣总额
		float subTotal = (float) (pmonthWages.getSubEndowmentIinsurance()+pmonthWages.getSubHouseIinsurance()+pmonthWages.getSubMedicare()+pmonthWages.getSubUnemployedInsurance());
		if(zhuanZhengRport!=null && (!zhuanZhengRport.equals(""))){
			if(zhuanZhengRport.length() >= 6){
				zhuanZhengRport.substring(0, 6);
				try {
					Integer a = Integer.valueOf(zhuanZhengRport.substring(0, 6));
					SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
					Calendar cal = Calendar.getInstance();
					String afterDate = df.format(cal.getTime());
					Integer b = Integer.valueOf(afterDate);
					if(a>=b){
						salary = lishiSalary;
					}
				} catch (Exception e) {
					return -1;
				}
			}else{
				return -1;
			}
		}
	
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
		return 0;
	}
	
	public void caculateWages(PMonthWages pmonthWages, int i) {
		Double salary = pmonthWages.getSalary();
		BigDecimal b_salary = new BigDecimal(salary.toString());
		Float chuqin = pmonthWages.getChuqinDay();
		BigDecimal b_chuqin = new BigDecimal(chuqin.toString());
		double chaeDay = pmonthWages.getZhuanzhengChaeDay()!=null ? pmonthWages.getZhuanzhengChaeDay():0;
//		BigDecimal b_chaeDay = new BigDecimal(String.valueOf(chaeDay));
//		double zhuanzhengChae = 0.00;
		double lishiSalary = pmonthWages.getLishiSalary()!=null ? pmonthWages.getLishiSalary():0;
		BigDecimal b_lishiSalary = new BigDecimal(String.valueOf(lishiSalary));
		double baomiSub = pmonthWages.getSecrecySubsidy()!=null?pmonthWages.getSecrecySubsidy():0;
		BigDecimal b_baomiSub = new BigDecimal(String.valueOf(baomiSub));
		double lunchSub = pmonthWages.getLunchSubsidy()!=null?pmonthWages.getLunchSubsidy():0;
		BigDecimal b_lunchSub = new BigDecimal(String.valueOf(lunchSub));
		double tongxunSub = pmonthWages.getCommunicationSubsidy()!=null?pmonthWages.getCommunicationSubsidy():0;
		BigDecimal b_tongxunSub = new BigDecimal(String.valueOf(tongxunSub));
		double jiangjin = pmonthWages.getJiangjin()!=null?pmonthWages.getJiangjin():0;
		BigDecimal b_jiangjin = new BigDecimal(String.valueOf(jiangjin));
		double bufaSalary = pmonthWages.getBufaSalary()!=null?pmonthWages.getBufaSalary():0;
		BigDecimal b_bufaSalary = new BigDecimal(String.valueOf(bufaSalary));
		double fakuan = pmonthWages.getFakuan()!=null?pmonthWages.getFakuan():0;
		BigDecimal b_fakuan = new BigDecimal(String.valueOf(fakuan));
		float shijia = (pmonthWages.getShiJiaHour()!=null)? pmonthWages.getShiJiaHour():0;
		BigDecimal b_shijia = new BigDecimal(String.valueOf(shijia));
		//计算考勤总额
		Double bingjia = 0.0;
		if(pmonthWages.getSickLleaveTotal()<=24){
			bingjia =  ((pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal())>24?(pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal() -24):0);
		}else{
			bingjia =(double)pmonthWages.getBingJiaHour();
		}
		BigDecimal b_bingjia = new BigDecimal(bingjia.toString());
		Float kuanggong = pmonthWages.getKuangGongHour();
		BigDecimal b_kuanggong = new BigDecimal(kuanggong.toString());
		
		//迟到应扣
		Float chidaoYingkou = pmonthWages.getChidaoYingkouDay();
		if(chidaoYingkou<=3){
			chidaoYingkou = chidaoYingkou * 20;
		}else{
			chidaoYingkou = chidaoYingkou * 50 - 150;
		}
		BigDecimal b_chidaoYingkou = new BigDecimal(chidaoYingkou.toString());
		
		//转正差额
		BigDecimal b_zhuanzhengChae = new BigDecimal("0");
		if(lishiSalary!=0){
			b_zhuanzhengChae = b_salary.subtract(b_lishiSalary).divide(new BigDecimal("21")).multiply(new BigDecimal(String.valueOf(21-chaeDay)));
//			zhuanzhengChae = (salary - lishiSalary)/21 * (21 - chaeDay);
		}
		
		//计算考勤总额，包括转正差额
		BigDecimal b_kaoqinTotal = b_salary.multiply(b_shijia).divide(new BigDecimal("168")).
				add(b_bingjia.multiply(b_salary).divide(new BigDecimal("336"))).
				add(b_kuanggong.multiply(b_salary).divide(new BigDecimal("56"))).
				add(b_chidaoYingkou).
				add(b_zhuanzhengChae);
//		float kaoqinTotal = (float) (salary*shijia/168 + bingjia*salary/336 + kuanggong*salary/56 + chidaoYingkou + zhuanzhengChae) ;
		
		//计算社保代扣总额
//		float subTotal = (float) (pmonthWages.getSubEndowmentIinsurance()+pmonthWages.getSubHouseIinsurance()+pmonthWages.getSubMedicare()+pmonthWages.getSubUnemployedInsurance());
		BigDecimal b_subTotal = (new BigDecimal(pmonthWages.getSubEndowmentIinsurance().toString())).
				add(new BigDecimal(pmonthWages.getSubHouseIinsurance().toString())).
				add(new BigDecimal(pmonthWages.getSubMedicare().toString())).
				add(new BigDecimal(pmonthWages.getSubUnemployedInsurance().toString()));
		
		//应发工资
		BigDecimal b_yingfa = b_salary.multiply(b_chuqin).divide(new BigDecimal("21")).
				subtract(b_kaoqinTotal).
				subtract(b_subTotal).
				add(b_baomiSub).
				add(b_lunchSub).
				add(b_tongxunSub).
				add(b_jiangjin).
				add(b_bufaSalary).
				subtract(b_fakuan);
//		float yingfa = (float) (salary*chuqin/21 -kaoqinTotal-subTotal + baomiSub + lunchSub + tongxunSub+jiangjin+bufaSalary-fakuan);
		//个税
		BigDecimal b_selfTax = new BigDecimal("0.00");
		if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=1500){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.03")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=4500){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.10")).subtract(new BigDecimal("105")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=9000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.20")).subtract(new BigDecimal("555")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=35000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.35")).subtract(new BigDecimal("1005")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=55000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.30")).subtract(new BigDecimal("2755")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=80000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.35")).subtract(new BigDecimal("5505")));
		}else{
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.45")).subtract(new BigDecimal("13505")));
		}
//		float selfTax = 0;
//		if((yingfa - 3500)<=0){
//			selfTax = 0;
//		}else if(0<(yingfa-3500)&&(yingfa-3500)<=1500){
//			selfTax = (float) ((yingfa-3500)*0.03);
//		}else if((yingfa-3500)<=4500){
//			selfTax = (float) ((yingfa-3500)*0.1 - 105);
//		}else if((yingfa - 3500)<=9000){
//			selfTax = (float) ((yingfa-3500)*0.2 - 555);
//		}else if((yingfa - 3500)<=35000){
//			selfTax = (float) ((yingfa-3500)*0.25 - 1005);
//		}else if((yingfa - 3500)<=55000){
//			selfTax = (float) ((yingfa-3500)*0.30 - 2755);
//		}else if((yingfa - 3500)<=80000){
//			selfTax = (float) ((yingfa-3500)*0.35 - 5505);
//		}else{
//			selfTax = (float) ((yingfa-3500)*0.45 - 13505);
//		}
		pmonthWages.setSubTotal(b_subTotal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setKaoqinTotal(b_kaoqinTotal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setYingfaTotal(b_yingfa.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setSelfTax(b_selfTax.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setShifaTotal(b_yingfa.subtract(b_selfTax).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
//		pmonthWages.setShifaTotal(yingfa-selfTax);
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
//		System.out.println(date);
		QueryForm queryForm = new QueryForm();
		queryForm.setWagesMonth(date);
		PSystemConfig ps = employeeInfoService.findSysconfig(null, SysConfig.KAOQIN_BUTTON).get(0);
		if(ps!=null&&ps.getLocked()==0){
		if(((Long)employeeInfoService.queryWagesDate(queryForm).get("total"))>27){
			queryForm.setWagesMonth(preDate);
			if(((Long)employeeInfoService.queryWagesDate(queryForm).get("total"))>27){
				int savaNum = employeeInfoService.generateKaoqin(date,preDate,afterDate);
				if(savaNum>0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}
			}else{
				result.setMsg("请检查[" + preDate +"]的工作日~！");
			}
		}else{
			result.setMsg("请检查[" + date +"]的工作日~！");
		}
		}else{
			result.setMsg("生成考勤已加锁，请联系人事管理员解锁。");
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
	
	@RequestMapping(value = "/getIfGenerateKaoqin")
	@ResponseBody
	public Json getIfGenerate(){
		Json result = new Json();
		List<PSystemConfig> rs = employeeInfoService.findSysconfig("ifGenerate", SysConfig.KAOQIN_BUTTON);
		if(rs.size()>0){
			result.setSuccess(true);
			if(rs.get(0)!=null){
				result.setObj(rs.get(0).getLocked());
			}else{
				result.setObj(0);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/lockedKaoqin")
	@ResponseBody
	public Json lockedKaoqin(Boolean locked){
		System.out.println(locked);
		Json result = new Json();
		PSystemConfig  ps = new PSystemConfig();
		ps.setName("ifGenerate");
		ps.setConfigType(SysConfig.KAOQIN_BUTTON);
		if(locked){
			ps.setLocked((byte) 1);
		}else{
			ps.setLocked((byte) 0);
		}
		if(employeeInfoService.updateSysconfig(ps)>0){
			result.setSuccess(true);
			result.setMsg("更新成功！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/fangfa")
	@ResponseBody
	public Json fangfa(Boolean locked){
		System.out.println(locked);
		Json result = new Json();
		PSystemConfig  ps = new PSystemConfig();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		ps.setName("wages_fafang_" + date);
		ps.setConfigType(SysConfig.MONTHWAGE_FAFANG);
		ps.setLocked((byte) 1);
		if(employeeInfoService.updateSysconfig(ps)>0){
			if(employeeInfoService.fafang()>0){
				result.setSuccess(true);
				result.setMsg("更新成功！~");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "testDir.action")
	@ResponseBody
	public Json getDir(HttpServletRequest request){
		Json result = new Json();
//		String dir = request.
//		request.getRealPath("");
//		System.out.println(request.getRealPath(""));
		KuaidiList kl = new KuaidiList();
		kl.setAddService_name("增值服务名");
		kl.setAddService_value1("value1");
		kl.setAddService_value2("value2");
		kl.setContent("发票");
		kl.setCustid("66666");
		kl.setD_address("jxnu");
		kl.setD_company("江西师范大学");
		kl.setD_contact("Mrs deng");
		kl.setD_mobile("131346789");
		kl.setD_tel("0791-123456");
		kl.setDestcode("079");
		kl.setExpress_type(26);
		kl.setJ_address("BeiJing");
		kl.setJ_company("SJCX");
		kl.setJ_contact("Mrs deng");
		kl.setJ_tel("17745678913");
		kl.setMailno("123456789");
		kl.setRemark("备注");
		result.setObj(kl);
		return result;
	}

	@RequestMapping(value = "/getgongzihuizong")
	@ResponseBody
	public Map<String, Object>  getgongzihuizong(QueryForm queryform){
		Map<String, Object> pshebaos = employeeInfoService.getgongzihuizong(queryform);
		return pshebaos;
	}
	
	@RequestMapping(value = "/quickQuery")
	@ResponseBody
	public Map<String, Object>  quickQuery(PQuickQuery pquick, HttpSession session){
		if(pquick.getType()!=0){
			Map<String, Object> pshebaos = employeeInfoService.findRenshiQuick(pquick, session);
			return pshebaos;
		}
		return null;
	}
}
