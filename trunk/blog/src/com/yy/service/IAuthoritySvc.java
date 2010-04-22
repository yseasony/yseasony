package com.yy.service;

import org.springframework.stereotype.Service;

import com.yy.model.Authority;

@Service
public interface IAuthoritySvc extends IBaseService<Authority, Long>{
	
	public void save(Authority authority, String[] resourceId);

}