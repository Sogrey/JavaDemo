/**
 * @author Sogrey
 * 2015年3月21日
 */
package org.sogrey.url;

import java.net.URLEncoder;

/**
 * @author Sogrey 2015年3月21日
 */
public class URLTest {

	/**
	 * @author Sogrey 2015年3月21日
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "西安市";
		System.out.println(URLEncoder.encode(str));
	}
	
}
