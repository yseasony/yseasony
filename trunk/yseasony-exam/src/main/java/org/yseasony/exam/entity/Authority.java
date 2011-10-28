package org.yseasony.exam.entity;

import java.io.Serializable;

public class Authority implements Serializable {

	private static final long serialVersionUID = 4736362455844134581L;

	/**
	 * SpringSecurity中默认的角色/授权名前缀.
	 */
	public static final String AUTHORITY_PREFIX = "ROLE_";

	private Integer id;

	private String name;
	
	private String displayName;
	
	/**
	 * ext上对应的按钮
	 */
	private String buttonName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefixedName() {
		return AUTHORITY_PREFIX + name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	
	
}