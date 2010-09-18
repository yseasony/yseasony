package org.yseasony.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.blog.dao.HibernateDao;
import org.yseasony.blog.dao.RoleDao;
import org.yseasony.blog.entity.Role;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.RoleSvc;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


@Service
public class RoleSvcImpl extends BaseSvcImpl<Role, Long> implements RoleSvc {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Resource(name = "roleDaoImpl")
	public void setHibernateDao(HibernateDao<Role, Long> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
	
	@Transactional
	public void save(Role role ,String[] authorityId) {
		try {
			save(role);
			if (authorityId != null) {
				for (String id : authorityId) {
					roleDao.insertRoleAuth(role.getId(), Long.valueOf(id));
				}
			}
		} catch (Exception e) {
			throw new MyException(e);
		}
	}
	
	@Transactional(readOnly=true)
	public Page<Role> getPage(Page<Role> page,final List<PropertyFilter> filters) {
		return roleDao.findPage(page, filters);
	}
}
