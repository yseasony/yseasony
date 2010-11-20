package com.mzland.analytics.dto;


/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
public class UploadDTO implements java.io.Serializable {

	private static final long serialVersionUID = -394842751697938466L;
	private String multimediaId;
	private String customerId;
	private String title;
	private String username;
	private String userIp;
	private String userImei;

	public String getMultimediaId() {
		return multimediaId;
	}

	public void setMultimediaId(String multimediaId) {
		this.multimediaId = multimediaId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserImei() {
		return userImei;
	}

	public void setUserImei(String userImei) {
		this.userImei = userImei;
	}

}