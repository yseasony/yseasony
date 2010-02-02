package com.yy.dao.impl;

import org.springframework.stereotype.Repository;

import com.yy.dao.IUserDao;
import com.yy.model.User;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Repository
public class UserDaoImpl extends HibernateDao<User, Long> implements IUserDao {

	public User findUniqueBy(String propertyName, String value) {
		return super.findUniqueBy(propertyName, value);
	}
	
}
