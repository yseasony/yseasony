package com.yy.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yy.model.Resource;
import com.yy.service.IResourceSvc;
import com.yy.utils.StringUtil;
import com.yy.utils.Token;

@Controller
public class ResourceAction {

	@Autowired
	private IResourceSvc resourceSvc;

	@RequestMapping("/user/resourceSave.do")
	public void resourceSave(HttpServletRequest request,
			HttpServletResponse response, String value, String resourceType,
			String position, String token) throws IOException {

		if (!Token.isTokenStringValid(token, request.getSession())) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("请勿重复提交");
			return;
		}

		if (StringUtil.isEmpty(value, resourceType, position)) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("缺少参数");
			return;
		}

		Resource resource = new Resource();
		resource.setValue(value);
		resource.setResourceType(resourceType);
		resource.setPosition(Double.valueOf(position));

		this.resourceSvc.save(resource);
	}

	@RequestMapping("/user/resourceCreate.do")
	public ModelAndView userCreate(HttpSession session) {

		HashMap<String, String> map = new HashMap<String, String>();
		String max = String.valueOf(resourceSvc.getMax());

		map.put("token", Token.getTokenString(session));
		map.put("max", max);
		return new ModelAndView("Manager/resourceCreate", map);
	}

}
