/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class arrayOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  List<String>  list =new ArrayList<String>();
		  list.add("China");
		  list.add("USA");
		  list.add("Engish");
		  list.add("France");
		  list.add("Germany");
		  //对list集合进进排序
		  Collections.sort(list);
		  //打印
		  System.out.println(list);
	}

}
