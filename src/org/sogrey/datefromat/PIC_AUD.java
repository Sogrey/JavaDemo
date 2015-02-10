/**
 * 
 */
package org.sogrey.datefromat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.TextUI;

/**
 * @author Administrator
 * 
 */
public class PIC_AUD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 final String RES_URL = "http://media.liren-eschoolbag.com";
//		String str = "pic:/xxx01;/xxx02#*#audio:/yyy03;/yyy04#*#video:/zzz05;/zzz06";
//		String str = "pic:xxx01;xxx02#*#audio:yyy03;yyy04";
//		String str = "aud:yyy03;yyy04";
//		String str = "pic:xxx01;xxx02";
//		String str = "";
		String str="pic:/upload/exam/20150123/uycce41422244464218.jpg;/upload/exam/20150123/aw2idy1422244464219.jpg#*#audio:/upload/exam/20150123/5jbf2s1422244464139.mp3#*#video:/upload/exam/20150123/md1del1422244464136.mp4".toString();
		 String attach_audio="www.hao123.com;www.baidu.com";
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		map = getPicAndAud(str);
		System.out.println(map);
		if (null!=map) {
			String[] pic =map.get("pic");
			String[] audio =map.get("audio");
			String[] video =map.get("video");
			System.out.println(pic);
			if (null!=pic) for (int i = 0; i < pic.length; i++) {
				pic[i] = RES_URL+pic[i];
				System.out.println("pic["+i+"]=="+pic[i]);
			}
			System.out.println(audio);
			if (null!=audio)for (int i = 0; i < audio.length; i++) {
				audio[i] = RES_URL+audio[i];
				System.out.println("audio["+i+"]=="+audio[i]);
			}
			System.out.println(video);
			if (null!=video)for (int i = 0; i < video.length; i++) {
				video[i] = RES_URL+video[i];
				System.out.println("video["+i+"]=="+video[i]);
			}
		}
		attach_audio = attach_audio.replace(";", "\n");
		System.out.println(attach_audio);
		return;
	}
/**
 * 解析附件列表-获取到图片，音频，视频路径
 * @param str 接口返回的附件列表字符串
 * @return Map ：key：类别String，value：对应资源路径数组String[]
 * @see Map
 */
	private static Map<String, String[]> getPicAndAud(String str) {
		if (null==str||"".equals(str)) {
			return null;
		}
		Map<String, String[]> map = new HashMap<String,String[]>();
		
		boolean haveIt = str.contains("#*#");
		System.out.println(haveIt);
		String[] array = null;
		if (haveIt) {
			array = str.split("#\\*#");
		}else{
			array = new String[]{str};
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println("array[" + i + "]=" + array[i]);
			String[] array2 = array[i].split(":");
			String key = array2[0];
			String[] array3 =  array2[1].split(";");
			map.put(key, array3);
		}
		return map;
	}
}
