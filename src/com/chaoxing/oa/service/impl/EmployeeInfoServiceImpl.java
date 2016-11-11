package com.chaoxing.oa.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.common.PComboBox;
import com.chaoxing.oa.entity.page.common.PCompany;
import com.chaoxing.oa.entity.page.common.PHouseholdType;
import com.chaoxing.oa.entity.page.common.PLevel;
import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.common.POStructs;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PGongziHuiZong;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PSheBaoSummary;
import com.chaoxing.oa.entity.page.employee.PShebao;
import com.chaoxing.oa.entity.page.employee.PShebaoType;
import com.chaoxing.oa.entity.page.employee.PWagesDate;
import com.chaoxing.oa.entity.page.employee.PYidong;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.entity.page.employee.Pwages;
import com.chaoxing.oa.entity.page.system.PSystemConfig;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.commmon.Company;
import com.chaoxing.oa.entity.po.commmon.HouseholdType;
import com.chaoxing.oa.entity.po.commmon.Level;
import com.chaoxing.oa.entity.po.commmon.OrganizationStructure;
import com.chaoxing.oa.entity.po.commmon.ShebaoType;
import com.chaoxing.oa.entity.po.commmon.WagesDate;
import com.chaoxing.oa.entity.po.employee.KaoQin;
import com.chaoxing.oa.entity.po.employee.MonthWages;
import com.chaoxing.oa.entity.po.employee.Shebao;
import com.chaoxing.oa.entity.po.employee.UserName;
import com.chaoxing.oa.entity.po.employee.WageDistribution;
//import com.chaoxing.oa.entity.po.system.Struct;
import com.chaoxing.oa.entity.po.system.SystemConfig;
import com.chaoxing.oa.entity.po.view.GongziHuiZong;
import com.chaoxing.oa.entity.po.view.OStructureV;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.SheBaoSummary;
import com.chaoxing.oa.service.EmployeeInfoService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.system.cache.CacheManager;
import com.chaoxing.oa.util.ResourceUtil;
import com.chaoxing.oa.util.SqlHelper;

@Service("employeeInfoService")
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

	@Autowired
	private BaseDaoI<RenshiUserName> userNameDao;
	@Autowired
	private BaseDaoI<Object> objectDao;
	@Autowired
	private BaseDaoI<Company> companyDao;
	@Autowired
	private BaseDaoI<Level> levelDao;//级别
	@Autowired
	private BaseDaoI<OrganizationStructure> organizationStructureDao;//组织结构
	@Autowired
	private BaseDaoI<WageDistribution> wageDistributionDao;
	@Autowired
	private BaseDaoI<Shebao> sheBaoDao;
	@Autowired
	private BaseDaoI<ShebaoType> sheBaoTypeDao;
	@Autowired
	private BaseDaoI<HouseholdType> househodlType;
	@Autowired
	private BaseDaoI<SheBaoSummary> shebaoSummaryDao;
	@Autowired
	private BaseDaoI<KaoQin> kaoqinDao;
	@Autowired
	private BaseDaoI<MonthWages> monthWagesDao;
	@Autowired
	private BaseDaoI<WagesDate> wagesDateDao;
	@Autowired
	private BaseDaoI<UserName> useDao;
	@Autowired
	private BaseDaoI<SystemConfig> systemConfigDao;
	@Autowired
	private BaseDaoI<OStructureV> oStructureVdao;
	@Autowired
	private BaseDaoI<GongziHuiZong> gongzihuizongDao;
	

//	@Override
//	public List<PRenshiEmployee> getRenshiUserName() {
//		Map<String, Object> params = new HashMap<String, Object>();
//		List<PRenshiEmployee> renshiEmployeeInfos = new ArrayList<PRenshiEmployee>();
//		params.put("fourthLevel", "常规数据");
//		List<RenshiUserName> renshiUsernames = userNameDao
//				.find("from RenshiUserName r where r.fourthLevel=:fourthLevel", params);
//		for (RenshiUserName renshiUserName : renshiUsernames) {
//			if(renshiUserName!=null){
//				PRenshiEmployee renshiEmployeeInfo = new PRenshiEmployee();
//				BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
////			renshiEmployeeInfo.setId(renshiUserName.getID());
//				renshiEmployeeInfos.add(renshiEmployeeInfo);
//			}
//		}
//		return renshiEmployeeInfos;
//	}

	@Override
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session) {
		return getRenshiUserName(queryForm, session, 0);
	}
	
	@Override
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session, int isExport) {
//		System.out.println(queryForm);
		List<PRenshiEmployee> renshiEmployeeInfos = new ArrayList<PRenshiEmployee>();
		Map<String, Object> userInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from RenshiUserName t where 1=1 ");
		addCondition(hql, queryForm, params);
		SessionInfo userInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(userInfo.getRoleId() > 1 && !(userInfo.getRoleId()==100)){
			hql.append(" and t.renshiRight like :renshiRight ");
			params.put("renshiRight", "%" + userInfo.getUsername() + "%");
		}
		String sort = "id";
		String order = SysConfig.DESC;
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
		System.out.println(hql.toString());
		List<RenshiUserName> renshiUsernames = userNameDao.find(hql.toString(), params, intPage, pageSize);
		for (RenshiUserName renshiUserName : renshiUsernames) {
			if(renshiUserName!=null){
				PRenshiEmployee renshiEmployeeInfo = new PRenshiEmployee();
				BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
				renshiEmployeeInfos.add(renshiEmployeeInfo);
			}
		}
		long total = getRenshiUserNameCount(hql.toString(),params);
		userInfos.put("total", total);
		userInfos.put("rows", renshiEmployeeInfos);
		return userInfos;
	}
	
	@Override
	public Map<String,Object> findYidong(QueryForm queryForm, HttpSession session, boolean isExport) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> ydInfo = new HashMap<String, Object>();
		List<PYidong> ydList = new ArrayList<PYidong>();
		StringBuffer hql = new StringBuffer("select t.id,t.username,t.firstLevel,t.secondLevel,t.thirdLevel,t.fourthLevel,t.position,t.hiredate,t.zhuanzhengTime"
				+ ",w.tiaoxinRecord,t.company,t.remarks,t.bumentiaozhengReport from RenshiUserName t,WageDistribution w where t.id=w.employeeId ");
		addCondition(hql, queryForm, params);
		SessionInfo userInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(userInfo.getRoleId() > 1 && !(userInfo.getRoleId()==100)){
			hql.append(" and t.renshiRight like :renshiRight ");
			params.put("renshiRight", "%" + userInfo.getUsername() + "%");
		}
		String sort = "id";
		String order = SysConfig.DESC;
		if(queryForm.getSort() != null){
			sort = queryForm.getSort();
			if(queryForm.getOrder() != null){
				order = queryForm.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(!isExport){
			intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
			pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		}
		List<Object> olist = objectDao.find(hql.toString(),params, intPage, pageSize);
		if(olist.size()>0){
			PYidong pyidong = new PYidong();
			Object[] obList = (Object[])olist.get(0);
			if(null != obList){
				System.out.println("obList lenth:" + obList.length);
				pyidong.setId((obList[0]!=null) ? (Integer)obList[0] : 0);
				pyidong.setUsername(String.valueOf((obList[1]!=null) ? obList[1] : ""));
				String depart = ((obList[2]!=null) ? (String)obList[2] : "") + "/" +
						((obList[3]!=null) ? (String)obList[3] : "")+ "/" +
						((obList[4]!=null) ? (String)obList[4] : "")+ "/" +
						((obList[5]!=null) ? (String)obList[5] : "")
						;
				pyidong.setFourthLevel(depart);
				pyidong.setPosition(((obList[6]!=null) ? (String)obList[6] : ""));
				pyidong.setHiredate(((obList[7]!=null) ? (String)obList[7] : ""));
				pyidong.setZhuanzhengTime(((obList[8]!=null) ? (String)obList[8] : ""));
				pyidong.setTiaoxinRecord(((obList[9]!=null) ? (String)obList[9] : ""));
				pyidong.setCompany(((obList[10]!=null) ? (String)obList[10] : ""));
				pyidong.setRemark(((obList[11]!=null) ? (String)obList[11] : ""));
				pyidong.setdReport(((obList[12]!=null) ? (String)obList[12] : ""));
				ydList.add(pyidong);
			}
		}
		String csql = "select count(*) from " + (hql.toString()).split("from");
		long total = objectDao.count(csql,params);
		ydInfo.put("rows", ydList);
		ydInfo.put("total", total);
		return ydInfo;
	}

	@Override
	public Map<String, Object> findRenshiQuick(Page page, Integer type, HttpSession session) {
		StringBuffer hql = null;
		List<PRenshiEmployee> renshiEmployeeInfos = new ArrayList<PRenshiEmployee>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> userInfos = new HashMap<String, Object>();
//		Map<String, Object> params = new HashMap<String, Object>();
		String column = "select new RenshiUserName(t.renshiRight, t.firstLevel, t.secondLevel, t.thirdLevel,"
				+ "t.fourthLevel, t.cellCore, t.cellCoreEmail, t.guidance, t.guidanceEmail, t.id,"
				+ "t.username, t.password, t.departmentId, t.position, t.sex, t.identityCard,"
				+ "t.borthDay, t.nation, t.degree, t.graduatedSchool, t.major, t.phoneNumber,"
				+ "t.homeAddress, t.homeNumber, t.hiredate, t.zhuanzhengTime, t.pastLeaveTime,"
				+ "t.earlyEntryDate, t.householdType, t.insurance, t.insuranceCompany, t.company,"
				+ "t.resume, t.photo, t.identityCardCopy, t.familyRegister, t.leavingCertificate,"
				+ "t.contract, t.managementSystem, t.entryForm, t.signedTime, t.terminationTime,"
				+ "t.registeredAddress, t.postcode, t.remarks, t.contractNumber, t.dueSocialSecurity,"
				+ "t.socialSecurityHospital, t.level, t.recruitmentSources, t.contractRenewal,"
				+ "t.originalNumber, t.secrecyAgreement, t.reportForm, t.panCard, t.leaveTime,"
				+ "t.workPlace, t.email, t.ifSecret, t.maritalStatus, t.roleId, t.ruzhiReport,"
				+ "t.lizhiReport, t.zhuanzhengReport, t.bumentiaozhengReport) ";
//		String tableUnion = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
		Calendar cal = Calendar.getInstance();
		String afterDate = df.format(cal.getTime());
		cal.add(Calendar.MONTH, -1);
		String date = df.format(cal.getTime());
		cal.add(Calendar.MONTH, -1);
//		String preDate = df.format(cal.getTime());
//		System.out.println(afterDate.m);
		if(type == 111 || type == 112 || type == 211 || type == 212){
			hql = new StringBuffer(column + "from RenshiUserName as t,WageDistribution as w where t.id=w.employeeId ");
//			hql = new StringBuffer( "select new RenshiUserName(t.id) from RenshiUserName as t,WageDistribution as w where t.id=w.employeeId ");
			if(type == 111){
				hql.append(" and w.salary=0 and t.hiredate like :hiredate");
				params.put("hiredate", "%" + afterDate + "%");
			}else if(type == 112){
				hql.append(" and w.salary=0 and t.hiredate like :hiredate");
				params.put("hiredate", "%" + date + "%");
			}
		}
		//查询本月合同到期名单	
		if(type == 301)
		{
			Calendar nowcal = Calendar.getInstance();
			nowcal.add(Calendar.MONTH, 1);
			String nowDate = df.format(nowcal.getTime());
			
			hql = new StringBuffer(column + "from RenshiUserName as t  where (t.leaveTime is null or t.leaveTime ='') ");
			hql.append(" and t.terminationTime <= :hiredate");
			params.put("hiredate",  nowDate);
		
		}
		//查询没有身份证的员工名单
		if(type == 302)
		{
			hql = new StringBuffer(column + "from RenshiUserName as t  where (t.leaveTime is null or t.leaveTime ='') ");
			hql.append(" and length(t.identityCard) < 18");
				
		}
		SessionInfo userInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if(userInfo.getRoleId() > 1 && !(userInfo.getRoleId()==100)){
			hql.append(" and t.renshiRight like :renshiRight ");
			params.put("renshiRight", "%" + userInfo.getUsername() + "%");
		}
		String sort = "id";
		String order = SysConfig.DESC;
		if(page.getSort() != null){
			sort = page.getSort();
			if(page.getOrder() != null){
				order = page.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		List<RenshiUserName> renshiUsernames = userNameDao.find(hql.toString(), params, page.getPage(), page.getRows());
		for (RenshiUserName renshiUserName : renshiUsernames) {
			if(renshiUserName!=null){
				PRenshiEmployee renshiEmployeeInfo = new PRenshiEmployee();
				BeanUtils.copyProperties(renshiUserName, renshiEmployeeInfo);
				renshiEmployeeInfos.add(renshiEmployeeInfo);
			}
		}
		long total = getCount(hql.toString(),params);
		userInfos.put("total", total);
		userInfos.put("rows", renshiEmployeeInfos);
		return userInfos;
	}
	
	public long getCount(String hql, Map<String, Object>params){
		String hqll = "select count(*) from " + hql.split("from")[1];
//		String hqll = "select new RenshiUserName(t.id)  from " + hql.split("from")[1];
//		String hqll = "select {count(t.*)} from " + hql.split("from")[1];
		return objectDao.count(hqll,params);
	}

	@Override
	public long getRenshiUserNameCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from RenshiUserName t where ");
		hqll.append(hql.split("where")[1]);
//		System.out.println("+++++++hqll+++++employeeInfoServiceImpl.getRenshiusernameCount " + hqll.toString());
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
			if(level!=null){
				PLevel plevel = new PLevel();
				BeanUtils.copyProperties(level, plevel);
				plevels.add(plevel);
			}
		}
		return plevels;
	}
	@Override
	public List<PCompany> getCompany() {
		List<Company> cmopanys = companyDao.find("from Company");
		List<PCompany> pcompanys = new ArrayList<PCompany>();
		PCompany pcompany0 = new PCompany();
		pcompany0.setCmopany("");
		pcompany0.setId(0);
		pcompanys.add(pcompany0);
		for (Company company : cmopanys) {
			if(company!=null){
				PCompany pcompany = new PCompany();
				BeanUtils.copyProperties(company, pcompany);
				pcompanys.add(pcompany);
			}
		}
		return pcompanys;
	}

	@Override
	public List<PComboBox> getForthLevel() {
		List<OrganizationStructure> lists = organizationStructureDao.find("SELECT distinct o.thirdLevel from OrganizationStructure o");
		List<PComboBox> listComs = new ArrayList<PComboBox>();
		for (OrganizationStructure organizationStructure : lists) {
			if(organizationStructure!=null){
				PComboBox comb = new PComboBox();
				comb.setValue(organizationStructure.getFourthLevel());
				comb.setText(organizationStructure.getFourthLevel());
				listComs.add(comb);
			}
		}
		return listComs;
	}

	@Override
	public List<POStructs> findOStruct() {
		List<POStructs> listComs = new ArrayList<POStructs>();
		List<OrganizationStructure> lists = organizationStructureDao.find("from OrganizationStructure t where t.level=4");
		for (OrganizationStructure os : lists) {
			if(os!=null){
				POStructs pos = new POStructs();
				BeanUtils.copyProperties(os, pos);
				listComs.add(pos);
			}
		}
		return listComs;
	}

	@Override
	public List<POStructV> findOStruct(POStructV pOStructV,int isExport) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from OStructureV t where");
		List<POStructV> posList = new ArrayList<POStructV>();
		OStructureV o = new OStructureV();
		BeanUtils.copyProperties(pOStructV, o);
		try {
			hql.append(SqlHelper.prepareAndSql(o, params, true, "id"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if(pOStructV.getId()!=null){
			hql.append(" and t.preId=:id");
			params.put("id", pOStructV.getId());
		}
		System.out.println(hql.toString());
//		params.clear();
//		params.put("level", 3);
//		List<OStructureV> osList = oStructureVdao.find("from OStructureV t where level like '%:level%'",params);
		List<OStructureV> osList = oStructureVdao.find(hql.toString(),params);
		if(pOStructV.getLevel() != null && pOStructV.getLevel()<4 && osList.size()>0){
			for (OStructureV oStructureV : osList) {
				POStructV postructV = new POStructV();
				String name = oStructureV.getFourthLevel();
				Map<String,Object> param2 = new HashMap<String, Object>();
				param2.put("name", name);
				Integer level = oStructureV.getLevel();
				String level_ = "";
				if(level==1){
					level_ = "firstLevel";
				}else if(level==2){
					level_ = "secondLevel";
				}else{
					level_ = "thirdLevel";
				}
				List<Object> olist = objectDao.find("select sum(t.total) as total,sum(t.onJob) as onJob from OStructureV t where t." + level_ +
						"=:name",param2);
				String value1;
				String value2;
				if(olist.size()>0){
					Object[] obList = (Object[])olist.get(0);
					value1 = String.valueOf((obList[0]!=null) ? obList[0] : "0");
					value2 = String.valueOf((obList[1]!=null) ? obList[1] : "0");
//					System.out.println(name);
//					System.out.println(obList);
//					System.out.println(obList[0]);
//					System.out.println(obList[1]);
					postructV.setTotal(Integer.valueOf(value1));
					postructV.setTotal(Integer.valueOf(value2));
				}
//				postructV.setTotal((Integer)obList[0]);
//				postructV.setOnJob((Integer)obList[1]);
				BeanUtils.copyProperties(oStructureV, postructV);
				posList.add(postructV);
			}
		}else{
			for (OStructureV oStructureV : osList) {
				POStructV postructV = new POStructV();
				BeanUtils.copyProperties(oStructureV, postructV);
				posList.add(postructV);
			}
		}
		return posList;
	}
	
	class OsTotal{
		private int total;
		private int onJob;
		public OsTotal(int total2, int onJob2) {
			this.total=total2;
			this.onJob=onJob2;
		}
		public int getTotal() {
			return total;
		}
		public int getOnJob() {
			return onJob;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public void setOnJob(int onJob) {
			this.onJob = onJob;
		}
		public void addTotal(int num){
			this.total += num;
		}
		public void addOnJob(int num){
			this.onJob += num;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllStruct(POStructV pOStructV) {
		Map<String, Object> osInfo = new HashMap<String, Object>();
		if(null == CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_JIAGOU)){
			StringBuffer hql = new StringBuffer("from OStructureV t where 1=1");
//			Map<String,Object> params = new HashMap<String, Object>();
//			if(null !=pOStructV.getId()){
//				hql.append(" and id=:id");
//				params.put("id", pOStructV.getId());
//			}
			List<OStructureV> osList = oStructureVdao.find(hql.toString(),null);
			List<POStructV> posList = new ArrayList<POStructV>();
			
			Iterator<OStructureV> it = osList.iterator();
			if(osList.size()>0){
				Map<Integer,OsTotal> countMap = new HashMap<Integer, OsTotal>();
				while(it.hasNext()){
					OStructureV os4 = it.next();
					if(os4.getLevel() == 4){
						int s3 = os4.getPreId();
						int total = os4.getTotal();
						int onJob = os4.getOnJob();
						OsTotal osTotal = countMap.get(s3);
						if(null != osTotal){
							osTotal.addTotal(total);
							osTotal.addOnJob(onJob);
						}else{
							countMap.put(s3, new OsTotal(total,onJob));
						}
						POStructV pos = new POStructV();
						BeanUtils.copyProperties(os4, pos);
						pos.setState("");
						posList.add(pos);
						it.remove();
//						osList.remove(os4);
					}
				}
				it = osList.iterator();
				while(it.hasNext()){
					OStructureV os3 = it.next();
					if(os3.getLevel()==3){
						int s2 = os3.getPreId();
						OsTotal osTotal = countMap.get(os3.getId());
						if(null != osTotal){
							//3级先取给自己
							os3.setTotal(osTotal.getTotal());
							os3.setOnJob(osTotal.getOnJob());
							//后去加给2级
							OsTotal osTotal2 = countMap.get(s2);
							if(null != osTotal2){
								osTotal2.addTotal(osTotal.getTotal());
								osTotal2.addOnJob(osTotal.getOnJob());
							}else{
								countMap.put(s2, new OsTotal(osTotal.getTotal(),osTotal.getOnJob()));
							}
						}
						POStructV pos = new POStructV();
						BeanUtils.copyProperties(os3, pos);
						posList.add(pos);
//						osList.remove(os3);
						it.remove();
					}
				}
				
				it = osList.iterator();
				while(it.hasNext()){
					OStructureV os2 = it.next();
					if(os2.getLevel()==2){
						int s1 = os2.getPreId();
						OsTotal osTotal = countMap.get(os2.getId());
						if(null != osTotal){
							//2级先取给自己
							os2.setTotal(osTotal.getTotal());
							os2.setOnJob(osTotal.getOnJob());
							//后去加给1级
							OsTotal osTotal2 = countMap.get(s1);
							if(null != osTotal2){
								osTotal2.addTotal(osTotal.getTotal());
								osTotal2.addOnJob(osTotal.getOnJob());
							}else{
								countMap.put(s1, new OsTotal(osTotal.getTotal(),osTotal.getOnJob()));
							}
						}
						POStructV pos = new POStructV();
						BeanUtils.copyProperties(os2, pos);
						posList.add(pos);
//						osList.remove(os2);
						it.remove();
					}
				}
				
				osInfo.put("total", osList.size());
				it = osList.iterator();
				while(it.hasNext()){
					OStructureV os1 = it.next();
					if(os1.getLevel()==1){
						OsTotal osTotal = countMap.get(os1.getId());
						if(null != osTotal){
							os1.setTotal(osTotal.getTotal());
							os1.setOnJob(osTotal.getOnJob());
						}
						POStructV pos = new POStructV();
						BeanUtils.copyProperties(os1, pos);
						posList.add(pos);
//						osList.remove(os1);
						it.remove();
					}
				}
			}
			osInfo.put("rows", posList);
			CacheManager.getInstance().put(SysConfig.CACHE_COMMON + SysConfig.COMMON_JIAGOU, osInfo);
		}else{
			osInfo = (Map<String, Object>) CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_JIAGOU);
		}
		return osInfo;
	}
	

	@Override
	public int updateOrSave(POStructV poStructV) {
		OrganizationStructure os = new OrganizationStructure();
		BeanUtils.copyProperties(poStructV, os);
		try {
			organizationStructureDao.saveOrUpdate(os);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	

	@Override
	public int deleteOS(POStructV poStructV) {
		OrganizationStructure os = new OrganizationStructure();
		BeanUtils.copyProperties(poStructV, os);
		try {
			organizationStructureDao.delete(os);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

//	@Override
//		public Map<String,Object> getOStruct(QueryForm queryForm,int isExport) {
//		Map<String,Object> osInfo = new HashMap<String, Object>();
//		Set<POStructs> listComs = new TreeSet<POStructs>();
//		StringBuffer hql;
//		Map<String,Object> params = new HashMap<String, Object>();
//		String sort = "sortCode";
//		String order = SysConfig.ASC;
//		int intPage = 0;
//		int pageSize = 30000;//最多导出30000条数据
//		if(queryForm==null){
//			hql = new StringBuffer("from OrganizationStructure t where t.level=4");
//		}else{
//			hql = new StringBuffer("from OrganizationStructure t where t.level=1");
//		}
////		if(queryForm.getFirstLevel()==null&&queryForm.getSecondLevel()==null&&queryForm.getThirdLevel()==null&&queryForm.getFourthLevel()==null){
////			if(queryForm.getId()!=0){
////				hql.append(" and t.preId=:id");
////				params.put("id", queryForm.getId());
////			}else{
////				hql.append(" and t.level = 1 ");
////			}
////		}else{
////			addCondition(hql, queryForm, params);
////		}
//		if(queryForm.getSort() != null){
//			sort = queryForm.getSort();
//			if(queryForm.getOrder() != null){
//				order = queryForm.getOrder();
//			}
//		}
//		if(isExport == 0&&queryForm!=null){
//			intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
//			pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
//		}
//		hql.append(" order by t." + sort + " " + order);
//		List<OrganizationStructure> lists = organizationStructureDao.find(hql.toString(), params, intPage, pageSize);
//		listComs = copyos2t(lists);
////		for (OrganizationStructure organizationStructure : lists) {
////			if(organizationStructure!=null){
////				POStructs postruct = new POStructs();
//////				if(organizationStructure.getSecondLevel().equals("离职")){
//////					organizationStructure.setFourthLevel("离职");
//////				}
////				BeanUtils.copyProperties(organizationStructure, postruct);
////				if(postruct.getLevel()==4){
////					postruct.setState(null);
////				}
////				listComs.add(postruct);
////			}
////		}
//		long count = getOStructCount(hql.toString(), params);
//		osInfo.put("rows", listComs);
//		osInfo.put("total", count);
//		return osInfo;
//	}
	
//	private Set<POStructs> copyos2t(List<OrganizationStructure> lists) {
//		Set<POStructs> pstructs = new TreeSet<POStructs>();
//		for (OrganizationStructure struct : lists) {
//			POStructs pstruct = new POStructs();
//			BeanUtils.copyProperties(struct, pstruct);
//			if(struct.getStructures().size()>0){
//				pstruct.setChildren(copyos2t(new ArrayList<OrganizationStructure>(struct.getStructures())));
//			}else{
//				pstruct.setState(null);
//			}
//			pstructs.add(pstruct);
//		}
//		return pstructs;
//	}
	
	@Override
	public long getOStructCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from OrganizationStructure t where ");
		hqll.append(hql.split("where")[1]);
		return organizationStructureDao.count(hqll.toString(), params);
	}
	
	/*@Override
	public Map<String, Object> findStruct(QueryForm queryform, int isExport) {
		Map<String,Object> structInfo = new HashMap<String, Object>();
		Set<Pstruct> structs = new TreeSet<Pstruct>();
		StringBuffer hql = new StringBuffer("from Struct t where 1=1");
		Map<String,Object> params = new HashMap<String, Object>();
		String sort = "sortCode";
		String order = SysConfig.ASC;
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(queryform!=null){
			if(queryform.getFirstLevel()==null&&queryform.getSecondLevel()==null&&queryform.getThirdLevel()==null&&queryform.getFourthLevel()==null){
				hql.append(" and t.level = 1 ");
			}else{
				addCondition(hql, queryform, params);
			}
			if(queryform.getSort() != null){
				sort = queryform.getSort();
				if(queryform.getOrder() != null){
					order = queryform.getOrder();
				}
			}
			if(isExport == 0){
				intPage = (queryform == null || queryform.getPage() == 0) ? 1 : queryform.getPage();
				pageSize = (queryform == null || queryform.getRows() == 0) ? 100 : queryform.getRows();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		List<Struct> lists = structDao.find(hql.toString(), params, intPage, pageSize);
		structs = copyo2t(lists);
//		for (Struct struct : lists) {
//			Pstruct pstruct = new Pstruct();
//			pstruct.setId(struct.getId());
//			pstruct.setName(struct.getName());
//			pstruct.setLevel(struct.getLevel());
//			pstruct.setLeader(struct.getLeader());
//			pstruct.setEmail(struct.getEmail());
//			pstruct.setSortCode(struct.getSortCode());
//			pstruct.setFirstLevel(struct.getFirstLevel());
//			pstruct.setSecondLevl(struct.getSecondLevl());
//			pstruct.setThirdLevl(struct.getThirdLevl());
//			for (Struct struct2 : struct.getChildren()) {
//				
//			}
////			BeanUtils.copyProperties(struct, pstruct);
//			structs.add(pstruct);
//		}
		long count = getStructCount(hql.toString(), params);
		structInfo.put("rows", structs);
		structInfo.put("total", count);
		return structInfo;
	}*/
	
	/*private Set<Pstruct> copyo2t(List<Struct> lists) {
		Set<Pstruct> pstructs = new TreeSet<Pstruct>();
		for (Struct struct : lists) {
			Pstruct pstruct = new Pstruct();
			pstruct.setId(struct.getId());
			pstruct.setName(struct.getName());
			pstruct.setLevel(struct.getLevel());
			pstruct.setLeader(struct.getLeader());
			pstruct.setEmail(struct.getEmail());
			pstruct.setSortCode(struct.getSortCode());
			pstruct.setFirstLevel(struct.getFirstLevel());
			pstruct.setSecondLevel(struct.getSecondLevel());
			pstruct.setThirdLevel(struct.getThirdLevel());
			if(struct.getChildren().size()>0){
				pstruct.setChildren(copyo2t(new ArrayList<Struct>(struct.getChildren())));
			}else{
				pstruct.setState(null);
			}
			pstructs.add(pstruct);
		}
		return pstructs;
	}*/
	/*public long getStructCount(String hql, Map<String,Object> params){
		StringBuffer hqll = new StringBuffer("select count(*) from Struct t where ");
		hqll.append(hql.split("where")[1]);
		return structDao.count(hqll.toString(), params);
	}*/
	
	@Override
	public List<PComboBox> getInsuranceCompany() {
		List<Object> lists = objectDao.find("select distinct(u.insuranceCompany) from UserName u");
		List<PComboBox> pcbs = new ArrayList<PComboBox>();
		PComboBox pcb0 = new PComboBox();
		pcb0.setText("");
		pcb0.setValue("");
		pcbs.add(pcb0);
		for (Object renshiUserName : lists) {
			if(renshiUserName!=null){
				PComboBox pcb = new PComboBox();
				pcb.setText((String)renshiUserName);
				pcb.setValue((String)renshiUserName);
				pcbs.add(pcb);
			}
		}
		return pcbs;
	}

	@Override
	public List<Pwages> getWagesList(int id,SessionInfo sessionInfo, String ifSecret) {
		Map<String,Object> params = new HashMap<String, Object>();
		int roleId = sessionInfo.getRoleId();
		params.put("employeeId", id);
		List<Pwages> pwages = new ArrayList<Pwages>();
		List<WageDistribution> wages = wageDistributionDao.find("from WageDistribution w where w.employeeId = :employeeId", params);
		for (WageDistribution wageDistribution : wages) {
			if(wageDistribution!=null){
				Pwages p = new Pwages();
				BeanUtils.copyProperties(wageDistribution, p);
				if(roleId!=100&&roleId!=0&&!(ifSecret.equals("off"))){
					p.setSalary(null);
					p.setBasicWage(null);
					p.setPostSalary(null);
					p.setPerformanceRelatedPay(null);
					p.setRemarks("");
					p.setLishiSalary(null);
				}
				pwages.add(p);
			}
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
	public int updateWagesRadix(PshebaoDetail pwages) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("update WageDistribution set "); 
		hql.append(" radix = :radix,");
		params.put("radix", pwages.getRadix());
		hql.append(" householdType = :householdType,");
		params.put("householdType", pwages.getHouseholdType());
		hql.append("subEndowmentIinsurance = :subEndowmentIinsurance,");
		params.put("subEndowmentIinsurance", pwages.getSubEndowmentIinsurance());
		hql.append("subMedicare = :subMedicare,");
		params.put("subMedicare", pwages.getSubMedicare());
		hql.append("subUnemployedInsurance = :subUnemployedInsurance,");
		params.put("subUnemployedInsurance", pwages.getSubUnemployedInsurance());
		hql.append("subHouseIinsurance = :subHouseIinsurance,");
		params.put("subHouseIinsurance", pwages.getSubHouseIinsurance());
		hql.append("cEndowmentIinsurance = :cEndowmentIinsurance,");
		params.put("cEndowmentIinsurance", pwages.getcEndowmentIinsurance());
		hql.append("cMedicare = :cMedicare,");
		params.put("cMedicare", pwages.getcMedicare());
		hql.append("cUnemployedInsurance = :cUnemployedInsurance,");
		params.put("cUnemployedInsurance", pwages.getcUnemployedInsurance());
		hql.append("cHouseIinsurance = :cHouseIinsurance,");
		params.put("cHouseIinsurance", pwages.getcHouseIinsurance());
		hql.append("cInjuryInsurance = :cInjuryInsurance,");
		params.put("cInjuryInsurance", pwages.getcInjuryInsurance());
		hql.append("cBirthIinsurance = :cBirthIinsurance");
		params.put("cBirthIinsurance", pwages.getcBirthIinsurance());
		hql.append(" where id = :id");
		params.put("id", pwages.getId());
		String hhql = hql.toString();
		try {
			wageDistributionDao.executeHql(hhql, params);
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
			if(shebao!=null){
				PShebao pshebao = new PShebao();
				BeanUtils.copyProperties(shebao, pshebao);
				pshebaos.add(pshebao);
			}
		}
		result.put("total", total);
		result.put("rows", pshebaos);
	
		return result;
	}
	@Override
	public List<PShebao> getShebaoRadioByCompany(String company) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company", company);
		List<PShebao> pshebaos = new ArrayList<PShebao>();
		List<Shebao> shebaos = sheBaoDao.find("from Shebao s where s.company = :company", params);
		for (Shebao shebao : shebaos) {
			if(shebao!=null){
				PShebao pshebao = new PShebao();
				BeanUtils.copyProperties(shebao, pshebao);
				pshebaos.add(pshebao);
			}
		}
		return pshebaos;
	}
	@Override
	public List<PShebaoType> getShebaoType() {
//		return null;
		List<PShebaoType> pshebaoTypes = new ArrayList<PShebaoType>();
		List<ShebaoType> typeList = sheBaoTypeDao.find("from ShebaoType");
		for (ShebaoType shebaoType : typeList) {
			if(shebaoType!=null){
				PShebaoType pshebao = new PShebaoType();
				BeanUtils.copyProperties(shebaoType, pshebao);
				pshebaoTypes.add(pshebao);
			}
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
			if(householdType!=null){
				PHouseholdType phouseholdeType = new PHouseholdType();
				BeanUtils.copyProperties(householdType, phouseholdeType);
				phouseTypes.add(phouseholdeType);
			}
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
	@Override
	public Map<String, Object> getShebaoSummary(QueryForm queryForm, HttpSession session) {
		return getShebaoSummary(queryForm, session, 0);
	}
	@Override
	public Map<String, Object> getShebaoSummary(QueryForm queryForm, HttpSession session, int isExport) {

		List<PSheBaoSummary> pshebaoSummaries = new ArrayList<PSheBaoSummary>();
		Map<String, Object> summaryInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("from SheBaoSummary t where 1=1 ");
		if(queryForm.getCompany() != null){
			hql.append(" and t.company like :company ");
			params.put("company", "%" + queryForm.getCompany() + "%");
		}
//		String sort = "company";
//		String order = SysConfig.DESC;
//		if(queryForm.getSort() != null){
//			String sort = queryForm.getSort();
//			String order ="";
//			if(queryForm.getOrder() != null){
//				order = queryForm.getOrder();
//			}
//			hql.append(" order by t." + sort + " " + order);
//		}
		
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(isExport == 0){
			intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
			pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		}
		
		List<SheBaoSummary> sheBaoSummaries = shebaoSummaryDao.find(hql.toString(), params, intPage, pageSize);
		List<PSystemConfig> psystemConfig = findSysconfig(null, SysConfig.SHEBAO_SUMMARY);
		for (SheBaoSummary shebaoSummary : sheBaoSummaries) {
			if(shebaoSummary!=null){
				String company = shebaoSummary.getCompany();
				StringBuffer hql2 = new StringBuffer("from WageDistribution t where t.radix > 0 and t.company ='" + company + "'");
				long total = getWageDistributionCount(hql2.toString(), null);
				PSheBaoSummary pshebaoSummary = new PSheBaoSummary();
				for (PSystemConfig pC : psystemConfig) {
					if(pC.getName().equals(company)){
						pshebaoSummary.setLocked(pC.getLocked());
					}
				}
				pshebaoSummary.setCount(total);
				pshebaoSummary.setCompany(shebaoSummary.getCompany());
				BigDecimal subEndowment = new BigDecimal(shebaoSummary.getSubEndowmentIinsurance().toString());
				pshebaoSummary.setSubEndowmentIinsurance(subEndowment.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal subHouse = new BigDecimal(shebaoSummary.getSubHouseIinsurance().toString());
				pshebaoSummary.setSubHouseIinsurance(subHouse.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal subMedicare = new BigDecimal(shebaoSummary.getSubMedicare().toString());
				pshebaoSummary.setSubMedicare(subMedicare.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal subUnemployed = new BigDecimal(shebaoSummary.getSubUnemployedInsurance().toString());
				pshebaoSummary.setSubUnemployedInsurance(subUnemployed.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal cEndowment = new BigDecimal(shebaoSummary.getcEndowmentIinsurance().toString());
//				System.out.println("[公司养老BigDecimal:" + cEndowment + "][float:" + pshebaoSummary.getcEndowmentIinsurance());
				pshebaoSummary.setcEndowmentIinsurance(cEndowment.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal cHouse = new BigDecimal(shebaoSummary.getcHouseIinsurance().toString());
				pshebaoSummary.setcHouseIinsurance(cHouse.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal cMedicare = new BigDecimal(shebaoSummary.getcMedicare().toString());
				pshebaoSummary.setcMedicare(cMedicare.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal cUnemployed = new BigDecimal(shebaoSummary.getcUnemployedInsurance().toString());
				pshebaoSummary.setcUnemployedInsurance(cUnemployed.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal cBirth = new BigDecimal(shebaoSummary.getcBirthIinsurance().toString());
				pshebaoSummary.setcBirthIinsurance(cBirth.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				BigDecimal cInjury = new BigDecimal(shebaoSummary.getcInjuryInsurance().toString());
				pshebaoSummary.setcInjuryInsurance(cInjury.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				
			pshebaoSummaries.add(pshebaoSummary);
			}
		}
		long total = getSheBaoSummaryCount(hql.toString(),params);
		summaryInfos.put("total", total);
		summaryInfos.put("rows", pshebaoSummaries);
		return summaryInfos;
	}
	
//	@Override
//	public List<PSystemConfig> findShebaoSumaryLock() {
//		List<SystemConfig> syses = systemConfigDao.find("from SystemConfig t where t.configType='shebaoSummary'");
//		List<PSystemConfig> psyses = new ArrayList<PSystemConfig>();
//		for (SystemConfig systemConfig : syses) {
//			PSystemConfig psysconfig = new PSystemConfig();
//			BeanUtils.copyProperties(systemConfig, psysconfig);
//			psyses.add(psysconfig);
//		}
//		return psyses;
//	}
	
	public long getSheBaoSummaryCount(String hql, Map<String, Object> params){
		StringBuffer hqll = new StringBuffer("select count(*) from SheBaoSummary t where ");
		hqll.append(hql.split("where")[1]);
		return shebaoSummaryDao.count(hqll.toString(), params);
	}
	
	@Override
	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session) {
		return getShebaoCompany(queryForm, session, 0);
	}
	
	@Override
	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session,int isExport) {
		Map<String, Object> shebaoCompanyInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		List<WageDistribution> wageDistributions = new ArrayList<WageDistribution>();
		List<PshebaoDetail> pwages = new ArrayList<PshebaoDetail>();
		StringBuffer hql = new StringBuffer("from WageDistribution t where t.radix > 0 and t.company = :company");
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
//		Calendar cal = Calendar.getInstance();
//		String date = df.format(cal.getTime());
//		hql.append(" and t.rubaoTime < :rubaoTime ");
//		params.put("rubaoTime", date);
		long total = 0;
		if(queryForm.getCompany()!=null){
			params.put("company", queryForm.getCompany());
			if(queryForm.getUsername()!=null&&!queryForm.getUsername().equals("")){
				hql.append(" and username like :username");
				params.put("username", "%"+queryForm.getUsername()+"%");
			}
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			if(isExport == 0){
				intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
				pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
			}
			String sort = "username";
			String order = SysConfig.DESC;
			if(queryForm.getSort() != null){
				sort = queryForm.getSort();
				if(queryForm.getOrder() != null){
					order = queryForm.getOrder();
				}
			}
			hql.append(" order by t." + sort + " " + order);
			wageDistributions = wageDistributionDao.find(hql.toString(), params, intPage, pageSize);
			for (WageDistribution wageDistribution : wageDistributions) {
				if(wageDistribution!=null){
					PshebaoDetail pwage = new PshebaoDetail();
					BeanUtils.copyProperties(wageDistribution, pwage);
					pwages.add(pwage);
				}
			}
			total = getWageDistributionCount(hql.toString(), params);
		}
		shebaoCompanyInfos.put("total", total);
		shebaoCompanyInfos.put("rows", pwages);
		return shebaoCompanyInfos;
	}
	
	@Override
	 public long getWageDistributionCount(String hql, Map<String, Object> params){
			StringBuffer hqll = new StringBuffer("select count(*) from WageDistribution t where ");
			hqll.append(hql.split("where")[1]);
			return wageDistributionDao.count(hqll.toString(), params);
	 }
	
	public Map<String, Object> findKaoqin(QueryForm queryForm, HttpSession session) {
		return findKaoqin(queryForm,session,0);
	}
	
	@Override
	public Map<String, Object> findKaoqin(QueryForm queryForm, HttpSession session,int isExport) {
		Map<String, Object> kaoqinInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		List<KaoQin> kaoqins = new ArrayList<KaoQin>();
		List<PKaoQin> pkaoqins = new ArrayList<PKaoQin>();
		StringBuffer hql = new StringBuffer("from KaoQin t where 1=1 ");
		long total = 0;
		addCondition(hql, queryForm, params);
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(isExport == 0){
			intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
			pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		}
		String sort = "userId";
		String order = SysConfig.DESC;
		if (queryForm.getSort() != null) {
			sort = queryForm.getSort();
			if (queryForm.getOrder() != null) {
				order = queryForm.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		
		kaoqins = kaoqinDao.find(hql.toString(), params, intPage, pageSize);
		for (KaoQin kaoqin : kaoqins) {
			if(kaoqin!=null){
				PKaoQin pkaoqin = new PKaoQin();
				BeanUtils.copyProperties(kaoqin, pkaoqin);
				pkaoqins.add(pkaoqin);
			}
		}
		total = getKaoQinCount(hql.toString(), params);
		kaoqinInfos.put("total", total);
		kaoqinInfos.put("rows", pkaoqins);
		return kaoqinInfos;
	}
	
	@Override
	public long getKaoQinCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from KaoQin t where ");
		hqll.append(hql.split("where")[1]);
		return kaoqinDao.count(hqll.toString(), params);
	}
	
	@Override
	public int updateKaoqin(PKaoQin pkaoqin) {
		KaoQin kaoqin = new KaoQin();
		BeanUtils.copyProperties(pkaoqin, kaoqin);
		try {
			kaoqinDao.update(kaoqin);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public int deleteKaoqin(PKaoQin pkaoqin) {
		KaoQin kaoqin = new KaoQin();
		BeanUtils.copyProperties(pkaoqin, kaoqin);
		try {
			kaoqinDao.delete(kaoqin);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Map<String, Object> findMonthWages(QueryForm queryForm, HttpSession session) {
		return findMonthWages(queryForm, session, 0);
	}
	
	@Override
	public Map<String, Object> findMonthWages(QueryForm queryForm, HttpSession session, int isExport) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		List<PMonthWages> pMonthWages = new ArrayList<PMonthWages>();
		Map<String, Object> monthWagesInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from MonthWages t where 1=1 ");
		addCondition(hql, queryForm, params);
		String sort = "employeeId";
		String order = SysConfig.DESC;
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
		List<MonthWages> monthWages = monthWagesDao.find(hql.toString(), params, intPage, pageSize);
		if(sessionInfo.getRoleId()<1 || sessionInfo.getRoleId()==100){
			for (MonthWages monthWage : monthWages) {
				if(monthWage!=null){
					PMonthWages pMonthWage = new PMonthWages();
					BeanUtils.copyProperties(monthWage, pMonthWage);
					pMonthWages.add(pMonthWage);
				}
			}
		}else{
			for (MonthWages monthWage : monthWages) {
				if(monthWage!=null){
					PMonthWages pMonthWage = new PMonthWages();
//					BeanUtils.copyProperties(monthWage, pMonthWage);
//					pMonthWage.setBasicWage(null);
//					pMonthWage.setPostSalary(null);
//					pMonthWage.setPerformanceRelatedPay(null);
//					pMonthWage.setSalary(null);
					pMonthWage.setId(monthWage.getId());
					pMonthWage.setUsername(monthWage.getUsername());
					pMonthWage.setFirstLevel(monthWage.getFirstLevel());
					pMonthWage.setSecondLevel(monthWage.getSecondLevel());
					pMonthWage.setThirdLevel(monthWage.getThirdLevel());
					pMonthWage.setFourthLevel(monthWage.getFourthLevel());
					pMonthWage.setAccountBank(monthWage.getAccountBank());
					pMonthWage.setAccount(monthWage.getAccount());
					pMonthWage.setIdentityCard(monthWage.getIdentityCard());
					pMonthWage.setChuqinDay(monthWage.getChuqinDay());
					pMonthWage.setZhuanzhengChaeDay(monthWage.getZhuanzhengChaeDay());
					pMonthWage.setFakuan(monthWage.getFakuan());
					pMonthWage.setJiangjin(monthWage.getJiangjin());
					pMonthWage.setBufaSalary(monthWage.getBufaSalary());
					pMonthWage.setShiJiaHour(monthWage.getShiJiaHour());
					pMonthWage.setBingJiaHour(monthWage.getBingJiaHour());
					pMonthWage.setKuangGongHour(monthWage.getKuangGongHour());
					pMonthWage.setChidaoYingkouDay(monthWage.getChidaoYingkouDay());
					pMonthWage.setChanJiaDay(monthWage.getChanJiaDay());
					pMonthWage.setAnnualLleave(monthWage.getAnnualLleave());
					pMonthWage.setSickLleaveTotal(monthWage.getSickLleaveTotal());
					pMonthWage.setHiredate(monthWage.getHiredate());
					pMonthWage.setLeaveTime(monthWage.getLeaveTime());
					pMonthWage.setZhuanzhengTime(monthWage.getZhuanzhengTime());
					pMonthWage.setKaoQinremarks(monthWage.getKaoQinremarks());
					pMonthWage.setCompany(monthWage.getCompany());
					pMonthWage.setZhuanzhengReport(monthWage.getZhuanzhengReport());
					pMonthWage.setLizhiReport(monthWage.getLizhiReport());
					pMonthWage.setRuzhiReport(monthWage.getRuzhiReport());
					pMonthWages.add(pMonthWage);
				}
			}
		}
		long total = getMonthWagesCount(hql.toString(),params);
		monthWagesInfos.put("total", total);
		monthWagesInfos.put("rows", pMonthWages);
		return monthWagesInfos;
	}
	
	@Override
	public long getMonthWagesCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from MonthWages t where ");
		hqll.append(hql.split("where")[1]);
		return monthWagesDao.count(hqll.toString(), params);
	}
	
	@Override
	public int updateMonthWages(PMonthWages pmonthWages, SessionInfo sessionInfo) {
//		int roleId = sessionInfo.getRoleId();
//		MonthWages monthWages = monthWagesDao.get(MonthWages.class, pmonthWages.getId());
//		if(monthWages!=null){
//			
//		}
		MonthWages monthWages = new MonthWages();
		BeanUtils.copyProperties(pmonthWages, monthWages);
		try {
			monthWagesDao.update(monthWages);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public Map<String, Object> queryWagesDate(QueryForm queryForm) {
		List<PWagesDate> pWagesDates = new ArrayList<PWagesDate>();
		Map<String, Object> wagesDateInfos = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from WagesDate t where 1=1 ");
		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MONTH, -1);
		if(queryForm.getWagesMonth()!=null && queryForm.getWagesMonth()!=""){
			hql.append(" and date like :date ");
			params.put("date",queryForm.getWagesMonth() + "%");
		}else{
			String month = (new SimpleDateFormat("yyyy.MM").format(calendar.getTime())).toString();
			hql.append(" and date like :date ");
			params.put("date",month + "%");
		}
		String sort = "date";
		String order = SysConfig.ASC;
		if(queryForm.getSort() != null){
			sort = queryForm.getSort();
			if(queryForm.getOrder() != null){
				order = queryForm.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
		int pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		List<WagesDate> wagesDates = wagesDateDao.find(hql.toString(), params, intPage, pageSize);
		for (WagesDate wagesDate : wagesDates) {
			PWagesDate pWagesDate = new PWagesDate();
			BeanUtils.copyProperties(wagesDate, pWagesDate);
			pWagesDates.add(pWagesDate);
		}
		long total = getWagesDateCount(hql.toString(),params);
		wagesDateInfos.put("total", total);
		wagesDateInfos.put("rows", pWagesDates);
		return wagesDateInfos;
	}
	
	public long getWagesDateCount(String hql,Map<String,Object> params){
		StringBuffer hqll = new StringBuffer("select count(*) from WagesDate t where ");
		hqll.append(hql.split("where")[1]);
		return wagesDateDao.count(hqll.toString(), params);
	}
	
	
	@Override
	public int updateWagesDate(PWagesDate pwagesDate) {
		WagesDate wagesDate = new WagesDate();
		BeanUtils.copyProperties(pwagesDate, wagesDate);
		try {
			wagesDateDao.saveOrUpdate(wagesDate);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateWagesDates(List<PWagesDate> pwagesDates) {
		int saveNum = 0;
		for (PWagesDate pWagesDate : pwagesDates) {
			saveNum +=updateWagesDate(pWagesDate);
		}
		return saveNum;
	}
	
	@Override
	public int deleteWagesDate(PWagesDate pwagesDate) {
		if(pwagesDate!=null&&pwagesDate.getDate()!=null){
			WagesDate wagesDate = new WagesDate();
			BeanUtils.copyProperties(pwagesDate, wagesDate);
			try {
				wagesDateDao.delete(wagesDate);
				return 1;
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}
	
	@Override
	public long updateSysconfig(PSystemConfig ps) {
		SystemConfig sys = new SystemConfig();
		BeanUtils.copyProperties(ps, sys);
		try {
			systemConfigDao.saveOrUpdate(sys);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	@Override
	public int generateKaoqin(String date, String preDate, String afterDate) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("date1", date);
		params.put("date2", preDate);
		params.put("date3", afterDate);
		String sql = "{CALL update_kaoqin_pr( :date1, :date2, :date3)}";
		try {
			objectDao.prepareCall(sql, params);
			return 1;
		} catch (HibernateException e) {
			return 0;
		}
	}
	
	@Override
	public int generateMonthWages() {
//		String sql = " CALL update_monthWages_pr(); ";
		String sql = "{CALL update_monthWages_pr()}";
		try {
			objectDao.prepareCall(sql, null);
			return 1;
		} catch (HibernateException e) {
			return 0;
		}
	}
	
	@Override
	public PMonthWages getMonthWages(Integer id) {
		MonthWages monthWages = monthWagesDao.get(MonthWages.class, id);
		PMonthWages pMonthWages = new PMonthWages();
		BeanUtils.copyProperties(monthWages, pMonthWages);
		return pMonthWages;
	}
	
	@Override
	public List<PSystemConfig> findSysconfig(String name, String type) {
		if(type==null){
			return null;
		}
		StringBuffer hql = new StringBuffer("from SystemConfig t where t.configType=:type");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("type", type);
		if(name!=null){
			hql.append(" and name=:name");
			params.put("name", name);
		}
		List<SystemConfig> syses = systemConfigDao.find(hql.toString(),params);
		List<PSystemConfig> psyses = new ArrayList<PSystemConfig>();
		for (SystemConfig systemConfig : syses) {
			PSystemConfig psysconfig = new PSystemConfig();
			BeanUtils.copyProperties(systemConfig, psysconfig);
			psyses.add(psysconfig);
		}
		return psyses;
	}
	
	@Override
	public PSystemConfig getSysconfig(String name, String type) {
		if(type!=null&&name!=null){
			StringBuffer hql = new StringBuffer("from SystemConfig t where t.configType=:type and name=:name");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("type", type);
			params.put("name", name);
			SystemConfig syse = systemConfigDao.get(hql.toString(),params);
			PSystemConfig psysconfig = new PSystemConfig();
			if(syse!=null){
				BeanUtils.copyProperties(syse, psysconfig);
				return psysconfig;
			}
		}
		return null;
	}
	
	@Override
	public long fafang() {
		try {
			objectDao.prepareCall("UPDATE 职员工资分布表 z,当月工资表 m SET z.病假累计=(z.病假累计 + m.病假小时数),z.年假累计=(z.年假累计 + m.年假天数) WHERE z.职员编号=m.职工编号 AND (m.病假小时数>0 OR m.年假天数>0)", null);
//			objectDao.executeHql("update WageDistribution,MonthWages m set t.sickLleaveTotal=(t.sickLleaveTotal + m.bingJiaHour),t.annualLleave=(t.annualLleave + m.nianJiaDay) where t.employeeId = m.employeeId");
//			objectDao.executeHql("update WageDistribution t,MonthWages m set t.sickLleaveTotal=(t.sickLleaveTotal + m.bingJiaHour),t.annualLleave=(t.annualLleave + m.nianJiaDay) where t.employeeId = m.employeeId");
			return 1;
		} catch (Exception e) {
		}
		return 0;
	}
	protected void addCondition(StringBuffer hql, QueryForm queryForm, Map<String, Object> params) {
		if(queryForm != null){
			if(queryForm.getConfigurable() != null && !queryForm.getConfigurable().equals("")){
				if(queryForm.getConfigurable_value() != null && !queryForm.getConfigurable_value().equals("")){
					if(queryForm.getConfigurable_value().equalsIgnoreCase("null")){
						hql.append(" and t." + queryForm.getConfigurable() + " is null");
					}else{
						hql.append(" and t." + queryForm.getConfigurable() + " like '%" + queryForm.getConfigurable_value() + "%' ");
					}
				}
			}
			if(queryForm.getUsername() != null && !queryForm.getUsername().equals("")){
				if(queryForm.getUsername().equalsIgnoreCase("null")){
					hql.append(" and t.username is null ");
				}else{
					hql.append(" and t.username like :username ");
					params.put("username", "%" + queryForm.getUsername() + "%");
				}
			}
			if(queryForm.getFirstLevel() != null && !queryForm.getFirstLevel().equals("")){
				if(queryForm.getFirstLevel().equalsIgnoreCase("null")){
					hql.append(" and t.firstLevel is null ");
				}else{
					hql.append(" and t.firstLevel like :firstLevel ");
					params.put("firstLevel", "%" + queryForm.getFirstLevel() + "%");
				}
			}
			if(queryForm.getSecondLevel() != null && !queryForm.getSecondLevel().equals("")){
				if(queryForm.getSecondLevel().equalsIgnoreCase("null")){
					hql.append(" and t.secondLevel is null ");
				}else{
					hql.append(" and t.secondLevel like :secondLevel ");
					params.put("secondLevel", "%" + queryForm.getSecondLevel() + "%");
				}
			}
			if(queryForm.getThirdLevel() != null && !queryForm.getThirdLevel().equals("")){
				if(queryForm.getThirdLevel().equalsIgnoreCase("null")){
					hql.append(" and t.thirdLevel is null ");
				}else{
					hql.append(" and t.thirdLevel like :thirdLevel ");
					params.put("thirdLevel", "%" + queryForm.getThirdLevel() + "%");
				}
			}
			if(queryForm.getFourthLevel() != null && !queryForm.getFourthLevel().equals("")){
				if(queryForm.getFourthLevel().equalsIgnoreCase("null")){
					hql.append(" and t.fourthLevel is null ");
				}else{
					hql.append(" and t.fourthLevel like :fourthLevel ");
					params.put("fourthLevel", "%" + queryForm.getFourthLevel() + "%");
				}
			}
			if(queryForm.getCompany() != null && !queryForm.getCompany().equals("")){
				if(queryForm.getCompany().equalsIgnoreCase("null")){
					hql.append(" and t.company is null ");
				}else{
					hql.append(" and t.company like :company ");
					params.put("company", "%" + queryForm.getCompany() + "%");
				}
			}
			if(queryForm.getInsuranceCompany() != null && !queryForm.getInsuranceCompany().equals("")){
				if(queryForm.getInsuranceCompany().equalsIgnoreCase("null")){
					hql.append(" and t.insuranceCompany is null ");
				}else{
					hql.append(" and t.insuranceCompany like :insuranceCompany ");
					params.put("insuranceCompany", "%" + queryForm.getInsuranceCompany() + "%");
				}
			}
			if(queryForm.getDegree() != null && !queryForm.getDegree().equals("")){
				if(queryForm.getDegree().equalsIgnoreCase("null")){
					hql.append(" and t.degree is null ");
				}else{
					hql.append(" and t.degree like :degree ");
					params.put("degree", "%" + queryForm.getDegree() + "%");
				}
			}
			if(queryForm.getHiredate() != null && !queryForm.getHiredate().equals("")){
				if(queryForm.getHiredate().equalsIgnoreCase("null")){
					hql.append(" and t.hiredate is null ");
				}else{
					hql.append(" and t.hiredate like :hiredate ");
					params.put("hiredate", "%" + queryForm.getHiredate() + "%");
				}
			}
			if(queryForm.getLeaveTime() != null && !queryForm.getLeaveTime().equals("")){
				if(queryForm.getLeaveTime().equalsIgnoreCase("null")){
					hql.append(" and t.leaveTime is null ");
				}else{
					hql.append(" and t.leaveTime like :leaveTime ");
					params.put("leaveTime", "%" + queryForm.getLeaveTime() + "%");
				}
			}
			if(queryForm.getZhuanzhengTime() != null && !queryForm.getZhuanzhengTime().equals("")){
				if(queryForm.getZhuanzhengTime().equalsIgnoreCase("null")){
					hql.append(" and t.zhuanzhengTime is null ");
				}else{
					hql.append(" and t.zhuanzhengTime like :zhuanzhengTime ");
					params.put("zhuanzhengTime", "%" + queryForm.getZhuanzhengTime() + "%");
				}
			}if(queryForm.getHiredate() != null && !queryForm.getHiredate().equals("")){
				if(queryForm.getHiredate().equalsIgnoreCase("null")){
					hql.append(" and t.hiredate is null ");
				}else{
					hql.append(" and t.hiredate like :hiredate ");
					params.put("hiredate", "%" + queryForm.getHiredate() + "%");
				}
			}if(queryForm.getRuzhiReport() != null && !queryForm.getRuzhiReport().equals("")){
				if(queryForm.getRuzhiReport().equalsIgnoreCase("null")){
					hql.append(" and t.ruzhiReport is null ");
				}else{
					hql.append(" and t.ruzhiReport like :ruzhiReport ");
					params.put("ruzhiReport", "%" + queryForm.getRuzhiReport() + "%");
				}
			}if(queryForm.getLizhiReport() != null && !queryForm.getLizhiReport().equals("")){
				if(queryForm.getLizhiReport().equalsIgnoreCase("null")){
					hql.append(" and t.lizhiReport is null ");
				}else{
					hql.append(" and t.lizhiReport like :lizhiReport ");
					params.put("lizhiReport", "%" + queryForm.getLizhiReport() + "%");
				}
			}if(queryForm.getZhuanzhengReport() != null && !queryForm.getZhuanzhengReport().equals("")){
				if(queryForm.getZhuanzhengReport().equalsIgnoreCase("null")){
					hql.append(" and t.zhuanzhengReport is null ");
				}else{
					hql.append(" and t.zhuanzhengReport like :zhuanzhengReport ");
					params.put("zhuanzhengReport", "%" + queryForm.getZhuanzhengReport() + "%");
				}
			}if(queryForm.getAccount() != null && !queryForm.getAccount().equals("")){
				if(queryForm.getAccount().equalsIgnoreCase("null")){
					hql.append(" and t.account is null ");
				}else{
					hql.append(" and t.account like :account ");
					params.put("account", "%" + queryForm.getAccount() + "%");
				}
			}if(queryForm.getAccountBank() != null && !queryForm.getAccountBank().equals("")){
				if(queryForm.getAccountBank().equalsIgnoreCase("null")){
					hql.append(" and t.accountBank is null ");
				}else{
					hql.append(" and t.accountBank like :accountBank ");
					params.put("accountBank", "%" + queryForm.getAccountBank() + "%");
				}
			}if(queryForm.getIdentityCard() != null && !queryForm.getIdentityCard().equals("")){
				if(queryForm.getIdentityCard().equalsIgnoreCase("null")){
					hql.append(" and t.identityCard is null ");
				}else{
					hql.append(" and t.identityCard like :identityCard ");
					params.put("identityCard", "%" + queryForm.getIdentityCard() + "%");
				}
			}if(queryForm.getLevelc() != null && !queryForm.getLevelc().equals("")){
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
	public UserName getUserInfo(QueryForm queryform) {
		return useDao.get(UserName.class, queryform.getId());
	}
	
	/*打开工资汇总信息
	 * 史昊  2016.10.17*/
	public long getGongziHuiZongCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from GongziHuiZong t where ");
		hqll.append(hql.split("where")[1]);
		return sheBaoDao.count(hqll.toString(), params);
	}
	@Override
	public Map<String, Object> getgongzihuizong(QueryForm queryForm) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<PGongziHuiZong> pgongzihuizongs = new ArrayList<PGongziHuiZong>();
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from GongziHuiZong t where 1=1");
		addCondition(hql, queryForm, params);
//		if(queryForm.getUsername()!=null && queryForm.getUsername()!=""){
//			hql.append(" and (t.username like :username1 )");
//			params.put("username1", "%" + queryForm.getUsername() + "%");
//		}
//		if(queryForm.getFourthLevel()!=null && queryForm.getFourthLevel()!=""){
//			hql.append(" and t.fourthLevel like :fourthLevel1");
//			params.put("fourthLevel1", "%" + queryForm.getFourthLevel() + "%");
//		}
			
		String sort = "id";
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
		
		List<GongziHuiZong> gongzihuizongs =gongzihuizongDao.find(hql.toString(), params, intPage, pageSize);
		
		long total = getGongziHuiZongCount(hql.toString(), params);
		for (GongziHuiZong gongzihuizong : gongzihuizongs) {
			if(gongzihuizong!=null){
				PGongziHuiZong pgongzihuizong1 = new PGongziHuiZong();
				BeanUtils.copyProperties(gongzihuizong, pgongzihuizong1);
				pgongzihuizongs.add(pgongzihuizong1);
			}
		}
		result.put("total", total);
		result.put("rows", pgongzihuizongs);
	
		return result;
	}
}
