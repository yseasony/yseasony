package org.yseasony.exam.vo;

import java.io.Serializable;

public class AuthVO implements Serializable {

	private static final long serialVersionUID = 8673091335333554708L;

	private Long inputValue;

	private String boxLabel;
	
	public Long getInputValue() {
		return inputValue;
	}

	public void setInputValue(Long inputValue) {
		this.inputValue = inputValue;
	}

	public String getBoxLabel() {
		return boxLabel;
	}

	public void setBoxLabel(String boxLabel) {
		this.boxLabel = boxLabel;
	}

	public String getName() {
		return "authIds";
	}

}