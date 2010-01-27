package com.yy.action;

import java.sql.Timestamp;
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

	@RequestMapping("/user/userSave.do")
	public void userSave(HttpServletRequest request,
			HttpServletResponse response, String status, String email,
			String loginName, String name, String password, String displayorder) {

		User user = new User();
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setEmail(email);
		user.setEnabled(true);
		user.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
		user.setLoginname(loginName);
		user.setName(name);
		user.setPassword(password);
		user.setStatus(status);
		user.setDisplayorder(Integer.valueOf(displayorder));
		this.userSvc.save(user);

	}

	@RequestMapping("/user/userCreate.do")
	public ModelAndView userCreate() {

		HashMap<String, String> map = new HashMap<String, String>();
		String max = String.valueOf(userSvc.getMax("User"));
		map.put("max", max);
		return new ModelAndView("Manager/userCreate", map);
	}
}
