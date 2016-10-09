package com.chaoxing.oa.entity.page.hetong;

public class PFahuo {
	private int orderid;//序号
	private int hetongCode;//合同编号
	private String d_contact;//收件人
	private String d_company;//邮寄单位(到方
	private String d_address;//邮寄地址
	private String d_tel;//联系电话（到方）
	private String postMethod;//这里我只需要顺丰的
	private String jDate;//邮寄时间
	private String mailno;//邮寄凭证号(运单号)
	private String remark;//备注
	private String d_post_code;//邮编
	private String content;//内容
	private String areaCode;//区号
	private String sender;//发货人
	private String city;//城市
	private String area;//地区
	private String recorder;//录库人
	/**
	 * 分页项
	 */
	private int page;
	private int rows;
	private String sort;
	private String order;
	public int getOrderid() {
		return orderid;
	}
	public int getHetongCode() {
		return hetongCode;
	}
	public String getD_contact() {
		return d_contact;
	}
	public String getD_company() {
		return d_company;
	}
	public String getD_address() {
		return d_address;
	}
	public String getD_tel() {
		return d_tel;
	}
	public String getPostMethod() {
		return postMethod;
	}
	public String getjDate() {
		return jDate;
	}
	public String getMailno() {
		return mailno;
	}
	public String getRemark() {
		return remark;
	}
	public String getD_post_code() {
		return d_post_code;
	}
	public String getContent() {
		return content;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getSender() {
		return sender;
	}
	public String getCity() {
		return city;
	}
	public String getArea() {
		return area;
	}
	public String getRecorder() {
		return recorder;
	}
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public void setHetongCode(int hetongCode) {
		this.hetongCode = hetongCode;
	}
	public void setD_contact(String d_contact) {
		this.d_contact = d_contact;
	}
	public void setD_company(String d_company) {
		this.d_company = d_company;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}
	public void setPostMethod(String postMethod) {
		this.postMethod = postMethod;
	}
	public void setjDate(String jDate) {
		this.jDate = jDate;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setD_post_code(String d_post_code) {
		this.d_post_code = d_post_code;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
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
	public void setOrder(String order) {
		this.order = order;
	}
	
}