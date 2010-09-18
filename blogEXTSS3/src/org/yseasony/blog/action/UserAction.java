package org.yseasony.blog.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yseasony.blog.entity.User;
import org.yseasony.blog.service.UserSvc;


@Controller
public class UserAction {

	@Autowired
	private UserSvc userSvc;

	@RequestMapping("/manage/login.do")
	public ModelAndView login() {
		return new ModelAndView("Pages/login");
	}

	@RequestMapping("/manage/user/userSave.do")
	public void userSave(HttpServletRequest request,
			HttpServletResponse response, User user) {
		user.setEnabled(true);
		this.userSvc.save(user);
	}

	@RequestMapping("/manage/user/userCreate.do")
	public ModelAndView userCreate() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int max = userSvc.getMax("User");
		map.put("max", max);
		return new ModelAndView("Manager/userCreate", map);
	}
}
