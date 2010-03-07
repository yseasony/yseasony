package com.yy.service;

import org.springframework.stereotype.Service;

import com.yy.model.Authority;

@Service
public interface IAuthoritySvc {
	
	public void save(Authority authority) ;
	
	public int getMax(String table);

}