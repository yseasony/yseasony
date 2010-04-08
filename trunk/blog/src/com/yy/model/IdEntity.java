package com.yy.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author calvin
 * @param <T>
 */
// JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity<T> {

	private T id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
}
