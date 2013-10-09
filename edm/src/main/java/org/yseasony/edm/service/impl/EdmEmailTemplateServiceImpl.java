package org.yseasony.edm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.edm.dao.EdmEmailTemplateDao;
import org.yseasony.edm.entiy.EdmEmailTemplate;
import org.yseasony.edm.entiy.EdmTask;
import org.yseasony.edm.service.EdmEmailTemplateService;
import org.yseasony.edm.utils.Page;

/**
 * User: yseasony Date: 13-10-4 Time: 下午2:50
 */
@Service
public class EdmEmailTemplateServiceImpl implements EdmEmailTemplateService {

    @Autowired
    private EdmEmailTemplateDao edmEmailTemplateDao;

    @Override
    @Transactional
    public void save(EdmEmailTemplate edmEmailTemplate) {
        if (edmEmailTemplate.getId() == null) {
            edmEmailTemplateDao.insert(edmEmailTemplate);
        } else {
            edmEmailTemplateDao.update(edmEmailTemplate);
        }

    }

    @Override
    public Page<EdmEmailTemplate> getEdmEmailTemplatePage(Page<EdmEmailTemplate> page, Map<String, Object> args) {
        return edmEmailTemplateDao.getEdmEmailTemplatePage(page, args);
    }

    @Override
    public EdmEmailTemplate get(Integer id) {
        return edmEmailTemplateDao.selectById(id);
    }

    @Override
    public List<EdmEmailTemplate> getList() {
        return edmEmailTemplateDao.getEdmEmailTemplateList();
    }
}
