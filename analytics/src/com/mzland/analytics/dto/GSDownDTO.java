package com.mzland.analytics.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@XmlRootElement
public class GSDownDTO {

	private String id;
	private String title;
	private int categoryId;
	private int phoneModelId;
	private String customerId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getPhoneModelId() {
		return phoneModelId;
	}

	public void setPhoneModelId(int phoneModelId) {
		this.phoneModelId = phoneModelId;
	}

}
