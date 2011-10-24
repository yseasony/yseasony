package org.yseasony.acg.utils.plugin.mybatis;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

public class MyConfiguration extends Configuration {

	protected MapperRegistry mapperRegistry = new PaginationMapperRegistry(this);  
    
    public <T> void addMapper(Class<T> type) {  
        mapperRegistry.addMapper(type);  
    }  
  
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {  
        return mapperRegistry.getMapper(type, sqlSession);  
    }  
  
    @SuppressWarnings("rawtypes")  
    public boolean hasMapper(Class type) {  
        return mapperRegistry.hasMapper(type);  
    }  
}
