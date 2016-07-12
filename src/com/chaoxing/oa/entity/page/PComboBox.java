package com.chaoxing.oa.entity.page;

import java.io.Serializable;

public class PComboBox implements Serializable {
	private String value;
	private String text;
	public String getValue() {
		return value;
	}
	public String getText() {
		return text;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
