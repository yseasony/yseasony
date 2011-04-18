package com.yy.j2se.gcd;

import java.util.Arrays;

/**
 * 题目：数组中有n个数据，要将它们顺序循环向后移动k位，即前面的元素向后移动k 位，后面的元素则循环
 * 向前移k位，例如，0、1、2、3、4循环移动3位后为2、3、4、0、1。考虑到n会很大，不允许用2n以上 个空间来完成此题。
 * 
 * @author YseasonY
 * @version time ：2010-6-28 下午01:55:05
 * 
 */
public class GCDTest1 {

	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 4 };
		int n = a.length;
		int[] b = new int[n];
		int k = 3;

		for (int i = 0; i < n; i++) {
			b[(k + i) % n] = a[i]; // 求余解决循环后移
		}

		System.out.println(Arrays.toString(b));

	}
}
