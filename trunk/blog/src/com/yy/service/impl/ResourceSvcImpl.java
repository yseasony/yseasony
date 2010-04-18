package com.yy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IHibernateDao;
import com.yy.dao.IResourceDao;
import com.yy.exception.MyException;
import com.yy.model.Resource;
import com.yy.service.IResourceSvc;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

@Service
public class ResourceSvcImpl extends BaseServiceImpl<Resource, Long> implements
		IResourceSvc {

	@Autowired
	private IResourceDao resourceDao;
	

	@Override
	@javax.annotation.Resource(name = "resourceDaoImpl")
	public void setHibernateDao(IHibernateDao<Resource, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}

	@Transactional
	public void delete(Long id, Double position) {
		try {
			this.delete(id);
			this.resourceDao.updatePosition(position);
		} catch (MyException e) {
			throw new MyException(e.getMessage());
		}
	}
	
	@Transactional(readOnly = true)
	public Page<Resource> getPage(Page<Resource> page,final List<PropertyFilter> filters){
		return resourceDao.getPage(page,filters);
	}
}
