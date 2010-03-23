package com.yy.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yy.model.User;
import com.yy.service.IUserSvc;

@Controller
public class UserAjax extends BaseAjax<UserAjax> {

	@Autowired
	private IUserSvc userSvc;

	@RequestMapping("/userExist.ajax")
	public ModelAndView exist(HttpServletRequest request,
			HttpServletResponse response, String loginname) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		boolean exist = false;
		modelAndView.addObject("exist", exist);

		if (isBlank(loginname)) {
			return modelAndView;
		}

		User user = this.userSvc.exist("loginname", loginname.trim());

		if (user != null) {
			return modelAndView;
		}

		exist = true;
		modelAndView.addObject("exist", exist);
		return modelAndView;
	}

}
