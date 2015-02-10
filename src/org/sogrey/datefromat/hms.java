/**
 * 
 */
package org.sogrey.datefromat;


/**
 * @author Administrator
 *
 */
public class hms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(second2minitue(22229));
	}
	public static String second2minitue(long s){
		String time  = "";
		if (0<s/60/60) {
			time+=s/60/60+"h";
			s-=s/60/60 *60 * 60;
		}
		if (0<s/60) {
			time+=s/60+"m";
			s-=s/60 *60 ;
		}
		time+=s+"s";
		return time;
	}
}
