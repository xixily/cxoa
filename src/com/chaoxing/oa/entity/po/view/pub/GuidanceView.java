package com.chaoxing.oa.entity.po.view.pub;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "指导视图")
public class GuidanceView implements Serializable{
	private static final long serialVersionUID = 151064019463042338L;
	private String cellCore;
	private String cemail;
	private String gemail;
	private Double lastYear;
	private Double thisYear;
	private Double yingshou;
	
	@Column(name="细胞核")
	public String getCellCore() {
		return cellCore;
	}
	@Id
	@Column(name="细胞核邮箱")
	public String getCemail() {
		return cemail;
	}
	@Column(name="指导邮箱")
	public String getGemail() {
		return gemail;
	}
	@Column(name="去年")
	public Double getLastYear() {
		return lastYear;
	}
	@Column(name="今年")
	public Double getThisYear() {
		return thisYear;
	}
	@Column(name="应收")
	public Double getYingshou() {
		return yingshou;
	}
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public void setGemail(String gemail) {
		this.gemail = gemail;
	}
	public void setLastYear(Double lastYear) {
		this.lastYear = lastYear;
	}
	public void setThisYear(Double thisYear) {
		this.thisYear = thisYear;
	}
	public void setYingshou(Double yingshou) {
		this.yingshou = yingshou;
	}

}
