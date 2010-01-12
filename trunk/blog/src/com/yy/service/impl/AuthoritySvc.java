package com.yy.service.impl;

import org.springframework.stereotype.Service;

import com.yy.model.Authority;

@Service
public interface AuthoritySvc {
	
	public void save(Authority authority);

}