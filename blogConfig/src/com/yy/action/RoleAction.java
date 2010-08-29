package com.yy.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.action.validate.AuthorityValidator;
import com.yy.exception.MyException;
import com.yy.lang.utils.Token;
import com.yy.model.Authority;
import com.yy.model.Role;
import com.yy.service.IAuthoritySvc;
import com.yy.service.IRoleSvc;


@Controller
public class RoleAction extends BaseAction<RoleAction>{
	
	@Autowired
	private IRoleSvc roleSvc;
	
	@Autowired
	private IAuthoritySvc authoritySvc;

	@RequestMapping(value = "/manage/user/saveRole.do", method = RequestMethod.POST)
	public String roleSave(HttpServletRequest request,
			String authorityIds, Role role, BindingResult result) {
		new AuthorityValidator().validate(role, result);
		if (result.hasErrors()) {
			setErrorMsgWithToken(request, result);
			return "Pages/Manager/editAuthority";
		}
		
		String[] authorityId = null;
		if (!isBlank(authorityIds)) {
			authorityId = authorityIds.split(",");
		}
		try {
			roleSvc.save(role, authorityId);
			return "redirect:/manage/user/getRoleList.do";
		} catch (MyException e) {
			setErrorMsgWithToken(request, "保存失败！");
			return "Pages/Manager/editAuthority";
		}

	}
	
	@RequestMapping("/manage/user/createRole.do")
	public ModelAndView roleCreate(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Authority> list = authoritySvc.getListAll();
		map.put("token", Token.getTokenString(session));
		map.put("list", list);
		return new ModelAndView("Pages/Manager/editRole", map);
	}
	
	@RequestMapping("/manage/user/getRoleList.do")
	public ModelAndView getRoleList() {
		return new ModelAndView("Pages/Manager/Ajax/roleList");
	}
	
	@RequestMapping("/manage/user/editRole.do")
	public ModelAndView editAuthority(HttpSession session, Long roleId) {
		Role role = roleSvc.getById(roleId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("Pages/Manager/editRole", map);
	}
}
