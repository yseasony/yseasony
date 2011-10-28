package org.yseasony.acg.dao;

import java.util.List;

import org.yseasony.acg.entity.Authority;

public interface AuthorityDao extends BaseDao<Authority> {
	
	List<Authority> getAll();
}
