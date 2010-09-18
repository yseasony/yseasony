package org.yseasony.blog.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yseasony.blog.entity.User;
import org.yseasony.blog.service.UserSvc;

@Controller
public class UserAction {

	@Autowired
	private UserSvc userSvc;

	@RequestMapping(value = "/manage/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> getUserList() {
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", "1");
		modelMap.put("success", "true");
		modelMap.put("success2", "啊");
		return modelMap;
	}
	
	@RequestMapping("/manage/user/userSave.do")
	public void userSave(HttpServletRequest request, HttpServletResponse response, User user) {
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
