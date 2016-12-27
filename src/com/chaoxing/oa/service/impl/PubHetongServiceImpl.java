package com.chaoxing.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.hetong.PCustomerDepart;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.entity.page.hetong.PItemPrice;
import com.chaoxing.oa.entity.page.pub.hetong.Cells;
import com.chaoxing.oa.entity.page.pub.hetong.PFapiaoDetail;
import com.chaoxing.oa.entity.page.pub.hetong.PGuidanceView;
import com.chaoxing.oa.entity.page.pub.hetong.PucfView;
import com.chaoxing.oa.entity.page.pub.hetong.UserDepart;
import com.chaoxing.oa.entity.po.hetong.CustomerDepart;
import com.chaoxing.oa.entity.po.hetong.FaPiao;
import com.chaoxing.oa.entity.po.hetong.ItemPrice;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.pub.UcfView;
import com.chaoxing.oa.service.PubHetongService;
import com.chaoxing.oa.util.DateUtil;
import com.chaoxing.oa.util.ResourceUtil;
import com.chaoxing.oa.util.SqlHelper;

@Service(value="pubHetongService")
public class PubHetongServiceImpl implements PubHetongService {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private BaseDaoI<Object> objectDao;
	@Autowired
	private BaseDaoI<RenshiUserName> rusernameDao;
	@Autowired
	private BaseDaoI<CustomerDepart> cdepartDao;
	@Autowired
	private BaseDaoI<UcfView> ucfViewDao;
	@Autowired
	private BaseDaoI<ItemPrice> itemPriceDao;
	@Autowired
	private BaseDaoI<FaPiao> fapiaoDao;
	
	@Override
	public void findCellcoreTotal(String email) {
		
	}
	
	@Override
	public List<PGuidanceView> findCellCoresCount(String email) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT t1.细胞核,t1.细胞核邮箱, t2.去年 ,t3.今年, (t4.合同款 - t1.回款) 应收 FROM"
				+ "(SELECT 细胞核邮箱,细胞核,SUM(IFNULL(回款情况,0)) 回款 FROM 用户合同发票 "
				+ "WHERE 指导邮箱=:gemail "
				+ "GROUP BY 细胞核邮箱) t1 LEFT JOIN "
				+ "(SELECT 细胞核邮箱,SUM(IFNULL(回款情况,0)) 去年 FROM 用户合同发票 "
				+ "WHERE 指导邮箱=:gemail AND YEAR(汇款时间)='" + ResourceUtil.getKey("lastyear") + "' "
				+ "GROUP BY 细胞核邮箱) t2 ON t1.细胞核邮箱 = t2.细胞核邮箱  LEFT JOIN "
				+ "(SELECT 细胞核邮箱,SUM(IFNULL(回款情况,0)) 今年 FROM 用户合同发票 "
				+ " WHERE 指导邮箱=:gemail AND YEAR(汇款时间)='"+ ResourceUtil.getKey("thisyear") + "' "
				+ "GROUP BY 细胞核邮箱) t3 ON t1.细胞核邮箱 = t3.细胞核邮箱 LEFT JOIN "
				+ "(SELECT 细胞核邮箱,(SUM(IFNULL(合同金额,0)) - SUM(IFNULL(年代,0)) - SUM(IFNULL(开票总金额,0))) 合同款 FROM 用户合同 "
				+ "WHERE 指导邮箱=:gemail "
				+ "GROUP BY 细胞核邮箱) t4 ON t1.细胞核邮箱=t4.细胞核邮箱";
		params.put("gemail", email);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<PGuidanceView> pgvs = new ArrayList<PGuidanceView>();
		String cellcore;
		String cemail;
		BigDecimal lastyear;
		BigDecimal thisyear;
		BigDecimal yingshou;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				PGuidanceView pgv = new PGuidanceView();
				cellcore = null!=obs[0] ? String.valueOf(obs[0]) : "";
				cemail = null!=obs[1] ? String.valueOf(obs[1]) : "";
				lastyear = null!=obs[2] ? (BigDecimal)obs[2] : new BigDecimal(0);
				thisyear = null!=obs[3] ? (BigDecimal)obs[3] : new BigDecimal(0);
				yingshou = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				pgv.setCellCore(cellcore);
				pgv.setCemail(cemail);
				pgv.setLastYear(lastyear);
				pgv.setThisYear(thisyear);
				pgv.setYingshou(yingshou);
				pgv.setGemail(email);
				pgvs.add(pgv);
			}
		}
		return pgvs;
	}

	@Override
	public List<Cells> findCoreCellsCount(String cemail) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT t1.负责人,t1.邮箱, t2.去年 ,t3.今年, (t4.合同款 - t1.回款) 应收 FROM"
				+ "(SELECT 邮箱,负责人,SUM(IFNULL(回款情况,0)) 回款 FROM 用户合同发票 "
				+ "WHERE 细胞核邮箱=:cemail "
				+ "GROUP BY 邮箱) t1 LEFT JOIN "
				+ "(SELECT 邮箱,SUM(IFNULL(回款情况,0)) 去年 FROM 用户合同发票 "
				+ "WHERE 细胞核邮箱=:cemail AND YEAR(汇款时间)='" + ResourceUtil.getKey("lastyear") + "' "
				+ "GROUP BY 邮箱) t2 ON t1.邮箱 = t2.邮箱  LEFT JOIN "
				+ "(SELECT 邮箱,SUM(IFNULL(回款情况,0)) 今年 FROM 用户合同发票 "
				+ " WHERE 细胞核邮箱=:cemail AND YEAR(汇款时间)='"+ ResourceUtil.getKey("thisyear") + "' "
				+ "GROUP BY 邮箱) t3 ON t1.邮箱 = t3.邮箱 LEFT JOIN "
				+ "(SELECT 邮箱,(SUM(IFNULL(合同金额,0)) - SUM(IFNULL(年代,0)) - SUM(IFNULL(开票总金额,0))) 合同款 FROM 用户合同 "
				+ "WHERE 细胞核邮箱=:cemail "
				+ "GROUP BY 邮箱) t4 ON t1.邮箱=t4.邮箱";
		params.put("cemail", cemail);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<Cells> cells = new ArrayList<Cells>();
		String username;
		String email;
		BigDecimal lastyear;
		BigDecimal thisyear;
		BigDecimal yingshou;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				Cells cell = new Cells();
				username = null!=obs[0] ? String.valueOf(obs[0]) : "";
				email = null!=obs[1] ? String.valueOf(obs[1]) : "";
				lastyear = null!=obs[2] ? (BigDecimal)obs[2] : new BigDecimal(0);
				thisyear = null!=obs[3] ? (BigDecimal)obs[3] : new BigDecimal(0);
				yingshou = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				cell.setCemail(cemail);
				cell.setUsername(username);
				cell.setEmail(email);
				cell.setLastYear(lastyear);
				cell.setThisYear(thisyear);
				cell.setYingshou(yingshou);
				System.out.println(cell);
				cells.add(cell);
			}
		}
		return cells;
	}
	

	@Override
	public Map<String, Object> findUcfView(Page page, PucfView pucf) {
		StringBuffer hql = new StringBuffer("from UcfView t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<PucfView> pucfs = new ArrayList<PucfView>();
		long count = 0;
		try{
			hql.append(SqlHelper.prepareAndSql(pucf, params, true));
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
			pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 500 : page.getRows();
			List<UcfView> ucfs = ucfViewDao.find(hql.toString(),params, intPage, pageSize);
			count = getCount(hql.toString(),params);
			Iterator<UcfView> it = ucfs.iterator();
			while(it.hasNext()){
				UcfView ucf = it.next();
				PucfView puc = new PucfView();
				BeanUtils.copyProperties(ucf, puc);
				pucfs.add(puc);
			}
		}catch(Exception e){
			log.error("pubHetongService[findUcfView] has a error with message:[" + e + "]");
		}
		result.put("rows", pucfs);
		result.put("total", count);
		return result;
	}

	@Override
	public List<UserDepart> findUserListCount(String email, String charger) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT t1.用户名称,t1.自动编号,t2.去年 ,t3.今年, (t4.合同款 - t1.回款) 应收 FROM"
				+ "(SELECT 自动编号,用户名称,SUM(IFNULL(回款情况,0)) 回款 FROM 用户合同发票 "
				+ "WHERE 邮箱=:email "
				+ "GROUP BY 自动编号) t1 LEFT JOIN "
				+ "(SELECT 自动编号,SUM(IFNULL(回款情况,0)) 去年 FROM 用户合同发票 "
				+ "WHERE 邮箱=:email AND YEAR(汇款时间)='" + ResourceUtil.getKey("lastyear") + "' "
				+ "GROUP BY 自动编号) t2 ON t1.自动编号 = t2.自动编号  LEFT JOIN "
				+ "(SELECT 自动编号,SUM(IFNULL(回款情况,0)) 今年 FROM 用户合同发票 "
				+ " WHERE 邮箱=:email AND YEAR(汇款时间)='"+ ResourceUtil.getKey("thisyear") + "' "
				+ "GROUP BY 自动编号) t3 ON t1.自动编号 = t3.自动编号 LEFT JOIN "
				+ "(SELECT 自动编号,(SUM(IFNULL(合同金额,0)) - SUM(IFNULL(年代,0)) - SUM(IFNULL(开票总金额,0))) 合同款 FROM 用户合同 "
				+ "WHERE 邮箱=:email "
				+ "GROUP BY 自动编号) t4 ON t1.自动编号=t4.自动编号";
		params.put("email", email);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<UserDepart> uds = new ArrayList<UserDepart>();
		String dname;
		Integer dId;
		BigDecimal lastyear;
		BigDecimal thisyear;
		BigDecimal yingshou;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				UserDepart ud = new UserDepart();
				dname = null!=obs[0] ? String.valueOf(obs[0]) : "";
				dId = null!=obs[1] ? (Integer)obs[1] : -1;
				lastyear = null!=obs[2] ? (BigDecimal)obs[2] : new BigDecimal(0);
				thisyear = null!=obs[3] ? (BigDecimal)obs[3] : new BigDecimal(0);
				yingshou = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				ud.setDname(dname);
				ud.setEmail(email);
				ud.setCharger(charger);
				ud.setdId(dId);
				ud.setLastYear(lastyear);
				ud.setThisYear(thisyear);
				ud.setYingshou(yingshou);
				System.out.println(ud);
				uds.add(ud);
			}
		}
		return uds;
	}

	@Override
	public List<PFapiaoDetail> findUserContractsCount(Integer autoCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT 负责人,自动编号,汇款时间,发票品名,回款情况 FROM 用户合同发票  WHERE 自动编号= :autoCode";
		params.put("autoCode", autoCode);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<PFapiaoDetail> pfs = new ArrayList<PFapiaoDetail>();
		String charger;
		Integer dId;
		Date huikuanDate;
		String pinming;
		BigDecimal huikuan;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				PFapiaoDetail pf = new PFapiaoDetail();
				try{
					charger = null!=obs[0] ? String.valueOf(obs[0]) : "";
					dId = null!=obs[1] ? (Integer)obs[1] : -1;
					huikuanDate = null!=obs[2] ? (Date)obs[2] : null;
					pinming = null!=obs[3] ? (String)obs[3] : "";
					huikuan = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				}catch(Exception e){
					continue;
				}
				pf.setCharger(charger);
				pf.setAutoCode(dId);
				pf.setHuikuanTime(DateUtil.format(huikuanDate));
				pf.setPinming(pinming);
				pf.setHuikuan(huikuan);
				pfs.add(pf);
			}
		}
		return pfs;
	}

	@Override
	public void findContractFapiaoCount(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findUserList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findUserContracts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> findContractFapiaos(Page page, PFapiao pfapiao) {
		StringBuffer hql = new StringBuffer("from FaPiao t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<PFapiao> pfapiaos = new ArrayList<PFapiao>();
		long count = 0;
		try{
			hql.append(SqlHelper.prepareAndSql(pfapiao, params, true));
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
			pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 500 : page.getRows();
			List<FaPiao> fapiaos = fapiaoDao.find(hql.toString(),params, intPage, pageSize);
			count = getCount(hql.toString(),params);
			Iterator<FaPiao> it = fapiaos.iterator();
			while(it.hasNext()){
				FaPiao fapiao = it.next();
				PFapiao pf = new PFapiao();
				BeanUtils.copyProperties(fapiao, pf);
				pfapiaos.add(pf);
			}
		}catch(Exception e){
			log.error("pubHetongService[findContractFapiaos] has a error with message:[" + e + "]");
		}
		result.put("rows", pfapiaos);
		result.put("total", count);
		return result;
	}
	

	@Override
	public Map<String, Object> findContractItemPrice(Page page, PItemPrice ptprice) {
		StringBuffer hql = new StringBuffer("from ItemPrice t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<PItemPrice> pitps = new ArrayList<PItemPrice>();
		long count = 0;
		try{
			hql.append(SqlHelper.prepareAndSql(ptprice, params, true));
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
			pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 500 : page.getRows();
			List<ItemPrice> itemPrices = itemPriceDao.find(hql.toString(),params, intPage, pageSize);
			count = getCount(hql.toString(),params);
			Iterator<ItemPrice> it = itemPrices.iterator();
			while(it.hasNext()){
				ItemPrice item = it.next();
				PItemPrice pitem = new PItemPrice();
				BeanUtils.copyProperties(item, pitem);
				pitps.add(pitem);
			}
		}catch(Exception e){
			log.error("pubHetongService[findContractItemPrice] has a error with message:[" + e + "]");
		}
		result.put("rows", pitps);
		result.put("total", count);
		return result;
	}

	@Override
	public PCustomerDepart getUserList(Integer id) {
		PCustomerDepart pcd = new PCustomerDepart();
		CustomerDepart cd = cdepartDao.get(CustomerDepart.class, id);
		BeanUtils.copyProperties(cd, pcd);
		return pcd;
	}

	@Override
	public void getContractDetail(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFapiaoDetail(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PRenshiEmployee getUserByEmai(String email) {
		Map<String,Object> params = new HashMap<String, Object>();
		String hql = "from RenshiUserName t where t.email = :email";
		params.put("email", email);
		List<RenshiUserName> employee = rusernameDao.find(hql, params);
		PRenshiEmployee prenshi = null;
		if(null!=employee && employee.size()>0){
			new PRenshiEmployee();
			BeanUtils.copyProperties(employee.get(0), prenshi);
		}
		return prenshi;
	}

	protected long getCount(String hql, Map<String, Object> params) {
		String hqll = "select count(*) from " + hql.split("from")[1];
		return objectDao.count(hqll,params);
	}


}
