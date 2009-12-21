package com.yy.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.IRoleDao;

@Service
public class RoleSvcImpl {
	
	private IRoleDao roleDao;
	
	@Transactional(readOnly = true)
	public int getMax() {
		return this.roleDao.getMax("tbl_role");
	}

}
