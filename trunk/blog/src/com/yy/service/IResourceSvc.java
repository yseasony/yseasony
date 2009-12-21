package com.yy.service;

import com.yy.model.Resource;

public interface IResourceSvc {

	public void save(Resource resource);

	public Resource resourceExist(String value);
	
	public int getMax();

}