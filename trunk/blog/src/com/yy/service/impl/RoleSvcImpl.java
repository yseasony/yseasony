package com.yy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IHibernateDao;
import com.yy.model.Role;

@Service
public class RoleSvcImpl extends BaseServiceImpl<Role, Long> {

//	@SuppressWarnings("unused")
//	@Autowired
//	private IRoleDao roleDao;

	@Transactional(readOnly = true)
	public int getMax() {
		return this.getMax("tbl_role");
	}

	@Override
	@Resource(name = "roleDaoImpl")
	public void setHibernateDao(IHibernateDao<Role, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
