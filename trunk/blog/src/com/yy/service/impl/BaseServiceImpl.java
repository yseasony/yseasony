package com.yy.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.impl.HibernateDao;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public class BaseServiceImpl<T, PK extends Serializable> {

	private HibernateDao<T, PK> hibernateDao;

	//private Class<T> entityClass;

	//private Log log = LogFactory.getLog(entityClass);

	@AfterThrowing(pointcut="afterThrowing()")
	@Transactional
	public void save(T t) {
		try {
			this.hibernateDao.save(t);
		} catch (ServiceException e) {
			throw new ServiceException("保存失败");
		}
	}

	@Transactional
	public void delete(PK id) {
		try {
			hibernateDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException("删除失败");
		}
	}

	@Transactional(readOnly = true)
	public int getMax(String table) {
		return this.hibernateDao.getMax(table);
	}

	public void setHibernateDao(HibernateDao<T, PK> hibernateDao) {
		this.hibernateDao = hibernateDao;
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

	@SuppressWarnings("unused")
	private void afterThrowing() {
		System.out.println("do~~~");
//		log.error(e.getCause());
//		log.error(e.getMessage());
	}
}
