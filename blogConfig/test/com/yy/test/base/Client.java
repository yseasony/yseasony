package com.yy.test.base;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Client extends SpringTxTestCase {

	@Autowired
	private HelloWorld helloWorld;

	@Test
	public void main() {

		helloWorld.sayHello("hello world");
	}
}
