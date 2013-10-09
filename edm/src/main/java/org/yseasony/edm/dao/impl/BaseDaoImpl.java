package org.yseasony.edm.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.yseasony.edm.utils.SQLBuilder;

/**
 * User: yseasony Date: 13-5-8 Time: 上午11:43
 */
public class BaseDaoImpl extends NamedParameterJdbcDaoSupport {

    private SQLBuilder sqlBuilder;

    @Resource
    public void setSqlBuilder(SQLBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    @Resource
    public void setJdbc(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);

    }

    protected String getDynamicalSql(String key, Map<String, ?> param) {
        return sqlBuilder.getDynamicalSql(key, param);
    }

    protected <T> T queryForObject(String sql, SqlParameterSource sqlParameterSource, Class<T> requiredType) {
        try {
            return getNamedParameterJdbcTemplate().queryForObject(sql, sqlParameterSource, requiredType);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    protected <T> T queryForObject(String sql, String key, Object Object, Class<T> requiredType) {
        try {
            Map<String, Object> args = new HashMap<String, java.lang.Object>();
            args.put(key, Object);
            return getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource(args),
                                                                  new BeanPropertyRowMapper<T>(requiredType));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

}
