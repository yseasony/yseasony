package com.yy.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.action.validate.AuthorityValidator;
import com.yy.exception.MyException;
import com.yy.model.Authority;
import com.yy.model.Role;
import com.yy.service.IAuthoritySvc;
import com.yy.service.IRoleSvc;
import com.yy.utils.Token;


@Controller
public class RoleAction extends BaseAction<RoleAction>{
	
	@Autowired
	private IRoleSvc roleSvc;
	
	@Autowired
	private IAuthoritySvc authoritySvc;

	@RequestMapping(value = "/user/roleSave.do", method = RequestMethod.POST)
	public void roleSave(HttpServletResponse response,
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
	
	@RequestMapping("/user/roleCreate.do")
	public ModelAndView roleCreate(HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		List<Authority> list = authoritySvc.getAllAuthority();
		System.out.println(list.get(0).getName());
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("Pages/Manager/roleCreate", map);
	}
}
