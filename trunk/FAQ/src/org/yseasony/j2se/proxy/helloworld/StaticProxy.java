package org.yseasony.j2se.proxy.helloworld;

public class StaticProxy implements HelloWorld {

	public HelloWorld helloWorld;

	public StaticProxy(HelloWorld helloWorld) {
		this.helloWorld = helloWorld;
	}

	public void print() {
		System.out.println("Welcome");
		// 相当于回调
		helloWorld.print();
	}

}
