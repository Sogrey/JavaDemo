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
		String answer ="���,�õ�,����, ����";
		String[] answerList = answer.split(",");
		ArrayList<String> str = new ArrayList<String>();
		for (int i = 0; i < answerList.length; i++) {
			System.out.println("answerList["+i+"]=="+answerList[i]);
			str.add(answerList[i]);
		}
		System.out.println("����"+answerList.toString());
		System.out.println("����"+str.toString());
		
		System.out.println("=============");
		String answer2 ="���";
		String[] answerList2 = answer2.split(",");
		for (int i = 0; i < answerList2.length; i++) {
			System.out.println("answerList2["+i+"]=="+answerList2[i]);
		}
		System.out.println("=============");
		String s ="xxx;yyy";
//		answerList ���ֺŷָ���䵽ÿ���հ���
		String[] StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
		System.out.println("=============");
		 s =";";
//		answerList ���ֺŷָ���䵽ÿ���հ���
		 StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
		System.out.println("=============");
		s ="";
//		answerList ���ֺŷָ���䵽ÿ���հ���
		StrList = s.split(";");
		for (int i = 0; i < StrList.length; i++) {
			System.out.println("StrList["+i+"]=="+StrList[i]);
		}
		System.out.println("StrList.size=="+StrList.length);
	}

}
