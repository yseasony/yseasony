package com.yy.test.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.impl.ResourceDaoImpl;
import com.yy.exception.MyException;
import com.yy.model.Resource;
import com.yy.test.base.SpringTxTestCase;
import com.yy.utils.Page;

public class ResourceDao extends SpringTxTestCase{

	@Autowired
	private ResourceDaoImpl resourceDao;

	@Before
	public void setUp() {
	}
	
	@Transactional
	@Test
	public void insert(){
		long b = System.currentTimeMillis();
		int j = 0;
		try {
			for (int i = 0; i < 50000; i++) {
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
			throw new MyException("失败");
		}
		System.out.println(System.currentTimeMillis()-b);
	}
	
	@Transactional
	@Test
	public void batchInsert() {
		long b = System.currentTimeMillis();
		List<Resource> list = new ArrayList<Resource>();
		for (int i = 0; i < 50000; i++) {
			Resource resource = new Resource();
			resource.setPosition(i);
			resource.setResourceName("test"+i);
			resource.setResourceType("url");
			resource.setValue("test"+i);
			list.add(resource);
		}
		resourceDao.batchInsert(list,3000);
		System.out.println(System.currentTimeMillis()-b);
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
	
	
	@Test
	public void page(){
		
		Page<Resource> page = new Page<Resource>(1, 1, "", "");
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value", "q");
		
		String hql = "select new Resource(id,value) from Resource where value =:value";
		resourceDao.findPage(page, hql, map);
		
	}
	                     
}
