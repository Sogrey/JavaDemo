/**
 * 
 */
package org.sogrey.datefromat;

/**
 * @author Administrator
 * 
 */
public class isBMP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="";
		 str = "http://123.139.89.90:5080/upload/image/20140807/20140807053256_33.bmp";
		System.out.println(isBmp(str));
		str = "http://123.139.89.90:5080/upload/image/20140807/20140807071654_981.png";
		System.out.println(isBmp(str));
	}

	private static boolean isBmp(String source) {
		boolean result = false;
		String[] arr = source.split("\\.");

		if (arr[arr.length - 1].indexOf("bmp") != -1 || arr[arr.length - 1].indexOf("BMP") != -1  ) {
			result = true;
		}
		return result;
	}
}
