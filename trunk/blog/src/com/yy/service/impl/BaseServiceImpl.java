package com.yy.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.impl.HibernateDao;
import com.yy.exception.MyException;
import com.yy.utils.AopLog;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public class BaseServiceImpl<T, PK extends Serializable> extends AopLog<T>{

	private HibernateDao<T, PK> hibernateDao;

	@Transactional
	public void save(T t) {
		try {
			this.hibernateDao.save(t);
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Transactional
	public void delete(PK id) {
		try {
			hibernateDao.delete(id);
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}

	@Transactional
	public void delete(T entity) {
		try {
			hibernateDao.delete(entity);
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Transactional(readOnly = true)
	public int getMax(String table) {
		return this.hibernateDao.getMax(table);
	}

	@Transactional(readOnly = true)
	public T exist(String column, String value) {
		return this.hibernateDao.findUniqueBy(column, value);
	}

	@Transactional(readOnly = true)
	public Page<T> searchResource(final Page<T> page,
			final List<PropertyFilter> filters) {
		return this.hibernateDao.findPage(page, filters);
	}
	
	public void setHibernateDao(HibernateDao<T, PK> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	@Transactional(readOnly = true)
	public T getById(PK id){
		return this.hibernateDao.get(id);
	}
}
