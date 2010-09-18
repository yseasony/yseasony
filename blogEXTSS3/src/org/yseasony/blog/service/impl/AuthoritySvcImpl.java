package org.yseasony.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.blog.dao.AuthorityDao;
import org.yseasony.blog.dao.HibernateDao;
import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.service.AuthoritySvc;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


@Service
public class AuthoritySvcImpl extends BaseSvcImpl<Authority, Long>
		implements AuthoritySvc {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	@Resource(name = "authorityDaoImpl")
	public void setHibernateDao(HibernateDao<Authority, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
	
	@Transactional(readOnly=true)
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters) {
		return authorityDao.getPage(page, filters);
	}
}
