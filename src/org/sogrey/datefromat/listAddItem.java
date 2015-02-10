/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class listAddItem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			index.add(i);
		}
		System.out.println(index.toString());
		index.add(0, 88);
		System.out.println(index.toString());
	}

}
