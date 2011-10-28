package org.yseasony.exam.dao;




import org.apache.ibatis.annotations.Param;
import org.yseasony.exam.entity.User;

public interface UserDao extends BaseDao<User>{

	User findUserByLoginName(String loginName);
	
	void deleteUserRole(long id);
	
	void insertUserRole(@Param("userId") long userId, @Param("roleId") long roleId);
	
}
