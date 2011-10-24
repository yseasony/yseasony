package org.yseasony.acg.vo;

import java.io.Serializable;

public class RoleVO implements Serializable {

	private static final long serialVersionUID = 756458481650346572L;

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
		return "roleIds";
	}

}