package org.yseasony.acg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BaseDao<T> {

	List<T> page(@Param("filters") Map<String, ?> filters,RowBounds rowBounds);
	
	int pageCount(@Param("filters") Map<String, ?> filters);
	
	int count(@Param("filters") Map<String, ?> filters);

	int count();

	void delete(Object id);

	void insert(T t);

	T get(Object id);

	void update(T t);
}
