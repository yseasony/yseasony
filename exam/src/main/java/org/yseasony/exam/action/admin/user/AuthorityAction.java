package org.yseasony.exam.action.admin.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.exam.action.BaseAction;
import org.yseasony.exam.entity.Authority;
import org.yseasony.exam.services.UserSvcImpl;
import org.yseasony.exam.utils.Page;
import org.yseasony.exam.utils.sql.SqlWebUtils;
import org.yseasony.exam.vo.AuthVO;

@Controller
@RequestMapping("/admin/auth")
public class AuthorityAction extends BaseAction {

	@Autowired
	private UserSvcImpl userSvcImpl;


	@RequestMapping("/authPage")
	@ResponseBody
	public Page<Authority> authPage(Integer start, Integer limit) {
		Page<Authority> page = new Page<Authority>(start, limit);
		return userSvcImpl.getAuthPage(page);
	}

	@RequestMapping("/authAll")
	@ResponseBody
	public List<AuthVO> authAll() {
		try {
			List<Authority> authorities = userSvcImpl.getAllAuth();
			return null;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@RequestMapping("/authDelete")
	@ResponseBody
	public Map<String, Object> authDelete(Long[] ids) {
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
			map.put(SUCCESS, b);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}

}
