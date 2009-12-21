package com.yy.service;

import com.yy.model.User;

public interface IUserSvc {

	public abstract void save(User user);
	
	public int getMax();
	
	public User userExist(String loginname);

}