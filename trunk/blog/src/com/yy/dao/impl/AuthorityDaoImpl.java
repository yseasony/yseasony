package com.yy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yy.dao.IAuthorityDao;
import com.yy.model.Authority;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class AuthorityDaoImpl extends HibernateDao<Authority, Long> implements
		IAuthorityDao {
	
	public Page<Authority> getPage(Page<Authority> page,final List<PropertyFilter> filters){
		
		if (filters.size() > 0) {
			String hql = "select new Authority(id,displayName) from Authority "+super.filters(filters)+"order by "+page.getOrderBy()+" "+page.getOrder()+"";
			return super.findPage(page, hql);
		}
		
		String hql = "select new Authority(id,displayName) from Authority order by "+page.getOrderBy()+" "+page.getOrder()+"";
		
		return super.findPage(page, hql);
	}
}
