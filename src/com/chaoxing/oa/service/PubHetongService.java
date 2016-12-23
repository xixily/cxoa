package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.pub.hetong.Cells;
import com.chaoxing.oa.entity.page.pub.hetong.PFapiaoDetail;
import com.chaoxing.oa.entity.page.pub.hetong.PGuidanceView;
import com.chaoxing.oa.entity.page.pub.hetong.UserDepart;

public interface PubHetongService {

	public void findCellcoreTotal(String email);

//	public Map<String, Object> findGdView(String email);
	
	//获取细胞核统计信息
	public List<PGuidanceView> findCellCores(String email);

	//查找细胞核下面的销售（胞质）
	public  List<Cells> findCoreCells(String cemail);
	
	//查找用户单位列表
	public List<UserDepart> findUserList(String email, String charger);
	
	//查找用户单位合同列表
	public List<PFapiaoDetail> findUserContracts(Integer autoCode);
	
	//查找合同发票信息
	public void findContractFapiao(Integer id);
	
	//获得发票详情
	public void getFapiaoDetail(Integer id);
	
	public PRenshiEmployee getUserByEmai(String email);
}
