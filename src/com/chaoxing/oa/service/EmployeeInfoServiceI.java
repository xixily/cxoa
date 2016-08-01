package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.chaoxing.oa.entity.page.PComboBox;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PHouseholdType;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.PShebao;
import com.chaoxing.oa.entity.page.PShebaoType;
import com.chaoxing.oa.entity.page.Pwages;
import com.chaoxing.oa.entity.page.QueryForm;

public interface EmployeeInfoServiceI {
	public List<PRenshiEmployee> getRenshiUserName();

	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session);
	
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session, int isExport);

	public long getRenshiUserNameCount(String hql, Map<String, Object> params);

	public Map<String, Object> getQueryForm();
	
	public List<PCompany> getCompany();

	public List<PComboBox> getForthLevel();

	public List<POStructs> getOStruct();
	
	public List<PLevel> getLevel();

	public List<PComboBox> getInsuranceCompany();

	public List<Pwages> getWagesList(int id);

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

}
