package org.yseasony.blog.dao.impl;

import org.springframework.stereotype.Repository;
import org.yseasony.blog.dao.UserDao;
import org.yseasony.blog.entity.User;


/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Repository
public class UserDaoImpl extends HibernateDaoImpl<User, Long> implements UserDao {

	public User findUniqueBy(String propertyName, String value) {
		return findUniqueBy(propertyName, value);
	}
	
}
