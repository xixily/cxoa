package com.chaoxing.oa.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.UserInfo;
import com.chaoxing.oa.entity.po.UserName;
import com.chaoxing.oa.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private BaseDaoI<UserName> usernameDao;

	public BaseDaoI<UserName> getUsernameDao() {
		return usernameDao;
	}

	@Autowired
	public void setUsernameDao(BaseDaoI<UserName> usernameDao) {
		this.usernameDao = usernameDao;
	}

	
	/**
	 * 查找一个userName信息copy到UserInfo中
	 */
	@Override
	public UserInfo findUser(UserInfo userPageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		UserName userName;
		params.put("email", userPageInfo.getEmail());
		userName = usernameDao.get("from UserName u where u.email=:email",params);
		if(null != userName){
			BeanUtils.copyProperties(userName, userPageInfo);
			userPageInfo.setRights(userName.getRoleId());
			return userPageInfo;
		}
		return null;
	}

	
	
}
