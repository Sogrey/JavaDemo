/**
 * 
 */
package org.sogrey.datefromat;

/**
 * @author Administrator
 *
 */
public class String_replace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string ="ËýËµ£ºaÄãºÃa";
		String newStr = string.replaceAll("a","b");
		System.out.println(newStr);
	}

}
