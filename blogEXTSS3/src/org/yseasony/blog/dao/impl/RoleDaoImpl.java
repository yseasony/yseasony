package org.yseasony.blog.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.yseasony.blog.dao.RoleDao;
import org.yseasony.blog.entity.Role;
import org.yseasony.blog.entity.User;


/**
 * 角色对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class RoleDaoImpl extends HibernateDaoImpl<Role, Long> implements RoleDao {

	private static final String QUERY_USER_BY_ROLEID = "select u from User u left join u.roleList r where r.id=?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.IRoleDao#delete(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) {
		Role role = get(id);
		// 查询出拥有该角色的用户,并删除该用户的角色.
		List<User> users = createQuery(QUERY_USER_BY_ROLEID, false,
				role.getId()).list();
		for (User u : users) {
			u.getRoleList().remove(role);
		}
		super.delete(role);
	}
	
	public void insertRoleAuth(Long roleId, Long authId) throws HibernateException {
		String sql = "INSERT INTO tbl_role_authority(role_id,  authority_id) VALUES (?,?)";
		SQLQuery q = super.executeSQLQuery(sql);
		q.setParameter(0, roleId);
		q.setParameter(1, authId);
		q.executeUpdate();
	}
}
