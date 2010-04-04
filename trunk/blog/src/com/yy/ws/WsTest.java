package com.yy.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WsTest {
	
	private String a = "a";
	private String b = "b";
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	
	

}
