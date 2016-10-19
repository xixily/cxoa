package com.chaoxing.oa.entity.sqlpo;

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
@Table(name="发票情况")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FaPiao implements Serializable{
	private static final long serialVersionUID = 6160133666108543609L;
	private Integer id;
	private String date;
	private String company;
	private String departMement;
	private String type;
	private String name;
	private Float money;
	private String remark;
	private Integer hetongNumber;
	private Float huiKuan;
	private String capitalMoney;
	private String Applicant;
	private String remittanceDate;
	private Integer queryStatus; 
	private String fundType; 
	private String account;
	private String recorder;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "序号")
	public Integer getId() {
		return id;
	}
	@Column(name = "开票时间")
	public String getDate() {
		return date;
	}
	@Column(name = "开票公司")
	public String getCompany() {
		return company;
	}
	@Column(name = "开票单位")
	public String getDepartMement() {
		return departMement;
	}
	@Column(name = "发票类型")
	public String getType() {
		return type;
	}
	@Column(name = "发票品名")
	public String getName() {
		return name;
	}
	@Column(name = "金额")
	public Float getMoney() {
		return money;
	}
	@Column(name = "备注")
	public String getRemark() {
		return remark;
	}
	@Column(name = "合同编号")
	public Integer getHetongNumber() {
		return hetongNumber;
	}
	@Column(name = "回款情况")
	public Float getHuiKuan() {
		return huiKuan;
	}
	@Column(name = "大写金额")
	public String getCapitalMoney() {
		return capitalMoney;
	}
	@Column(name = "申请人")
	public String getApplicant() {
		return Applicant;
	}
	@Column(name = "汇款时间")
	public String getRemittanceDate() {
		return remittanceDate;
	}
	@Column(name = "查询情况")
	public Integer getQueryStatus() {
		return queryStatus;
	}
	@Column(name = "资金类型")
	public String getFundType() {
		return fundType;
	}
	@Column(name = "账户")
	public String getAccount() {
		return account;
	}
	@Column(name = "录库人")
	public String getRecorder() {
		return recorder;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setDepartMement(String departMement) {
		this.departMement = departMement;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setHetongNumber(Integer hetongNumber) {
		this.hetongNumber = hetongNumber;
	}
	public void setHuiKuan(Float huiKuan) {
		this.huiKuan = huiKuan;
	}
	public void setCapitalMoney(String capitalMoney) {
		this.capitalMoney = capitalMoney;
	}
	public void setApplicant(String applicant) {
		Applicant = applicant;
	}
	public void setRemittanceDate(String remittanceDate) {
		this.remittanceDate = remittanceDate;
	}
	public void setQueryStatus(Integer queryStatus) {
		this.queryStatus = queryStatus;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	
}
