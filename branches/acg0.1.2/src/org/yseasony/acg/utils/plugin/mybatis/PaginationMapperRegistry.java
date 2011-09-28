package org.yseasony.acg.utils.plugin.mybatis;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

public class PaginationMapperRegistry extends MapperRegistry {

	public PaginationMapperRegistry(Configuration config) {
		super(config);
	}

	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		if (!hasMapper(type)) {
			throw new BindingException("Type " + type
					+ " is not known to the MapperRegistry.");
		}
		
		try {
			return PaginationMapperProxy.newMapperProxy(type, sqlSession);
		} catch (Exception e) {
			throw new BindingException("Error getting mapper instance. Cause: "
					+ e, e);
		}
	}
}
