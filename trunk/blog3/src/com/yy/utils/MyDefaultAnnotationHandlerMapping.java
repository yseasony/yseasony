package com.yy.utils;

import java.util.ArrayList;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class MyDefaultAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {
	private ArrayList<String> urls;

	private ArrayList<String> regexUrls;

	public void setUrls(ArrayList<String> paramArrayList) {
		this.urls = paramArrayList;
	}

	public void setRegexUrls(ArrayList<String> regexUrls) {
		this.regexUrls = regexUrls;
	}

	public String[] getFiltered(String[] paramArrayOfString) {
		if (paramArrayOfString == null)
			return null;
		ArrayList<String> localArrayList = new ArrayList<String>();
		for (String str : paramArrayOfString) {
			if (this.urls.contains(str)) {
				localArrayList.add(str);
			} else {
				for (String regex : regexUrls) {
					if (str.matches(regex)) {
						localArrayList.add(str);
					}
				}
			}
		}
		return ((String[]) localArrayList.toArray(new String[localArrayList
				.size()]));
	}

	protected String[] determineUrlsForHandler(String paramString) {
		return getFiltered(super.determineUrlsForHandler(paramString));
	}
}
