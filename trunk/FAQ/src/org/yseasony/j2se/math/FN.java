package org.yseasony.j2se.math;

/**
 * f(0)=0,f(1)=1 f(n)=f(n-1)+f(n-2) n>1 给定n， 编写函数计算f(n)的值（效率越高越好)
 * 
 * @author yy
 * 
 */
public class FN {

	static int f(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		return f(n - 1) + f(n - 2);

	}

	public static void main(String[] args) {
		System.out.println(f(5));
	}

}
