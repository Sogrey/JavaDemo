/**
 * 
 */
package org.sogrey.datefromat;

/**
 * @author Administrator
 *
 */
public class Stringsize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "";
		int Stringsize =0;
		
		System.out.println("Stringsize=="+str.split(";").length);
		if(!"".equals(str)) {
			Stringsize=str.split(";").length;
		}
		System.out.println("Stringsize=="+Stringsize);
	}

}
