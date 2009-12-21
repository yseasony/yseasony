package com.yy.dao;

import com.yy.model.Role;

public interface IRoleDao extends IHibernateDao<Role, Long>{

	/**
	 * 重载函数,因为Role中没有建立与User的关联,因此需要以较低效率的方式进行删除User与Role的多对多中间表.
	 */
	public void delete(Long id);

}