package org.yseasony.acg.dao;

import org.yseasony.acg.entity.User;

public interface UserDao extends BaseDao<User>{

	User findUserByLoginName(String loginName);
	
}
