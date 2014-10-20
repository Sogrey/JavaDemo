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
public class date {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		fromatDate();
		dateStr();
		dateNow();
		dateNowFromat();
	}

	private static void dateNowFromat() {
		long date = System.currentTimeMillis();
		System.out.println("����ʱ�̣�ʱ�������"+date);
		//fromat
		String patternDate = "yyyy-MM-dd HH:mm:ss";
		/* new ��SimpleDateFormat ����sdf */
		SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
		/* ��ʽ����ǰʱ�丳�� dateString */
		String dateString = sdfDate.format(date);
		System.out.println(dateString);
	}

	private static void dateNow() {
		System.out.println("����ʱ�̣�ʱ�������"+new Date().getTime());
		
	}

	private static void dateStr() {
		/* ���ڸ�ʽ */
		String patternDate = "yyyy-MM-dd EEEE";
		/* new ��SimpleDateFormat ����sdf */
		SimpleDateFormat sdfDate = new SimpleDateFormat(patternDate);
		/* ��ʽ����ǰʱ�丳�� dateString */
		String dateString = sdfDate.format(new Date());
		System.out.println(dateString);

	}

	private static void fromatDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss",
				Locale.getDefault());
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		date.setTime(6000 * 1000);
		String time = formatter.format(date);
		System.out.println(time);
	}
}
