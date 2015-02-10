/**
 * 
 */
package org.sogrey.datefromat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配
 * @author Administrator
 *
 */
public class Regex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] arr = {
				111,
				112,
				113,
				114,
				115,
				116,
				117,
				118,
				119,
				121,
				123,
				124,
				125,
				126,
				127,
				131,
				132,
				133,
				134,
				135,
				136,
				137,
				141,
				142,
				143,
				144,
				145,
				151,
				152,
				153,
				154,
				155,
				156,
				161,
				162,
				163,
				171,
				172,
				173,
				1111,
				1112,
				1113,
				1511,
				1512,
				1513,
				1531,
				1532,
				1533,
				1534,
				1535,
				1551,
				1552,
				1553,
				1554,
				1555,
				1556,
				1557,
				1558,
				1559,
				1561,
				1562,
				1563,
				1564,
				1565,
				1566,
				1567,
				1568,
				1569,
				15510,
				15511,
				15512,
				15513,
				15514,
				15515,
				15516,
				15517,
				15518,
				15519,
				15520,
				15521,
				15522,
				15523,
				15610
		};
		for (int i = 0; i < arr.length; i++) {
			isSMJ(arr[i]);
		}
		eg1();
	}
	private static void eg1() {
		String str="For my money, the important thing "+"about the meeting was bridge-building";
		String regEx="a|f"; //表示a或f 
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(str);
		boolean result=m.find();
		System.out.println(result);
	}
	private static boolean isSMJ(int  id){
		boolean result = false;
		String IDStr = String.valueOf(id);
//		String single = "^151\\d*$";
//		String mul = "^152\\d*$";
//		String judge = "^154\\d*$";
//		
//		Pattern p=Pattern.compile(single);
//		Matcher m=p.matcher(IDStr);
//		boolean resultSingle=m.find();
//		Pattern p1=Pattern.compile(mul);
//		Matcher m1=p1.matcher(IDStr);
//		boolean resultMul=m1.find();
//		Pattern p2=Pattern.compile(judge);
//		Matcher m2=p2.matcher(IDStr);
//		boolean resultJudge=m2.find();
//		result = resultSingle || resultMul || resultJudge;
		
		String str = "^15[1|2|4]\\d*$";
		Pattern p=Pattern.compile(str);
		Matcher m=p.matcher(IDStr);
		result=m.find();
		
		if (result) {
			System.out.println(result+",匹配=="+id);
		}
		return result;
	}
}
