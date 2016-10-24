package com.chaoxing.oa.entity.page.hetong;

public class PFahuo {
	private Integer orderid;//序号
	private Integer hetongCode;//合同编号<
	private String d_contact;//收件人
	private String d_company;//邮寄单位(到方
	private String d_address;//邮寄地址
	private String d_tel;//联系电话（到方）
	private String postMethod;//这里我只需要顺丰的<
	private String jDate;//邮寄时间
	private String sendstarttime;//要求上门取件开始时间>
	private String mailno;//邮寄凭证号(运单号)
	private String remark;//备注
	private String d_post_code;//邮编
	private String content;//内容<
	private String areaCode;//区号
	private String sender;//发货人<
	private String order_name;//客户订单下单人姓名 >
	private String d_city;//城市
	private String area;//地区<
	private String recorder;//录库人<
	/**
	 * 货单新增
	 */
	private String destcode;//目的地区域代码
	/**
	 * 分页项
	 */
	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	
	public Integer getOrderid() {
		return orderid;
	}
	public Integer getHetongCode() {
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
	public String getSendstarttime() {
		return sendstarttime;
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
	public String getOrder_name() {
		return order_name;
	}
	public String getD_city() {
		return d_city;
	}
	public String getArea() {
		return area;
	}
	public String getRecorder() {
		return recorder;
	}
	public Integer getPage() {
		return page;
	}
	public Integer getRows() {
		return rows;
	}
	public String getSort() {
		return sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public void setHetongCode(Integer hetongCode) {
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
	public void setSendstarttime(String sendstarttime) {
		this.sendstarttime = sendstarttime;
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
		setOrder_name(sender);
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public void setD_city(String d_city) {
		this.d_city = d_city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getDestcode() {
		return destcode;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	@Override
	public String toString() {
		return "PFahuo [orderid=" + orderid + ", hetongCode=" + hetongCode + ", d_contact=" + d_contact + ", d_company="
				+ d_company + ", d_address=" + d_address + ", d_tel=" + d_tel + ", postMethod=" + postMethod
				+ ", jDate=" + jDate + ", sendstarttime=" + sendstarttime + ", mailno=" + mailno + ", remark=" + remark
				+ ", d_post_code=" + d_post_code + ", content=" + content + ", areaCode=" + areaCode + ", sender="
				+ sender + ", order_name=" + order_name + ", d_city=" + d_city + ", area=" + area + ", recorder="
				+ recorder + ", page=" + page + ", rows=" + rows + ", sort=" + sort + ", order=" + order + "]";
	}
	
}
