package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.Pager;
import com.chaoxing.oa.entity.page.RenshiEmployeeInfo;

public interface EmployeeInfoServiceI {
	public List<RenshiEmployeeInfo> getRenshiUserName();

	public List<RenshiEmployeeInfo> getRenshiUserName(RenshiEmployeeInfo page);

	public long getRenshiUserNameCount();
}
