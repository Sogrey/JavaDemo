/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 * 
 */
public class txt_img2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "8. 如图{0}，已知FD∥BE，{1}则∠1+∠2-∠3=(    ) {2}"
//				+ "@{/upload/image/20140807/20140807091249_606.gif"
//				+ "/upload/image/20140807/20140807091249_707.gif"
//				+ "/upload/image/20140807/20140807091249_808.gif}";
		String str ="选项E-pic{0},这组有本地图片{1}@{/upload/image/20140807/20140807091249_606.gif/upload/image/20140807/20140807091249_707.gif}";
		Map<String, ArrayList<String>> map = patternPic(str);
		for (Map.Entry entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + "\nvalue= "
					+ entry.getValue());
		}
	}

	public static Map<String, ArrayList<String>> patternPic(String str) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		String[] arr = str.split("@");
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "]=" + arr[i]);
		}
		// arr[1]掐头去尾
		arr[1] = arr[1].replace("{", "");
		arr[1] = arr[1].replace("}", "");
		System.out.println("arr[1]==" + arr[1]);
		String[] arr2 = arr[1].split("/upload/");
		ArrayList<String> listImgs = new ArrayList<String>();
		for (int i = 0; i < arr2.length; i++) {
			// if (!TextUtils.isEmpty(arr2[i])) {
			if (!"".equals(arr2[i])) {
				listImgs.add("/upload/" + arr2[i]);
			}
		}
		System.out.println(listImgs.toString());

		String pat = "\\{\\d+\\}";
		Pattern pattern1 = Pattern.compile(pat);
		Matcher matcher1 = pattern1.matcher(arr[0]);
		arr[0] = matcher1.replaceAll("#**#");
		System.out.println(arr[0]);
		map.put(arr[0], listImgs);
		return map;
	}

}
