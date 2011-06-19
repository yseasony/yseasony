package org.yseasony.acg.dao;



import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Pagination;

public interface UserDao extends BaseDao<User>{

	User findUserByLoginName(String loginName);
	
	void deleteUserRole(long id);
	
	void insertUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
	
	Pagination<User> page(@Param("filters") Map<String, ?> filters, Pagination<User> page);
}
