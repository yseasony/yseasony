package com.yy.service;

import java.util.List;

import com.yy.lang.utils.Page;
import com.yy.lang.utils.PropertyFilter;
import com.yy.model.Role;


public interface IRoleSvc extends IBaseService<Role, Long>{
	
	public void save(Role role ,String[] authorityId);
	
	public Page<Role> getPage(Page<Role> page,final List<PropertyFilter> filters);

}