package org.yseasony.blog.service;

import java.util.List;

import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;

public interface AuthoritySvc extends BaseSvc<Authority, Long>{
	
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters);

}