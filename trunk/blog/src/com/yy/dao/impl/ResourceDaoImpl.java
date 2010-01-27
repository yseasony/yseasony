package com.yy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yy.dao.IResourceDao;
import com.yy.model.Resource;
import com.yy.utils.Page;
import com.yy.utils.PropertyFilter;

/**
 * 受保护资源对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class ResourceDaoImpl extends HibernateDao<Resource, Long> implements
		IResourceDao {

	public static final String QUERY_BY_RESOURCETYPE_WITH_AUTHORITY = "from Resource r left join fetch r.authorityList WHERE r.resourceType=? ORDER BY r.position ASC";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yy.dao.impl.IResourceDao#getUrlResourceWithAuthorities()
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> getUrlResourceWithAuthorities() {
		Query query = createQuery(QUERY_BY_RESOURCETYPE_WITH_AUTHORITY,false,
				Resource.URL_TYPE);
		return distinct(query).list();
	}

	public void updatePosition(Double position) {
		super.executeSql("update tbl_resource set position = position-1 where position >"+ position + "");
	}
	
	public Page<Resource> getPage(Page<Resource> page,final List<PropertyFilter> filters){
		
		if (filters.size() > 0) {
			String hql = "select new Resource(id,resourceName,description,resourceType,value,position) from Resource "+super.filters(filters)+"order by "+page.getOrderBy()+" "+page.getOrder()+"";
			return super.findPage(page, hql);
			
		}
		
		String hql = "select new Resource(id,resourceName,description,resourceType,value,position) from Resource order by "+page.getOrderBy()+" "+page.getOrder()+"";
		return super.findPage(page, hql);
	}
	
}
