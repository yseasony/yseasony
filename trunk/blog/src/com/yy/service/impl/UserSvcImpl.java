package com.yy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IUserDao;
import com.yy.model.User;
import com.yy.service.IUserSvc;

@Service
public class UserSvcImpl implements IUserSvc {

	@Autowired
	private IUserDao userDao;

	/* (non-Javadoc)
	 * @see com.yy.service.impl.UserSvc#save(com.yy.model.User)
	 */
	@Transactional
	public void save(User user) {
		this.userDao.save(user);
	}
	
	@Transactional(readOnly = true)
	public int getMax() {
		return this.userDao.getMax("tbl_user");
	}
	
	
	@Transactional(readOnly = true)
	public User userExist(String loginname){
		return this.userDao.findUniqueBy("loginname", loginname);
	}
}
