package com.yy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IResourceDao;
import com.yy.model.Resource;
import com.yy.service.IResourceSvc;

@Service
public class ResourceSvcImpl implements IResourceSvc {

	@Autowired
	private IResourceDao resourceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.service.impl.ResourceSvc#save(com.yy.model.Resource)
	 */
	@Transactional
	public void save(Resource resource) {
		try {
			this.resourceDao.save(resource);
		} catch (Exception e) {
			throw new ServiceException("资源地址保存失败");
		}
	}

	@Transactional(readOnly = true)
	public Resource resourceExist(String value) {
		return this.resourceDao.findUniqueBy("value", value);
	}

	@Transactional(readOnly = true)
	public int getMax() {
		return this.resourceDao.getMax("TBL_RESOURCE");
	}

}
