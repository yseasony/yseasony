package com.yy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IAuthorityDao;
import com.yy.dao.IHibernateDao;
import com.yy.exception.MyException;
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

	@Transactional
	public void save(Authority authority, String[] resourceId) {
		try {
			save(authority);
			if (resourceId != null) {
				for (String id : resourceId) {
					authorityDao.executeSql(
					"INSERT INTO tbl_resource_authority(resource_id,  authority_id) " +
					"VALUES ("+ Long.valueOf(id)+ ","+ authority.getId() + ");");
				}
			}
		} catch (Exception e) {
			throw new MyException(e);
		}

	}
}
