package org.yseasony.exam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yseasony.exam.entity.Role;

public interface RoleDao extends BaseDao<Role> {

	void insertRoleAuth(@Param("roleId") long roleId, @Param("authId") long authId);
	
	void deleteRoleAuth(long id);
	
	List<Role> getAll();
}
