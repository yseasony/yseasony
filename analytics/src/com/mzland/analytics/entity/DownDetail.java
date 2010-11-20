package com.mzland.analytics.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * MaDownDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ma_down_detail", catalog = "mz_analytics")
public class DownDetail implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 3112519483325892360L;
	private String downDetailId;
	private Down down;
	private UserInfo userInfo;
	private Timestamp downloadTime;
	private String customerId;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "down_detail_id", unique = true, nullable = false, length = 38)
	public String getDownDetailId() {
		return this.downDetailId;
	}

	public void setDownDetailId(String downDetailId) {
		this.downDetailId = downDetailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "multimedia_id", nullable = false)
	public Down getDown() {
		return down;
	}

	public void setDown(Down down) {
		this.down = down;
	}

	@Column(name = "download_time", nullable = false, length = 19)
	public Timestamp getDownloadTime() {
		return this.downloadTime;
	}

	public void setDownloadTime(Timestamp downloadTime) {
		this.downloadTime = downloadTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "customer_id", nullable = false)
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}