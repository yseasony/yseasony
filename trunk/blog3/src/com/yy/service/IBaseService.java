package com.yy.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.yy.exception.MyException;

public interface IBaseService<T, PK extends Serializable> {

	@Transactional
	public void save(T t) throws MyException;

	@Transactional
	public void delete(PK id);

	@Transactional
	public void delete(T entity);

	@Transactional(readOnly = true)
	public int getMax(String table);

	@Transactional(readOnly = true)
	public T exist(String column, String value);

	@Transactional(readOnly = true)
	public T getById(PK id);

}