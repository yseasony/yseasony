package com.yy.test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yy.dao.impl.AuthorityDaoImpl;
import com.yy.test.base.SpringTxTestCase;

/**
 * @author YseasonY
 * @version time ：2010-4-28 下午04:58:13
 * 
 */
public class AuthorityDaoTest extends SpringTxTestCase{
	
	@Autowired
	private AuthorityDaoImpl authorityDaoImpl;


	@Before
	public void setUp() {
	}
	
	@Test
	public void test() {
		
		String hql = "from Authority a,Resource r ";
	}
}
