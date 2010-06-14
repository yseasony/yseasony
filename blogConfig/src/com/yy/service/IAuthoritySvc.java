package com.yy.service;

import java.util.List;

import com.yy.model.Authority;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

public interface IAuthoritySvc extends IBaseService<Authority, Long>{
	
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters);

}