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

	// Property accessors
	@Id
	@Column(name = "pkg_number", unique = true, nullable = false, length = 10)
	public String getPkgNumber() {
		return this.pkgNumber;
	}

	public void setPkgNumber(String pkgNumber) {
		this.pkgNumber = pkgNumber;
	}

}