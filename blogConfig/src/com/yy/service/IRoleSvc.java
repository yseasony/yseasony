package com.yy.service;

import java.util.List;

import com.yy.model.Role;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;


public interface IRoleSvc extends IBaseService<Role, Long>{
	
	public void save(Role role ,String[] authorityId);
	
	public Page<Role> getPage(Page<Role> page,final List<PropertyFilter> filters);

}