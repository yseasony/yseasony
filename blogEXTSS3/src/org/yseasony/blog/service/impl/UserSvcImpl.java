package org.yseasony.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.blog.dao.HibernateDao;
import org.yseasony.blog.dao.UserDao;
import org.yseasony.blog.entity.User;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.UserSvc;

@Service
public class UserSvcImpl extends BaseSvcImpl<User, Long> implements
		UserSvc {

	@Autowired
	private UserDao userDao;

	@Override
	@Resource(name = "userDaoImpl")
	public void setHibernateDao(HibernateDao<User, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}

	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.get(id);
	}

	public void saveUser(User entity) {
		try {
			this.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteUser(Long id) {
		if (id == 1) {
			// logger.warn("操作员{}尝试删除超级管理员用户", SpringSecurityUtils
			// .getCurrentUserName());
			throw new MyException("不能删除超级管理员用户");
		}
		this.delete(id);
	}
}
