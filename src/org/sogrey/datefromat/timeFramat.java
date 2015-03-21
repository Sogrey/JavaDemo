/**
 * 
 */
package org.sogrey.datefromat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Administrator
 *
 */
public class timeFramat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String[] formats = new String[] {
				   "yyyy-MM-dd",
				   "yyyy-MM-dd HH:mm",
				   "yyyy-MM-dd HH:mmZ",
				   "yyyy-MM-dd HH:mm:ss.SSSZ",
				   "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
				 };
//		 long data = Long.parseLong("1421113317000");
		 long data = Long.parseLong("1426755593404");
				 for (String format : formats) {
				   SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
				   System.out.format("%30s %s\n", format, sdf.format(new Date(data)));
				   sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				   System.out.format("%30s %s\n", format, sdf.format(new Date(data)));
				 }
	}

}
