package com.yy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IHibernateDao;
import com.yy.dao.IRoleDao;
import com.yy.exception.MyException;
import com.yy.model.Role;
import com.yy.service.IRoleSvc;

@Service
public class RoleSvcImpl extends BaseServiceImpl<Role, Long> implements IRoleSvc {

	@Autowired
	private IRoleDao roleDao;

	@Override
	@Resource(name = "roleDaoImpl")
	public void setHibernateDao(IHibernateDao<Role, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
	
	@Transactional
	public void save(Role role ,String[] authorityId) {
		try {
			save(role);
			if (authorityId != null) {
				for (String id : authorityId) {
					roleDao.executeSql(
					"INSERT INTO tbl_role_authority(role_id,  authority_id) " +
					"VALUES ('"+role.getId()+ "','"+ Long.valueOf(id) + "');");
				}
			}
		} catch (Exception e) {
			throw new MyException(e);
		}
	}
}
