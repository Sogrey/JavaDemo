/**
 * @author Sogrey
 * 2015��3��21��
 */
package org.sogrey.url;

import java.net.URLEncoder;

/**
 * @author Sogrey 2015��3��21��
 */
public class URLTest {

	/**
	 * @author Sogrey 2015��3��21��
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "������";
		System.out.println(URLEncoder.encode(str));
	}
	
}
