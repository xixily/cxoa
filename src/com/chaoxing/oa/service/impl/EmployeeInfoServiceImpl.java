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
import com.chaoxing.oa.entity.page.UserInfo;
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
	public Map<String, Object> getRenshiUserName(UserInfo page) {
		System.out.println(page);
		List<RenshiEmployeeInfo> renshiEmployeeInfos = new ArrayList<RenshiEmployeeInfo>();
		Map<String, Object> userInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from RenshiUserName t where 1=1 ");
		addCondition(hql, page, params);
		hql.append(" order by t.ID asc");
		int intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
		int pageSize = (page == null || page.getRows() == 0) ? 100 : page.getRows();
		List<RenshiUserName> renshiUsernames = userNameDao.find(hql.toString(), params, intPage, pageSize);
//		List<RenshiUserName> renshiUsernames = userNameDao
//				.find("from RenshiUserName t where t.fourthLevel=:fourthLevel", params, intPage, pageSize);
		for (RenshiUserName renshiUserName : renshiUsernames) {
			RenshiEmployeeInfo renshiEmployeeInfo = new RenshiEmployeeInfo();
			BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
			renshiEmployeeInfo.setId(renshiUserName.getID());
			renshiEmployeeInfos.add(renshiEmployeeInfo);
		}
		long total = getRenshiUserNameCount(hql.toString(),params);
		userInfos.put("total", total);
		userInfos.put("rows", renshiEmployeeInfos);
		return userInfos;
	}

	protected void addCondition(StringBuffer hql, UserInfo page, Map<String, Object> params) {
		if(page != null){
			if(page.getUsername() != null && page.getUsername() != ""){
				hql.append(" and t.username like :username ");
				params.put("username", "%" + page.getUsername() + "%");
			}
			if(page.getIdentityCard() != null && page.getIdentityCard() != ""){
				hql.append(" and t.identityCard like :identityCard ");
				params.put("identityCard", "%" + page.getIdentityCard() + "%");
			}
			if(page.getCompany() != null && page.getCompany() != ""){
				hql.append(" and t.company like :company ");
				params.put("company", "%" + page.getCompany() + "%");
			}
			if(page.getPosition() != null && page.getPosition() != ""){
				hql.append(" and t.position like :position ");
				params.put("position", "%" + page.getPosition() + "%");
			}
			if(page.getLevel() != null && page.getLevel() != ""){
				hql.append(" and t.level like :level ");
				params.put("level", "%" + page.getLevel() + "%");
			}
			if(page.getHiredate() != null && page.getHiredate() != ""){
				hql.append(" and t.hiredate like :hiredate ");
				params.put("hiredate", "%" + page.getHiredate() + "%");
			}
			if(page.getLeaveTime() != null && page.getLeaveTime() != ""){
				hql.append(" and t.leaveTime like :leaveTime% ");
				params.put("leaveTime", "%" + page.getLeaveTime() + "%");
			}
			if(page.getZhuanzhengTime() != null && page.getZhuanzhengTime() != ""){
				hql.append(" and t.zhuanzhengTime like :zhuanzhengTime% ");
				params.put("zhuanzhengTime", "%" + page.getZhuanzhengTime() + "%");
			}
			if(page.getDueSocialSecurity() != null && page.getDueSocialSecurity() != ""){
				hql.append(" and t.dueSocialSecurity like :dueSocialSecurity ");
				params.put("dueSocialSecurity", "%" + page.getDueSocialSecurity() + "%");
			}
		}
	}

	@Override
	public long getRenshiUserNameCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from RenshiUserName t where ");
		hqll.append(hql.split("where")[1]);
		System.out.println("+++++++hqll+++++employeeInfoServiceImpl.getRenshiusernameCount" + hqll.toString());
		return userNameDao.count(hqll.toString(), params);
	}
}
