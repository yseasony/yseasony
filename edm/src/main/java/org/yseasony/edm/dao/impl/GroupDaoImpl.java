package org.yseasony.edm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.yseasony.edm.dao.GroupDao;
import org.yseasony.edm.entiy.EdmEmailGroup;

/**
 * User: yseasony Date: 13-10-1 Time: 下午12:45
 */
@Repository
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao {

    @Override
    public int insert(String groupName) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("group_name", groupName);
        String sql = "INSERT INTO edm_email_group(group_name) VALUES (:group_name)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource(args);
        getNamedParameterJdbcTemplate().update(sql, parameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer findByGroupName(String groupName) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("group_name", groupName);
        String sql = "select id from edm_email_group where group_name = :group_name";
        return queryForObject(sql, new MapSqlParameterSource(args), Integer.class);
    }

    @Override
    public List<EdmEmailGroup> getList() {
        String sql = "SELECT id, group_name FROM edm_email_group";
        return getNamedParameterJdbcTemplate().query(sql, new EmptySqlParameterSource(),
                                                     new BeanPropertyRowMapper<EdmEmailGroup>(EdmEmailGroup.class));
    }
}
