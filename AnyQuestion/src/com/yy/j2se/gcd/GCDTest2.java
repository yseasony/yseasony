package com.yy.j2se.gcd;

import java.util.Arrays;

/**
 * @author YseasonY
 * @version time ：2010-6-28 下午02:15:04
 * 
 */
public class GCDTest2 {

	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 4 };
		int n = a.length;
		int k = 3;
		int temp;
		int j;

		for (int i = 0; i < k; i++) {
			temp = a[n - 1]; // 最后一个存储空间的数据存储在临时存储空间中

			for (j = n - 1; j > 0; j--) { // 其余数据逐个向后移动一位
				a[j] = a[j - 1];
			}

			a[0] = temp;
		}

		System.out.println(Arrays.toString(a));

	}
}
