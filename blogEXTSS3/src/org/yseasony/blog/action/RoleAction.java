package org.yseasony.blog.action;

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
import org.yseasony.blog.action.validate.AuthorityValidator;
import org.yseasony.blog.entity.Authority;
import org.yseasony.blog.entity.Role;
import org.yseasony.blog.exception.MyException;
import org.yseasony.blog.service.AuthoritySvc;
import org.yseasony.blog.service.RoleSvc;
import org.yseasony.blog.utils.Token;



@Controller
public class RoleAction extends BaseAction<RoleAction>{
	
	@Autowired
	private RoleSvc roleSvc;
	
	@Autowired
	private AuthoritySvc authoritySvc;

	@RequestMapping(value = "/manage/user/role/saveRole.do", method = RequestMethod.POST)
	public String roleSave(HttpServletRequest request,
			String authorityIds, Role role, BindingResult result) {
		new AuthorityValidator().validate(role, result);
		if (result.hasErrors()) {
			setErrorMsgWithToken(request, result);
			return "manager/user/role/editAuthority";
		}
		
		String[] authorityId = null;
		if (!isBlank(authorityIds)) {
			authorityId = authorityIds.split(",");
		}
		try {
			roleSvc.save(role, authorityId);
			return "redirect:/manage/user/role/getRoleList.do";
		} catch (MyException e) {
			setErrorMsgWithToken(request, "保存失败！");
			return "manager/user/role/editAuthority";
		}

	}
	
	@RequestMapping("/manage/user/role/createRole.do")
	public ModelAndView roleCreate(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Authority> list = authoritySvc.getListAll();
		map.put("token", Token.getTokenString(session));
		map.put("list", list);
		return new ModelAndView("manager/user/role/editRole", map);
	}
	
	@RequestMapping("/manage/user/role/getRoleList.do")
	public ModelAndView getRoleList() {
		return new ModelAndView("manager/user/role/roleList");
	}
	
	@RequestMapping("/manage/user/role/editRole.do")
	public ModelAndView editAuthority(HttpSession session, Long roleId) {
		Role role = roleSvc.getById(roleId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("token", Token.getTokenString(session));
		return new ModelAndView("manager/user/role/editRole", map);
	}
}
