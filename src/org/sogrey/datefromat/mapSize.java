/**
 * 
 */
package org.sogrey.datefromat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class mapSize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Map<Integer, String> map = new HashMap<Integer, String>();
for (int i = 0; i < 10; i++) {
	map.put(i, "第"+i+"个");
}
System.out.println("map.size=="+map.size());
for (Map.Entry entry : map.entrySet()) {
	   System.out.println("继续答题  key= " + entry.getKey() + " and value= " + entry.getValue());
	  }

map.put(5, "55555555555");
System.out.println("map.size=="+map.size());
for (Map.Entry entry : map.entrySet()) {
	System.out.println("继续答题  key= " + entry.getKey() + " and value= " + entry.getValue());
}
	}
}
