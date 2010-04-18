package com.yy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.IAuthorityDao;
import com.yy.dao.IHibernateDao;
import com.yy.model.Authority;
import com.yy.service.IAuthoritySvc;

@Service
public class AuthoritySvcImpl extends BaseServiceImpl<Authority, Long> implements IAuthoritySvc{

	@SuppressWarnings("unused")
	@Autowired
	private IAuthorityDao authorityDao;
	
	@Override
	@Resource(name="authorityDaoImpl")
	public void setHibernateDao(IHibernateDao<Authority, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
