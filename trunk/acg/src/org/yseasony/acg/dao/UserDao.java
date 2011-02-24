package org.yseasony.acg.dao;

import java.util.List;

import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Page;

public interface UserDao {

	User findUserByLoginName(String loginName);
	
	void insert(User user);
	
	User get(long id);
	
	int count();
	
	List<User> page(Page<User> page);
	
	void delete(int id);
}
