package com.yy.service;

import com.yy.model.User;

public interface IUserSvc {

	public void save(User user);

	public int getMax(String table);

	public User exist(String column, String loginname);

}