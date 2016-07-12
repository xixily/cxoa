package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.PUser;

public interface EmployeeInfoServiceI {
	public List<PRenshiEmployee> getRenshiUserName();

	public Map<String, Object> getRenshiUserName(PUser page);

	public long getRenshiUserNameCount(String hql, Map<String, Object> params);

	public Map<String, Object> getQueryForm();
}
