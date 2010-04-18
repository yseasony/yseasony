package com.yy.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IHibernateDao;
import com.yy.exception.MyException;
import com.yy.service.IBaseService;
import com.yy.utils.AopLog;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public class BaseServiceImpl<T, PK extends Serializable> extends AopLog<T> implements IBaseService<T, PK>{

	private IHibernateDao<T, PK> hibernateDao;
	
	public void setHibernateDao(IHibernateDao<T, PK> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	/* (non-Javadoc)
	 * @see com.yy.service.impl.IBaseService#save(T)
	 */
	@Transactional
	public void save(T t) {
		try {
			this.hibernateDao.save(t);
		} catch (Exception e) {
			throw new MyException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.yy.service.impl.IBaseService#delete(PK)
	 */
	@Transactional
	public void delete(PK id) {
		try {
			hibernateDao.delete(id);
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.yy.service.impl.IBaseService#delete(T)
	 */
	@Transactional
	public void delete(T entity) {
		try {
			hibernateDao.delete(entity);
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.yy.service.impl.IBaseService#getMax(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public int getMax(String table) {
		return this.hibernateDao.getMax(table);
	}

	/* (non-Javadoc)
	 * @see com.yy.service.impl.IBaseService#exist(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public T exist(String column, String value) {
		return this.hibernateDao.findUniqueBy(column, value);
	}

	@Transactional(readOnly = true)
	public Page<T> searchResource(final Page<T> page,
			final List<PropertyFilter> filters) {
		return this.hibernateDao.findPage(page, filters);
	}

	/* (non-Javadoc)
	 * @see com.yy.service.impl.IBaseService#getById(PK)
	 */
	@Transactional(readOnly = true)
	public T getById(PK id){
		return this.hibernateDao.get(id);
	}
	
	
}
