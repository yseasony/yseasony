/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SimpleHibernateDao.java 582 2009-10-22 14:26:23Z calvinxiu $
 */
package com.yy.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.yy.dao.ISimpleHibernateDao;
import com.yy.exception.MyException;
import com.yy.utils.MyStringUtils;
import com.yy.utils.ReflectionUtils;

/**
 * 封装Hibernate原生API的DAO泛型基类.
 * 
 * 可在Service层直接使用,也可以扩展泛型DAO子类使用.
 * 参考Spring2.5自带的Petlinc例子,取消了HibernateTemplate,直接使用Hibernate原生API.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 * @author calvin
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateDao<T, PK extends Serializable> implements
		ISimpleHibernateDao<T, PK> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * SimpleHibernateDao<User, Long>
	 */
	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleHibernateDao的构造函数. 在构造函数中定义对象类型Class.
	 * eg. SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 */
	public SimpleHibernateDao(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	/**
	 * 取得sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#save(T)
	 */
	public void save(final T entity) throws MyException, HibernateException {
		if (entity == null) {
			throw new MyException("save entity is null");
		}
		try {
			getSession().saveOrUpdate(entity);
		} catch (HibernateException e) {
			logger.error("save " + entity.getClass() + "error", e);
			throw e;
		}
		logger.debug("save entity success : {}", entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#delete(T)
	 */
	public void delete(final T entity) throws MyException {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#delete(PK)
	 */
	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(),
				id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#get(PK)
	 */
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#getAll()
	 */
	public List<T> getAll() {
		return find();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#getAll(java.lang.String,
	 * boolean)
	 */
	public List<T> getAll(String orderBy, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc)
			c.addOrder(Order.asc(orderBy));
		else
			c.addOrder(Order.desc(orderBy));
		return c.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#findBy(java.lang.String,
	 * java.lang.Object)
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#findUniqueBy(java.lang.String,
	 * java.lang.Object)
	 */
	public T findUniqueBy(final String propertyName, final Object value)
			throws MyException, HibernateException {
		if (MyStringUtils.isBlank(propertyName)) {
			throw new MyException("propertyName is can't be null");
		}
		Criterion criterion = Restrictions.eq(propertyName, value);
		try {
			return (T) createCriteria(criterion).uniqueResult();
		} catch (HibernateException e) {
			logger.error("findUniqueBy " + propertyName + "error", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#findByIds(java.util.List)
	 */
	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#find(java.lang.String,
	 * java.lang.Object)
	 */
	public <X> List<X> find(final String hql, boolean cache,
			final Object... values) {
		return createQuery(hql, cache, values).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#find(java.lang.String,
	 * java.util.Map)
	 */
	public <X> List<X> find(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#findUnique(java.lang.String,
	 * java.lang.Object)
	 */
	public <X> X findUnique(final String hql, boolean cache,
			final Object... values) {
		return (X) createQuery(hql, cache, values).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#findUnique(java.lang.String,
	 * java.util.Map)
	 */
	public <X> X findUnique(final String hql, final Map<String, Object> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#batchExecute(java.lang.String,
	 * java.lang.Object)
	 */
	public int batchExecute(final String hql, boolean cache,
			final Object... values) {
		return createQuery(hql, cache, values).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#batchExecute(java.lang.String,
	 * java.util.Map)
	 */
	public int batchExecute(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).executeUpdate();
	}
	
	public void batchInsert(List<T> list,int batchSize) {
		int i = 0;
		for (T t : list) {
			this.getSession().save(t);
			i++;
			if (i % batchSize == 0) {
				this.getSession().flush();
				this.getSession().clear();
			}
		}
	} 

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#createQuery(java.lang.String,
	 * java.lang.Object)
	 */
	public Query createQuery(final String queryString, boolean cache,
			final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (cache) {
			query.setCacheable(true);
		}
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#createQuery(java.lang.String,
	 * java.util.Map)
	 */
	public Query createQuery(final String queryString,
			final Map<String, Object> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yy.dao.impl.ISimpleHibernateDao#find(org.hibernate.criterion.Criterion
	 * )
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yy.dao.impl.ISimpleHibernateDao#findUnique(org.hibernate.criterion
	 * .Criterion)
	 */
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yy.dao.impl.ISimpleHibernateDao#createCriteria(org.hibernate.criterion
	 * .Criterion)
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setCacheable(true);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#initEntity(T)
	 */
	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#initEntity(java.util.List)
	 */
	public void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#distinct(org.hibernate.Query)
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#distinct(org.hibernate.Criteria)
	 */
	public Criteria distinct(Criteria criteria) {
		criteria
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#distinct(java.util.List)
	 */
	public <X> List<X> distinct(List list) {
		Set<X> set = new LinkedHashSet<X>(list);
		return new ArrayList<X>(set);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.ISimpleHibernateDao#getIdName()
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	public int getMax(String table) {
		String hql = "select count(*)+1 from " + table + "";
		return Integer.valueOf(this.createQuery(hql, true).uniqueResult()
				.toString());
	}

	public SQLQuery executeSql(String sql) throws HibernateException{
		try {
			return this.getSession().createSQLQuery(sql);
		} catch (HibernateException e) {
			throw e;
		}
	}
}