package org.yseasony.edm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.yseasony.edm.dao.EdmEmailTemplateDao;
import org.yseasony.edm.entiy.EdmEmailTemplate;
import org.yseasony.edm.utils.Page;

/**
 * User: yseasony Date: 13-10-4 Time: 下午2:51
 */
@Repository
public class EdmEmailTemplateDaoImpl extends BaseDaoImpl implements EdmEmailTemplateDao {

    @Override
    public int insert(EdmEmailTemplate edmEmailTemplate) {
        String sql = "INSERT INTO edm_email_template (name, email_title, email_html, email_parameter, create_time, from_name, from_email) "
                     + "VALUES (:name,:emailTitle,:emailHtml,:emailParameter,:createTime, :fromName, :fromEmail)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(edmEmailTemplate);
        getNamedParameterJdbcTemplate().update(sql, parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public EdmEmailTemplate selectById(Integer id) {
        String sql = "SELECT * FROM edm_email_template WHERE id = :id";
        return queryForObject(sql, "id", id, EdmEmailTemplate.class);
    }

    @Override
    public void update(EdmEmailTemplate edmEmailTemplate) {
        String sql = "UPDATE edm_email_template SET name = :name, email_title = :emailTitle, email_html = :emailHtml,email_parameter = :emailParameter, from_name = :fromName, from_email = :fromEmail"
                     + " WHERE id = :id";
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(edmEmailTemplate);
        getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    @Override
    public Page<EdmEmailTemplate> getEdmEmailTemplatePage(Page<EdmEmailTemplate> edmEmailTemplatePage,
                                                          Map<String, Object> args) {
        args.put("offset", edmEmailTemplatePage.getOffset());
        args.put("limit", edmEmailTemplatePage.getPageSize());
        edmEmailTemplatePage.setResult(getEdmEmailTemplate(args));
        edmEmailTemplatePage.setTotalCount(getEdmEmailTemplateCount(args));
        return edmEmailTemplatePage;
    }

    private List<EdmEmailTemplate> getEdmEmailTemplate(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = "SELECT * FROM edm_email_template order by create_time desc limit :offset,:limit";
        return getNamedParameterJdbcTemplate().query(sql,
                                                     parameterSource,
                                                     new BeanPropertyRowMapper<EdmEmailTemplate>(EdmEmailTemplate.class));
    }

    @Override
    public List<EdmEmailTemplate> getEdmEmailTemplateList() {
        String sql = "SELECT * FROM edm_email_template order by create_time desc";
        return getNamedParameterJdbcTemplate().query(sql,
                                                     new EmptySqlParameterSource(),
                                                     new BeanPropertyRowMapper<EdmEmailTemplate>(EdmEmailTemplate.class));
    }

    private int getEdmEmailTemplateCount(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = "SELECT count(*) FROM edm_email_template";
        return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, Integer.class);
    }
}
