package com.yy.service;

import com.yy.model.User;

public interface IUserSvc extends IBaseService<User, Long>{

	public User exist(String column, String loginname);

}