package com.chaoxing.oa.entity.page;

public class pGongziHuiZong {
	private int id;
	private String username;
	private Float  fGongziZonge;
	private Float  fSheBaoZiFu;
	private Float  fSheBaoGongsi;
	private String firstLevel;//一级
	private String secondLevel;//二级
	private String thirdLevel;//三级
	private String fourthLevel;//四级
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Float getfGongziZonge() {
		return fGongziZonge;
	}
	public void setfGongziZonge(Float fGongziZonge) {
		this.fGongziZonge = fGongziZonge;
	}
	public Float getfSheBaoZiFu() {
		return fSheBaoZiFu;
	}
	public void setfSheBaoZiFu(Float fSheBaoZiFu) {
		this.fSheBaoZiFu = fSheBaoZiFu;
	}
	public Float getfSheBaoGongsi() {
		return fSheBaoGongsi;
	}
	public void setfSheBaoGongsi(Float fSheBaoGongsi) {
		this.fSheBaoGongsi = fSheBaoGongsi;
	}
	public String getFirstLevel() {
		return firstLevel;
	}
	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}
	public String getSecondLevel() {
		return secondLevel;
	}
	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}
	public String getThirdLevel() {
		return thirdLevel;
	}
	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}

}
