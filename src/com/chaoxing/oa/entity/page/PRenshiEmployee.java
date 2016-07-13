package com.chaoxing.oa.entity.page;

import java.io.Serializable;

public class PRenshiEmployee implements Serializable{
	private static final long serialVersionUID = 8022325028735486200L;
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
	private String maritalStatus;//婚姻状况
	private String insuranceCompany;//保险公司
	private String company;//公司名称
	private String earlyEntryDate;//早期入职时间
	private String zhuanzhengTime;//转正时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public String getCompany() {
		return company;
	}
	public String getEarlyEntryDate() {
		return earlyEntryDate;
	}
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setEarlyEntryDate(String earlyEntryDate) {
		this.earlyEntryDate = earlyEntryDate;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	
}
