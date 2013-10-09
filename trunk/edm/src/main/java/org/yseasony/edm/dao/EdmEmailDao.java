package org.yseasony.edm.dao;

import org.yseasony.edm.entiy.EdmEmail;
import org.yseasony.edm.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * User: yseasony
 * Date: 13-10-1
 * Time: 下午11:30
 */
public interface EdmEmailDao {
    int batchInsert(List<EdmEmail> edmEmails);

    Page<EdmEmail> getEdmEmailPage(Page<EdmEmail> edmEmailPage, Map<String, Object> args);

    int getTaskCount(Map<String, Object> args);

    EdmEmail getEdmEmailByEmail(String email);

    List<EdmEmail> getTaskList(Map<String, Object> args);
}
