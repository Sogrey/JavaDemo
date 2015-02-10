/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Administrator
 *
 */
public class arrayContains {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
ArrayList<String > list = new ArrayList<String>();
list.add("A");
list.add("C");
list.add("B");
System.out.println(list.toString());
System.out.println(list.contains("C"));
Collections.sort(list);
System.out.println(list.toString());
list.remove("A");
list.remove("B");
list.remove("C");
System.out.println(list.toString());
Collections.sort(list);
System.out.println(list.toString());
	}

}
