package com.yy.test.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.impl.ResourceDaoImpl;
import com.yy.model.Resource;
import com.yy.service.impl.ServiceException;
import com.yy.test.base.SpringTxTestCase;

public class ResourceDao extends SpringTxTestCase{

	@Autowired
	private ResourceDaoImpl resourceDao;

	@Before
	public void setUp() {
	}
	
	@Transactional
	@Test
	public void insert(){
		
		int j = 0;
		try {
			for (int i = 0; i < 100000; i++) {
				Resource resource = new Resource();
				resource.setPosition(i);
				resource.setResourceName("test"+i);
				resource.setResourceType("url");
				resource.setValue("test"+i);
				this.resourceDao.save(resource);
				j++;
				if (j == 1000) {
					System.out.println("commit");
					j = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("失败");
		}
		
	}
	
	
	@Transactional
	@Test
	public void delete(){
		System.out.println(new Date(System.currentTimeMillis()));
		this.resourceDao.delete(987153L);
		this.resourceDao.getSessionFactory().getCurrentSession().createSQLQuery("update tbl_resource set position = position-1 where position >3").executeUpdate();
		System.out.println(new Date(System.currentTimeMillis()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void select(){
		
		String hql = "SELECT new Resource(id,value) FROM Resource";
		Query query = resourceDao.getSession().createQuery(hql);
		List<Resource> list = query.list();
		System.out.println(list.get(0).getId());
		
	}
	                     
}
