package org.yseasony.edm.dao;

import org.yseasony.edm.entiy.EdmTask;
import org.yseasony.edm.utils.Page;

import java.util.Map;

/**
 * User: yseasony
 * Date: 13-10-5
 * Time: 下午12:30
 */
public interface EdmTaskDao {
    Integer insert(EdmTask edmTask);

    Page<EdmTask> getEdmTaskPage(Page<EdmTask> edmTaskPage, Map<String, Object> args);
}
