package com.yy.dao;

import com.yy.model.User;

public interface IUserDao{
	
	public User get(Long id);
	
	public User findUniqueBy(String propertyName,String value);

}