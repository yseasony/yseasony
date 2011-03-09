package org.yseasony.acg.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SystemAction {


	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/test")
	public String test() {
		return "admin/test";
	}
}
