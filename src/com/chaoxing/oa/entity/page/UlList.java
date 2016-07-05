package com.chaoxing.oa.entity.page;


public class UlList {
	private String text;//li名字
	private String url;//tab页
	private String iconCls = "icon-man";//设置图标
	private int preMenuId;//前置menu
	private int domId;//映射menuid
	/*private List<UlList> children;*/
//	private String state;//ul的开启状态
	public int getPreMenuId() {
		return preMenuId;
	}
	public int getDomId() {
		return domId;
	}
	public String getText() {
		return text;
	}
	public String getUrl() {
		return url;
	}
	public void setPreMenuId(int preMenuId) {
		this.preMenuId = preMenuId;
	}
	public void setDomId(int domId) {
		this.domId = domId;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
}
