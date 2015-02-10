/**
 * 
 */
package org.sogrey.datefromat;

/**
 * @author Administrator
 *
 */
public class IndexInArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] array = {"1","3","4","6","7","9"};
		System.out.println(getIndexInArray(array, 9));
	}

	/**
	 * 判断元素在数组中的位置
	 * @param array
	 * @param item
	 * @return
	 */
	public static int getIndexInArray(String[] array, int item) {
		for (int i = 0; i < array.length; i++) {
			if (item==Integer.parseInt(array[i])) {
				return i;
			}
		}
		return 0;
	}
}
