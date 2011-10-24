package org.yseasony.acg.utils.sql;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

public class SqlWebUtils {

	private SqlWebUtils() {
	}

	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
	 * 默认Filter属性名前缀为filter_.
	 * 
	 * @see #buildPropertyFilters(HttpServletRequest, String)
	 */
	public static Map<String, Object> buildPropertyFilters(
			final HttpServletRequest request) {
		return buildPropertyFilters(request, "filter_");
	}

	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
	 * PropertyFilter命名规则为Filter_属性名.
	 * 
	 * eg. filter_name
	 */
	public static Map<String, Object> buildPropertyFilters(
			final HttpServletRequest request, final String filterPrefix) {
		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		return WebUtils.getParametersStartingWith(request, filterPrefix);
	}

}
