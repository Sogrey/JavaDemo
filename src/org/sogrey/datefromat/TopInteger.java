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
		// ���һλ
		System.out.println("���һλ��"+a % 10);

		int m = 1;
		for (int i = 0; i < (int) Math.log10(a)-2; i++) {
			m *= 10;
		}
		// ��һλ
		System.out.println("ǰ��λ��"+a / m);
	}
}
