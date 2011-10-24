package org.yseasony.exam.utils.sql.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 类似hibernate的Dialect,但只精简出分页部分
 * 
 * @author badqiu
 */
public interface Dialect {
	public void setLimitParamters(PreparedStatement ps, int parameterSize,
			int offset, int limit) throws SQLException;

	public String getLimitString(String sql, boolean hasOffset);

	public String getLimitString(String sql, int offset, int limit);
}
