package com.chaoxing.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseHetongDaoI;
import com.chaoxing.oa.entity.page.SF.Cargo;
import com.chaoxing.oa.entity.page.SF.Order;
import com.chaoxing.oa.entity.page.SF.OrderResponse;
import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.entity.sqlpo.FaPiao;
import com.chaoxing.oa.entity.sqlpo.Fahuo;
import com.chaoxing.oa.service.HetongService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.SFUtil;
@Service("hetongService")
public class HetongServiceImpl implements HetongService {
	private BaseHetongDaoI<Fahuo> htDao;
	private BaseHetongDaoI<FaPiao> fpDao;
	@Autowired
	private BaseHetongDaoI<Object> objDao;
	
	
	public BaseHetongDaoI<FaPiao> getFpDao() {
		return fpDao;
	}
	@Autowired
	public void setFpDao(BaseHetongDaoI<FaPiao> fpDao) {
		this.fpDao = fpDao;
	}
	public BaseHetongDaoI<Fahuo> getHtDao() {
		return htDao;
	}
	@Autowired
	public void setHtDao(BaseHetongDaoI<Fahuo> htDao) {
		this.htDao = htDao;
	}

	@Override
	public Map<String, Object> findHetong(PFahuo queryForm, int isExport) {
		StringBuffer hql = new StringBuffer("from Fahuo t where 1=1 and postMethod=:postMethod");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("postMethod", "顺丰");
		List<Fahuo> fahuos = null;
		List<PFahuo> pfahuos = new ArrayList<PFahuo>();
		Map<String, Object> result = new HashMap<String, Object>();
//		addConditions(queryForm, hql, params);
		String sort = "id";
		String order = SysConfig.DESC;
		if(queryForm.getSort() != null){
			sort = queryForm.getSort();
			if(queryForm.getOrder() != null){
				order = queryForm.getOrder();
			}
		}
		long count = getHetongCount(hql.toString(),params);
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(isExport == 0){
			intPage = (queryForm == null || queryForm.getPage() == 0) ? 1 : queryForm.getPage();
			pageSize = (queryForm == null || queryForm.getRows() == 0) ? 100 : queryForm.getRows();
		}
		hql.append(" order by t." + sort + " " + order);
		fahuos = htDao.find(hql.toString(), params, intPage, pageSize);
		for (Fahuo fahuo : fahuos) {
			PFahuo pfahuo = new PFahuo();
			BeanUtils.copyProperties(fahuo, pfahuo);
			pfahuos.add(pfahuo);
		}
		result.put("rows", pfahuos);
		result.put("total", count);
		return result;
//		return pfahuos;
	}

	private long getHetongCount(String hql, Map<String, Object> params) {
		StringBuffer hqll = new StringBuffer("select count(*) from Fahuo t where ");
		hqll.append(hql.split("where")[1]);
		return htDao.count(hqll.toString(), params);
	}
	
	
	@Override
	public OrderResponse sendKuaidi(PFahuo pfahuo) {
//		PFahuo pf = new PFahuo();
//		Integer a = (int) (Math.random()*10000000);
//		pf.setOrderid(a);
//		pf.setArea("0796");
//		pf.setAreaCode("0796");
//		pf.setContent("就是想发个快递");
//		pf.setD_address("师范大学（瑶湖校区）邓 17744543034");
//		pf.setD_company("师范大学");
//		pf.setD_contact("小邓");
//		pf.setD_tel("18146612837");
//		pf.setD_post_code("330022");
//		pf.setD_city("南昌");
//		pf.setRemark("这是一个很长很长的故事i·~~");
//		pf.setSender("邓~");
		Order order = new Order();
		BeanUtils.copyProperties(pfahuo, order);
		order.setJ_tel("17744543034");
		order.setJ_company("世纪超星公司");
		order.setJ_contact("小邓");
		order.setJ_address("北京市海淀区 上地东里3区4号楼 601");
		Cargo cargo = new Cargo();
		cargo.setName("发票");
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(cargo);
//		Json result = SFUtil.sendOrder(order, cargos, null);
//		if(result.isSuccess()){
//			OrderResponse or = (OrderResponse) result.getObj();
//			String mailno = or.getMailno();
//			or.getOrderid();
//			BarCode128C.getCode128CPicture(mailno, 22, "d:/code3.jpg");
//		}
//		System.out.println(result.getMsg());
		//查询订单 6320318
//		Json result2 = s.queryOrder(String.valueOf(pf.getOrderid()));
//		Json result2 = s.queryOrder("4624347");
//		System.out.println((OrderResponse)result2.getObj());
		Json result3 = SFUtil.queryRoute("4624347");
		System.out.println(result3.getMsg());
		System.out.println("路由查询结果" + (OrderResponse)result3.getObj());
		return null;
	}
	/*private void addConditions(QueryForm queryForm, StringBuffer hql, Map<String, Object> params) {
	
	}*/
	/*@Override
	 */
	public Map<String, Object> findFapiao(PFapiao pfapiao, int isExport) {
		StringBuffer hql = new StringBuffer("from FaPiao t where t.money > 0 ");
		Map<String,Object> params = new HashMap<String, Object>();
		List<FaPiao> fapiaos = null;
		List<PFapiao> pfapiaos = new ArrayList<PFapiao>();
		Map<String, Object> result = new HashMap<String, Object>();
		if(pfapiao.getCompany()!=null&&!pfapiao.getCompany().equals("")){
			hql.append(" and t.company like :company");
			params.put("company", "%" + pfapiao.getCompany() + "%");
		}
		if(pfapiao.getDepartMement()!=null&&!pfapiao.getDepartMement().equals("")){
			hql.append(" and t.departMement like :departMement");
			params.put("departMement", "%" + pfapiao.getDepartMement() + "%");
		}
		if(pfapiao.getType()!=null&&!pfapiao.getType().equals("")){
			hql.append(" and t.type=:type");
			params.put("type", pfapiao.getType());
		}
		if(pfapiao.getQueryStatus()!=null&&!pfapiao.getQueryStatus().equals("")){
			hql.append(" and t.queryStatus=:queryStatus");
			params.put("queryStatus", pfapiao.getQueryStatus());
		}
//		addConditions(queryForm, hql, params);
		String sort = "id";
		String order = SysConfig.DESC;
		if(pfapiao.getSort() != null){
			sort = pfapiao.getSort();
			if(pfapiao.getOrder() != null){
				order = pfapiao.getOrder();
			}
		}
		long count = getFapiaoCount(hql.toString(),params);
		int intPage = 0;
		int pageSize = 30000;//最多导出30000条数据
		if(isExport == 0){
			intPage = (pfapiao == null || pfapiao.getPage() == 0) ? 1 : pfapiao.getPage();
			pageSize = (pfapiao == null || pfapiao.getRows() == 0) ? 100 : pfapiao.getRows();
		}
		hql.append(" order by t." + sort + " " + order);
		fapiaos = fpDao.find(hql.toString(), params, intPage, pageSize);
		for (FaPiao fapiao : fapiaos) {
			PFapiao pfapiao1 = new PFapiao();
			BeanUtils.copyProperties(fapiao, pfapiao1);
			pfapiaos.add(pfapiao1);
		}
		result.put("rows", pfapiaos);
		result.put("total", count);
		return result;
	}
	public long getFapiaoCount(String hql, Map<String, Object> params){
		StringBuffer hqll = new StringBuffer("select count(*) from FaPiao t where ");
		hqll.append(hql.split("where")[1]);
		return objDao.count(hqll.toString(), params);
	}
	@Override
	public int updateFapiao(PFapiao pfapiao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryStatus", pfapiao.getQueryStatus());
		params.put("id", pfapiao.getId());
		StringBuffer hql = new StringBuffer("update FaPiao t set t.queryStatus = :queryStatus where id = :id");
		int i = fpDao.executeHql(hql.toString(), params);
		return i;
	}
	@Override
	public PFahuo getFahuo(int orderId) {
		Fahuo fahuo = htDao.get(Fahuo.class, orderId);
		PFahuo pfahuo = new PFahuo();
		BeanUtils.copyProperties(fahuo, pfahuo);
		return pfahuo;
	}
	
}