package com.yy.utils;

import java.util.ArrayList;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@SuppressWarnings("unchecked")
public class MyDefaultAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {
	private ArrayList a;

	public void setUrls(ArrayList paramArrayList) {
		this.a = paramArrayList;
	}

	public String[] getFiltered(String[] paramArrayOfString) {
		System.out.println(paramArrayOfString);
		if (paramArrayOfString == null)
			return null;
		ArrayList localArrayList = new ArrayList();
		for (String str : paramArrayOfString) {
			if (!(this.a.contains(str)))
				continue;
			localArrayList.add(str);
		}
		return ((String[]) localArrayList.toArray(new String[localArrayList
				.size()]));
	}

	protected String[] determineUrlsForHandler(String paramString) {
		return getFiltered(super.determineUrlsForHandler(paramString));
	}
}
