package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

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

public interface PubHetongService {

	/**
	 * 统计数据 
	 * @param email
	 */
	public void findCellcoreTotal(String email);

//	public Map<String, Object> findGdView(String email);
	
	//获取细胞核统计信息
	public List<PGuidanceView> findCellCoresCount(String email);

	//查找细胞核下面的销售（胞质）
	public  List<Cells> findCoreCellsCount(String cemail);
	
	//查找用户单位列表
	public List<UserDepart> findUserListCount(String email, String charger);
	
	//查找用户单位合同列表
	public List<PFapiaoDetail> findUserContractsCount(Integer autoCode);
	
	//查找合同发票信息
	public void findContractFapiaoCount(Integer id);
	
	
	
	/**
	 * 合同数据
	 */
	
	public Map<String, Object> findUcfView(Page page, PucfView pucf);
	
	public void findUserList();
	
	public void findUserContracts();
	
	public Map<String, Object> findContractFapiaos(Page page, PFapiao pfapiao);
	
	public Map<String, Object> findContractItemPrice(Page page, PItemPrice ptprice);
	
	public PCustomerDepart getUserList(Integer id);
	
	public void getContractDetail(Integer id);
	
	public void getFapiaoDetail(Integer id);
	
	public PRenshiEmployee getUserByEmai(String email);
}
