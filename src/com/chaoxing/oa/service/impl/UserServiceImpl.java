package com.chaoxing.oa.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.entity.page.PUserName;
import com.chaoxing.oa.entity.po.OrganizationStructure;
import com.chaoxing.oa.entity.po.UserName;
import com.chaoxing.oa.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private BaseDaoI<UserName> usernameDao;
	private BaseDaoI<OrganizationStructure> ogsDao;

	
	public BaseDaoI<OrganizationStructure> getOgsDao() {
		return ogsDao;
	}
	@Autowired
	public void setOgsDao(BaseDaoI<OrganizationStructure> ogsDao) {
		this.ogsDao = ogsDao;
	}

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
	public SessionInfo findUser(QueryForm userPageInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", userPageInfo.getEmail());
		UserName userName = usernameDao.get("from UserName u where u.email=:email",params);
		if(null != userName){
			SessionInfo sessioninfo = new SessionInfo();
			BeanUtils.copyProperties(userName, sessioninfo);
//			BeanUtils.copyProperties(userName, userPageInfo);
//			sessioninfo.setRights(userName.getRoleId());
			return sessioninfo;
		}
		return null;
	}

	@Override
	public PUserName getUserName(int id) {
		PUserName pusername = null; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		UserName username = usernameDao.get("from UserName u where u.id = :id", params);
		if(username!=null){
			OrganizationStructure ogs = ogsDao.get("from OrganizationStructure o where o.id=" + username.getDepartmentId());
			pusername = new PUserName();
			BeanUtils.copyProperties(username, pusername);
			BeanUtils.copyProperties(ogs, pusername);//这里部门ID传入pusername了
			pusername.setId(username.getId());
		}
		return pusername;
	}

	@Override
	public int deleteUserName(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		UserName username = usernameDao.get("from UserName u where u.id = :id", params);
		try {
			usernameDao.delete(username);
		} catch (Exception e) {
			System.out.println("delete 失败！");
			return 0;
		}
		return 1;
	}

	@Override
	public long addUserName(PUserName username) {
		UserName u = new UserName();
		BeanUtils.copyProperties(username, u);
		try {
			Integer re = (Integer) usernameDao.save(u);
			return re;
		} catch (Exception e) {
			System.out.println("添加失败：" + e);
			return -1;
		}
	}

	@Override
	public long updateUserName(PUserName username) {
		UserName u = new UserName();
		BeanUtils.copyProperties(username, u);
		try {
			usernameDao.update(u);
		} catch (Exception e) {
			System.out.println("更新失败：" + e);
			return -1;
		}
		return 1;
	}
}
