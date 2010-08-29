
package com.yy.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.action.validate.AuthorityValidator;
import com.yy.exception.MyException;
import com.yy.lang.utils.Token;
import com.yy.model.Authority;
import com.yy.service.IAuthoritySvc;

@Controller
@Scope("session")
public class AuthorityAction extends BaseAction<AuthorityAction>{
	
	@Autowired
	private IAuthoritySvc authoritySvc;
	
	@RequestMapping(value = "/manage/user/saveAuthority.do", method = RequestMethod.POST)
	public String saveAuthority(
			HttpServletRequest request,Authority authority, BindingResult result) {
		new AuthorityValidator().validate(authority, result);
		if (result.hasErrors()) {
			setErrorMsgWithToken(request, result);
			return "Pages/Manager/editAuthority";
		}
		try {
			authoritySvc.save(authority);
			return "redirect:/manage/user/getAuthorityList.do";
		} catch (MyException e) {
			setErrorMsgWithToken(request, "保存失败！");
			return "Pages/Manager/editAuthority";
		}
		
	}
	
	@RequestMapping(value = "/manage/user/deleteAuthority.do")
	public String deleteAuthority(HttpServletRequest request,Long authorityId) {
		try {
			authoritySvc.delete(authorityId);
			return "redirect:/manage/user/getAuthorityList.do";
		} catch (MyException e) {
			setErrorMsg(request, "删除失败！");
			return "Pages/Manager/Ajax/authorityList";
		}
		
	}

	@RequestMapping("/manage/user/createAuthority.do")
	public ModelAndView createAuthority(HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("Pages/Manager/editAuthority", map);
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
