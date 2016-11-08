package com.chaoxing.oa.entity.page.common;

public class PQuickQuery {
	private int type;
	/**
	 * 分页项
	 */
	private int page;
	private int rows;
	private String sort;
	private String order;
	public int getType() {
		return type;
	}
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder() {
		return order;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setOrder(String order) {
		this.order = order;
	}


	
}
