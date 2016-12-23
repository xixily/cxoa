package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.po.caiwu.Baoxiao;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.DateUtil;
import com.chaoxing.oa.util.SqlHelper;

@Service("publicService")
public class PubCaiwuServiceImpl implements PubCaiwuService {
	@Autowired
	private BaseDaoI<Baoxiao> baoxiaoDao;
	@Autowired
	private BaseDaoI<Object> objectDao;

	@Override
	public PBaoxiao getBaoxiao(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, int id) {
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
				pbx.setApprover(bx.getApprover());
				pbx.setAproEmail(bx.getAproEmail());
				pbx.setAproTime(DateUtil.format(bx.getAproTime(), "yyyy-MM-dd"));
				pbx.setBank(bx.getBank());
				pbx.setBaoxMoney(bx.getBaoxMoney());
				pbx.setBaoxTime(DateUtil.format(bx.getBaoxTime(), "yyyy-MM-dd"));
				pbx.setCaiwuRemarks(bx.getCaiwuRemarks());
				pbx.setCellCoreId(bx.getCellCoreId());
				pbx.setEmail(bx.getEmail());
				pbx.setExplain(bx.getExplain());
				pbx.setHuankuan(bx.getHuankuan());
				pbx.setjTime(DateUtil.format(bx.getjTime(), "yyyy-MM-dd"));
				pbx.setMoney(bx.getMoney());
				pbx.setNumber(bx.getNumber());
				pbx.setReciveTime(DateUtil.format(bx.getReciveTime(), "yyyy-MM-dd"));
				pbx.setStatus(bx.getStatus());
				pbx.setTuipiao(bx.getTuipiao());
				pbx.setUid(bx.getUid());
				pbx.setUsername(bx.getUsername());
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
	public long updateBaoxiao(PBaoxiao pBaoxiao) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBaoxiao(int id) {
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
//		int thisyear = cal.get(Calendar.YEAR);
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from Baoxiao t where t.baoxTime>=:lastYear and t.baoxTime<:thisYear");
//		params.put("thisYear", thisyear + "-01" + "-01");
//		params.put("afterYear", (thisyear+1) + "-01" + "-01");
		params.put("thisYear", thisYear);
		params.put("lastYear", lastYear);
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

	

}
