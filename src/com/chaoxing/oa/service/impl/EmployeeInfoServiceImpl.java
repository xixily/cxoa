package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.config.SysConfig;
import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.PComboBox;
import com.chaoxing.oa.entity.page.PCompany;
import com.chaoxing.oa.entity.page.PHouseholdType;
import com.chaoxing.oa.entity.page.PLevel;
import com.chaoxing.oa.entity.page.POStructs;
import com.chaoxing.oa.entity.page.PRenshiEmployee;
import com.chaoxing.oa.entity.page.PShebao;
import com.chaoxing.oa.entity.page.PShebaoType;
import com.chaoxing.oa.entity.page.Pwages;
import com.chaoxing.oa.entity.page.QueryForm;
import com.chaoxing.oa.entity.page.SessionInfo;
import com.chaoxing.oa.entity.po.Company;
import com.chaoxing.oa.entity.po.HouseholdType;
import com.chaoxing.oa.entity.po.Level;
import com.chaoxing.oa.entity.po.OrganizationStructure;
import com.chaoxing.oa.entity.po.Shebao;
import com.chaoxing.oa.entity.po.ShebaoType;
import com.chaoxing.oa.entity.po.WageDistribution;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.service.EmployeeInfoServiceI;
import com.chaoxing.oa.util.ResourceUtil;

import sun.util.logging.resources.logging;

@Service("employeeInfoService")
public class EmployeeInfoServiceImpl implements EmployeeInfoServiceI {

	private BaseDaoI<RenshiUserName> userNameDao;
	private BaseDaoI<Object> objectDao;
	private BaseDaoI<Company> companyDao;
	private BaseDaoI<Level> levelDao;//级别
	private BaseDaoI<OrganizationStructure> organizationStructureDao;//组织结构
	private BaseDaoI<WageDistribution> wageDistributionDao;
	private BaseDaoI<Shebao> sheBaoDao;
	private BaseDaoI<ShebaoType> sheBaoTypeDao;
	private BaseDaoI<HouseholdType> househodlType;

	
	public BaseDaoI<HouseholdType> getHousehodlType() {
		return househodlType;
	}
	@Autowired
	public void setHousehodlType(BaseDaoI<HouseholdType> househodlType) {
		this.househodlType = househodlType;
	}
	public BaseDaoI<ShebaoType> getSheBaoTypeDao() {
		return sheBaoTypeDao;
	}
	@Autowired
	public void setSheBaoTypeDao(BaseDaoI<ShebaoType> sheBaoTypeDao) {
		this.sheBaoTypeDao = sheBaoTypeDao;
	}
	public BaseDaoI<Shebao> getSheBaoDao() {
		return sheBaoDao;
	}
	@Autowired
	public void setSheBaoDao(BaseDaoI<Shebao> sheBaoDao) {
		this.sheBaoDao = sheBaoDao;
	}
	public BaseDaoI<WageDistribution> getWageDistributionDao() {
		return wageDistributionDao;
	}
	@Autowired
	public void setWageDistributionDao(BaseDaoI<WageDistribution> wageDistributionDao) {
		this.wageDistributionDao = wageDistributionDao;
	}

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
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session) {
		return getRenshiUserName(queryForm, session, 0);
	}
	
	@Override
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session, int isExport) {
		System.out.println(queryForm);
		List<PRenshiEmployee> renshiEmployeeInfos = new ArrayList<PRenshiEmployee>();
		Map<String, Object> userInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from RenshiUserName t where 1=1 ");
		addCondition(hql, queryForm, params);
		SessionInfo userInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(userInfo.getRoleId() > 1){
			hql.append(" and t.renshiRight like :renshiRight ");
			params.put("renshiRight", "%" + userInfo.getUsername() + "%");
		}
		String sort = "id";
		String order = SysConfig.DESC;
//		String order = SysConfig.ASC;
		if(queryForm.getSort() != null){
			sort = queryForm.getSort();
			if(queryForm.getOrder() != null){
				order = queryForm.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(isExport == 0){
			intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
			pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		}
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

	protected void addCondition(StringBuffer hql, QueryForm queryForm, Map<String, Object> params) {
		if(queryForm != null){
			if(queryForm.getConfigurable() != null && queryForm.getConfigurable() != ""){
				if(queryForm.getConfigurable_value() != null && queryForm.getConfigurable_value() != ""){
					hql.append(" and t." + queryForm.getConfigurable() + " like '%" + queryForm.getConfigurable_value() + "%' ");
				}
			}
			if(queryForm.getUsername() != null && queryForm.getUsername() != ""){
				hql.append(" and t.username like :username ");
				params.put("username", "%" + queryForm.getUsername() + "%");
			}
			if(queryForm.getFourthLevel() != null && queryForm.getFourthLevel() != ""){
				hql.append(" and t.fourthLevel like :fourthLevel ");
				params.put("fourthLevel", "%" + queryForm.getFourthLevel() + "%");
			}
			if(queryForm.getCompany() != null && queryForm.getCompany() != ""){
				hql.append(" and t.company like :company ");
				params.put("company", "%" + queryForm.getCompany() + "%");
			}
			if(queryForm.getInsuranceCompany() != null && queryForm.getInsuranceCompany() != ""){
				hql.append(" and t.insuranceCompany like :insuranceCompany ");
				params.put("insuranceCompany", "%" + queryForm.getInsuranceCompany() + "%");
			}
			if(queryForm.getDegree() != null && queryForm.getDegree() != ""){
				hql.append(" and t.degree like :degree ");
				params.put("degree", "%" + queryForm.getDegree() + "%");
			}
			if(queryForm.getEarlyEntryDate() != null && queryForm.getEarlyEntryDate() != ""){
				hql.append(" and t.earlyEntryDate like :earlyEntryDate ");
				params.put("earlyEntryDate", "%" + queryForm.getEarlyEntryDate() + "%");
			}
			if(queryForm.getLeaveTime() != null && queryForm.getLeaveTime() != ""){
				hql.append(" and t.leaveTime like :leaveTime ");
				params.put("leaveTime", "%" + queryForm.getLeaveTime() + "%");
			}
			if(queryForm.getZhuanzhengTime() != null && queryForm.getZhuanzhengTime() != ""){
				hql.append(" and t.zhuanzhengTime like :zhuanzhengTime ");
				params.put("zhuanzhengTime", "%" + queryForm.getZhuanzhengTime() + "%");
			}if(queryForm.getLevelc() != null ){
				if(queryForm.getLevelc().equals("实习生")){
					hql.append(" and t.level like :level ");
					params.put("level", "%" + queryForm.getLevelc() + "%");
				}else{
					hql.append(" and t.level <> '实习生' ");
				}
			}
		}
	}

	@Override
	public long getRenshiUserNameCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from RenshiUserName t where ");
		hqll.append(hql.split("where")[1]);
		System.out.println("+++++++hqll+++++employeeInfoServiceImpl.getRenshiusernameCount " + hqll.toString());
		return userNameDao.count(hqll.toString(), params);
	}

	public long getShebaoCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from Shebao t where ");
		hqll.append(hql.split("where")[1]);
		return sheBaoDao.count(hqll.toString(), params);
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
		PCompany pcompany0 = new PCompany();
		pcompanys.add(pcompany0);
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
		PComboBox pcb0 = new PComboBox();
		pcbs.add(pcb0);
		for (Object renshiUserName : lists) {
			PComboBox pcb = new PComboBox();
			pcb.setText((String)renshiUserName);
			pcb.setValue((String)renshiUserName);
			pcbs.add(pcb);
		}
		return pcbs;
	}

	@Override
	public List<Pwages> getWagesList(int id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("employeeId", id);
		List<Pwages> pwages = new ArrayList<Pwages>();
		List<WageDistribution> wages = wageDistributionDao.find("from WageDistribution w where w.employeeId = :employeeId", params);
		for (WageDistribution wageDistribution : wages) {
			Pwages p = new Pwages();
			BeanUtils.copyProperties(wageDistribution, p);
			pwages.add(p);
		}
		return pwages;
	}
	@Override
	public Pwages getWages(int id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("employeeId", id);
		WageDistribution wage = wageDistributionDao.get(WageDistribution.class, id);
		Pwages pwage = new Pwages();
		BeanUtils.copyProperties(wage, pwage);
		return pwage;
	}
	@Override
	public int updateWages(Pwages pwages) {
		WageDistribution wage = new WageDistribution();
		BeanUtils.copyProperties(pwages, wage);
		try {
			wageDistributionDao.update(wage);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public Map<String, Object> getAllShebaoRadio(QueryForm queryForm) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<PShebao> pshebaos = new ArrayList<PShebao>();
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from Shebao t where 1=1");
		if(queryForm.getCompany()!=null && queryForm.getCompany()!=""){
			hql.append(" and t.company like :company");
			params.put("company", "%" + queryForm.getCompany() + "%");
		}
		if(queryForm.getShebaoType()!=null && queryForm.getShebaoType()!=""){
			hql.append(" and t.shebaoType like :shebaoType");
			params.put("shebaoType", "%" + queryForm.getShebaoType() + "%");
		}
		String sort = "sid";
		String order = SysConfig.DESC;
		if(queryForm.getSort() != null){
			sort = queryForm.getSort();
			if(queryForm.getOrder() != null){
				order = queryForm.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int	intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
		int	pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		
		List<Shebao> shebaos = sheBaoDao.find(hql.toString(), params, intPage, pageSize);
		long total = getShebaoCount(hql.toString(), params);
		for (Shebao shebao : shebaos) {
			PShebao pshebao = new PShebao();
			BeanUtils.copyProperties(shebao, pshebao);
			pshebaos.add(pshebao);
		}
		result.put("total", total);
		result.put("rows", pshebaos);
	
		return result;
	}
	@Override
	public List<PShebao> getShebaoRadioByCompany(String company) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company", "江西慕课信息技术有限公司");
//		String c = "成都超星数图信息技术有限公司";
//		params.put("companyName", company);
		List<PShebao> pshebaos = new ArrayList<PShebao>();
		List<Shebao> shebaos = sheBaoDao.find("from Shebao s where s.company = :company", params);
		for (Shebao shebao : shebaos) {
			PShebao pshebao = new PShebao();
			BeanUtils.copyProperties(shebao, pshebao);
			pshebaos.add(pshebao);
		}
		return pshebaos;
	}
	@Override
	public List<PShebaoType> getShebaoType() {
//		return null;
		List<PShebaoType> pshebaoTypes = new ArrayList<PShebaoType>();
		List<ShebaoType> typeList = sheBaoTypeDao.find("from ShebaoType");
		for (ShebaoType shebaoType : typeList) {
			PShebaoType pshebao = new PShebaoType();
			BeanUtils.copyProperties(shebaoType, pshebao);
			pshebaoTypes.add(pshebao);
		}
		return pshebaoTypes;
	}
	
	@Override
	public int addWages(Pwages pwages) {
		WageDistribution wage = new WageDistribution();
		BeanUtils.copyProperties(pwages, wage);
		try {
			wageDistributionDao.save(wage);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int deleteWages(Pwages pwages) {
		WageDistribution wage = new WageDistribution();
		BeanUtils.copyProperties(pwages, wage);
		try {
			wageDistributionDao.delete(wage);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int updateShebao(PShebao pshebao) {
		Shebao shebao = new Shebao();
		BeanUtils.copyProperties(pshebao, shebao);
		try {
			sheBaoDao.update(shebao);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<PHouseholdType> getHouseholdType() {
		List<PHouseholdType> phouseTypes = new ArrayList<PHouseholdType>();
		List<HouseholdType> houseTypes = househodlType.find("from HouseholdType");
		for (HouseholdType householdType : houseTypes) {
			PHouseholdType phouseholdeType = new PHouseholdType();
			BeanUtils.copyProperties(householdType, phouseholdeType);
			phouseTypes.add(phouseholdeType);
		}
		return phouseTypes;
	}
	
	@Override
	public int addShebao(PShebao pshebao) {
		Shebao shebao = new Shebao();
		BeanUtils.copyProperties(pshebao, shebao);
		try {
			sheBaoDao.save(shebao);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int deleteShebao(PShebao pshebao) {
		Shebao shebao = new Shebao();
		BeanUtils.copyProperties(pshebao, shebao);
		try {
			sheBaoDao.delete(shebao);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
