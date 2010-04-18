package com.yy.utils;

import java.util.ArrayList;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class MyDefaultAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {
	private ArrayList<String> a;

	public void setUrls(ArrayList<String> paramArrayList) {
		this.a = paramArrayList;
	}

	public String[] getFiltered(String[] paramArrayOfString) {
		if (paramArrayOfString == null)
			return null;
		ArrayList<String> localArrayList = new ArrayList<String>();
		for (String str : paramArrayOfString) {
			if (!(this.a.contains(str))) {
				continue;
			}
			localArrayList.add(str);
		}
		return ((String[]) localArrayList.toArray(new String[localArrayList
				.size()]));
	}

	protected String[] determineUrlsForHandler(String paramString) {
		return getFiltered(super.determineUrlsForHandler(paramString));
	}
}
