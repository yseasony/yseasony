package org.yseasony.acg.action.admin.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.acg.action.BaseAction;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.services.UserSvcImpl;
import org.yseasony.acg.utils.Page;
import org.yseasony.acg.utils.sql.SqlWebUtils;

@Controller
@RequestMapping("/admin/user")
public class UserAction extends BaseAction {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@RequestMapping("/userPage")
	@ResponseBody
	public Page<User> userPage(Integer start, Integer limit) {
		Page<User> page = new Page<User>(limit, start);
		return userSvcImpl.getUserPage(page);
	}

	@RequestMapping("/userDelete")
	@ResponseBody
	public Map<String, Object> userDelete(Integer[] ids) {
		try {
			userSvcImpl.deleteUser(ids);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/userSave")
	@ResponseBody
	public Map<String, Object> userSave(User user) {
		try {
			userSvcImpl.saveUser(user);
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
	public Map<String, Object> userExist(Long uid) {
		try {
			User user = userSvcImpl.getUser(uid);
			Map<String, Object> map = getMapSuccess();
			map.put(EXT_DATA, user);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}

}
