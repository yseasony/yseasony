package com.yy.dao;

import java.util.List;

import com.yy.model.Resource;

public interface IResourceDao extends IHibernateDao<Resource, Long> {

	/**
	 * 查询URL类型的资源并预加载可访问该资源的授权信息.
	 */
	public List<Resource> getUrlResourceWithAuthorities();
	
	public void updatePosition(Double position);

}