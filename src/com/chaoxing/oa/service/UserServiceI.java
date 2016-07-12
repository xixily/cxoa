package com.chaoxing.oa.service;


import com.chaoxing.oa.entity.page.PUser;
import com.chaoxing.oa.entity.page.PUserName;

public interface UserServiceI {

	public PUser findUser(PUser userInfo);
	
	public PUserName getUserName(int id);

	public int deleteUserName(int id);

	public long addUserName(PUserName username);

	public long updateUserName(PUserName username);

}
