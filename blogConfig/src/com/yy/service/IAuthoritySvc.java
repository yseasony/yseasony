package com.yy.service;

import java.util.List;

import com.yy.lang.utils.Page;
import com.yy.lang.utils.PropertyFilter;
import com.yy.model.Authority;

public interface IAuthoritySvc extends IBaseService<Authority, Long>{
	
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters);

}