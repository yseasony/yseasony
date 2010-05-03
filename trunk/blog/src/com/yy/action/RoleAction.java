package com.yy.action;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yy.action.validate.AuthorityValidator;
import com.yy.exception.MyException;
import com.yy.model.Role;
import com.yy.service.IRoleSvc;


@Controller
public class RoleAction extends BaseAction<RoleAction>{
	
	@Autowired
	private IRoleSvc roleSvc;

	@RequestMapping(value = "/user/authoritySave.do", method = RequestMethod.POST)
	public void authoritySave(HttpServletResponse response,
			String authorityIds, Role role, BindingResult result) {
		new AuthorityValidator().validate(role, result);
		if (result.hasErrors()) {
			writeOut(response, result.getFieldError().getCode());
			return;
		}

		String[] authorityId = {};
		if (!isBlank(authorityIds)) {
			authorityId = authorityIds.split(",");
		}

		try {
			roleSvc.save(role, authorityId);
		} catch (MyException e) {
			writeOut(response, "保存失败！");
		}

	}
}
