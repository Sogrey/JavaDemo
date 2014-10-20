/**
 * 
 */
package org.sogrey.datefromat;


/**
 * @author Administrator
 *
 */
public class StringReplace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "关关雎鸠，在河之洲。窈窕淑女，君子好逑。\n"
				+ "参差荇菜，左右流之。窈窕淑女，寤寐求之。\n"
				+ "求之不得，寤寐思服。悠哉悠哉，辗转反侧。\n"
				+ "参差荇菜，左右采之。窈窕淑女，琴瑟友之。\n"
				+ "参差荇菜，左右芼之。窈窕淑女，钟鼓乐之。\n";
		String subString  ="2" ; 
		String repString = "8";
		System.out.println(str.replaceAll(subString, repString)); 
		
	}

}
