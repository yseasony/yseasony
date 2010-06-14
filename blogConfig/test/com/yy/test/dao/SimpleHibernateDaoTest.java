package com.yy.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yy.dao.ISimpleHibernateDao;
import com.yy.dao.impl.SimpleHibernateDao;
import com.yy.model.User;
import com.yy.test.base.SpringTxTestCase;

/**
 * springside-core中{@link SimpleHibernateDao}的测试用例.
 * 
 * @author calvin
 */
public class SimpleHibernateDaoTest extends SpringTxTestCase {

	private static final String DEFAULT_LOGIN_NAME = "admin1";

	private ISimpleHibernateDao<User, Long> dao;

	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
		dao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
	}

	@Test
	@Rollback
	public void crud() {
		User user = new User();
		user.setName("foo");
		user.setLoginname("foo");
		//dao.save(user);
		user.setName("boo");
		//dao.save(user);
		dao.delete(user);
	}

	@Test
	public void findByProperty() {
		List<User> users = dao.findBy("loginname", DEFAULT_LOGIN_NAME);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginname());

		User user = dao.findUniqueBy("loginname", DEFAULT_LOGIN_NAME);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginname());
	}

	@Test
	public void findByHQL() {

		List<User> users = dao.find("from User u where loginName=?", false,
				DEFAULT_LOGIN_NAME);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginname());

		User user = dao.findUnique("from User u where loginName=?", false,
				DEFAULT_LOGIN_NAME);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginname());

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("loginName", DEFAULT_LOGIN_NAME);
		users = dao.find("from User u where loginName=:loginName", values);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginname());

		user = dao.findUnique("from User u where loginName=:loginName", values);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginname());
	}

	@Test
	public void findByCriterion() {
		Criterion c = Restrictions.eq("loginname", DEFAULT_LOGIN_NAME);
		List<User> users = dao.find(c);
		assertEquals(1, users.size());
		assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getLoginname());

		User user = dao.findUnique(c);
		assertEquals(DEFAULT_LOGIN_NAME, user.getLoginname());

		dao.findUnique(c);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBatchUpdate() {
		Map map = new HashMap();
		map.put("ids", new Long[] { 1L, 23L });

		dao.batchExecute(
				"update User u set u.status='disabled' where id in(:ids)", map);
		User u1 = dao.get(1L);
		assertEquals("disabled", u1.getStatus());
		User u3 = dao.get(3L);
		assertEquals("enabled", u3.getStatus());
	}

	@Test
	public void getIdName() {
		assertEquals("id", dao.getIdName());
	}

}
