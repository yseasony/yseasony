package com.yy.dao;


import java.util.List;

import org.hibernate.HibernateException;

import com.yy.model.Role;


public interface IRoleDao extends IHibernateDao<Role, Long>{

	/**
	 * 重载函数,因为Role中没有建立与User的关联,因此需要以较低效率的方式进行删除User与Role的多对多中间表.
	 */
	public void delete(Long id);
	
	public Role get(Long id);
	
	public List<Role> getAll(String propertyName,boolean b);
	
	/**
	 * 保存到中间表role_authority
	 * @param roleId
	 * @param authorityId
	 * @throws HibernateException
	 */
	public void save_Role_Authority(Long roleId,Long authorityId) throws HibernateException;
	
}