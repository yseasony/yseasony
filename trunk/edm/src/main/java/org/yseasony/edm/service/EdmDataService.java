package org.yseasony.edm.service;

import org.yseasony.edm.entiy.EdmEmail;
import org.yseasony.edm.entiy.EdmEmailGroup;
import org.yseasony.edm.entiy.EdmTask;
import org.yseasony.edm.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * User: yseasony
 * Date: 13-10-1
 * Time: 下午12:40
 */
public interface EdmDataService {
    int importing(String groupName, String data);

    Page<EdmEmail> getEdmEmailPage(Page<EdmEmail> edmEmailPage, Map<String, Object> args);

    int getTaskCount(Map<String, Object> args);

    List<EdmEmailGroup> getEdmEmailGroupList();

    void saveTask(Map<String, Object> args);

    void saveTestTask(String email, Integer edmEmailTemplateId);

    Page<EdmTask> getTaskPage(Page<EdmTask> page, Map<String, Object> args);
}
