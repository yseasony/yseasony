
package com.yy.action;

import java.util.HashMap;

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
	
	@RequestMapping(value = "/manage/user/saveAuthority.do", method = RequestMethod.POST)
	public void saveAuthority(HttpServletResponse response,
			Authority authority, BindingResult result) {
		new AuthorityValidator().validate(authority, result);
		if (result.hasErrors()) {
			writeOut(response, result.getFieldError().getCode());
			return;
		}
		
		try {
			authoritySvc.save(authority);
		} catch (MyException e) {
			writeOut(response,"保存失败！");
		}
		
	}
	
	@RequestMapping(value = "/manage/user/deleteAuthority.do")
	public String deleteAuthority(HttpServletResponse response,Long authorityId) {
		try {
			authoritySvc.delete(authorityId);
		} catch (MyException e) {
			writeOut(response,"删除失败！");
		}
		return "redirect:/manage/user/getAuthorityList.do";
	}

	@RequestMapping("/manage/user/createAuthority.do")
	public ModelAndView createAuthority(HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("Pages/Manager/createAuthority", map);
	}
	
	@RequestMapping("/manage/user/getAuthorityList.do")
	public ModelAndView getAuthorityList() {
		return new ModelAndView("Pages/Manager/Ajax/authorityList");
	}
	
	@RequestMapping("/manage/user/editAuthority.do")
	public ModelAndView editAuthority(HttpSession session, Long authorityId) {
		Authority authority = authoritySvc.getById(authorityId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("authority", authority);
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("Pages/Manager/editAuthority", map);
	}
}
