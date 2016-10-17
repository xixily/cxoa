package com.chaoxing.oa.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
//@Table(name = "工资汇总情况", schema="")

@Table(name = "工资汇总情况", schema="")

@DynamicInsert(true)
@DynamicUpdate(true)

public class gongziHuiZong  implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -4801301252905007137L;
//	private static final long serialVersionUID = 8832579790637183264L;
	
	private int id;
	private String username;
	private Float  fGongziZonge;
	private Float  fSheBaoZiFu;
	private Float  fSheBaoGongsi;
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	
	@Id
	@Column(name = "ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "工资总额")
	public Float getfGongziZonge() {
		return fGongziZonge;
	}
	public void setfGongziZonge(Float fGongziZonge) {
		this.fGongziZonge = fGongziZonge;
	}
	@Column(name = "社保自付")
	public Float getfSheBaoZiFu() {
		return fSheBaoZiFu;
	}
	public void setfSheBaoZiFu(Float fSheBaoZiFu) {
		this.fSheBaoZiFu = fSheBaoZiFu;
	}
	@Column(name = "公司社保")
	public Float getfSheBaoGongsi() {
		return fSheBaoGongsi;
	}
	public void setfSheBaoGongsi(Float fSheBaoGongsi) {
		this.fSheBaoGongsi = fSheBaoGongsi;
	}
	@Column(name = "一级")
	public String getFirstLevel() {
		return firstLevel;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	@Column(name = "二级")
	public String getSecondLevel() {
		return secondLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	@Column(name = "三级")
	public String getThirdLevel() {
		return thirdLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	@Column(name = "四级")
	public String getFourthLevel() {
		return fourthLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}

}
