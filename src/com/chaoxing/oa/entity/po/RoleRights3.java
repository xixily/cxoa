//package com.chaoxing.oa.entity.po;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.GenericGenerator;
//
////@Entity
////@Table(name = "role_menu", schema = "")
////@DynamicInsert(true)
////@DynamicUpdate(true)
//public class RoleRights3 implements Serializable{
//	private int roleId;
//	private int menuId;
//	private String menuName;
//	private int menuLevel;
//	private int preMenuId;
//	private String url;
//	@Column
//	public int getRoleId() {
//		return roleId;
//	}
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GenericGenerator(name = "roleMenuTableGenerator", strategy = "native")
//	public int getMenuId() {
//		return menuId;
//	}
//	@Column
//	public String getMenuName() {
//		return menuName;
//	}
//	@Column
//	public int getMenuLevel() {
//		return menuLevel;
//	}
//	@Column
//	public int getPreMenuId() {
//		return preMenuId;
//	}
//	@Column
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}
//	public void setMenuId(int menuId) {
//		this.menuId = menuId;
//	}
//	public void setMenuName(String menuName) {
//		this.menuName = menuName;
//	}
//	public void setMenuLevel(int menuLevel) {
//		this.menuLevel = menuLevel;
//	}
//	public void setPreMenuId(int preMenuId) {
//		this.preMenuId = preMenuId;
//	}
//	
//	
//	
//}
