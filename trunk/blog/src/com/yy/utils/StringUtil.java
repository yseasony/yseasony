package com.yy.utils;

public class StringUtil {

	public static boolean isEmpty(String... string) {

		for (int i = 0; i < string.length; i++) {
			if (string[i] == null || string[i].trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

}
