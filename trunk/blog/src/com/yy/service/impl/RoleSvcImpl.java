package com.yy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IRoleDao;
import com.yy.dao.impl.HibernateDao;
import com.yy.model.Role;

@Service
public class RoleSvcImpl extends BaseServiceImpl<Role, Long> {

	@SuppressWarnings("unused")
	@Autowired
	private IRoleDao roleDao;

	@Transactional(readOnly = true)
	public int getMax() {
		return this.getMax("tbl_role");
	}

	@Override
	@Resource(name = "roleDaoImpl")
	public void setHibernateDao(HibernateDao<Role, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
