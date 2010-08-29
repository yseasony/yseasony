package com.yy.dao;


import java.util.List;

import com.yy.lang.utils.Page;
import com.yy.lang.utils.PropertyFilter;
import com.yy.model.Authority;

public interface IAuthorityDao extends IHibernateDao<Authority, Long> {

	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters);
}