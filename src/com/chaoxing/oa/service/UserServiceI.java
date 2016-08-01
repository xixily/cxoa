package com.chaoxing.oa.service;


import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.entity.page.PUserName;

public interface UserServiceI {

	public SessionInfo findUser(QueryForm userInfo);
	
	public PUserName getUserName(int id);

	public int deleteUserName(QueryForm queryForm);

	public long addUserName(PUserName username);

	public long updateUserName(PUserName username);

	public long updateUserRole(PUserName username);

}
