package org.yseasony.exam.dao;

import java.util.List;

import org.yseasony.exam.entity.Authority;

public interface AuthorityDao extends BaseDao<Authority> {
	
	List<Authority> getAll();
}
