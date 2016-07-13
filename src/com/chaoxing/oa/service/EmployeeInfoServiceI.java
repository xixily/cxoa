package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.PComboBox;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.QueryForm;

public interface EmployeeInfoServiceI {
	public List<PRenshiEmployee> getRenshiUserName();

	public Map<String, Object> getRenshiUserName(QueryForm page);

	public long getRenshiUserNameCount(String hql, Map<String, Object> params);

	public Map<String, Object> getQueryForm();
	
	public List<PCompany> getCompany();

	public List<PComboBox> getForthLevel();

	public List<POStructs> getOStruct();
	
	public List<PLevel> getLevel();

	public List<PComboBox> getInsuranceCompany();
}
