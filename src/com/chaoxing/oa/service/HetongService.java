package com.chaoxing.oa.service;

import java.util.Map;

import com.chaoxing.oa.entity.page.SF.OrderResponse;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.hetong.PFapiao;

public interface HetongService {
	public Map<String, Object> findHetong(PFahuo pfahuo, int isExport);

	public OrderResponse sendKuaidi(PFahuo pfahuo);

	public Map<String, Object> findFapiao(PFapiao pfapiao, int isExport);

	public int updateFapiao(PFapiao pfapiao);

	public PFahuo getFahuo(int orderId);
}
