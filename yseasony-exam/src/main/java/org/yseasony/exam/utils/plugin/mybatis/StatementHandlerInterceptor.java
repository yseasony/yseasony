package org.yseasony.exam.utils.plugin.mybatis;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.SimpleStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;
import org.yseasony.exam.utils.FieldUtils;
import org.yseasony.exam.utils.PropertiesHelper;
import org.yseasony.exam.utils.sql.dialect.Dialect;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }),
		@Signature(type = StatementHandler.class, method = "parameterize", args = { Statement.class }) })
public class StatementHandlerInterceptor extends
		AbstractStatementHandlerInterceptor implements Interceptor {

	private static Map<String, Dialect> dialects = new HashMap<String, Dialect>(5);
	
	private Object prepare(Invocation invocation) throws Throwable {
		StatementHandler statement = getStatementHandler(invocation);

		if (statement instanceof SimpleStatementHandler
				|| statement instanceof PreparedStatementHandler) {

			RowBounds rowBounds = getRowBounds(statement);

			if (hasBounds(rowBounds)) {
				BoundSql boundSql = statement.getBoundSql();
				String sql = boundSql.getSql();

				String sessionFactoryId = getConfiguration(statement).getEnvironment().getId();
				Dialect dialect = dialects.get(sessionFactoryId);
				if (statement instanceof SimpleStatementHandler) {
					sql = dialect.getLimitString(sql, rowBounds.getOffset(),
							rowBounds.getLimit());
				} else if (statement instanceof PreparedStatementHandler) {
					sql = dialect.getLimitString(sql, rowBounds.getOffset() > 0);
				}
				FieldUtils.setFieldValue(boundSql, "sql", sql);
			}
		}

		return invocation.proceed();
	}

	private Object parameterize(Invocation invocation) throws Throwable {
		Statement statement = (Statement) invocation.getArgs()[0];

		Object rtn = invocation.proceed();

		if (statement instanceof PreparedStatement) {
			PreparedStatement ps = (PreparedStatement) statement;

			StatementHandler statementHandler = getStatementHandler(invocation);
			RowBounds rowBounds = getRowBounds(statementHandler);
			String sessionFactoryId = getConfiguration(statementHandler).getEnvironment().getId();
			Dialect dialect = dialects.get(sessionFactoryId);
			if (hasBounds(rowBounds)) {
				BoundSql boundSql = statementHandler.getBoundSql();
				int parameterSize = boundSql.getParameterMappings().size();
				dialect.setLimitParamters(ps, parameterSize,
						rowBounds.getOffset(), rowBounds.getLimit());
			}
		}
		return rtn;
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Method m = invocation.getMethod();
		if ("prepare".equals(m.getName())) {
			return prepare(invocation);
		} else if ("parameterize".equals(m.getName())) {
			return parameterize(invocation);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String dialectClass = new PropertiesHelper(properties).getRequiredString("dialect");
		String[] args = dialectClass.split("_");
		for (int i = 0; i < args.length; i++) {
			try {
				String[] dialectSFId = args[i].split(",");
				Dialect dialect = (Dialect)Class.forName(dialectSFId[0]).newInstance();
				dialects.put(dialectSFId[1]+"Bean", dialect);
			} catch (Exception e) {
				throw new RuntimeException("cannot create dialect instance by dialectClass:"+dialectClass,e);
			} 
		}
	}

}
