package com.chaoxing.oa.entity.page.caiwu;

public class PBaoxiao {
	private Long id;
//	private Long piciNum;//
	private String jTime;//邮寄时间
	private Integer uid;//报销人ID
	private String username;//报销人
	private String email;//报销人邮箱
	private Integer cellCoreId;//细胞核ID
	private Integer number;//报销张数
	private Float money;//金额
	private Float huankuan;//还款金额
	private String bank;//银行
	private String account;//账号
	private String explain;//说明
	private Integer approid;//批准人ID
	private String approver;//批准人
	private String aproEmail;//批准人邮箱
	private String aproTime;//批准时间
	private String reciveTime;//收票时间
	private String baoxTime;//报销时间
	private Float baoxMoney;//允许报销金额
	private Float tuipiao;//退票金额
	private String caiwuRemarks;//财务备注
	private Integer status;//状态
	public Long getId() {
		return id;
	}
	public String getjTime() {
		return jTime;
	}
	public Integer getUid() {
		return uid;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public Integer getCellCoreId() {
		return cellCoreId;
	}
	public Integer getNumber() {
		return number;
	}
	public Float getMoney() {
		return money;
	}
	public Float getHuankuan() {
		return huankuan;
	}
	public String getBank() {
		return bank;
	}
	public String getAccount() {
		return account;
	}
	public String getExplain() {
		return explain;
	}
	public Integer getApproid() {
		return approid;
	}
	public String getApprover() {
		return approver;
	}
	public String getAproEmail() {
		return aproEmail;
	}
	public String getAproTime() {
		return aproTime;
	}
	public String getReciveTime() {
		return reciveTime;
	}
	public String getBaoxTime() {
		return baoxTime;
	}
	public Float getBaoxMoney() {
		return baoxMoney;
	}
	public Float getTuipiao() {
		return tuipiao;
	}
	public String getCaiwuRemarks() {
		return caiwuRemarks;
	}
	public Integer getStatus() {
		return status;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setjTime(String jTime) {
		this.jTime = jTime;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCellCoreId(Integer cellCoreId) {
		this.cellCoreId = cellCoreId;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public void setHuankuan(Float huankuan) {
		this.huankuan = huankuan;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public void setApproid(Integer approid) {
		this.approid = approid;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public void setAproEmail(String aproEmail) {
		this.aproEmail = aproEmail;
	}
	public void setAproTime(String aproTime) {
		this.aproTime = aproTime;
	}
	public void setReciveTime(String reciveTime) {
		this.reciveTime = reciveTime;
	}
	public void setBaoxTime(String baoxTime) {
		this.baoxTime = baoxTime;
	}
	public void setBaoxMoney(Float baoxMoney) {
		this.baoxMoney = baoxMoney;
	}
	public void setTuipiao(Float tuipiao) {
		this.tuipiao = tuipiao;
	}
	public void setCaiwuRemarks(String caiwuRemarks) {
		this.caiwuRemarks = caiwuRemarks;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
