package com.yy.utils;

import org.apache.commons.lang.StringUtils;

public class MyStringUtils {

	public static boolean isBlank(String... string) {
		for (int i = 0; i < string.length; i++) {
			if (isBlank(string[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}
}
