package org.yseasony.exam.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yseasony.exam.utils.Page;

public interface BaseDao<T> {

	Page<T> page(@Param("filters") Map<String, ?> filters, Page<T> page);
	
	Page<T> page(Page<T> page);
	
	int count(@Param("filters") Map<String, ?> filters);

	int count();

	void delete(Object id);

	T insert(T t);

	T get(Object id);

	void update(T t);
}