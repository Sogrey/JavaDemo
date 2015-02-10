/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class array2split {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String answer ="你好,好的,哈哈, 好了";
		String[] answerList = answer.split(",");
		ArrayList<String> str = new ArrayList<String>();
		for (int i = 0; i < answerList.length; i++) {
			System.out.println("answerList["+i+"]=="+answerList[i]);
			str.add(answerList[i]);
		}
		System.out.println("数组"+answerList.toString());
		System.out.println("集合"+str.toString());
		
		System.out.println("=============");
		String answer2 ="你好";
		String[] answerList2 = answer2.split(",");
		for (int i = 0; i < answerList2.length; i++) {
			System.out.println("answerList2["+i+"]=="+answerList2[i]);
		}
		System.out.println("=============");
		String s ="xxx;yyy";
//		answerList 按分号分割。分配到每个空白中
		String[] StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
		System.out.println("=============");
		 s =";";
//		answerList 按分号分割。分配到每个空白中
		 StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
		System.out.println("=============");
		s ="a";
//		answerList 按分号分割。分配到每个空白中
		StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
		System.out.println("=============");
		s ="1;2;3;4";
//		answerList 按分号分割。分配到每个空白中
		StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
		
		eg1();
		eg2();
	}
	
	private static void eg2() {
		 String s = "#**#选项E-pic#**#,这组有本地图片#**#";
		String[] arr = s.split("#\\*\\*#");
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr["+i+"]=="+arr[i]);
		}
	}

	private static void eg1() {
		String s ="string";
		int blankNum = SubstringCount(s, ";") + 1;//英文
		System.out.println("blankNum=="+blankNum);
	}

	/**
	 * 判断str中有几个substring
	 * 
	 * @param str 源字符串
	 * @param substring 子字符串
	 * @return
	 */
	public static int SubstringCount(String str, String substring) {
		if (str.contains(substring)) {
			String strReplaced = str.replace(substring, "");
			return (str.length() - strReplaced.length()) / substring.length();
		}
		return 0;
	}

}
