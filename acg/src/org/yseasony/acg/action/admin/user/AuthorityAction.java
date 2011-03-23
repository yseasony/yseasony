package org.yseasony.acg.action.admin.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.acg.action.BaseAction;
import org.yseasony.acg.entity.Authority;
import org.yseasony.acg.services.UserSvcImpl;
import org.yseasony.acg.utils.Page;
import org.yseasony.acg.utils.sql.SqlWebUtils;

@Controller
@RequestMapping("/admin/auth")
public class AuthorityAction extends BaseAction {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@RequestMapping("/authPage")
	@ResponseBody
	public Page<Authority> authPage(Integer start, Integer limit) {
		Page<Authority> page = new Page<Authority>(limit, start);
		return userSvcImpl.getAuthPage(page);
	}

	@RequestMapping("/authDelete")
	@ResponseBody
	public Map<String, Object> authDelete(Integer[] ids) {
		try {
			userSvcImpl.deleteAuth(ids);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/authSave")
	@ResponseBody
	public Map<String, Object> authSave(@RequestBody Authority authority) {
		try {
			userSvcImpl.saveAuth(authority);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping(value = "/authUpdate/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> authUpdate(@RequestBody Authority authority) {
		try {
			userSvcImpl.saveAuth(authority);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/authExist")
	@ResponseBody
	public Map<String, Object> authExist(HttpServletRequest request) {
		try {
			boolean b = userSvcImpl.getAuthExits(SqlWebUtils
					.buildPropertyFilters(request));
			Map<String, Object> map = getMapSuccess();
			map.put(EXT_STATUS, b);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}

}
