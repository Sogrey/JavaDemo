/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;


/**
 * @author Administrator
 * 
 */
public class IndexInArr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = { "1", "2", "3", "4", "5" };
		String index = indexInArr(arr, 3);
		System.out.println(index);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		System.out.println(list.toString());
		int indexInt = CheckSubstrInParentIndex(list, "3");
		System.out.println(indexInt + "");
	}

	/** ���ĳ��Ԫ���������еĵڼ�������0��ʼ�� */
	public static String indexInArr(String[] arr, int d) {
		int index = 0;
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			if (d == Integer.parseInt(arr[i])) {
				index = i;
			}
		}
		if (index != arr.length - 1) {// ���һ��
			result = arr[index + 1];
		}
		return result;
	}

	/** ��ѯָ���ַ������ַ��������е����� */
	public static int CheckSubstrInParentIndex(ArrayList<String> parent,
			String subStr) {
		int index = 0;
		if (null != parent && 0 != parent.size()) {
			for (int i = 0; i < parent.size(); i++) {
				if (subStr.equals(parent.get(i))) {
					index = i;
				}
			}
		}
		return index;
	}
}
