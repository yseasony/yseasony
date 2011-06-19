package org.yseasony.acg.utils.plugin.mybatis;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.util.FieldUtils;
import org.yseasony.acg.utils.sql.dialect.Dialect;

public abstract class AbstractStatementHandlerInterceptor implements
		Interceptor, InitializingBean {

	private Class<Dialect> dialectClass;

	public void setDialectClass(Class<Dialect> dialectClass) {
		this.dialectClass = dialectClass;
	}

	protected Dialect dialect;

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public void afterPropertiesSet() throws Exception {
		setDialect(dialectClass.newInstance());
	}

	protected StatementHandler getStatementHandler(Invocation invocation) {
		StatementHandler statement = (StatementHandler) invocation.getTarget();
		if (statement instanceof RoutingStatementHandler) {
			try {
				statement = (StatementHandler) FieldUtils.getFieldValue(
						statement, "delegate");
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return statement;
	}

	protected RowBounds getRowBounds(StatementHandler statement) {
		try {
			return (RowBounds) FieldUtils.getFieldValue(statement, "rowBounds");
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	protected Configuration getConfiguration(StatementHandler statement) {
		try {
			return (Configuration) FieldUtils.getFieldValue(statement, "configuration");
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected boolean hasBounds(RowBounds rowBounds) {
		return (rowBounds != null && rowBounds.getLimit() > 0 && rowBounds
				.getLimit() < RowBounds.NO_ROW_LIMIT);
	}

}
