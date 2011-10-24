package org.yseasony.exam.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.exam.dao.AuthorityDao;
import org.yseasony.exam.dao.RoleDao;
import org.yseasony.exam.dao.UserDao;
import org.yseasony.exam.entity.Authority;
import org.yseasony.exam.entity.Role;
import org.yseasony.exam.entity.User;
import org.yseasony.exam.utils.Page;

@Service
public class UserSvcImpl extends BaseSvcImpl {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	private RoleDao roleDao;

	@Transactional
	public void saveUser(User user, Long[] roleIds) {
		if (user.getId() != null) {
			userDao.deleteUserRole(user.getId());
			userDao.update(user);
		} else {
			userDao.insert(user);
		}
		if (roleIds != null) {
			for (Long roleId : roleIds) {
				userDao.insertUserRole(user.getId(), roleId);
			}
		}
	}

	@Transactional
	public void saveAuth(Authority authority) {
		if (authority.getId() != null) {
			authorityDao.update(authority);
		} else {
			authorityDao.insert(authority);
		}
	}

	@Transactional
	public void saveRole(Role role, Long[] authIds) {
		if (role.getId() != null) {
			roleDao.deleteRoleAuth(role.getId());
			roleDao.update(role);
		} else {
			roleDao.insert(role);
		}
		if (authIds != null) {
			for (Long authId : authIds) {
				roleDao.insertRoleAuth(role.getId(), authId);
			}
		}
	}

	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

	@Transactional(readOnly = true)
	public Page<User> getUserPage(Page<User> page, Map<String, Object> filters) {
		return userDao.page(filters, page);
	}

	@Transactional
	public void deleteUser(Long[] userIds) {
		for (Long id : userIds) {
			userDao.deleteUserRole(id);
			userDao.delete(id);
		}
	}

	@Transactional
	public void deleteAuth(Long[] ids) {
		for (Long id : ids) {
			authorityDao.delete(id);
		}
	}

	@Transactional
	public void deleteRole(Long[] ids) {
		for (Long id : ids) {
			roleDao.delete(id);
		}
	}

	@Transactional(readOnly = true)
	public boolean getUserExits(Map<String, ?> filters) {
		return userDao.count(filters) == 1 ? false : true;
	}

	@Transactional(readOnly = true)
	public boolean getAuthExits(Map<String, ?> filters) {
		return authorityDao.count(filters) == 1 ? false : true;
	}

	@Transactional(readOnly = true)
	public boolean getRoleExits(Map<String, ?> filters) {
		return roleDao.count(filters) == 1 ? false : true;
	}

	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.get(id);
	}

	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		return roleDao.get(id);
	}

	@Transactional(readOnly = true)
	public Page<Authority> getAuthPage(Page<Authority> page) {
		return authorityDao.page(page);
	}

	@Transactional(readOnly = true)
	public List<Authority> getAllAuth() {
		return authorityDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<Role> getAllRole() {
		return roleDao.getAll();
	}

	@Transactional(readOnly = true)
	public Page<Role> getRolePage(Page<Role> page) {
		return roleDao.page(page);
	}

}
