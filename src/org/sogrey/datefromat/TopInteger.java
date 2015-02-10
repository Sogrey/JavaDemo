/**
 * 
 */
package org.sogrey.datefromat;

/**
 * @author Administrator
 *
 */
public class TopInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 15516;
		// 最后一位
		System.out.println("最后一位是"+a % 10);

		int m = 1;
		for (int i = 0; i < (int) Math.log10(a)-2; i++) {
			m *= 10;
		}
		// 第一位
		System.out.println("前三位是"+a / m);
	}
}
