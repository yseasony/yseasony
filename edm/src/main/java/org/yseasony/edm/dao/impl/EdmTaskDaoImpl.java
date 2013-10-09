package org.yseasony.edm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.yseasony.edm.dao.EdmTaskDao;
import org.yseasony.edm.entiy.EdmTask;
import org.yseasony.edm.utils.Page;

/**
 * User: yseasony Date: 13-10-5 Time: 下午12:29
 */
@Repository
public class EdmTaskDaoImpl extends BaseDaoImpl implements EdmTaskDao {

    @Override
    public Integer insert(EdmTask edmTask) {
        String sql = "INSERT INTO edm_task (name, task_start_time, is_test, test_num, task_send_num, send_success_num, send_conditions, template_id, create_time) "
                     + "VALUES (:name, :taskStartTime, :isTest, :testNum, :taskSendNum, :sendSuccessNum, :sendConditions, :templateId, :createTime)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(edmTask);
        getNamedParameterJdbcTemplate().update(sql, parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Page<EdmTask> getEdmTaskPage(Page<EdmTask> edmTaskPage, Map<String, Object> args) {
        args.put("offset", edmTaskPage.getOffset());
        args.put("limit", edmTaskPage.getPageSize());
        edmTaskPage.setResult(getEdmTaskList(args));
        edmTaskPage.setTotalCount(getEdmTaskCount(args));
        return edmTaskPage;
    }

    public List<EdmTask> getEdmTaskList(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = "SELECT * FROM edm_task order by create_time desc";
        return getNamedParameterJdbcTemplate().query(sql, parameterSource,
                                                     new BeanPropertyRowMapper<EdmTask>(EdmTask.class));
    }

    private int getEdmTaskCount(Map<String, Object> args) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(args);
        String sql = "SELECT count(*) FROM edm_task";
        return getNamedParameterJdbcTemplate().queryForObject(sql, parameterSource, Integer.class);
    }

}
