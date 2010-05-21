package com.yy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yy.model.Authority;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

@Service
public interface IAuthoritySvc extends IBaseService<Authority, Long>{
	
	public void save(Authority authority, String[] resourceId);
	
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters);

	public List<Authority> getAllAuthority();
}