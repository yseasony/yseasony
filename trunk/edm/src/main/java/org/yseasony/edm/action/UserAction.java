package org.yseasony.edm.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.yseasony.edm.entiy.EdmEmail;
import org.yseasony.edm.service.EdmDataService;
import org.yseasony.edm.utils.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * User: yseasony Date: 13-9-29 Time: 下午5:27
 */
@Controller
public class UserAction extends BaseAction{

    @Autowired
    private EdmDataService edmDataService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    public String logon(String username, String password) {
        if (StringUtils.equals(username, "damon") && StringUtils.equals(password, "damon")) {
            return "redirect:index";
        } else {
            return "login";
        }

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        Page<EdmEmail> page = edmDataService.getEdmEmailPage(new Page<EdmEmail>(15), getParameters(request));
        model.addAttribute("page", page);
        return "index";
    }
}
