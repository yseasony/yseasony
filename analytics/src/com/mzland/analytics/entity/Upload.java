package com.mzland.analytics.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@Entity
@Table(name = "ma_upload", catalog = "mz_analytics")
public class Upload implements java.io.Serializable {

	private static final long serialVersionUID = -1600809703807451591L;
	private String multimediaId;
	private String customerId;
	private String title;
	private String username;
	private String userIp;
	private String userImei;
	private Timestamp uploadTime = new Timestamp(System.currentTimeMillis());

	@Id
	@Column(name = "multimedia_id", unique = true, nullable = false, length = 38)
	public String getMultimediaId() {
		return this.multimediaId;
	}

	public void setMultimediaId(String multimediaId) {
		this.multimediaId = multimediaId;
	}

	@Column(name = "customer_id", nullable = false, length = 38)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "title", nullable = false, length = 150)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "username", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "user_ip", nullable = false, length = 40)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "user_imei", length = 50)
	public String getUserImei() {
		return this.userImei;
	}

	public void setUserImei(String userImei) {
		this.userImei = userImei;
	}

	@Column(name = "upload_time", nullable = false, length = 19)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

}