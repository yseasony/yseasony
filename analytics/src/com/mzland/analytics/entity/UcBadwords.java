package com.mzland.analytics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@Entity
@Table(name = "uc_badwords", catalog = "ucenter", uniqueConstraints = @UniqueConstraint(columnNames = "find"))
public class UcBadwords implements java.io.Serializable {

	private static final long serialVersionUID = -7744256598292368277L;
	private Short id;
	private String admin;
	private String find;
	private String replacement;
	private String findpattern;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Column(name = "admin", nullable = false, length = 15)
	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Column(name = "find", unique = true, nullable = false)
	public String getFind() {
		return this.find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	@Column(name = "replacement", nullable = false)
	public String getReplacement() {
		return this.replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	@Column(name = "findpattern", nullable = false)
	public String getFindpattern() {
		return this.findpattern;
	}

	public void setFindpattern(String findpattern) {
		this.findpattern = findpattern;
	}

}