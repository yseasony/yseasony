package org.yseasony.acg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.acg.dao.UserDao;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Page;

@Service
public class UserSvcImpl {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void save(User user) {
		userDao.insert(user);
	}

	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

	@Transactional(readOnly = true)
	public Page<User> userPage(Page<User> page) {
		List<User> list = userDao.page(page);
		page.setResult(list);
		page.setTotalCount(userDao.count());
		return page;
	}

	@Transactional
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			userDao.delete(id);
		}
	}

}
