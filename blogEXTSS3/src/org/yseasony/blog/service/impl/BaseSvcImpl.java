package org.yseasony.blog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.yseasony.blog.dao.HibernateDao;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.BaseSvc;

public class BaseSvcImpl<T, PK extends Serializable> implements BaseSvc<T, PK>{

	private HibernateDao<T, PK> hibernateDao;
	
	public void setHibernateDao(HibernateDao<T, PK> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	@Transactional
	public void save(T t) {
		try {
			hibernateDao.save(t);
		} catch (Exception e) {
			throw new MyException(e);
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
		return hibernateDao.getMax(table);
	}

	@Transactional(readOnly = true)
	public boolean exist(String column, String value) {
		try {
			T t = hibernateDao.findUniqueBy(column, value);
			if (t == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyException(e);
		}
	}

	@Transactional(readOnly = true)
	public T getById(PK id){
		return hibernateDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public List<T> getListAll(){
		return hibernateDao.getAll();
	}
	
}
