package org.yseasony.blog.utils;

import org.yseasony.blog.utils.string.YYStringUtils;


public class RegexUtils extends YYStringUtils{
	
	public static boolean password(String value) {
		if (isBlank(value)) {
			return false;
		} else if (value.matches("[a-zA-Z0-9_]{6,12}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean loginname(String value) {
		if (isBlank(value)) {
			return false;
		} else if (value.matches("[a-zA-Z0-9_]{5,12}")) {
			return true;
		} else {
			return false;
		}
	}
}
