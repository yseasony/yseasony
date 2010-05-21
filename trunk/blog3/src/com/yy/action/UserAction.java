package com.yy.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yy.model.User;
import com.yy.service.IUserSvc;

@Controller
public class UserAction {

	@Autowired
	private IUserSvc userSvc;

	@RequestMapping("/user/login.do")
	public ModelAndView login() {
		return new ModelAndView("Pages/login");
	}
	@RequestMapping("/user/userSave.do")
	public void userSave(HttpServletRequest request,
			HttpServletResponse response, User user) {
		user.setEnabled(true);
		this.userSvc.save(user);
	}

	@RequestMapping("/user/userCreate.do")
	public ModelAndView userCreate() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int max = userSvc.getMax("User");
		map.put("max", max);
		return new ModelAndView("Manager/userCreate", map);
	}
}
