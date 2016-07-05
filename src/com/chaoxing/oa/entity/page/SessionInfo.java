package com.chaoxing.oa.entity.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SessionInfo implements java.io.Serializable {
	private static final long serialVersionUID = -610081469379406214L;
	private String userId;//ID
	private String loginName;//username
	private String ip;
	private int roleId;//权限
	private Set<Right> rights;//权限
	private int departmentId;//部门ID
	private String position;//职位
	private String sex;//性别
	private String company;//公司名称
	private String email;//邮箱
	private int paySecrecy;//工资保密
	private String roleNames;
	private String roleIds;
	private String resourceIds;
	private String resourceNames;
	private List<String> resourceUrls = new ArrayList<String>();

	public List<String> getResourceUrls() {
		return resourceUrls;
	}

	public void setResourceUrls(List<String> resourceUrls) {
		this.resourceUrls = resourceUrls;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getResourceNames() {
		return resourceNames;
	}

	public void setResourceNames(String resourceNames) {
		this.resourceNames = resourceNames;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public String getPosition() {
		return position;
	}

	public String getSex() {
		return sex;
	}

	public String getCompany() {
		return company;
	}

	public String getEmail() {
		return email;
	}

	public int getPaySecrecy() {
		return paySecrecy;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPaySecrecy(int paySecrecy) {
		this.paySecrecy = paySecrecy;
	}

	@Override
	public String toString() {
		return loginName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}
