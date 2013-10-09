package org.yseasony.edm.service;

import org.yseasony.edm.entiy.EdmEmailTemplate;
import org.yseasony.edm.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * User: yseasony
 * Date: 13-10-4
 * Time: 下午2:50
 */
public interface EdmEmailTemplateService {

    void save(EdmEmailTemplate edmEmailTemplate);

    Page<EdmEmailTemplate> getEdmEmailTemplatePage(Page<EdmEmailTemplate> page, Map<String, Object> args);

    EdmEmailTemplate get(Integer id);

    List<EdmEmailTemplate> getList();
}
