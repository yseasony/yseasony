package org.yseasony.edm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.yseasony.edm.dao.EdmEmailDao;
import org.yseasony.edm.entiy.EdmEmail;
import org.yseasony.edm.utils.Page;

/**
 * User: yseasony Date: 13-10-1 Time: 下午11:30
 */
@Repository
public class EdmEmailDaoImpl extends BaseDaoImpl implements EdmEmailDao {

    @Override
    public int batchInsert(List<EdmEmail> edmEmails) {
        final List<EdmEmail> tempEdmEmails = edmEmails;
        String sql = "insert into edm_email(email,name,phone,sex,group_id,create_time) values(?,?,?,?,?,?)";

        BatchPreparedStatementSetter b = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, tempEdmEmails.get(i).getEmail());
                preparedStatement.setString(2, tempEdmEmails.get(i).getName());
                preparedStatement.setString(3, tempEdmEmails.get(i).getPhone());
                preparedStatement.setInt(4, tempEdmEmails.get(i).getSex());
                preparedStatement.setInt(5, tempEdmEmails.get(i).getGroupId());
                preparedStatement.setLong(6, tempEdmEmails.get(i).getCreateTime());
            }

            @Override
            public int getBatchSize() {
                return tempEdmEmails.size();
            }
        };

        getNamedParameterJdbcTemplate().getJdbcOperations().batchUpdate(sql, b);
        return b.getBatchSize();
    }

    @Override
    public Page<EdmEmail> getEdmEmailPage(Page<EdmEmail> edmEmailPage, Map<String, Object> args) {
        args.put("offset", edmEmailPage.getOffset());
        args.put("limit", edmEmailPage.getPageSize());
        edmEmailPage.setResult(getEdmEmail(args));
        edmEmailPage.setTotalCount(getEdmEmailCount(args));
        return edmEmailPage;
    }

    private List<EdmEmail> getEdmEmail(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = "SELECT * FROM edm_email order by create_time desc limit :offset,:limit";
        return getNamedParameterJdbcTemplate().query(sql, parameterSource,
                                                     new BeanPropertyRowMapper<EdmEmail>(EdmEmail.class));
    }

    private int getEdmEmailCount(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = "SELECT count(*) FROM edm_email";
        return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public EdmEmail getEdmEmailByEmail(String email) {
        String sql = "SELECT id, email FROM edm_email where email = :email";
        return queryForObject(sql, "email", email, EdmEmail.class);
    }

    @Override
    public int getTaskCount(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = getDynamicalSql("edm_email.getTaskCount", args);
        return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, Integer.class);
    }

    @Override
    public List<EdmEmail> getTaskList(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = getDynamicalSql("edm_email.getTaskList", args);
        return getNamedParameterJdbcTemplate().query(sql, parameterSource,
                                                     new BeanPropertyRowMapper<EdmEmail>(EdmEmail.class));
    }

}
