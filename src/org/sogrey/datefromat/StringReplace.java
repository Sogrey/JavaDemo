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
		String str = "�ع���𣚣�ں�֮�ޡ�����Ů�����Ӻ��ϡ�\n"
				+ "�β����ˣ�������֮������Ů�������֮��\n"
				+ "��֮���ã����˼�����������գ�շת���ࡣ\n"
				+ "�β����ˣ����Ҳ�֮������Ů����ɪ��֮��\n"
				+ "�β����ˣ������d֮������Ů���ӹ���֮��\n";
		String subString  ="2" ; 
		String repString = "8";
		System.out.println(str.replaceAll(subString, repString)); 
		
	}

}
