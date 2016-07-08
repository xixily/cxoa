package com.chaoxing.oa.entity.page;

import java.io.Serializable;

public class PCompany implements Serializable{
	private static final long serialVersionUID = 4469578425454537573L;
	private int id;
	private String cmopany;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCmopany() {
		return cmopany;
	}

	public void setCmopany(String cmopany) {
		this.cmopany = cmopany;
	}
	
}
