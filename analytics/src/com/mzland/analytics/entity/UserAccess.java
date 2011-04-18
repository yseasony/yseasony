package com.mzland.analytics.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ma_user_access", catalog = "mz_analytics")
public class UserAccess implements java.io.Serializable {

	private String accessId;
	private String customerId;
	private Short itemId;
	private String userIp;
	private String userImei;
	private Timestamp datatimes = new Timestamp(System.currentTimeMillis());
	private String version;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "access_id", unique = true, nullable = false, length = 38)
	public String getAccessId() {
		return this.accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	@Column(name = "item_id", nullable = false)
	public Short getItemId() {
		return this.itemId;
	}

	public void setItemId(Short itemId) {
		this.itemId = itemId;
	}

	@Column(name = "user_ip", length = 40)
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

	@Column(name = "datatimes", nullable = false, length = 19)
	public Timestamp getDatatimes() {
		return this.datatimes;
	}

	public void setDatatimes(Timestamp datatimes) {
		this.datatimes = datatimes;
	}

	@Column(name = "version", length = 15)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "customer_id", nullable = false)
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}