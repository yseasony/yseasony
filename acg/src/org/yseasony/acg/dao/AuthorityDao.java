package org.yseasony.acg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.yseasony.acg.entity.Authority;
import org.yseasony.acg.utils.Page;

public interface AuthorityDao {

	int count(@Param("filters") Map<String, ?> filters);
	
	List<Authority> page(Page<Authority> page);
}
