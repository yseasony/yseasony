package org.yseasony.blog.dao;

import org.yseasony.blog.entity.User;


public interface UserDao{
	
	public User get(Long id);
	
	public User findUniqueBy(String propertyName,String value);

}