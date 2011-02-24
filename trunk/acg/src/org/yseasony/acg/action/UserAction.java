package org.yseasony.acg.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.acg.entity.User;
import org.yseasony.acg.services.UserSvcImpl;
import org.yseasony.acg.utils.Page;

@Controller
@RequestMapping("/admin")
public class UserAction extends BaseAction {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "admin/test";
	}
	
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
}
