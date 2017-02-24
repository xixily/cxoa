package com.chaoxing.oa.entity.page.pub.caiwu;

public class PSelfBaoxiao {
	private Long id;
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
	
	
}
