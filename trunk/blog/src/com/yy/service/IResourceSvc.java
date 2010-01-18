package com.yy.service;

import java.util.List;

import com.yy.model.Resource;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public interface IResourceSvc {

	public void save(Resource resource);

	public Resource exist(String column, String value);

	public int getMax(String table);

	public Page<Resource> searchResource(final Page<Resource> page,
			final List<PropertyFilter> filters);

	public void delete(Long id, Double position);
	
	public Page<Resource> getPage(Page<Resource> page);

}