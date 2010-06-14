package com.yy.test.base;

import java.lang.reflect.ParameterizedType;

import org.junit.Test;

public class B extends A<Prisoner>{

	@Test
	public void main() {

		System.out.println(((ParameterizedType) super.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);

	}
}
