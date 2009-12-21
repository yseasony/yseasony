package com.yy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yy.dao.IRoleDao;
import com.yy.model.Role;
import com.yy.model.User;

/**
 * 角色对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class RoleDaoImpl extends HibernateDao<Role, Long> implements IRoleDao {
	
	private static final String QUERY_USER_BY_ROLEID = "select u from User u left join u.roleList r where r.id=?";

	/* (non-Javadoc)
	 * @see com.yy.dao.impl.IRoleDao#delete(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) {
		Role role = get(id);
		//查询出拥有该角色的用户,并删除该用户的角色.
		List<User> users = createQuery(QUERY_USER_BY_ROLEID, role.getId()).list();
		for (User u : users) {
			u.getRoleList().remove(role);
		}
		super.delete(role);
	}
}
