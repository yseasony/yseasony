package com.yy.service;

import com.yy.model.Role;


public interface IRoleSvc extends IBaseService<Role, Long>{
	
	public void save(Role role ,String[] authorityId);

}