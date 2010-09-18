package org.yseasony.blog.dao;


import java.util.List;

import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


public interface AuthorityDao extends HibernateDao<Authority, Long> {

	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters);
}