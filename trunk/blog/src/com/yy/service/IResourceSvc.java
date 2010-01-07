package com.yy.service;

import java.util.List;

import com.yy.model.Resource;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public interface IResourceSvc {

	public void save(Resource resource);

	public Resource resourceExist(String value);
	
	public int getMax();
	
	public Page<Resource> searchResource(final Page<Resource> page,
			final List<PropertyFilter> filters);

}