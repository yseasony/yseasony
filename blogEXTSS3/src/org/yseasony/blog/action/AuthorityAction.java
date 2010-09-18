
package org.yseasony.blog.action;

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
import org.yseasony.blog.action.validate.AuthorityValidator;
import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.AuthoritySvc;
import org.yseasony.blog.utils.Token;


@Controller
@Scope("session")
public class AuthorityAction extends BaseAction<AuthorityAction>{
	
	@Autowired
	private AuthoritySvc authoritySvc;
	
	@RequestMapping(value = "/manage/user/auth/saveAuthority.do", method = RequestMethod.POST)
	public String saveAuthority(
			HttpServletRequest request,Authority authority, BindingResult result) {
		new AuthorityValidator().validate(authority, result);
		if (result.hasErrors()) {
			setErrorMsgWithToken(request, result);
			return "manager/user/auth/editAuthority";
		}
		try {
			authoritySvc.save(authority);
			return "redirect:/manage/user/auth/getAuthorityList.do";
		} catch (MyException e) {
			setErrorMsgWithToken(request, "保存失败！");
			return "manager/user/auth/editAuthority";
		}
		
	}
	
	@RequestMapping(value = "/manage/user/auth/deleteAuthority.do")
	public String deleteAuthority(HttpServletRequest request,Long authorityId) {
		try {
			authoritySvc.delete(authorityId);
			return "redirect:/manage/user/auth/getAuthorityList.do";
		} catch (MyException e) {
			setErrorMsg(request, "删除失败！");
			return "manager/user/auth/authorityList";
		}
		
	}

	@RequestMapping("/manage/user/auth/createAuthority.do")
	public ModelAndView createAuthority(HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("manager/user/auth/editAuthority", map);
	}
	
	@RequestMapping("/manage/user/auth/getAuthorityList.do")
	public ModelAndView getAuthorityList() {
		return new ModelAndView("manager/user/auth/authorityList");
	}
	
	@RequestMapping("/manage/user/auth/editAuthority.do")
	public ModelAndView editAuthority(HttpSession session, Long authorityId) {
		Authority authority = authoritySvc.getById(authorityId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("authority", authority);
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("manager/user/auth/editAuthority", map);
	}
}
