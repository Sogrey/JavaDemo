/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author Administrator
 *
 */
public class listRemoveItem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		remove1();
		remove2();
	    }

	private static void remove2() {
			    
		        TreeSet<Integer> set = new TreeSet<Integer>();     
		        ArrayList<Integer> list = new ArrayList<Integer>();     
		        Vector<Integer> vector = new Vector<Integer>();     
		    
		        char ch='a';  
		        for (int i = 0; i < 10; i++) {     
		            set.add(10+i);     
		            list.add(10+i);     
		            vector.add(10+i);     
		        }     
		        System.out.println("初始化后set里的值为:" + set.toString());     
		        System.out.println("初始化后list里的值为:" + list.toString());     
		        System.out.println("初始化后vector里的值为:" + vector.toString());     
		    
		        for (int i = 0; i < 5; i++) {     
		            set.remove(i);     
		            list.remove(i);     
		            vector.remove(i);     
		    
		        }     
		        System.out.println("此时set的值为" + set.toString());     
		        System.out.println("此时后list的值为" + list.toString());     
		        System.out.println("此时后vector的值为" + vector.toString());     
		    
	}

	private static void remove1() {
		Collection e=new HashSet();
	      e.add("bubble sort");
	      e.add("quick sort");
	      e.add("insert sort");
	      System.out.println(e);
	      Iterator iter=e.iterator();
	      while(iter.hasNext()){
	          String algorithm=iter.next().toString();
	          if(algorithm.equals("quick sort")){
	            //e.remove(algorithm);//改为e.remove(algorithm);就行，不改就报错
	              iter.remove();
	          }
	          System.out.println();
	      }
	      System.out.println(e);
	}

}
