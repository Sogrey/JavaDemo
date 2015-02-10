package org.sogrey.datefromat;

import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *
 */
public class GetZHFromString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  String s = "a99中国b香港c恒生dd嘿嘿";
		  String regex = "\\w";
		  Pattern p = Pattern.compile(regex);
		  String intercept = "";
		  String intercept2 = "";
		  Vector<String> output =  new Vector<String>();
		  
		  for(int i = 0;i < s.length();i ++){
		   String input = s.substring(i,i + 1);
		   Matcher m = p.matcher(input);
		   boolean b = m.matches();
		   if(b){
		    output.add(intercept2);
		    intercept2 = "";
		    intercept = intercept + input;
		    
		   }else{
		    output.add(intercept);
		    intercept = "";
		    intercept2 = intercept2 + input;
		   }
		  }
		  //添加最后一串字符
		  if(intercept.equals("")){
		   output.add(intercept2);
		  }else{
		   output.add(intercept);
		  }
		  
		  Iterator it = output.iterator();
		  System.out.println("原始字符串为：" + s);
		  System.out.print("分隔后的字符串为:");
		  while(it.hasNext()){
		   System.out.print(((String)it.next()).trim());
		   if(it.hasNext()){
		    System.out.print(" ");
		   }
		  }
	}

}
