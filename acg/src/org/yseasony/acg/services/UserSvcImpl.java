package org.yseasony.acg.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.acg.dao.AuthorityDao;
import org.yseasony.acg.dao.RoleDao;
import org.yseasony.acg.dao.UserDao;
import org.yseasony.acg.entity.Authority;
import org.yseasony.acg.entity.Role;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Page;

@Service
public class UserSvcImpl extends BaseSvcImpl{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	private RoleDao roleDao;

	@Transactional
	public void saveUser(User user) {
		if (user.getId() != null) {
			userDao.update(user);
		} else {
			userDao.insert(user);
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
	public void saveRole(Role role) {
		if (role.getId() != null) {
			roleDao.update(role);
		} else {
			roleDao.insert(role);
		}
	}
	
	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

	@Transactional(readOnly = true)
	public Page<User> getUserPage(Page<User> page) {
		List<User> list = userDao.page(page);
		page.setResult(list);
		page.setTotalCount(userDao.count(null));
		return page;
	}

	@Transactional
	public void deleteUser(Integer[] ids) {
		for (Integer id : ids) {
			userDao.delete(id);
		}
	}

	@Transactional
	public void deleteAuth(Integer[] ids) {
		for (Integer id : ids) {
			authorityDao.delete(id);
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
	public User getUser(Long id) {
		return userDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Authority> getAuthPage(Page<Authority> page) {
		return getPage(authorityDao, page);
	}
	
	@Transactional(readOnly = true)
	public Page<Role> getRolePage(Page<Role> page) {
		return getPage(roleDao, page);
	}

}
