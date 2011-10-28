package org.yseasony.acg.action.admin.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.acg.action.BaseAction;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.security.UserEx;
import org.yseasony.acg.services.UserSvcImpl;
import org.yseasony.acg.utils.Page;
import org.yseasony.acg.utils.SpringSecurityUtils;
import org.yseasony.acg.utils.sql.SqlWebUtils;

@Controller
@RequestMapping("/admin/user")
public class UserAction extends BaseAction {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@RequestMapping("/userList")
	@ResponseBody
	public Page<User> userList(Integer pageNo, HttpServletRequest request) {
		Map<String, Object> filters = SqlWebUtils.buildPropertyFilters(request);
		Page<User> page = new Page<User>(10);
		page.setPageNo(pageNo);
		return userSvcImpl.getUserPage(page,filters);
	}
	
	@RequestMapping("/userPage")
	public String userPage() {
		return "admin/user/user";
	}

	@RequestMapping("/userDelete")
	@ResponseBody
	public Map<String, Object> userDelete(Long[] ids) {
		try {
			userSvcImpl.deleteUser(ids);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/userSave")
	@ResponseBody
	public Map<String, Object> userSave(User user,Long[] roleIds) {
		try {
			userSvcImpl.saveUser(user,roleIds);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/userExist")
	@ResponseBody
	public Map<String, Object> userExist(HttpServletRequest request) {
		try {
			boolean b = userSvcImpl.getUserExits(SqlWebUtils
					.buildPropertyFilters(request));
			Map<String, Object> map = getMapSuccess();
			map.put(EXT_STATUS, b);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/userEdit")
	@ResponseBody
	public Map<String, Object> userEdit(Long uid) {
		try {
			User user = userSvcImpl.getUser(uid);
			Map<String, Object> map = getMapSuccess();
			map.put(EXT_DATA, user);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}
	
	@RequestMapping("/userInfo")
	@ResponseBody
	public UserEx getUserInfo() {
		return SpringSecurityUtils.getCurrentUser();
	}

}
