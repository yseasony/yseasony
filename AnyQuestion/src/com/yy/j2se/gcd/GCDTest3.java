package com.yy.j2se.gcd;

import java.util.Arrays;

/**
 * @author YseasonY
 * @version time ：2010-6-28 下午02:17:35
 * 
 */
public class GCDTest3 {

	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 4, 5 };
		int n = a.length;
		int k = 3;
		int m;
		int i;
		int j;
		int b0;
		int b1;
		int t;

		m = ff(n, k); // 最大公约数

		for (j = 0; j < m; j++) {
			b0 = a[j];
			t = j;

			for (i = 0; i < n / m; i++) {
				t = (t + k) % n;
				b1 = a[t];
				a[t] = b0;
				b0 = b1;
			}

		}
		System.out.println(Arrays.toString(a));

	}

	/**
	 * 求 a和b的最大公约数
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int ff(int a, int b) {
		int i, t = 1;
		for (i = 2; i <= a && i <= b; i++) {
			while ((a % i == 0) && (b % i == 0)) {
				t = t * i;
				a = a / i;
				b = b / i;
			}
		}
		return t;
	}
}
