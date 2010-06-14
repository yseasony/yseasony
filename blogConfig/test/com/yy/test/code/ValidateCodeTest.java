package com.yy.test.code;

import java.io.IOException;
import java.util.Date;

public class ValidateCodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ValidateCode vCode = new ValidateCode();
		try {
			String path="D:/t/"+new Date().getTime()+".png";
			System.out.println(vCode.getCode()+" >"+path);
			vCode.write(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
