package org.yseasony.acg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yseasony.acg.utils.Page;

public interface BaseDao<T> {

	List<T> page(Page<T> page);

	int count(@Param("filters") Map<String, ?> filters);

	int count();

	void delete(Object id);

	void insert(T t);

	T get(Object id);

	void update(T t);
}
