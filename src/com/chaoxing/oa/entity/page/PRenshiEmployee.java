package com.chaoxing.oa.entity.page;

import java.io.Serializable;

public class PRenshiEmployee implements Serializable{
	private static final long serialVersionUID = 8022325028735486200L;
	/**
	 * 分页项
	 */
	private int page;
	private int rows;
	private String sortName;
	private String sortOrder;
	/**
	 * 信息项
	 */
	private int id;//employeeId
	private String username;//用户姓名
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String position;//职位
	private String sex;//性别
	private String hiredate;//入职时间
	private String leaveTime;//离职时间
	private String workPlace;//工作地点
	private String phoneNumber;//电话号码
	private String degree;//学历
	
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSortName() {
		return sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public String getUsername() {
		return username;
	}
	public String getFirstLevel() {
		return firstLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public String getPosition() {
		return position;
	}
	public String getSex() {
		return sex;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getDegree() {
		return degree;
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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Override
	public String toString() {
		return "RenshiEmployeeInfo [page=" + page + ", rows=" + rows + ", sortName=" + sortName + ", sortOrder="
				+ sortOrder + ", id=" + id + ", username=" + username + ", firstLevel=" + firstLevel + ", secondLevel="
				+ secondLevel + ", thirdLevel=" + thirdLevel + ", fourthLevel=" + fourthLevel + ", position=" + position
				+ ", sex=" + sex + ", hiredate=" + hiredate + ", leaveTime=" + leaveTime + ", workPlace=" + workPlace
				+ ", phoneNumber=" + phoneNumber + ", degree=" + degree + "]";
	}
	
}
