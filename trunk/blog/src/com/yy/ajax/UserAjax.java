package com.yy.ajax;

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
public class UserAjax {

	@Autowired
	private IUserSvc userSvc;

	@RequestMapping("/userExist.ajax")
	public ModelAndView exist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<Object, Object> map = new HashMap<Object, Object>();

		ModelAndView modelAndView = new ModelAndView("jsonView");

		boolean exist = false;

		String loginname = request.getParameter("loginname");

		if (loginname == null || loginname.trim().length() <= 0) {
			map.put("exist", exist);
			modelAndView.addAllObjects(map);
			return modelAndView;
		}

		User user = this.userSvc.exist("loginname", loginname.trim());

		if (user != null) {
			map.put("exist", exist);
			modelAndView.addAllObjects(map);
			return modelAndView;
		}
		exist = true;
		map.put("exist", exist);
		modelAndView.addAllObjects(map);
		return modelAndView;

	}

}
