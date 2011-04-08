package org.yseasony.acg.action.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yseasony.acg.action.BaseAction;
import org.yseasony.acg.security.UserEx;
import org.yseasony.acg.utils.SpringSecurityUtils;

@Controller
@RequestMapping("/admin")
public class SystemAction extends BaseAction {


	@RequestMapping("/login")
	public String login(HttpServletResponse response) {
		response.setHeader("login_timeout", "true");
		return "admin/login";
	}

	@RequestMapping("/index")
	public String index() {
		UserEx user = SpringSecurityUtils.getCurrentUser();
		if (user == null) {
			return redirect("/admin/login");
		}
		return "admin/index";
	}

	@RequestMapping("/test")
	public String test() {
		return "admin/test";
	}
}
