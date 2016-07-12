package com.chaoxing.oa.entity.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * entity 组织结构图(公司架构表)
 *
 */
@Entity
@Table(name = "组织结构图", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrganizationStructure implements Serializable{
	private static final long serialVersionUID = 2789824726889624857L;
	private int id;//部门id
	private String sortCode;//排序代码
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	private String cellCore;//细胞核
	private String cellCoreEmail;//细胞核邮箱
	private String guidance;//指导
	private String guidanceEmail;//指导邮箱
	private String taxStructure;//报税架构
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	@Column(name="代码")
	public String getSortCode() {
		return sortCode;
	}
	@Column(name="一级")
	public String getFirstLevel() {
		return firstLevel;
	}
	@Column(name="二级")
	public String getSecondLevel() {
		return secondLevel;
	}
	@Column(name="三级")
	public String getThirdLevel() {
		return thirdLevel;
	}
	@Column(name="四级")
	public String getFourthLevel() {
		return fourthLevel;
	}
	@Column(name="细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Column(name="细胞核邮箱")
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	@Column(name="指导")
	public String getGuidance() {
		return guidance;
	}
	@Column(name="指导邮箱")
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	@Column(name="报税架构")
	public String getTaxStructure() {
		return taxStructure;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
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
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
	}
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	public void setGuidanceEmail(String guidanceEmail) {
		this.guidanceEmail = guidanceEmail;
	}
	public void setTaxStructure(String taxStructure) {
		this.taxStructure = taxStructure;
	}
}
