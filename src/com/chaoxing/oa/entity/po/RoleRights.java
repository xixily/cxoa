package com.chaoxing.oa.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "roleRights", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class RoleRights {
	private int menuId;
	private UserRole roleId;
	private String menuName;
	private int menuLevel;
	private RoleRights preMenuId;
	private String url;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name ="roleRightstableGenerator", strategy = "native" )
	public int getMenuId() {
		return menuId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public UserRole getRoleId() {
		return roleId;
	}
	
	@Column
	public String getMenuName() {
		return menuName;
	}
	@Column
	public int getMenuLevel() {
		return menuLevel;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preMenuId")
	public RoleRights getPreMenuId() {
		return preMenuId;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public void setPreMenuId(RoleRights preMenuId) {
		this.preMenuId = preMenuId;
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
	public void setUrl(String url) {
		this.url = url;
	}
	
}
