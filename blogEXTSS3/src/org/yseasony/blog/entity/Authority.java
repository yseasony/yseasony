package org.yseasony.blog.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 权限.
 */
@Entity
@Table(name = "TBL_AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DataTransferObject
public class Authority extends AuditableEntity<Long> {

	/**
	 * SpringSecurity中默认的角色/授权名前缀.
	 */
	public static final String AUTHORITY_PREFIX = "A_";
	/**
	 * 权限代码
	 */
	private String name;
	/**
	 * 权限名
	 */
	private String displayName;
	
	public Authority() {
		
	}

	public Authority(long id, String displayName) {
		this.displayName = displayName;
		super.setId(id);
	}
	
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, unique = true)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Transient
	public String getPrefixedName() {
		return AUTHORITY_PREFIX + name;
	}
}