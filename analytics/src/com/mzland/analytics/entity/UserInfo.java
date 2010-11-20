package com.mzland.analytics.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MaUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ma_user_info", catalog = "mz_analytics")
public class UserInfo implements java.io.Serializable {

	private static final long serialVersionUID = 7251478561353588746L;
	private String customerId;
	private Integer uid;
	private String userImei;
	private Timestamp lastlogintime = new Timestamp(System.currentTimeMillis());
	private String lastloginip;
	private Set<DownDetail> downDetails = new HashSet<DownDetail>(0);
	private Set<UserAccess> userAccesses = new HashSet<UserAccess>(0);

	@Id
	@Column(name = "customer_id", unique = true, nullable = false, length = 38)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "uid", nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "lastlogintime", length = 19)
	public Timestamp getLastlogintime() {
		return this.lastlogintime;
	}

	public void setLastlogintime(Timestamp lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	@Column(name = "lastloginip", length = 40)
	public String getLastloginip() {
		return this.lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	@Column(name = "user_imei", length = 50)
	public String getUserImei() {
		return this.userImei;
	}

	public void setUserImei(String userImei) {
		this.userImei = userImei;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<DownDetail> getDownDetails() {
		return downDetails;
	}

	public void setDownDetails(Set<DownDetail> downDetails) {
		this.downDetails = downDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<UserAccess> getUserAccesses() {
		return userAccesses;
	}

	public void setUserAccesses(Set<UserAccess> userAccesses) {
		this.userAccesses = userAccesses;
	}

}