package org.yseasony.blog.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yseasony.blog.dao.AuthorityDao;
import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.utils.Page;
import org.yseasony.blog.utils.PropertyFilter;


/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class AuthorityDaoImpl extends HibernateDaoImpl<Authority, Long> implements
		AuthorityDao {
	
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
