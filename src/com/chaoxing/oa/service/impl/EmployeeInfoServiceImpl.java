package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.RenshiEmployeeInfo;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.service.EmployeeInfoServiceI;

@Service("employeeInfoService")
public class EmployeeInfoServiceImpl implements EmployeeInfoServiceI {

	private BaseDaoI<RenshiUserName> userNameDao;
	private BaseDaoI<Long> countDao;


	public BaseDaoI<Long> getCountDao() {
		return countDao;
	}

	@Autowired
	public void setCountDao(BaseDaoI<Long> countDao) {
		this.countDao = countDao;
	}

	public BaseDaoI<RenshiUserName> getUserNameDao() {
		return userNameDao;
	}

	@Autowired
	public void setUserNameDao(BaseDaoI<RenshiUserName> userNameDao) {
		this.userNameDao = userNameDao;
	}

	@Override
	public List<RenshiEmployeeInfo> getRenshiUserName() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<RenshiEmployeeInfo> renshiEmployeeInfos = new ArrayList<RenshiEmployeeInfo>();
		params.put("fourthLevel", "常规数据");
		List<RenshiUserName> renshiUsernames = userNameDao
				.find("from RenshiUserName r where r.fourthLevel=:fourthLevel", params);
		for (RenshiUserName renshiUserName : renshiUsernames) {
			RenshiEmployeeInfo renshiEmployeeInfo = new RenshiEmployeeInfo();
			BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
			renshiEmployeeInfo.setId(renshiUserName.getID());
			renshiEmployeeInfos.add(renshiEmployeeInfo);
		}
		return renshiEmployeeInfos;
	}

	@Override
	public List<RenshiEmployeeInfo> getRenshiUserName(RenshiEmployeeInfo page) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<RenshiEmployeeInfo> renshiEmployeeInfos = new ArrayList<RenshiEmployeeInfo>();
		params.put("fourthLevel", "常规数据");
		int intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
		int pageSize = (page == null || page.getRows() == 0) ? 10 : page.getRows();
		System.out.println("========>>>>intPage:" + intPage +"############>>>>pageSize:" + pageSize);
		List<RenshiUserName> renshiUsernames = userNameDao
				.find("from RenshiUserName r where r.fourthLevel=:fourthLevel", params, intPage, pageSize);
		for (RenshiUserName renshiUserName : renshiUsernames) {
			RenshiEmployeeInfo renshiEmployeeInfo = new RenshiEmployeeInfo();
			BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
			renshiEmployeeInfo.setId(renshiUserName.getID());
			renshiEmployeeInfos.add(renshiEmployeeInfo);
		}
		return renshiEmployeeInfos;
	}

	@Override
	public long getRenshiUserNameCount() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fourthLevel", "常规数据");
//		List<Long> list = countDao.find("select count(*) from RenshiUserName r where r.fourthLevel=:fourthLevel", params);
		return userNameDao.count("select count(*) from RenshiUserName r where r.fourthLevel=:fourthLevel", params);
//		return (long) list.get(0);
	}
	
	/*private String addWhere(RenshiUserName renshiUserName, String hql, Map<String, Object> params) {
		hql += " where 1=1 ";
		if (bug.getName() != null && !bug.getName().trim().equals("")) {
			hql += " and t.name like '%%" + bug.getName().trim() + "%%'";
		}
		return hql;
	}*/
}
