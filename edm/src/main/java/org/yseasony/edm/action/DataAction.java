package org.yseasony.edm.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.yseasony.edm.entiy.EdmEmail;
import org.yseasony.edm.entiy.EdmEmailGroup;
import org.yseasony.edm.entiy.EdmEmailTemplate;
import org.yseasony.edm.entiy.EdmTask;
import org.yseasony.edm.service.EdmDataService;
import org.yseasony.edm.service.EdmEmailTemplateService;
import org.yseasony.edm.utils.*;

/**
 * User: yseasony Date: 13-9-30 Time: 下午4:47
 */
@Controller
@RequestMapping(value = "/data")
public class DataAction extends BaseAction {

    private static final Logger     logger = LoggerFactory.getLogger(DataAction.class);

    @Autowired
    private EdmDataService          edmDataService;

    @Autowired
    private EdmEmailTemplateService edmEmailTemplateService;

    @RequestMapping(value = "/import")
    public String dataimport() {
        return "data/import";
    }

    @RequestMapping(value = "/importing")
    public String dataimport(String groupName, MultipartFile dataFile) {
        try {
            String data = IOUtils.toString(dataFile.getInputStream());
            edmDataService.importing(groupName, data);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/task_list")
    public String taskList(HttpServletRequest request, Model model) {
        Page<EdmTask> page = edmDataService.getTaskPage(new Page<EdmTask>(), getParameters(request));
        model.addAttribute("page", page);
        return "/data/task";
    }

    @RequestMapping(value = "/create_task")
    public String createTask(Model model) {
        List<EdmEmailGroup> edmEmailGroups = edmDataService.getEdmEmailGroupList();
        model.addAttribute("edmEmailGroups", edmEmailGroups);
        List<EdmEmailTemplate> edmEmailTemplates = edmEmailTemplateService.getList();
        model.addAttribute("edmEmailTemplates", edmEmailTemplates);
        return "/data/create_task";
    }

    @RequestMapping(value = "/save_task")
    @ResponseBody
    public String saveTask(HttpServletRequest request) {
        Map<String, Object> args = getParameters(request);
        if (StringUtils.isNotBlank(String.valueOf(args.get("startTime")))) {
            Long startTime = DateUtils.ymdFormatL(String.valueOf(args.get("startTime")));
            args.put("startTime", startTime);
        }

        if (StringUtils.isNotBlank(String.valueOf(args.get("endTime")))) {
            Long endTime = DateUtils.ymdFormatL(String.valueOf(args.get("endTime"))) + 235959;
            args.put("endTime", endTime);
        }

        if (StringUtils.isNotBlank(String.valueOf(args.get("taskStartTime")))) {
            Long taskStartTime = DateUtils.ymdhmsFormatL(String.valueOf(args.get("taskStartTime")));
            args.put("taskStartTime", taskStartTime);
        }

        edmDataService.saveTask(args);
        return "redirect:/data/task_list";

    }

    @RequestMapping(value = "/email_template_list")
    public String emailTemplateList(HttpServletRequest request, Model model) {
        Page<EdmEmailTemplate> page = edmEmailTemplateService.getEdmEmailTemplatePage(new Page<EdmEmailTemplate>(),
                                                                                      getParameters(request));
        model.addAttribute("page", page);
        return "/data/email_template_list";
    }

    @RequestMapping(value = "/edit_email_template")
    public String editEmailTemplate(Integer id, Model model) {
        if (id != null) {
            EdmEmailTemplate edmEmailTemplate = edmEmailTemplateService.get(id);
            model.addAttribute("edmEmailTemplate", edmEmailTemplate);
        }
        return "/data/edit_email_template";
    }

    @RequestMapping(value = "/preview_email_template")
    public String previewEmailTemplate(Integer id, Model model) {
        EdmEmailTemplate edmEmailTemplate = edmEmailTemplateService.get(id);
        EdmEmail edmEmail = new EdmEmail();
        edmEmail.setEmail("qwe123@163.com");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("edmEmail", edmEmail);

        try {
            String regUrlParam = Des.encrypt("qwe123@163.com", Des.KEY);
            args.put("_reg_url_param", regUrlParam);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        String[] params = StringUtils.splitByWholeSeparatorPreserveAllTokens(edmEmailTemplate.getEmailParameter(),
                                                                             Utils.LF);
        for (String str : params) {
            String[] s = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, "|");
            args.put(s[0], s[1]);
        }

        String html = VelocityUtils.render(edmEmailTemplate.getEmailHtml(), args);
        String title = VelocityUtils.render(edmEmailTemplate.getEmailTitle(), args);
        model.addAttribute("preview_email_html", html);
        model.addAttribute("title", title);

        return "/data/preview_email_template";
    }

    @RequestMapping(value = "/create_test_email_template")
    public String createTestEmailTemplate(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/data/create_test_email_template";
    }

    @RequestMapping(value = "/save_test_email_template")
    public String saveTestEmailTemplate(Integer edmEmailTemplateId, String email) {
        edmDataService.saveTestTask(email, edmEmailTemplateId);
        return "redirect:/data/email_template_list";
    }

    @RequestMapping(value = "/save_email_template")
    public String saveEmailTemplate(EdmEmailTemplate edmEmailTemplate) {
        edmEmailTemplate.setCreateTime(DateUtils.date2Long());
        edmEmailTemplateService.save(edmEmailTemplate);
        return "redirect:/data/email_template_list";
    }

    @RequestMapping(value = "/test_task_count", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> testTaskCount(HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<String, Object>();
        Map<String, Object> args = getParameters(request);
        if (StringUtils.isNotBlank(String.valueOf(args.get("startTime")))) {
            Long startTime = DateUtils.ymdFormatL(String.valueOf(args.get("startTime")));
            args.put("startTime", startTime);
        }

        if (StringUtils.isNotBlank(String.valueOf(args.get("endTime")))) {
            Long endTime = DateUtils.ymdFormatL(String.valueOf(args.get("endTime"))) + 235959;
            args.put("endTime", endTime);
        }

        int count = edmDataService.getTaskCount(args);
        maps.put("success", true);
        maps.put("count", count);
        return maps;
    }

}
