package org.yseasony.acg.utils.sql.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDialect implements Dialect {

	@Override
	public void setLimitParamters(PreparedStatement ps, int parameterSize,
			int offset, int limit) throws SQLException {
		if (offset <= 0) {
			ps.setInt(parameterSize + 1, limit);
		} else {
			ps.setInt(parameterSize + 1, offset);
			ps.setInt(parameterSize + 2, limit);
		}
	}

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		if (hasOffset) {
			sql = sql + " LIMIT ?,?";
		} else {
			sql = sql + " LIMIT ?";
		}
		return sql;
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		throw new RuntimeException("un implements");
	}

}
