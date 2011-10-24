package org.yseasony.exam.utils.plugin.mybatis;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;
import org.yseasony.exam.utils.FieldUtils;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class ResultSetHandlerInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		ResultSetHandler resultSet = (ResultSetHandler) invocation.getTarget();

		// 不用浪费性能做属性存在判断
		RowBounds rowBounds = (RowBounds) FieldUtils.getFieldValue(resultSet,
				"rowBounds");

		if (rowBounds.getLimit() > 0
				&& rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
			// 强制不允许游标分页
			FieldUtils.setFieldValue(resultSet, "rowBounds", RowBounds.DEFAULT);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
