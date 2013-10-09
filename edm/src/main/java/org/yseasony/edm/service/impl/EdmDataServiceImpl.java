package org.yseasony.edm.service.impl;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.edm.dao.EdmEmailDao;
import org.yseasony.edm.dao.EdmSendListDao;
import org.yseasony.edm.dao.EdmTaskDao;
import org.yseasony.edm.dao.GroupDao;
import org.yseasony.edm.entiy.EdmEmail;
import org.yseasony.edm.entiy.EdmEmailGroup;
import org.yseasony.edm.entiy.EdmSendList;
import org.yseasony.edm.entiy.EdmTask;
import org.yseasony.edm.service.EdmDataService;
import org.yseasony.edm.utils.DateUtils;
import org.yseasony.edm.utils.Page;
import org.yseasony.edm.utils.Utils;

import com.alibaba.fastjson.JSON;

/**
 * User: yseasony Date: 13-10-1 Time: 下午12:40
 */
@Service
public class EdmDataServiceImpl implements EdmDataService {

    @Autowired
    private GroupDao       groupDao;

    @Autowired
    private EdmEmailDao    edmEmailDao;

    @Autowired
    private EdmTaskDao     edmTaskDao;

    @Autowired
    private EdmSendListDao edmSendListDao;

    @Override
    public Page<EdmEmail> getEdmEmailPage(Page<EdmEmail> edmEmailPage, Map<String, Object> args) {
        return edmEmailDao.getEdmEmailPage(edmEmailPage, args);
    }

    @Transactional
    @Override
    public void importing(String groupName, String data) {
        Integer groupId = null;
        if (StringUtils.isNotBlank(groupName)) {
            groupId = groupDao.findByGroupName(groupName);
            if (groupId == null) {
                groupId = groupDao.insert(groupName);
            }
        }

        List<EdmEmail> edmEmails = new ArrayList<EdmEmail>();
        Set<String> duplicateEmail = new HashSet<String>();
        String[] rows = StringUtils.splitByWholeSeparatorPreserveAllTokens(data, "\n");
        for (String row : rows) {
            String values[] = StringUtils.splitByWholeSeparatorPreserveAllTokens(row, "|");
            String email = null;
            EdmEmail edmEmail = new EdmEmail();
            for (int i = 0; i < values.length; i++) {
                if (i == 0) {
                    email = StringUtils.trim(values[i]);
                    if (StringUtils.isBlank(email)) {
                        continue;
                    } else {
                        EdmEmail edmEmail1 = edmEmailDao.getEdmEmailByEmail(email);
                        if (edmEmail1 != null) {
                            continue;
                        }
                    }

                    edmEmail.setEmail(email);
                }

                if (i == 1) {
                    edmEmail.setName(StringUtils.trim(values[i]));
                }

                if (i == 2) {
                    if (StringUtils.isNotBlank(values[i])) {
                        edmEmail.setSex(Integer.valueOf(StringUtils.trim(values[i])));
                    }

                }

                if (i == 3) {
                    edmEmail.setPhone(StringUtils.trim(values[i]));
                }

            }

            edmEmail.setCreateTime(DateUtils.date2Long(new Date()));
            edmEmail.setGroupId(groupId);
            if (StringUtils.isNotBlank(edmEmail.getEmail()) && !duplicateEmail.contains(edmEmail.getEmail())) {
                edmEmails.add(edmEmail);
                duplicateEmail.add(edmEmail.getEmail());
            }

        }
        edmEmailDao.batchInsert(edmEmails);
    }

    @Override
    public int getTaskCount(Map<String, Object> args) {
        return edmEmailDao.getTaskCount(args);
    }

    @Override
    public List<EdmEmailGroup> getEdmEmailGroupList() {
        return groupDao.getList();
    }

    @Transactional
    @Override
    public void saveTask(Map<String, Object> args) {
        EdmTask edmTask = new EdmTask();
        edmTask.setName(args.get("groupName").toString());
        args.remove("groupName");
        edmTask.setTaskStartTime((Long) args.get("taskStartTime"));
        args.remove("taskStartTime");
        edmTask.setTemplateId(Integer.valueOf(args.get("templateId").toString()));
        args.remove("templateId");

        edmTask.setIsTest(0);
        edmTask.setTestNum(0);

        String json = JSON.toJSONString(args);
        edmTask.setSendConditions(json);

        List<EdmEmail> edmEmails = edmEmailDao.getTaskList(args);
        edmTask.setTaskSendNum(edmEmails.size());
        edmTask.setSendSuccessNum(0);

        edmTask.setCreateTime(DateUtils.date2Long());

        Integer pk = edmTaskDao.insert(edmTask);

        List<EdmSendList> edmSendLists = new ArrayList<EdmSendList>();
        for (EdmEmail edmEmail : edmEmails) {
            EdmSendList edmSendList = new EdmSendList();
            edmSendList.setTaskId(pk);
            edmSendList.setEmailId(edmEmail.getId());
            edmSendList.setSendTime(edmTask.getTaskStartTime());
            edmSendList.setStatus(0);
            edmSendList.setCreateTime(DateUtils.date2Long());
            edmSendList.setEmailData(JSON.toJSONString(edmEmail));
            edmSendList.setEmail(edmEmail.getEmail());
            edmSendList.setIsTest(0);
            edmSendList.setTemplateId(edmTask.getTemplateId());
            edmSendLists.add(edmSendList);
        }

        edmSendListDao.batchInsert(edmSendLists);

    }

    @Transactional
    @Override
    public void saveTestTask(String email, Integer edmEmailTemplateId) {
        String[] emails = StringUtils.splitByWholeSeparatorPreserveAllTokens(email, Utils.LF);

        EdmTask edmTask = new EdmTask();
        edmTask.setName("发送测试" + DateUtils.date2Long());
        edmTask.setTaskStartTime(DateUtils.date2Long());
        edmTask.setTemplateId(edmEmailTemplateId);
        edmTask.setIsTest(1);
        edmTask.setTestNum(emails.length);
        edmTask.setSendConditions("");
        edmTask.setTaskSendNum(emails.length);
        edmTask.setSendSuccessNum(0);
        edmTask.setCreateTime(DateUtils.date2Long());

        Integer pk = edmTaskDao.insert(edmTask);

        List<EdmSendList> edmSendLists = new ArrayList<EdmSendList>();
        for (String email1 : emails) {
            EdmSendList edmSendList = new EdmSendList();
            edmSendList.setTaskId(pk);
            edmSendList.setEmailId(0L);
            edmSendList.setSendTime(0L);
            edmSendList.setStatus(0);
            edmSendList.setCreateTime(DateUtils.date2Long());
            edmSendList.setEmailData("");
            edmSendList.setEmail(email1);
            edmSendList.setIsTest(1);
            edmSendList.setTemplateId(edmEmailTemplateId);
            edmSendLists.add(edmSendList);
        }

        edmSendListDao.batchInsert(edmSendLists);
    }

    @Override
    public Page<EdmTask> getTaskPage(Page<EdmTask> page, Map<String, Object> args) {
        return edmTaskDao.getEdmTaskPage(page, args);
    }
}
