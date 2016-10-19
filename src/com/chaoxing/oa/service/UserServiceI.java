package com.chaoxing.oa.service;


import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;

import java.util.List;

import com.chaoxing.oa.entity.page.PUserName;

public interface UserServiceI {

	public SessionInfo findUser(QueryForm userInfo);
	
	public PUserName getUserName(int id);

	public int deleteUserName(QueryForm queryForm);

	public long addUserName(PUserName username);

	public long updateUserName(PUserName username);

	public long updateUserRole(PUserName username);

	public int updateSecret(PUserName username);

	public List<String> finRoleResoures(int roleId);

	public long updatePassword(QueryForm queryForm);

}
