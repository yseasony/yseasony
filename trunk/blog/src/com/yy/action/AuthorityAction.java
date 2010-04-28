
package com.yy.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
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
import com.yy.service.IAuthoritySvc;
import com.yy.utils.Token;

@Controller
public class AuthorityAction extends BaseAction<AuthorityAction>{
	
	@Autowired
	private IAuthoritySvc authoritySvc;
	
	@RequestMapping(value = "/user/authoritySave.do", method = RequestMethod.POST)
	public void authoritySave(HttpServletRequest request,
			HttpServletResponse response,String resourceIds, Authority authority,BindingResult result) {
		new AuthorityValidator().validate(authority, result);
		if (result.hasErrors()) {
			writeOut(response, result.getFieldError().getCode());
			return;
		}
		
		String[] resourceId = {};
		if (!isBlank(resourceIds)) {
			resourceId =resourceIds.split(",");
		}
		
		try {
			authoritySvc.save(authority,resourceId);
		} catch (MyException e) {
			writeOut(response,"保存失败！");
		}
		
	}

	@RequestMapping("/user/authorityCreate.do")
	public ModelAndView authorityCreate(HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("Pages/Manager/authorityCreate", map);
	}
}
