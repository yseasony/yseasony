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
@Table(name = "ma_cooperation_log")
public class CooperationLog implements java.io.Serializable {

	private static final long serialVersionUID = -5646156100292881345L;
	private String imei;
	private String pkgNumber;
	private String version;
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());

	public CooperationLog() {}

	public CooperationLog(String imei, String version, String pkgNumber) {
		this.imei = imei;
		this.version = version;
		this.pkgNumber = pkgNumber;
	}

	@Id
	@Column(name = "imei", unique = true, nullable = false, length = 50)
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(name = "version", nullable = false, length = 10)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "pkg_number", nullable = false)
	public String getPkgNumber() {
		return pkgNumber;
	}

	public void setPkgNumber(String pkgNumber) {
		this.pkgNumber = pkgNumber;
	}

}