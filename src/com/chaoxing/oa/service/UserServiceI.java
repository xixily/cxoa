package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.UserInfo;

public interface UserServiceI {

	public UserInfo findUser(UserInfo userInfo);

	public List<PCompany> getCompany();
	
	public List<PLevel> getLevel();
}
