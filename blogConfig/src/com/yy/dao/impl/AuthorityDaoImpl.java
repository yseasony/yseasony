package com.yy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yy.dao.IAuthorityDao;
import com.yy.lang.utils.Page;
import com.yy.lang.utils.PropertyFilter;
import com.yy.model.Authority;

/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class AuthorityDaoImpl extends HibernateDao<Authority, Long> implements
		IAuthorityDao {
	
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters){
		String hql = null ;
		if (filters.size() > 0) {
			hql = "select new Authority(id,displayName) from Authority "+filters(filters)+"order by "+page.getOrderBy()+" "+page.getOrder()+"";
		} else {
			hql = "select new Authority(id,displayName) from Authority order by "+page.getOrderBy()+" "+page.getOrder()+"";
		}
		return super.findPage(page, hql);
	}
}
