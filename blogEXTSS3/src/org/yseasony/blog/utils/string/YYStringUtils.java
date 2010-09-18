package org.yseasony.blog.utils.string;

import org.apache.commons.lang.StringUtils;

public class YYStringUtils {

	/**
	 * 其中有一个为空 return false
	 * @param string
	 * @return
	 */
	public static boolean isBlank(String... string) {
		for (int i = 0; i < string.length; i++) {
			if (isBlank(string[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}
}
