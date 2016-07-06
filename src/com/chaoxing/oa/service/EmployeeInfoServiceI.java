package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.RenshiEmployeeInfo;
import com.chaoxing.oa.entity.page.UserInfo;

public interface EmployeeInfoServiceI {
	public List<RenshiEmployeeInfo> getRenshiUserName();

	public Map<String, Object> getRenshiUserName(UserInfo page);

	public long getRenshiUserNameCount(String hql, Map<String, Object> params);
}
