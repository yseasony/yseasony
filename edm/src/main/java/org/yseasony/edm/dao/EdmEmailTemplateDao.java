package org.yseasony.edm.dao;

import org.yseasony.edm.entiy.EdmEmailTemplate;
import org.yseasony.edm.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * User: yseasony
 * Date: 13-10-4
 * Time: 下午2:51
 */
public interface EdmEmailTemplateDao {

    int insert(EdmEmailTemplate edmEmailTemplate);

    Page<EdmEmailTemplate> getEdmEmailTemplatePage(Page<EdmEmailTemplate> edmEmailTemplatePage,
                                                   Map<String, Object> args);

    EdmEmailTemplate selectById(Integer id);

    void update(EdmEmailTemplate edmEmailTemplate);

    List<EdmEmailTemplate> getEdmEmailTemplateList();
}
