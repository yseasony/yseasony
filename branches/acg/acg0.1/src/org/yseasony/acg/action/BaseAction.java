package org.yseasony.acg.action;

import java.util.HashMap;
import java.util.Map;

import org.yseasony.utils.BasicConstant;

public class BaseAction {

	public final static String EXT_STATUS = "success";
	public final static String EXT_DATA = "data";
	public final static String EXT_MESSAGE = "message";
	public final static String PAGE = "page";
	public final static String FORWARD = "forward:";
	public final static String REDIRECT = "redirect:";

	public Map<String, Object> getMapSuccess() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put(EXT_STATUS, true);
		return modelMap;
	}

	public Map<String, Object> getMapError() {
		return getMapError(BasicConstant.EMPTY_STRING);
	}

	public Map<String, Object> getMapError(String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put(EXT_MESSAGE, msg);
		modelMap.put(EXT_STATUS, false);
		return modelMap;
	}

	public String forward(String url) {
		return FORWARD + url;
	}

	public String redirect(String url) {
		return REDIRECT + url;
	}
}
