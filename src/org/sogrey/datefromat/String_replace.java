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
		
//		replease();
		replease3();
		replease2();

	}

	private static void replease3() {
		String str1="[\"张三\",\"李四\"]";
		System.out.println(str1);
		str1 = str1.replace("[", "");
		System.out.println(str1);
		str1 = str1.replace("]", "");
		System.out.println(str1);
	}

	private static void replease2() {
		String str1="[\"张三\",\"李四\"]";
		System.out.println(str1);
		String str2 = str1.replaceAll("[", "");
//		str1.replaceAll("[", "");
		System.out.println(str2);
//		str1.replaceAll("]", "");
//		System.out.println(str1);
	}

	private static void replease() {
		String string ="她说：a你好a";
		String newStr = string.replaceAll("a","b");
		System.out.println(newStr);
	}

}
