package org.yseasony.blog.service;

import java.util.List;

import org.yseasony.blog.entity.Role;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


public interface RoleSvc extends BaseSvc<Role, Long>{
	
	public void save(Role role ,String[] authorityId);
	
	public Page<Role> getPage(Page<Role> page,final List<PropertyFilter> filters);

}