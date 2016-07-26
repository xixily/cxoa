package com.chaoxing.oa.entity.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="社保类型", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ShebaoType implements Serializable{
	private static final long serialVersionUID = 1941867794985573282L;
	private int id;
	private String name;
	@Id
	@Column
	public int getId() {
		return id;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
