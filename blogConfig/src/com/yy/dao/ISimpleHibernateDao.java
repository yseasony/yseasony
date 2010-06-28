package com.yy.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;

import com.yy.exception.MyException;

public interface ISimpleHibernateDao<T, PK extends Serializable> {

	/**
	 * 保存新增或修改的对象.
	 * @throws MyException 
	 * @throws HibernateException
	 */
	public void save(final T entity) throws MyException, HibernateException;

	/**
	 * 删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity);

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id);

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id);

	/**
	 * 获取全部对象.
	 */
	public List<T> getAll();

	/**
	 * 获取全部对象,支持排序.
	 * 
	 * @param order
	 *            排序方向,可选值为asc 或 desc.
	 */
	public List<T> getAll(String orderBy, boolean isAsc);

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value);

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value) throws MyException, HibernateException;

	/**
	 * 按id列表获取对象.
	 */
	public List<T> findByIds(List<PK> ids);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> List<X> find(final String hql, boolean cache,
			final Object... values);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String hql, final Map<String, Object> values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> X findUnique(final String hql, boolean cache,
			final Object... values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> X findUnique(final String hql, final Map<String, Object> values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, boolean cache,
			final Object... values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, Object> values);
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, boolean cache,
			final Object... values);

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString,
			final Map<String, Object> values);

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions);

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public T findUnique(final Criterion... criterions);

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions);

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize
	 * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initEntity(T entity);

	/**
	 * @see #initEntity(Object)
	 */
	public void initEntity(List<T> entityList);

	/**
	 * 为Query添加distinct transformer.
	 */
	public Query distinct(Query query);

	/**
	 * 为Criteria添加distinct transformer.
	 */
	public Criteria distinct(Criteria criteria);

	/**
	 * 通过Set将不唯一的对象列表唯一化. 主要用于HQL/Criteria预加载关联集合形成重复记录,又不方便使用distinct查询语句时.
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> distinct(List list);

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName();

	/**
	 * 取得总数+1
	 */
	public int getMax(String table);

	/**
	 * 执行SQL
	 * @param sql
	 * @return int
	 * @throws HibernateException
	 * @author YseasonY
	 * @version time ：2010-6-28 下午04:31:18
	 */
	public int executeSql(String sql) throws HibernateException;
	/**
	 * 执行SQL
	 * @param sql
	 * @return SQLQuery
	 * @throws HibernateException
	 * @author YseasonY
	 * @version time ：2010-6-28 下午04:30:40
	 */
	public SQLQuery executeSQLQuery(String sql) throws HibernateException;
}