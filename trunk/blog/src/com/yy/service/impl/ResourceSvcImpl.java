package com.yy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.IResourceDao;
import com.yy.dao.impl.HibernateDao;
import com.yy.model.Resource;
import com.yy.service.IResourceSvc;

@Service
public class ResourceSvcImpl extends BaseServiceImpl<Resource, Long> implements IResourceSvc {

	@SuppressWarnings("unused")
	@Autowired
	private IResourceDao resourceDao;

	@Override
	@javax.annotation.Resource(name="resourceDao")
	public void setHibernateDao(HibernateDao<Resource, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
