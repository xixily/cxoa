package com.chaoxing.oa.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * entity 职员工资分布表
 * @author Administrator
 *
 */
@Entity
@Table(name = "职员工资分布表", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WageDistribution {
	private int employeeId;//职员编号
//	private UserName userName;
	private String name;//姓名
	private double salary;//工资总额
	private String identityCard;//身份证号码
	private double secrecySubsidy;//保密补贴
	private double communicationSubsidy;//通讯补贴
	private double lunchSubsidy;//午餐补贴
	private String remarks;//备注
	private String companyName;//公司名称
	private int radix;//基数
	private String householdType;//户口性质
	private double basicWage;//基本工资
	private double postSalary;//岗位工资
	private double performanceRelatedPay;//绩效工资
	private String accountBank;//开户行
	private String account;//职工帐号
	private String salaryCode;//工资编号
	private double subEndowmentIinsurance;//代扣养老保险
	private double subMedicare;//代扣医疗保险
	private double subUnemployedInsurance;//代扣失业保险
	private double subHouseIinsurance;//代扣住房保险
	private double cEndowmentIinsurance;//公司养老保险
	private double cMedicare;//公司医疗保险
	private double cUnemployedInsurance;//公司失业保险
	private double cHouseIinsurance;//公司住房保险
	private double cSickPayTotal;//累计带薪病假
	private double cInjuryInsurance;//公司工伤保险
	private double cBirthIinsurance;//公司生育保险
	private int annualLleave;//年假累计
	private String workPhone;//办公电话
	private int internalNumber;//内部编号
	private int sickLleaveTotal;//病假累计
	@Id
	@Column(name="职员编号")
	public int getEmployeeId() {
		return employeeId;
	}
	@Column(name="姓名")
	public String getName() {
		return name;
	}
	@Column(name="工资总额")
	public double getSalary() {
		return salary;
	}
	
	@Column(name="身份证号码")
	public String getIdentityCard() {
		return identityCard;
	}
	@Column(name="保密补贴")
	public double getSecrecySubsidy() {
		return secrecySubsidy;
	}
	@Column(name="通讯补贴")
	public double getCommunicationSubsidy() {
		return communicationSubsidy;
	}
	@Column(name="午餐补贴")
	public double getLunchSubsidy() {
		return lunchSubsidy;
	}
	@Column(name="备注")
	public String getRemarks() {
		return remarks;
	}
	@Column(name="公司名称")
	public String getCompanyName() {
		return companyName;
	}
	@Column(name="基数")
	public int getRadix() {
		return radix;
	}
	@Column(name="户口性质")
	public String getHouseholdType() {
		return householdType;
	}
	@Column(name="基本工资")
	public double getBasicWage() {
		return basicWage;
	}
	@Column(name="岗位工资")
	public double getPostSalary() {
		return postSalary;
	}
	@Column(name="绩效工资")
	public double getPerformanceRelatedPay() {
		return performanceRelatedPay;
	}
	@Column(name="开户行")
	public String getAccountBank() {
		return accountBank;
	}
	@Column(name="职工帐号")
	public String getAccount() {
		return account;
	}
	@Column(name="工资编号")
	public String getSalaryCode() {
		return salaryCode;
	}
	@Column(name="代扣养老保险")
	public double getSubEndowmentIinsurance() {
		return subEndowmentIinsurance;
	}
	@Column(name="代扣医疗保险")
	public double getSubMedicare() {
		return subMedicare;
	}
	@Column(name="代扣失业保险")
	public double getSubUnemployedInsurance() {
		return subUnemployedInsurance;
	}
	@Column(name="代扣住房保险")
	public double getSubHouseIinsurance() {
		return subHouseIinsurance;
	}
	@Column(name="公司养老保险")
	public double getcEndowmentIinsurance() {
		return cEndowmentIinsurance;
	}
	@Column(name="公司医疗保险")
	public double getcMedicare() {
		return cMedicare;
	}
	@Column(name="公司失业保险")
	public double getcUnemployedInsurance() {
		return cUnemployedInsurance;
	}
	@Column(name="公司住房保险")
	public double getcHouseIinsurance() {
		return cHouseIinsurance;
	}
	@Column(name="累计带薪病假")
	public double getcSickPayTotal() {
		return cSickPayTotal;
	}
	@Column(name="公司工伤保险")
	public double getcInjuryInsurance() {
		return cInjuryInsurance;
	}
	@Column(name="公司生育保险")
	public double getcBirthIinsurance() {
		return cBirthIinsurance;
	}
	@Column(name="年假累计")
	public int getAnnualLleave() {
		return annualLleave;
	}
	@Column(name="办公电话")
	public String getWorkPhone() {
		return workPhone;
	}
	@Column(name="内部编号")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "paymentable_codeGenerator", strategy = "native")
	public int getInternalNumber() {
		return internalNumber;
	}
	@Column(name="病假累计")
	public int getSickLleaveTotal() {
		return sickLleaveTotal;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setSecrecySubsidy(double secrecySubsidy) {
		this.secrecySubsidy = secrecySubsidy;
	}
	public void setCommunicationSubsidy(double communicationSubsidy) {
		this.communicationSubsidy = communicationSubsidy;
	}
	public void setLunchSubsidy(double lunchSubsidy) {
		this.lunchSubsidy = lunchSubsidy;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setRadix(int radix) {
		this.radix = radix;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setBasicWage(double basicWage) {
		this.basicWage = basicWage;
	}
	public void setPostSalary(double postSalary) {
		this.postSalary = postSalary;
	}
	public void setPerformanceRelatedPay(double performanceRelatedPay) {
		this.performanceRelatedPay = performanceRelatedPay;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setSalaryCode(String salaryCode) {
		this.salaryCode = salaryCode;
	}
	public void setSubEndowmentIinsurance(double subEndowmentIinsurance) {
		this.subEndowmentIinsurance = subEndowmentIinsurance;
	}
	public void setSubMedicare(double subMedicare) {
		this.subMedicare = subMedicare;
	}
	public void setSubUnemployedInsurance(double subUnemployedInsurance) {
		this.subUnemployedInsurance = subUnemployedInsurance;
	}
	public void setSubHouseIinsurance(double subHouseIinsurance) {
		this.subHouseIinsurance = subHouseIinsurance;
	}
	public void setcEndowmentIinsurance(double cEndowmentIinsurance) {
		this.cEndowmentIinsurance = cEndowmentIinsurance;
	}
	public void setcMedicare(double cMedicare) {
		this.cMedicare = cMedicare;
	}
	public void setcUnemployedInsurance(double cUnemployedInsurance) {
		this.cUnemployedInsurance = cUnemployedInsurance;
	}
	public void setcHouseIinsurance(double cHouseIinsurance) {
		this.cHouseIinsurance = cHouseIinsurance;
	}
	public void setcSickPayTotal(double cSickPayTotal) {
		this.cSickPayTotal = cSickPayTotal;
	}
	public void setcInjuryInsurance(double cInjuryInsurance) {
		this.cInjuryInsurance = cInjuryInsurance;
	}
	public void setcBirthIinsurance(double cBirthIinsurance) {
		this.cBirthIinsurance = cBirthIinsurance;
	}
	public void setAnnualLleave(int annualLleave) {
		this.annualLleave = annualLleave;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public void setInternalNumber(int internalNumber) {
		this.internalNumber = internalNumber;
	}
	public void setSickLleaveTotal(int sickLleaveTotal) {
		this.sickLleaveTotal = sickLleaveTotal;
	}
	
	}