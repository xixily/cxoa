package com.chaoxing.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.caiwu.PBaoxiaoStatus;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.po.caiwu.Baoxiao;
import com.chaoxing.oa.entity.po.caiwu.BaoxiaoStatus;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.system.cache.CacheManager;
import com.chaoxing.oa.util.DateUtil;
import com.chaoxing.oa.util.SqlHelper;

@Service("publicCaiwuService")
public class PubCaiwuServiceImpl implements PubCaiwuService {
	@Autowired
	private BaseDaoI<Baoxiao> baoxiaoDao;
	@Autowired
	private BaseDaoI<Object> objectDao;
	@Autowired
	private BaseDaoI<RenshiUserName> userNameDao;
	@Autowired
	private BaseDaoI<BaoxiaoStatus> bxStatusDao;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public PBaoxiao getBaoxiao(Long id) {
		return null;
	}

	@Override
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, int id) {
		System.out.println("当前登录人ID："+id);
		List<Baoxiao> baoxiaos = new ArrayList<Baoxiao>();
		List<PBaoxiao> pbs = new ArrayList<PBaoxiao>();
		Map<String, Object> results = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from Baoxiao t where uid=" + id);
		try {
			hql.append(" and ").append(SqlHelper.prepareAndSql(pbaoxiao, params, true));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("hql>>>>:" + hql.toString());
		System.out.println("params>:" + params);
//		if(null!=value && null!=type){
//			params = new HashMap<String, Object>();
//			params.put("value", "%" +value + "%");
//			hql.append(" and t."+type +" like :value");
//		}
		String sort = "id";
		String order = SysConfig.DESC;
		if(page.getSort() != null){
			sort = page.getSort();
			if(page.getOrder() != null){
				order = page.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = null != page.getPage() ? page.getPage():0;
		int pageSize = null!=page.getRows() ? page.getRows():10;
		baoxiaos = baoxiaoDao.find(hql.toString(),params,intPage,pageSize);
		Iterator<Baoxiao> it = baoxiaos.iterator();
		Baoxiao bx = null;
		PBaoxiao pbx = null;
		if(null!=baoxiaos && baoxiaos.size()>0){
			while(it.hasNext()){
				bx = it.next();
				pbx = new PBaoxiao();
//				BeanUtils.copyProperties(bx, pbx);
				pbx.setId(bx.getId());
				pbx.setAccount(bx.getAccount());
				pbx.setApproid(bx.getApproid());
//				pbx.setApprover(bx.getApprover());
//				pbx.setAproEmail(bx.getAproEmail());
				pbx.setAproTime(DateUtil.format(bx.getAproTime(), "yyyy-MM-dd"));
				pbx.setBank(bx.getBank());
				pbx.setBaoxMoney(bx.getBaoxMoney());
				pbx.setBaoxTime(DateUtil.format(bx.getBaoxTime(), "yyyy-MM-dd"));
				pbx.setCaiwuRemarks(bx.getCaiwuRemarks());
//				pbx.setCellCoreId(bx.getCellCoreId());
//				pbx.setEmail(bx.getEmail());
				pbx.setExplain(bx.getExplain());
				pbx.setHuankuan(bx.getHuankuan());
				pbx.setjTime(DateUtil.format(bx.getjTime(), "yyyy-MM-dd"));
				pbx.setMoney(bx.getMoney());
				pbx.setNumber(bx.getNumber());
				pbx.setReciveTime(DateUtil.format(bx.getReciveTime(), "yyyy-MM-dd"));
				pbx.setStatus(bx.getStatus());
				pbx.setTuipiao(bx.getTuipiao());
				pbx.setUid(bx.getUid());
//				pbx.setUsername(bx.getUsername());
				pbs.add(pbx);
			}
		}
		results.put("rows", pbs);
		results.put("total", getCount(hql.toString(),params));
		return results;
	}

	private long getCount(String hql, Map<String, Object> params) {
		String hqll = "select count(*) from " + hql.split("from")[1];
		return objectDao.count(hqll,params);
	}

	@Override
	public Serializable addBaoxiao(PBaoxiao pbaoxiao) {
		Baoxiao baoxiao = new Baoxiao();
		copyp2e(pbaoxiao, baoxiao);
		try {
			return baoxiaoDao.save(baoxiao);
		} catch (Exception e) {
			logger.error("方法[PubCaiwuServiceImpl.addBaoxiao]失败" + e);
		}
		return null;
	}

	private void copyp2e(PBaoxiao pbaoxiao, Baoxiao baoxiao) {
		baoxiao.setId(pbaoxiao.getId());
		baoxiao.setjTime((null!=pbaoxiao.getjTime() ? DateUtil.format(pbaoxiao.getjTime()) : null));
		baoxiao.setUid(pbaoxiao.getUid());
//		baoxiao.setUsername(pbaoxiao.getUsername());
//		baoxiao.setEmail(pbaoxiao.getEmail());
//		baoxiao.setCellCoreId(pbaoxiao.getCellCoreId());
//		baoxiao.setCellCoreEmail(pbaoxiao.getCellCoreEmail());
		baoxiao.setNumber(pbaoxiao.getNumber());
		baoxiao.setMoney(pbaoxiao.getMoney());
		baoxiao.setHuankuan(pbaoxiao.getHuankuan());
		baoxiao.setBank(pbaoxiao.getBank());
		baoxiao.setAccount(pbaoxiao.getAccount().replace(" ", ""));
		baoxiao.setExplain(pbaoxiao.getExplain());
		baoxiao.setApproid(pbaoxiao.getApproid());
//		baoxiao.setApprover(pbaoxiao.getApprover());
//		baoxiao.setAproEmail(pbaoxiao.getAproEmail());
		baoxiao.setAproTime((null!=pbaoxiao.getAproTime() ? DateUtil.format(pbaoxiao.getAproTime()) : null));
		baoxiao.setReciveTime((null!=pbaoxiao.getReciveTime() ? DateUtil.format(pbaoxiao.getReciveTime()):null));
		baoxiao.setBaoxTime((null!=pbaoxiao.getBaoxTime() ? DateUtil.format(pbaoxiao.getBaoxTime()) : null));
		baoxiao.setBaoxMoney(pbaoxiao.getBaoxMoney());
		baoxiao.setTuipiao(pbaoxiao.getTuipiao());
		baoxiao.setCaiwuRemarks(pbaoxiao.getCaiwuRemarks());
		baoxiao.setStatus(pbaoxiao.getStatus());
	}

	@Override
	public long updateBaoxiao(PBaoxiao pBaoxiao) {
		Baoxiao baoxiao = new Baoxiao();
		copyp2e(pBaoxiao, baoxiao);
		try {
			baoxiaoDao.update(baoxiao);
			return 1;
		} catch (Exception e) {
			logger.error("[PubCaiwuServiceImpl][updateBaoxiao]失败：" + e);
			return 0;
		}
	}

	@Override
	public int deleteBaoxiao(Long id, Integer uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getLastYear(int id) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2016.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		Date lastYear = cal.getTime();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from Baoxiao t where t.uid=:id and t.baoxTime>=:lastYear and t.baoxTime<:thisYear");
		params.put("thisYear", thisYear);
		params.put("lastYear", lastYear);
		params.put("id", id);
		List<Object> lis = objectDao.find(hql.toString(), params);
		Double value1 = 0.0d;
		if(null!=lis && lis.size()>0){
			value1 = (Double) ((null!=lis.get(0)) ? lis.get(0):0.0d);
		}
		return value1;
	}

	@Override
	public Double getThisYear(String id) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2016.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();
//		int thisyear = cal.get(Calendar.YEAR);
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from Baoxiao t where t.baoxTime>=:thisYear and t.baoxTime<:afterYear");
//		params.put("thisYear", thisyear + "-01" + "-01");
//		params.put("afterYear", (thisyear+1) + "-01" + "-01");
		params.put("thisYear", thisYear);
		params.put("afterYear", afterYear);
		List<Object> lis = objectDao.find(hql.toString(), params);
		Double value1 = 0.0d;
		if(lis.size()>0){
			value1 = (Double) ((null!=lis.get(0)) ? lis.get(0):0.0d);
		}
		return value1;
	}

	/**
	 * 合同
	 */
	@Override
	public void findCellsByEmail(String email) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select id from RenshiUserName where guidanceEmail=:gemail");
		String sql = "select id from RenshiUserName where guidanceEmail=:gemail";
		Map<String,Object> params = new HashMap<String, Object>();
		List<Object> olist = objectDao.find(hql.toString(),params, 0, 1000);
		Iterator<Object> it = olist.iterator();
		while(it.hasNext()){
			Object id = it.next();
		}
	}

	@Override
	public PRenshiEmployee getEmployee(Integer uid) {
		RenshiUserName username = userNameDao.get(RenshiUserName.class, uid);
		PRenshiEmployee puser = new PRenshiEmployee();
		BeanUtils.copyProperties(username, puser);
		return puser;
	}

	/**
	 * @param pbaoxiaos 报销信息
	 * @param b <strong>true</strong>为批准<strong>false<strong>为拒绝批准
	 */
	@Override
	public int updateApprove(PBaoxiao pbaoxiaos, boolean b, int uid, String email) {
		String sql = "UPDATE 报销表2 t,人事username r  SET STATUS = :status,批准人id = :uid,批准时间  = CURRENT_TIMESTAMP  WHERE t.`报销人id`=r.id AND t.id=:id AND (r.细胞核邮箱=:email || r.指导邮箱=:email)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", b ? SysConfig.CW_BX_APPROVE_AGREE : SysConfig.CW_BX_APPROVE_DISAGREE );
		params.put("uid", uid);
		params.put("id", pbaoxiaos.getId());
		params.put("email", email);
		return baoxiaoDao.prepareCall(sql, params);
	}
	

	@Override
	public int updateBaoxiaoReceive(Integer id, boolean agree, String spRemarks, Integer uid) {
		String hql ="update Baoxiao set reciverId=:uid,reciveTime=CURRENT_TIMESTAMP,status=:status,rcRemarks=:rcRemarks where id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("id", id);
		params.put("rcRemarks", spRemarks);
		params.put("status", agree ? SysConfig.CW_BX_RECIVED_AGREE : SysConfig.CW_BX_RECIVED_DISAGREE);
		return baoxiaoDao.executeHql(hql, params);
	}

	@Override
	public void updateBaoxiaoCheck(PBaoxiao pbaoxiao, boolean agree) {
		String hql ="update Baoxiao set checkerId=:checkerId,baoxTime=:baoxTime,baoxMoney=:baoxMoney,tuipiao=:tuipiao,status:status where id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("checkerId", pbaoxiao.getCheckerId());
		params.put("baoxTime", DateUtil.format(pbaoxiao.getBaoxTime()));
		params.put("baoxMoney", pbaoxiao.getBaoxMoney());
		params.put("tuipiao", pbaoxiao.getTuipiao());
		params.put("status", (agree ? 6:7));
		params.put("id", pbaoxiao.getId());
		baoxiaoDao.executeHql(hql,params);
	}

	@Override
	public List<PBaoxiaoStatus> findAllBaoxiaoStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAllCells(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNextStep(Integer statu, int st) {
		List<BaoxiaoStatus> status = getBaoxiaoStatus();
		Iterator<BaoxiaoStatus> it = status.iterator();
		while(it.hasNext()){
			BaoxiaoStatus bxt = it.next();
			if(null != bxt.getPrestatus() && bxt.getPrestatus()==statu && null!=bxt.getGrade() && st == bxt.getGrade()){
				return bxt.getStatus();
			}
		}
		return 0;
	}

	@Override
	public int getPreStep(Integer statu) {
		List<BaoxiaoStatus> status = getBaoxiaoStatus();
		Iterator<BaoxiaoStatus> it = status.iterator();
		while(it.hasNext()){
			BaoxiaoStatus bxt = it.next();
			if(statu == bxt.getStatus()){
				if(null != bxt.getPrestatus()){
					return bxt.getPrestatus();
				}
			}
		}
		return 0;
	}

	@Override
	public int updateTonextStep(Long id, Integer statu, int st) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer nextStatu = getNextStep(statu, st);
		if(nextStatu != 0){
			params.put("status", nextStatu);
			params.put("id", id);
			return baoxiaoDao.executeHql("update Baoxiao set status=:status where id=:id", params);
		}
		return 0 ;
	}

	@Override
	public int updateToPreStep(Long id, Integer statu) {
		Integer preStatu = getPreStep(statu);
		if(preStatu != 0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", getPreStep(statu));
			params.put("id", id);
			return baoxiaoDao.executeHql("update Baoxiao set status=:status where id=:id", params);
		}
		return 0;
	}
	
	public List<BaoxiaoStatus> getBaoxiaoStatus(){
		return getBaoxiaoStatus(false);
	}
	
	public List<BaoxiaoStatus> getBaoxiaoStatus(boolean flag){
		if(flag || null == CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS)){
			List<BaoxiaoStatus> status = bxStatusDao.find("from BaoxiaoStatus");
			CacheManager.getInstance().put(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS, status);
		}
		return (List<BaoxiaoStatus>) CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS);
		
	}
	

}
