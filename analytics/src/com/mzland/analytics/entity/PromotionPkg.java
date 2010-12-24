package com.mzland.analytics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@Entity
@Table(name = "ma_promotion_pkg", catalog = "mz_analytics")
public class PromotionPkg implements java.io.Serializable {

	private static final long serialVersionUID = -1975672781803375499L;
	private String pkgNumber;
	private Double money;
	private String version;
	private String os;

	// Property accessors
	@Id
	@Column(name = "pkg_number", unique = true, nullable = false, length = 10)
	public String getPkgNumber() {
		return this.pkgNumber;
	}

	public void setPkgNumber(String pkgNumber) {
		this.pkgNumber = pkgNumber;
	}

	@Column(name = "money", nullable = false, precision = 5)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "version", nullable = false, length = 10)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "os", nullable = false, length = 20)
	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

}