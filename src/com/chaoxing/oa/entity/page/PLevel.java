package com.chaoxing.oa.entity.page;

import java.io.Serializable;

public class PLevel implements Serializable {
	private static final long serialVersionUID = 3946996978367282290L;
	private int id;
	private String name;
	public int getId() {
		return id;
	}
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
