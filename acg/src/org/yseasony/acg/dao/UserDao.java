package org.yseasony.acg.dao;



import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Page;

public interface UserDao extends BaseDao<User>{

	User findUserByLoginName(String loginName);
	
	void deleteUserRole(long id);
	
	void insertUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
	
	Page<User> page(@Param("filters") Map<String, ?> filters, Page<User> page);
	
	Page<User> page(Page<User> page);
}
