package org.yseasony.test.acg.dao;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yseasony.acg.dao.UserDao;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.utils.Page;
import org.yseasony.test.acg.base.BaseTest;

public class UserDaoTest extends BaseTest{

	@Autowired
	private UserDao userDao;
	
	@Test
	public void insert() {
		User user = new User();
		user.setEmail("aaa");
		user.setLoginName("admin");
		user.setPassword("123456");
		userDao.insert(user);
	}
	
	@Test
	public void get() {
		User user = new User();
		user.setId(4123L);
		user.setEmail("aaa11");
		user.setLoginName("weweew1");
		user.setPassword("qwewqewq11");
		//userDao.insert(user);
		user = userDao.get(4123L);
		user = userDao.get(4123L);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void findUserByLoginName(){
		User user = new User();
		user.setId(4123L);
		user.setEmail("aaa11");
		user.setLoginName("weweew1");
		user.setPassword("qwewqewq11");
		user.setEnabled(true);
		userDao.insert(user);
		user = userDao.findUserByLoginName("weweew1");
		Assert.assertNull(user.getRoleList());
		Assert.assertNotNull(user);
	}
	
	@Test
	public void userPage() {
		Page<User> page = new Page<User>().pageNo(3);
		System.out.println(page.getOffset());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", "1");
		page = userDao.page(page);
		System.out.println(page);
	}
}
