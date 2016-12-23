package com.chaoxing.oa.service;

import java.util.Map;

import com.chaoxing.oa.entity.page.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.common.Page;

public interface PubCaiwuService {

	/**
	 * 报销
	 */
	public PBaoxiao getBaoxiao(int id);
	
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, int id);
	
	public long updateBaoxiao(PBaoxiao pBaoxiao);
	
	public int deleteBaoxiao(int id);
	//去年总报销额
	public Double getLastYear(int id);
	//今年已经报销
	public Double getThisYear(String id);

	/**
	 * 合同
	 */
	public void findCellsByEmail(String email);
}
