package com.yy.service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IAuthorityDao;
import com.yy.dao.IUserDao;
import com.yy.model.Authority;
import com.yy.model.User;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class SecurityEntityManager {


	@Autowired
	private IUserDao userDao;
	@Autowired
	private IAuthorityDao authorityDao;

	// -- User Manager --//

	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findUniqueBy("loginName", loginName);
	}

	// -- Authority Manager --//
	@Transactional(readOnly = true)
	public List<Authority> getAllAuthority() {
		return authorityDao.getAll();
	}
}
