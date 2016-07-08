package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.UserInfo;
import com.chaoxing.oa.entity.po.Company;
import com.chaoxing.oa.entity.po.Level;
import com.chaoxing.oa.entity.po.UserName;
import com.chaoxing.oa.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private BaseDaoI<UserName> usernameDao;
	private BaseDaoI<Company> companyDao;
	private BaseDaoI<Level> levelDao;

	public BaseDaoI<UserName> getUsernameDao() {
		return usernameDao;
	}

	@Autowired
	public void setUsernameDao(BaseDaoI<UserName> usernameDao) {
		this.usernameDao = usernameDao;
	}

	
	public BaseDaoI<Company> getCompanyDao() {
		return companyDao;
	}
	@Autowired
	public void setCompanyDao(BaseDaoI<Company> companyDao) {
		this.companyDao = companyDao;
	}

	public BaseDaoI<Level> getLevelDao() {
		return levelDao;
	}
	@Autowired
	public void setLevelDao(BaseDaoI<Level> levelDao) {
		this.levelDao = levelDao;
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

	@Override
	public List<PCompany> getCompany() {
		List<Company> cmopanys = companyDao.find("from Company");
		List<PCompany> pcompanys = new ArrayList<PCompany>();
		for (Company company : cmopanys) {
			PCompany pcompany = new PCompany();
			BeanUtils.copyProperties(company, pcompany);
			pcompanys.add(pcompany);
		}
		return pcompanys;
	}

	@Override
	public List<PLevel> getLevel() {
		List<Level> levels = levelDao.find("from Level");
		List<PLevel> plevels = new ArrayList<PLevel>();
		for (Level level : levels) {
			PLevel plevel = new PLevel();
			BeanUtils.copyProperties(level, plevel);
			plevels.add(plevel);
		}
		return plevels;
	}

	
}
