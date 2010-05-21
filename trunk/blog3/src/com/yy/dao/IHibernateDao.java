package com.yy.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;
import com.yy.utils.PropertyFilter.MatchType;

public interface IHibernateDao<T, PK extends Serializable> extends
		ISimpleHibernateDao<T, PK> {

	// -- 分页查询函数 --//
	/**
	 * 分页获取全部对象.
	 */
	public Page<T> getAll(final Page<T> page);

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPage(final Page<T> page, final String hql,
			final Object... values);
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPage(final Page<T> page, final String hql);

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPage(final Page<T> page, final String hql,
			final Map<String, Object> values);

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPage(final Page<T> page, final Criterion... criterions);

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType
	 *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public List<T> findBy(final String propertyName, final Object value,
			final MatchType matchType);

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> find(List<PropertyFilter> filters);

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters);

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue);

}