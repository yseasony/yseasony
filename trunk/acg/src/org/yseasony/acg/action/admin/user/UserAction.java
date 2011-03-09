package org.yseasony.acg.action.admin.user;

import java.util.HashMap;

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
	public HashMap<String, Object> userPage(Integer start, Integer limit) {
		Page<User> page = new Page<User>(limit, start);
		page = userSvcImpl.userPage(page);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(PAGE, page);
		return map;
	}

	@RequestMapping("/userDelete")
	@ResponseBody
	public HashMap<String, Object> userDelete(Integer[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			userSvcImpl.delete(ids);
			map.put(EXT_STATUS, true);
		} catch (Exception e) {
			map.put(EXT_STATUS, false);
		}
		return map;
	}

	@RequestMapping("/userSave")
	@ResponseBody
	public HashMap<String, Object> userSave(User user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			userSvcImpl.save(user);
			map.put(EXT_STATUS, true);
		} catch (Exception e) {
			map.put(EXT_STATUS, false);
		}
		return map;
	}

	@RequestMapping("/userExist")
	@ResponseBody
	public HashMap<String, Object> userExist(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			boolean b = userSvcImpl.userExits(SqlWebUtils
					.buildPropertyFilters(request));
			map.put(EXT_STATUS, b);
		} catch (Exception e) {
			map.put(EXT_STATUS, false);
		}
		return map;
	}

	@RequestMapping("/userEdit")
	@ResponseBody
	public HashMap<String, Object> userExist(Long uid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map.put(EXT_STATUS, true);
			map.put(EXT_DATA, userSvcImpl.getUser(uid));
		} catch (Exception e) {
			map.put(EXT_STATUS, false);
		}
		return map;
	}

}
