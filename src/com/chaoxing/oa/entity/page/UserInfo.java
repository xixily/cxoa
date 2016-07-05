package com.chaoxing.oa.entity.page;


public class UserInfo {
	private int page;
	private int rows;
	private String sort;
	private String order_by;
	private String ip;
	private int ID;//ID
	private String email;//邮箱
	private String username;//用户姓名
	private String password;//用户密码
	private int rights;//权限(判断是否为人事)
	private int departmentId;//部门ID
	private String position;//职位
	private String sex;//性别
	private String identityCard;//身份号
	private String borthDay;//出生年月
	private String nation;//民族
	private String degree;//学历
	private String graduatedSchool;//毕业学校
	private String major;//专业
	private String phoneNumber;//电话号码
	private String homeAddress;//家庭住址
	private String homeNumber;//家庭电话
	private String hiredate;//入职时间
	private String zhuanzhengTime;//转正时间
	private String pastLeaveTime;//过去离职时间
	private String earlyEntryDate;//早期入职时间
	private String householdType;//户口性质
	private String insurance;//保险
	private String insuranceCompany;//保险公司
	private String company;//公司名称
	private String resume;//简历
	private String photo;//照片
	private String degreeCertificate;//学历证书
	private String identityCardCopy;//身份证复印件
	private String familyRegister;//户口本
	private String leavingCertificate;//离职证明
	private String contract;//合同
	private String managementSystem;//管理制度
	private String entryForm;//入职表
	private String signedTime;//签定时间
	private String terminationTime;//终止时间
	private String registeredAddress;//户口地址
	private String Postcode;//邮编
	private String remarks;//备注
	private String contractNumber;//合同编号
	private String dueSocialSecurity;//终止时间
	private String socialSecurityHospital;//社保医院
	private String level;//级别
	private String recruitmentSources;//招聘来源
	private String contractRenewal;//合同续签
	private String originalNumber;//原编号
	private String secrecyAgreement;//保密协议
	private String reportForm;//报表
	private String panCard;//办理工资卡
	private String leaveTime;//离职时间
	private String workPlace;//工作地点
	private int ifSecret;//是否保密
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder_by() {
		return order_by;
	}
	public int getID() {
		return ID;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getRights() {
		return rights;
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
	public String getIdentityCard() {
		return identityCard;
	}
	public String getBorthDay() {
		return borthDay;
	}
	public String getNation() {
		return nation;
	}
	public String getDegree() {
		return degree;
	}
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	public String getMajor() {
		return major;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getZhuanzhengTime() {
		return zhuanzhengTime;
	}
	public String getPastLeaveTime() {
		return pastLeaveTime;
	}
	public String getEarlyEntryDate() {
		return earlyEntryDate;
	}
	public String getHouseholdType() {
		return householdType;
	}
	public String getInsurance() {
		return insurance;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public String getCompany() {
		return company;
	}
	public String getResume() {
		return resume;
	}
	public String getPhoto() {
		return photo;
	}
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public String getIdentityCardCopy() {
		return identityCardCopy;
	}
	public String getFamilyRegister() {
		return familyRegister;
	}
	public String getLeavingCertificate() {
		return leavingCertificate;
	}
	public String getContract() {
		return contract;
	}
	public String getManagementSystem() {
		return managementSystem;
	}
	public String getEntryForm() {
		return entryForm;
	}
	public String getSignedTime() {
		return signedTime;
	}
	public String getTerminationTime() {
		return terminationTime;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public String getPostcode() {
		return Postcode;
	}
	public String getRemarks() {
		return remarks;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public String getDueSocialSecurity() {
		return dueSocialSecurity;
	}
	public String getSocialSecurityHospital() {
		return socialSecurityHospital;
	}
	public String getLevel() {
		return level;
	}
	public String getRecruitmentSources() {
		return recruitmentSources;
	}
	public String getContractRenewal() {
		return contractRenewal;
	}
	public String getOriginalNumber() {
		return originalNumber;
	}
	public String getSecrecyAgreement() {
		return secrecyAgreement;
	}
	public String getReportForm() {
		return reportForm;
	}
	public String getPanCard() {
		return panCard;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public int getIfSecret() {
		return ifSecret;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRights(int rights) {
		this.rights = rights;
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
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setBorthDay(String borthDay) {
		this.borthDay = borthDay;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setZhuanzhengTime(String zhuanzhengTime) {
		this.zhuanzhengTime = zhuanzhengTime;
	}
	public void setPastLeaveTime(String pastLeaveTime) {
		this.pastLeaveTime = pastLeaveTime;
	}
	public void setEarlyEntryDate(String earlyEntryDate) {
		this.earlyEntryDate = earlyEntryDate;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
	}
	public void setIdentityCardCopy(String identityCardCopy) {
		this.identityCardCopy = identityCardCopy;
	}
	public void setFamilyRegister(String familyRegister) {
		this.familyRegister = familyRegister;
	}
	public void setLeavingCertificate(String leavingCertificate) {
		this.leavingCertificate = leavingCertificate;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public void setManagementSystem(String managementSystem) {
		this.managementSystem = managementSystem;
	}
	public void setEntryForm(String entryForm) {
		this.entryForm = entryForm;
	}
	public void setSignedTime(String signedTime) {
		this.signedTime = signedTime;
	}
	public void setTerminationTime(String terminationTime) {
		this.terminationTime = terminationTime;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public void setPostcode(String postcode) {
		Postcode = postcode;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public void setDueSocialSecurity(String dueSocialSecurity) {
		this.dueSocialSecurity = dueSocialSecurity;
	}
	public void setSocialSecurityHospital(String socialSecurityHospital) {
		this.socialSecurityHospital = socialSecurityHospital;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setRecruitmentSources(String recruitmentSources) {
		this.recruitmentSources = recruitmentSources;
	}
	public void setContractRenewal(String contractRenewal) {
		this.contractRenewal = contractRenewal;
	}
	public void setOriginalNumber(String originalNumber) {
		this.originalNumber = originalNumber;
	}
	public void setSecrecyAgreement(String secrecyAgreement) {
		this.secrecyAgreement = secrecyAgreement;
	}
	public void setReportForm(String reportForm) {
		this.reportForm = reportForm;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setIfSecret(int ifSecret) {
		this.ifSecret = ifSecret;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}


}
