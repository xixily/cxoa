package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.caiwu.PBaoxiaoStatus;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;

public interface PubCaiwuService {

	/**
	 * 报销
	 */
	public PBaoxiao getBaoxiao(Long long1);
	
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, int id);

	public Serializable addBaoxiao(PBaoxiao pbaoxiao);

	public int deleteBaoxiao(Long id, Integer uid);
	
	public long updateBaoxiao(PBaoxiao pBaoxiao);
	//去年总报销额
	public Double getLastYear(int id);
	//今年已经报销
	public Double getThisYear(String id);

	public int updateApprove(PBaoxiao pbaoxiaos, boolean b, int id, String email);

	public void updateBaoxiaoCheck(PBaoxiao pbaoxiao, boolean agree);

	public int updateBaoxiaoReceive(Integer id, boolean agree, String spRemarks, Integer uid);
	
	public int getNextStep(Integer statu, int st);
	
	public int getPreStep(Integer statu);
	
	public int updateTonextStep(Long id, Integer statu, int st);
	
	public int updateToPreStep(Long id, Integer statu);

	/**
	 * 合同
	 */
	public void findCellsByEmail(String email);

	public PRenshiEmployee getEmployee(Integer uid);

	public List<PBaoxiaoStatus> findAllBaoxiaoStatus();

	public void findAllCells(String email);


}
