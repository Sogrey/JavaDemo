/**
 * 
 */
package org.sogrey.datefromat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *
 */
public class isSMJ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
int id = 1554;
System.out.println(isSMJ(id));
	}
	
	/** 过滤题型，只保留选择判断三大类 */
	public static boolean isSMJ(int id) {
		boolean result = false;
		String IDStr = String.valueOf(id);
		String str = "^15[1|2|4]\\d*$";// 判断类
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(IDStr);
		result = m.find();
		System.out.println(result + ",id==" + id);
		return result;
	}

}
