package com.chaoxing.oa.entity.page;

public class Pager {
	private int page;
	private int rows;
	private String sortName;
	private String sortOrder;
	private String userName;
	private String sex;
	private String position;
	private String department;
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	public String getSortName() {
		return sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public String getUserName() {
		return userName;
	}
	public String getSex() {
		return sex;
	}
	public String getPosition() {
		return position;
	}
	public String getDepartment() {
		return department;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Pager [page=" + page + ", rows=" + rows + ", sortName=" + sortName + ", sortOrder=" + sortOrder
				+ ", userName=" + userName + ", sex=" + sex + ", position=" + position + ", department=" + department
				+ "]";
	}
	
}
