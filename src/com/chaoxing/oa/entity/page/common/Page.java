package com.chaoxing.oa.entity.page.common;

public class Page {
	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	public Integer getPage() {
		return page;
	}
	public Integer getRows() {
		return rows;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder() {
		return order;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
