package com.yy.service.impl;

import javax.annotation.Resource;

import com.yy.dao.impl.HibernateDao;
import com.yy.model.Authority;
import com.yy.service.IAuthoritySvc;

public class AuthoritySvcImpl extends BaseServiceImpl<Authority, Long> implements IAuthoritySvc{

	@Override
	@Resource(name="AuthorityDaoImpl")
	public void setHibernateDao(HibernateDao<Authority, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
