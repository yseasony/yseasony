package org.yseasony.edm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.yseasony.edm.dao.EdmSendListDao;
import org.yseasony.edm.entiy.EdmSendList;

/**
 * User: yseasony Date: 13-10-5 Time: 下午1:28
 */
@Repository
public class EdmSendListDaoImpl extends BaseDaoImpl implements EdmSendListDao {

    @Override
    public int batchInsert(List<EdmSendList> edmSendLists) {
        final List<EdmSendList> tempEdmSendLists = edmSendLists;
        String sql = "INSERT INTO edm_send_list (task_id, email_id, send_time, status, create_time, email, email_data, is_test, template_id) VALUES (?,?,?,?,?,?,?,?,?)";

        BatchPreparedStatementSetter b = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, tempEdmSendLists.get(i).getTaskId());
                preparedStatement.setLong(2, tempEdmSendLists.get(i).getEmailId());
                preparedStatement.setLong(3, tempEdmSendLists.get(i).getSendTime());
                preparedStatement.setInt(4, tempEdmSendLists.get(i).getStatus());
                preparedStatement.setLong(5, tempEdmSendLists.get(i).getCreateTime());
                preparedStatement.setString(6, tempEdmSendLists.get(i).getEmail());
                preparedStatement.setString(7, tempEdmSendLists.get(i).getEmailData());
                preparedStatement.setInt(8, tempEdmSendLists.get(i).getIsTest());
                preparedStatement.setInt(9, tempEdmSendLists.get(i).getTemplateId());
            }

            @Override
            public int getBatchSize() {
                return tempEdmSendLists.size();
            }
        };

        getNamedParameterJdbcTemplate().getJdbcOperations().batchUpdate(sql, b);
        return b.getBatchSize();
    }

}
