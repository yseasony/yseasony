package org.yseasony.j2se.math;

/**
 * @author YseasonY
 * @version time ：2010-6-21 下午02:40:04
 * 
 */
public class Number {

	/**
	 * 有一个整数数组，有很多个元素，如何把零移到最前面，要求算法效率要高。如
	 * 0,1,72,3,0,5,9,0,6,51,0,3。移动后为0,0,0,0,1,72,3,5,9,6,51,3。
	 */
	public static void main(String[] argv) {
		int[] array = { 1, 0, 3, 0, 0, -34, 786, 99, 0, 6, 100, 0, 0, 99, 0,
				10, 4 };
		array = move(array);
		for (int i = 0; i < array.length; i++)
			System.out.print(" " + array[i]);
		System.out.println("");
	}

	public static int[] move(int[] array) {
		// 自右向左扫描，将所有非零元素紧凑到右侧
		int low, high;
		for (low = array.length - 1, high = low; low >= 0; low--)
			if (array[low] != 0) {
				array[high] = array[low];
				high--;// 更新紧凑序列的最左侧元素
			}
		// 将余下所有元素全部置为0
		for (; high >= 0; high--)
			array[high] = 0;
		return array;
	}
}
