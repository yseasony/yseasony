package com.yy.test.base;

import org.springframework.stereotype.Component;

import com.yy.exception.MyException;

@Component
public class HelloWorld {

	public void sayHello(String helloworld) {
		System.out.println(helloworld);
		throw new MyException();
		// 这个异常是拿来测试，可有可无
	}
}
