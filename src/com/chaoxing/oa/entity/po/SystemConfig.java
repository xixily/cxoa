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

@Entity
@Table(name="系统配置", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SystemConfig {
	private int id;
	private String ifGenrate;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "sysTable",strategy = "native")
	public int getId() {
		return id;
	}
	@Column(name = "考勤是否生成", updatable = false, columnDefinition = " varchar(10) DEFAULT 'off'")
	public String getIfGenrate() {
		return ifGenrate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIfGenrate(String ifGenrate) {
		this.ifGenrate = ifGenrate;
	}
	
}
