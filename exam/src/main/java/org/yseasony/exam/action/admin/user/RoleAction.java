package org.yseasony.exam.action.admin.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yseasony.exam.action.BaseAction;
import org.yseasony.exam.entity.Role;
import org.yseasony.exam.services.UserSvcImpl;
import org.yseasony.exam.utils.Page;
import org.yseasony.exam.utils.sql.SqlWebUtils;
import org.yseasony.exam.vo.RoleVO;

@Controller
@RequestMapping("/admin/role")
public class RoleAction extends BaseAction {

	@Autowired
	private UserSvcImpl userSvcImpl;

	@RequestMapping("/rolePage")
	@ResponseBody
	public Page<Role> rolePage(Integer start, Integer limit) {
		Page<Role> page = new Page<Role>(start, limit);
		return userSvcImpl.getRolePage(page);
	}

	@RequestMapping("/roleAll")
	@ResponseBody
	public List<RoleVO> roleAll() {
		List<Role> roles = userSvcImpl.getAllRole();
		return null;
	}

	@RequestMapping("/roleSave")
	@ResponseBody
	public Map<String, Object> roleSave(Role role, Long[] authIds) {
		userSvcImpl.saveRole(role, authIds);
		return getMapSuccess();
	}

	@RequestMapping("/roleUpdate")
	@ResponseBody
	public Map<String, Object> roleUpdate(Role role, Long[] authIds) {
		userSvcImpl.saveRole(role, authIds);
		return getMapSuccess();
	}

	@RequestMapping("/roleExist")
	@ResponseBody
	public Map<String, Object> roleExist(HttpServletRequest request) {
		try {
			boolean b = userSvcImpl.getRoleExits(SqlWebUtils
					.buildPropertyFilters(request));
			Map<String, Object> map = getMapSuccess();
			map.put(SUCCESS, b);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/roleEdit")
	@ResponseBody
	public Map<String, Object> roleEdit(Long id) {
		try {
			Role role = userSvcImpl.getRole(id);
			Map<String, Object> map = getMapSuccess();
			map.put(EXT_DATA, role);
			return map;
		} catch (Exception e) {
			return getMapError();
		}
	}

	@RequestMapping("/roleDelete")
	@ResponseBody
	public Map<String, Object> roleDelete(Long[] ids) {
		try {
			userSvcImpl.deleteRole(ids);
			return getMapSuccess();
		} catch (Exception e) {
			return getMapError();
		}
	}

}
