package com.chaoxing.oa.entity.page;

import java.util.HashSet;
import java.util.Set;

public class PMenu {
	private int userId;
	private int menuId;
	private String menuName;
	private Set<PUlList> uls = new HashSet<PUlList>();;
	public int getUserId() {
		return userId;
	}
	public int getMenuId() {
		return menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Set<PUlList> getUls() {
		return uls;
	}
	public void setUls(Set<PUlList> uls) {
		this.uls = uls;
	}
	@Override
	public String toString() {
		return "MenuInfo [userId=" + userId + ", menuId=" + menuId + ", menuName=" + menuName + ", uls=" + uls + "]";
	}
	
	
}
