package com.yy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IResourceDao;
import com.yy.dao.impl.HibernateDao;
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
	public void setHibernateDao(HibernateDao<Resource, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}

	@Transactional
	public void delete(Long id, Double position) {
		try {
			this.resourceDao.delete(id);
			this.resourceDao.updatePosition(position);
		} catch (Exception e) {
			throw new ServiceException("删除失败");
		}
		
	}
	
	public Page<Resource> getPage(Page<Resource> page,final List<PropertyFilter> filters){
		return resourceDao.getPage(page,filters);
	}
}
