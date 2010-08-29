package com.yy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IAuthorityDao;
import com.yy.dao.IHibernateDao;
import com.yy.lang.utils.Page;
import com.yy.lang.utils.PropertyFilter;
import com.yy.model.Authority;
import com.yy.service.IAuthoritySvc;

@Service
public class AuthoritySvcImpl extends BaseServiceImpl<Authority, Long>
		implements IAuthoritySvc {

	@Autowired
	private IAuthorityDao authorityDao;

	@Override
	@Resource(name = "authorityDaoImpl")
	public void setHibernateDao(IHibernateDao<Authority, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
	
	@Transactional(readOnly=true)
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters) {
		return authorityDao.getPage(page, filters);
	}
}
