package com.yy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.IUserDao;
import com.yy.dao.impl.HibernateDao;
import com.yy.model.User;
import com.yy.service.IUserSvc;

@Service
public class UserSvcImpl extends BaseServiceImpl<User, Long> implements
		IUserSvc {

	@SuppressWarnings("unused")
	@Autowired
	private IUserDao userDao;

	@Override
	@Resource(name = "userDaoImpl")
	public void setHibernateDao(HibernateDao<User, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
