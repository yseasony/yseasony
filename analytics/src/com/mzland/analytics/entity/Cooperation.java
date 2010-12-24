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
@Table(name = "ma_cooperation")
public class Cooperation implements java.io.Serializable {

	private static final long serialVersionUID = 6662936865608015886L;

	private Short cid;
	private String pkgNumber;
	private String name;
	private String email;
	private Timestamp createTime;

	@Id
	@Column(name = "cid", unique = true, nullable = false)
	public Short getCid() {
		return this.cid;
	}

	public void setCid(Short cid) {
		this.cid = cid;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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