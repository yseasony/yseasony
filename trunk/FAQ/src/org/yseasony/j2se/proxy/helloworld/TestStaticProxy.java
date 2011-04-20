package org.yseasony.j2se.proxy.helloworld;

public class TestStaticProxy {
	public static void main(String[] args) {
		HelloWorld helloWorld = new HelloWorldImpl();
		StaticProxy staticProxy = new StaticProxy(helloWorld);
		staticProxy.print();
	}
}
