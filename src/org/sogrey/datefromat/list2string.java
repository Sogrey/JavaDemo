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
		list.add("上海");
		list.add("自来");
		list.add("水");
		list.add("来自");
		list.add("海上");
		String listStr =list.toString();
		System.out.println(listStr);
		String[] listStrArr =listStr.split(",");
		System.out.println(listStrArr.toString());
		for (int i = 0; i < listStrArr.length; i++) {
			System.out.println("listStrArr["+i+"]=="+listStrArr[i]);
		}
	}
}
