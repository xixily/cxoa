package com.chaoxing.oa.entity.page;

import java.util.List;

import com.chaoxing.oa.entity.po.RoleRights;
import com.chaoxing.oa.entity.po.UserRole;

public class PMenus {
	private int menuId;
	private UserRole roleId;
	private String menuName;
	private int menuLevel;
	private RoleRights preMenuId;
	private String url;
	private String iconCls;
	private Integer greate;
	private String contains;
	private List<PMenus> children;
	public int getMenuId() {
		return menuId;
	}
	public UserRole getRoleId() {
		return roleId;
	}
	public String getMenuName() {
		return menuName;
	}
	public int getMenuLevel() {
		return menuLevel;
	}
	public RoleRights getPreMenuId() {
		return preMenuId;
	}
	public String getUrl() {
		return url;
	}
	public String getIconCls() {
		return iconCls;
	}
	public Integer getGreate() {
		return greate;
	}
	public String getContains() {
		return contains;
	}
	public List<PMenus> getChildren() {
		return children;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public void setRoleId(UserRole roleId) {
		this.roleId = roleId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public void setPreMenuId(RoleRights preMenuId) {
		this.preMenuId = preMenuId;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public void setGreate(Integer greate) {
		this.greate = greate;
	}
	public void setContains(String contains) {
		this.contains = contains;
	}
	public void setChildren(List<PMenus> children) {
		this.children = children;
	}

}
