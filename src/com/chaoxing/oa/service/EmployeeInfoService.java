package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.chaoxing.oa.entity.page.common.PComboBox;
import com.chaoxing.oa.entity.page.common.PCompany;
import com.chaoxing.oa.entity.page.common.PHouseholdType;
import com.chaoxing.oa.entity.page.common.PLevel;
import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.common.POStructs;
import com.chaoxing.oa.entity.page.common.PQuickQuery;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PShebao;
import com.chaoxing.oa.entity.page.employee.PShebaoType;
import com.chaoxing.oa.entity.page.employee.PWagesDate;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.entity.page.employee.Pwages;
import com.chaoxing.oa.entity.page.system.PSystemConfig;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.employee.UserName;

public interface EmployeeInfoService {
	public List<PRenshiEmployee> getRenshiUserName();

	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session);
	
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session, int isExport);
	
	public Map<String, Object> findRenshiQuick(PQuickQuery pquick, HttpSession session);

	public long getRenshiUserNameCount(String hql, Map<String, Object> params);

	public Map<String, Object> getQueryForm();
	
	public List<PCompany> getCompany();

	public List<PComboBox> getForthLevel();

	public List<POStructs> findOStruct();
	
	public List<PLevel> getLevel();

	public List<PComboBox> getInsuranceCompany();

	public List<Pwages> getWagesList(int id,SessionInfo sessionInfo, String ifSecret);

	public Pwages getWages(int id);

	public int updateWages(Pwages pwages);

	public Map<String, Object> getAllShebaoRadio(QueryForm queryForm);

	public List<PShebao> getShebaoRadioByCompany(String company);

	public List<PShebaoType> getShebaoType();

	public int addWages(Pwages pwages);

	public int deleteWages(Pwages pwages);

	public int updateShebao(PShebao pshebao);

	public List<PHouseholdType> getHouseholdType();

	public int addShebao(PShebao pshebao);

	public int deleteShebao(PShebao pshebao);

	public Map<String, Object> getShebaoSummary(QueryForm queryForm, HttpSession session);
	
	public Map<String, Object> getShebaoSummary(QueryForm queryForm, HttpSession session, int isExport);
	
//	public List<PSystemConfig> findShebaoSumaryLock();

	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session);
	
	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session, int isExport);
	
	 public long getWageDistributionCount(String hql, Map<String, Object> params);

	public int updateWagesRadix(PshebaoDetail pwages);

	public Map<String, Object> findKaoqin(QueryForm queryForm, HttpSession session);
	
	public Map<String, Object> findKaoqin(QueryForm queryForm, HttpSession session, int isExport);
	
	public long getKaoQinCount(String hql, Map<String,Object> params);

	public int updateKaoqin(PKaoQin pkaoqin);

	public Map<String, Object> findMonthWages(QueryForm queryForm, HttpSession session);
	
	public Map<String, Object> findMonthWages(QueryForm queryForm, HttpSession session, int isExport);

	public int updateMonthWages(PMonthWages pmonthWages, SessionInfo sessionInfo);
	
	public long getMonthWagesCount(String string, Map<String, Object> params);

	public Map<String, Object> queryWagesDate(QueryForm queryForm);

	public int updateWagesDates(List<PWagesDate> pwagesDates);

	public int updateWagesDate(PWagesDate pwagesDate);

	public int generateKaoqin(String date, String preDate, String afterDate);

	public int generateMonthWages();

	public UserName getUserInfo(QueryForm queryForm);

	public int deleteKaoqin(PKaoQin pkaoqin);

	public int deleteWagesDate(PWagesDate pwagesDate);

	public PMonthWages getMonthWages(Integer id);

//	public Map<String,Object> getOStruct(QueryForm queryform,int isExport);
	//重写
	public List<POStructV> findOStruct(POStructV pOStructV,int isExport);
	
	public Map<String,Object> findAllStruct(POStructV pOStructV);
	
	public int updateOrSave(POStructV poStructV);

	public int deleteOS(POStructV poStructV);

	public long getOStructCount(String hql, Map<String, Object> params);

//	public Map<String, Object> findStruct(QueryForm queryform, int isExport);

	public long updateSysconfig(PSystemConfig ps);
	
	/**
	 * 
	 * @param name
	 * @param type required
	 * @return
	 */
	public List<PSystemConfig> findSysconfig(String name, String type);
	
	public PSystemConfig getSysconfig(String name, String type);

	public long fafang();
	
	public Map<String, Object> getgongzihuizong(QueryForm queryForm);



}
