package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.PComboBox;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.po.Company;
import com.chaoxing.oa.entity.po.Level;
import com.chaoxing.oa.entity.po.OrganizationStructure;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.service.EmployeeInfoServiceI;

@Service("employeeInfoService")
public class EmployeeInfoServiceImpl implements EmployeeInfoServiceI {

	private BaseDaoI<RenshiUserName> userNameDao;
	private BaseDaoI<Object> objectDao;
	private BaseDaoI<Company> companyDao;
	private BaseDaoI<Level> levelDao;//级别
	private BaseDaoI<OrganizationStructure> organizationStructureDao;//组织结构


	
	
	public BaseDaoI<Object> getObjectDao() {
		return objectDao;
	}

	@Autowired
	public void setObjectDao(BaseDaoI<Object> objectDao) {
		this.objectDao = objectDao;
	}

	public BaseDaoI<OrganizationStructure> getOrganizationStructureDao() {
		return organizationStructureDao;
	}

	@Autowired
	public void setOrganizationStructureDao(BaseDaoI<OrganizationStructure> organizationStructureDao) {
		this.organizationStructureDao = organizationStructureDao;
	}

	public BaseDaoI<Company> getCompanyDao() {
		return companyDao;
	}

	public BaseDaoI<Level> getLevelDao() {
		return levelDao;
	}
	@Autowired
	public void setCompanyDao(BaseDaoI<Company> companyDao) {
		this.companyDao = companyDao;
	}
	@Autowired
	public void setLevelDao(BaseDaoI<Level> levelDao) {
		this.levelDao = levelDao;
	}


	public BaseDaoI<RenshiUserName> getUserNameDao() {
		return userNameDao;
	}

	@Autowired
	public void setUserNameDao(BaseDaoI<RenshiUserName> userNameDao) {
		this.userNameDao = userNameDao;
	}

	@Override
	public List<PRenshiEmployee> getRenshiUserName() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<PRenshiEmployee> renshiEmployeeInfos = new ArrayList<PRenshiEmployee>();
		params.put("fourthLevel", "常规数据");
		List<RenshiUserName> renshiUsernames = userNameDao
				.find("from RenshiUserName r where r.fourthLevel=:fourthLevel", params);
		for (RenshiUserName renshiUserName : renshiUsernames) {
			PRenshiEmployee renshiEmployeeInfo = new PRenshiEmployee();
			BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
//			renshiEmployeeInfo.setId(renshiUserName.getID());
			renshiEmployeeInfos.add(renshiEmployeeInfo);
		}
		return renshiEmployeeInfos;
	}

	@Override
	public Map<String, Object> getRenshiUserName(QueryForm page) {
		System.out.println(page);
		List<PRenshiEmployee> renshiEmployeeInfos = new ArrayList<PRenshiEmployee>();
		Map<String, Object> userInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from RenshiUserName t where 1=1 ");
		addCondition(hql, page, params);
		hql.append(" order by t.id asc");
		int intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
		int pageSize = (page == null || page.getRows() == 0) ? 100 : page.getRows();
		List<RenshiUserName> renshiUsernames = userNameDao.find(hql.toString(), params, intPage, pageSize);
		for (RenshiUserName renshiUserName : renshiUsernames) {
			PRenshiEmployee renshiEmployeeInfo = new PRenshiEmployee();
			BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
			renshiEmployeeInfos.add(renshiEmployeeInfo);
		}
		long total = getRenshiUserNameCount(hql.toString(),params);
		userInfos.put("total", total);
		userInfos.put("rows", renshiEmployeeInfos);
		return userInfos;
	}

	protected void addCondition(StringBuffer hql, QueryForm page, Map<String, Object> params) {
		if(page != null){
			if(page.getConfigurable() != null && page.getConfigurable() != ""){
				if(page.getConfigurable_value() != null && page.getConfigurable_value() != ""){
					hql.append(" and t." + page.getConfigurable() + " like '%" + page.getConfigurable_value() + "%' ");
				}
			}
			if(page.getUsername() != null && page.getUsername() != ""){
				hql.append(" and t.username like :username ");
				params.put("username", "%" + page.getUsername() + "%");
			}
			if(page.getFourthLevel() != null && page.getFourthLevel() != ""){
				hql.append(" and t.fourthLevel like :fourthLevel ");
				params.put("fourthLevel", "%" + page.getFourthLevel() + "%");
			}
			if(page.getCompany() != null && page.getCompany() != ""){
				hql.append(" and t.company like :company ");
				params.put("company", "%" + page.getCompany() + "%");
			}
			if(page.getInsuranceCompany() != null && page.getInsuranceCompany() != ""){
				hql.append(" and t.insuranceCompany like :insuranceCompany ");
				params.put("insuranceCompany", "%" + page.getInsuranceCompany() + "%");
			}
			if(page.getDegree() != null && page.getDegree() != ""){
				hql.append(" and t.degree like :degree ");
				params.put("degree", "%" + page.getDegree() + "%");
			}
			if(page.getEarlyEntryDate() != null && page.getEarlyEntryDate() != ""){
				hql.append(" and t.earlyEntryDate like :earlyEntryDate ");
				params.put("earlyEntryDate", "%" + page.getEarlyEntryDate() + "%");
			}
			if(page.getLeaveTime() != null && page.getLeaveTime() != ""){
				hql.append(" and t.leaveTime like :leaveTime ");
				params.put("leaveTime", "%" + page.getLeaveTime() + "%");
			}
			if(page.getZhuanzhengTime() != null && page.getZhuanzhengTime() != ""){
				hql.append(" and t.zhuanzhengTime like :zhuanzhengTime ");
				params.put("zhuanzhengTime", "%" + page.getZhuanzhengTime() + "%");
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

	@Override
	public Map<String, Object> getQueryForm() {
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("levels", getLevel());
		results.put("companys", getCompany());
		return results;
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
	public List<PComboBox> getForthLevel() {
		List<OrganizationStructure> lists = organizationStructureDao.find("SELECT distinct o.thirdLevel from OrganizationStructure o");
		List<PComboBox> listComs = new ArrayList<PComboBox>();
		for (OrganizationStructure organizationStructure : lists) {
			PComboBox comb = new PComboBox();
			comb.setValue(organizationStructure.getFourthLevel());
			comb.setText(organizationStructure.getFourthLevel());
			listComs.add(comb);
		}
		return listComs;
	}

	@Override
	public List<POStructs> getOStruct() {
		List<POStructs> listComs = new ArrayList<POStructs>();
		List<OrganizationStructure> lists = organizationStructureDao.find("from OrganizationStructure o");
		for (OrganizationStructure organizationStructure : lists) {
			POStructs postruct = new POStructs();
			BeanUtils.copyProperties(organizationStructure, postruct);
			postruct.setDepartmentId(organizationStructure.getId());
			listComs.add(postruct);
		}
		return listComs;
	}

	@Override
	public List<PComboBox> getInsuranceCompany() {
		List<Object> lists = objectDao.find("select distinct(u.insuranceCompany) from UserName u");
		List<PComboBox> pcbs = new ArrayList<PComboBox>();
		for (Object renshiUserName : lists) {
			PComboBox pcb = new PComboBox();
			pcb.setText((String)renshiUserName);
			pcb.setValue((String)renshiUserName);
			pcbs.add(pcb);
		}
		return pcbs;
	}
}
