/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class list2string {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		list.add("�Ϻ�");
		list.add("����");
		list.add("ˮ");
		list.add("����");
		list.add("����");
		String listStr =list.toString();
		System.out.println(listStr);
		String[] listStrArr =listStr.split(",");
		System.out.println(listStrArr.toString());
		for (int i = 0; i < listStrArr.length; i++) {
			System.out.println("listStrArr["+i+"]=="+listStrArr[i]);
		}
	}
}
