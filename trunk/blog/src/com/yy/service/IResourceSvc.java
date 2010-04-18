package com.yy.service;

import java.util.List;

import com.yy.model.Resource;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public interface IResourceSvc extends IBaseService<Resource, Long>{

	public Resource exist(String column, String value);

	public Page<Resource> searchResource(final Page<Resource> page,
			final List<PropertyFilter> filters);
	
	public Page<Resource> getPage(Page<Resource> page,final List<PropertyFilter> filters);
	
	public void delete(Long id, Double position);

}