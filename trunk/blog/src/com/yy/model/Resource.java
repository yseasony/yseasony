package com.yy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.yy.utils.ReflectionUtils;

/**
 * 受保护的资源.
 * 
 * 注释见{@link User}.
 * 
 * @author calvin
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Table(name = "TBL_RESOURCE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Resource extends AuditableEntity {
	// -- resourceType常量 --//
	public static final String URL_TYPE = "url";
	public static final String MENU_TYPE = "menu";

	private String resourceName;
	private String description;
	private String resourceType;
	private String value;
	private double position;
	private List<Authority> authorityList = new ArrayList<Authority>();

	/**
	 * 资源类型.
	 */
	@Column(nullable = false)
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * 资源标识.
	 */
	@Column(nullable = false, unique = true)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 资源在SpringSecurity中的校验顺序字段.
	 */
	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	@Column(nullable = false, length = 16)
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(length = 128)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 可访问该资源的授权集合.
	 */
	@ManyToMany
	@Cascade(value={org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(name = "TBL_RESOURCE_AUTHORITY", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.JOIN)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	/**
	 * 可访问该资源的授权名称字符串, 多个授权用','分隔.
	 */
	@Transient
	public String getAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(authorityList,
				"name", ",");
	}

	public Resource() {
	}
	
	public Resource(long id,String resourceName, String description,
			String resourceType, String value, double position) {
		super();
		this.resourceName = resourceName;
		this.description = description;
		this.resourceType = resourceType;
		this.value = value;
		this.position = position;
		super.setId(id);
	}

}
