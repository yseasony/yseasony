package com.yy.test.dao;

import java.math.BigInteger;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yy.test.base.SpringTxTestCase;

public class UserDaoTest extends SpringTxTestCase{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() {
	}
	
	@Transactional
	@Test
	public void getMax(){
		
		String sql = "select count(*)+1 from tbl_user";
		
		BigInteger  a = (BigInteger) this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
		
		System.out.println(a);
	}
	

}
