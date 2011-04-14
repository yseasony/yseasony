package org.yseasony.acg.utils.plugin;



import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.builder.xml.dynamic.DynamicSqlSource;
import org.apache.ibatis.builder.xml.dynamic.TextSqlNode;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.yseasony.acg.utils.PropertiesHelper;
import org.yseasony.acg.utils.sql.dialect.Dialect;


/**
 * 为mybatis提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * 
 * 配置文件内容:
 * <pre>
 * 	&lt;plugins>
 *		&lt;plugin interceptor="cn.org.rapid_framework.ibatis3.plugin.OffsetLimitInterceptor">
 *			&lt;property name="dialectClass" value="cn.org.rapid_framework.jdbc.dialect.MySQLDialect"/>
 *		&lt;/plugin>
 *	&lt;/plugins>
 * </pre>
 *
 */
@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor{
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;
	
	private HashMap<String, Dialect> dialect = new HashMap<String, Dialect>();
	
	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}
	
	@SuppressWarnings("unchecked")
	void processIntercept(final Object[] queryArgs) {
		//获取分页参数
		final RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		if(offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT) {
			MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
			String sessionFactoryId = ms.getConfiguration().getEnvironment().getId();
			
			//准备分页参数
			Object parameter = queryArgs[PARAMETER_INDEX];
			//获取sql
			String sql = ms.getStaticSql(parameter);
			HashMap<String,Object> map = new HashMap<String, Object>();
			if (parameter != null) {
				if (parameter instanceof Map) {
					map.putAll((Map<? extends String, ? extends Object>) parameter);
				} else{
					String valueKey = StringUtils.substringBetween(sql, "#{", "}");
					map.put(valueKey, parameter);
				}
			}
			map.put("sql_offset", offset);
			map.put("sql_limit", limit);
			queryArgs[PARAMETER_INDEX] = map;
			queryArgs[ROWBOUNDS_INDEX] = new RowBounds(0,limit);
			sql = dialect.get(sessionFactoryId).getLimitString(sql, offset, limit);
			DynamicSqlSource ds = new DynamicSqlSource(ms.getConfiguration(), new TextSqlNode(sql));
			MappedStatement newMs = copyFromMappedStatement(ms, ds);
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		}}
	
	//see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
		
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.keyProperty(ms.getKeyProperty());
		
		//setStatementTimeout()
		builder.timeout(ms.getTimeout());
		
		//setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		
		//setStatementResultMap()
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
	    
		//setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String dialectClass = new PropertiesHelper(properties).getRequiredString("dialect");
		String[] args = dialectClass.split("_");
		for (int i = 0; i < args.length; i++) {
			try {
				String[] dialectSFId = args[i].split(",");
				Dialect dialect = (Dialect)Class.forName(dialectSFId[0]).newInstance();
				this.dialect.put(dialectSFId[1]+"Bean", dialect);
			} catch (Exception e) {
				throw new RuntimeException("cannot create dialect instance by dialectClass:"+dialectClass,e);
			} 
			System.out.println(OffsetLimitInterceptor.class.getSimpleName()+".dialect="+dialectClass);
		}
	}

}
