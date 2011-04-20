package org.yseasony.j2se.Josephus;

import java.util.Arrays;

/**
 * n个人围成一圈，报到m的人出列
 * 
 */
public class Queue {

	public static void main(String[] args) {
		queue(10, 3);
	}

	/**
	 * 最后出队的人
	 * 
	 * @param total
	 *            总的人数
	 * @param num
	 *            第几号出队
	 */
	public static void queue(int total, int num) {
		// 定义一个数组，true表示没有出队列的，false表示已经出队列的
		boolean[] arr = new boolean[total];
		Arrays.fill(arr, true);

		// 移动变量，如：1 2 3 1 2 3 1 2
		int next = 1;

		// 数组下标
		int index = 0;

		// 剩下的人数
		int count = total;

		// 如果剩下的人数为1人时，停止报数
		while (count > 1) {
			if (arr[index] == true) {
				if (next == num) {
					arr[index] = false;

					// 剩下的人数减1
					--count;

					// 移动变量复位，从1开始报数
					next = 1;

					System.out.println("依次出列的人为：" + (index + 1));
				} else {
					++next;
				}
			}

			++index;
			if (index == total) {
				// 数组下标复位，从0开始新重遍历
				index = 0;
			}
		}
		for (int i = 0; i < total; i++) {
			if (arr[i] == true) {
				System.out.println("最后出列的人为：" + (i + 1));
			}
		}
	}

}