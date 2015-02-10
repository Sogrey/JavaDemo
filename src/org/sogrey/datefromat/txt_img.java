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
public class txt_img {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		eg();
	}

	private static void eg() {
		String str = "8. 如图{0}，已知FD∥BE，则∠1+∠2-∠3=(????) {1}@{/upload/image/20140807/20140807091249_606.gif,}";
		String pat = "^{\\d}$";
//		Pattern p = Pattern.compile(pat, Pattern.MULTILINE);
//		Matcher m = p.matcher(pat,str);
		Pattern pattern = Pattern.compile(pat,
				Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(str);
//		Pattern.matches(pat, str);
		int index = 0;
		while (matcher.find()) {
//			index++;
//			System.out.println(matcher.group(index));
			System.out.println(matcher.group(0));
		}
	}
}
