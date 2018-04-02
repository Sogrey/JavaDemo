package com.jac.jacmobile.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sogrey on 2018/3/28.
 */

public class GeoUtils {
	static String mcode = "31:8F:32:E8:72:53:F1:C0:C8:42:ED:12:AB:12:3D:6E:6D:CB:BD:68;com.jac.jacmobile";
	static String key = "pdoNIDvWQDsdcaKOb21cqNa69dufG69D";

	public static String getAddress(String location) {
		// http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=34.203034,108.891956&output=json&pois=1&mcode=10:04:EB:20:25:B5:AD:BF:AB:17:C9:01:F7:81:A1:56:B8:A5:4E:5B;cn.com.glendale.jqsb&ak=U41vseWnIrtM1pqDRPYWjOtcW3eR5ruq
		String url = String
				.format("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=%s&output=json&pois=1&mcode=%s&ak=%s",
						location, mcode, key);
		
		String json = loadJSON(url);
		System.out.println(json);
		return json;
	}

	/**
	 * 输入地址调用百度api获取经纬度
	 * 
	 * @param address
	 * @return 经度纬度的map
	 * @author SunQiChao
	 * @Date 2015年9月2日
	 */
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		// 调用百度接口
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
				+ "&output=json&mcode="+mcode+"&ak=" + key;
		String json = loadJSON(url);
//		JSONObject obj = JSONObject.fromObject(json);
//		if (obj.get("status").toString().equals("0")) {
//			double lng = obj.getJSONObject("result").getJSONObject("location")
//					.getDouble("lng");
//			double lat = obj.getJSONObject("result").getJSONObject("location")
//					.getDouble("lat");
//			map.put("lng", lng);
//			map.put("lat", lat);
//		}
		System.out.println(json);
		return map;
	}

	/**
	 * 处理url和所带的参数
	 * 
	 * @param url
	 * @return
	 * @author SunQiChao
	 * @Date 2015年9月2日
	 */
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}

	public static void main(String[] args) throws IOException {
		//地址获取经纬度
		getLngAndLat("陕西省西安市雁塔区丈八一路汇鑫IBC附近1米");
		//{"status":0,"result":{"location":{"lng":108.8919442936714,"lat":34.202934152785697},"precise":0,"confidence":80,"level":"鍟嗗姟澶у帵"}}


        //经纬度获取地址
		 getAddress("34.203034,108.891956");
		// renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":108.89195599999994,"lat":34.20303403835331},"formatted_address":"陕西省西安市雁塔区丈八一路","business":"","addressComponent":{"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"陕西省","city":"西安市","city_level":2,"district":"雁塔区","town":"","adcode":"610113","street":"丈八一路","street_number":"","direction":"","distance":""},"pois":[{"addr":"丈八一路1号(跳水馆对面)","cp":" ","direction":"附近","distance":"1","name":"汇鑫IBC","poiType":"房地产","point":{"x":108.89195610279563,"y":34.20301956544281},"tag":"房地产;写字楼","tel":"","uid":"b703e96b79f2fc878f1a89ce","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}},{"addr":"陕西省西安市雁塔区丈八一路汇鑫IBC12403室","cp":" ","direction":"附近","distance":"0","name":"心疼你网络科技有限公司","poiType":"公司企业","point":{"x":108.89194711974054,"y":34.203034493593047},"tag":"公司企业;公司","tel":"","uid":"b519b5b3b32f7c629900509b","zip":"","parent_poi":{"name":"汇鑫IBC","tag":"房地产;写字楼","addr":"丈八一路1号(跳水馆对面)","point":{"x":108.89195610279563,"y":34.20301956544281},"direction":"附近","distance":"1","uid":"b703e96b79f2fc878f1a89ce"}},{"addr":"西安市雁塔区丈八东路汇鑫IBC-B座8层","cp":" ","direction":"附近","distance":"8","name":"面对面互动教育","poiType":"教育培训","point":{"x":108.89202796723643,"y":34.20300463728991},"tag":"教育培训;培训机构","tel":"","uid":"b9023219dc88e8cf230220ae","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}},{"addr":"西安市雁塔区丈八东路汇鑫IBC-B座1层","cp":" ","direction":"附近","distance":"7","name":"汇鑫IBC国际商务中心","poiType":"公司企业","point":{"x":108.89201898418134,"y":34.203012101366699},"tag":"公司企业","tel":"","uid":"86085c4e19327b71eedfcae7","zip":"","parent_poi":{"name":"汇鑫IBC","tag":"房地产;写字楼","addr":"丈八一路1号(跳水馆对面)","point":{"x":108.89195610279563,"y":34.20301956544281},"direction":"附近","distance":"1","uid":"b703e96b79f2fc878f1a89ce"}},{"addr":"西安市雁塔区丈八一路汇鑫IBC-C座4层","cp":" ","direction":"附近","distance":"7","name":"西安贝加尔网络科技有限公司","poiType":"公司企业","point":{"x":108.89201898418134,"y":34.203012101366699},"tag":"公司企业;公司","tel":"","uid":"d272bd88670b3e2e4f3a596f","zip":"","parent_poi":{"name":"汇鑫IBC-C座","tag":"房地产;写字楼","addr":"陕西省西安市雁塔区汇鑫国际IBC国际商务中心C座","point":{"x":108.89177644169364,"y":34.20240750898598},"direction":"北","distance":"86","uid":"328789c7b00660373586f44d"}},{"addr":"西安高新区丈八东路汇鑫IBC","cp":" ","direction":"附近","distance":"8","name":"i广场","poiType":"美食","point":{"x":108.89201898418134,"y":34.20300463728991},"tag":"美食;中餐厅","tel":"","uid":"212e71c83f515df88b36ee0d","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}},{"addr":"高新区太白南路高山流水和诚第16幢1单元4层","cp":" ","direction":"附近","distance":"47","name":"陕北窑洞","poiType":"酒店","point":{"x":108.89234237416493,"y":34.20319123900941},"tag":"酒店;其他","tel":"","uid":"10010a4c1a3f59a5a0acc467","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}},{"addr":"陕西省体育训练中心南门对面汇鑫IBC-B座1层","cp":" ","direction":"附近","distance":"48","name":"法国之光·葡萄酒","poiType":"购物","point":{"x":108.89223457750373,"y":34.2033106638911},"tag":"购物;商铺","tel":"","uid":"8e781028ab145393c929150f","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}},{"addr":"汇鑫国际IBC国际商务中心A座","cp":" ","direction":"南","distance":"59","name":"汇鑫国际IBC国际商务中心A座","poiType":"房地产","point":{"x":108.89182135696913,"y":34.20346740878921},"tag":"房地产;写字楼","tel":"","uid":"5b37443de29841febbbd63f4","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}},{"addr":"陕西省体育训练中心南门对面汇鑫IBC-A座1层","cp":" ","direction":"南","distance":"66","name":"招商银行(高新科技支行)","poiType":"金融","point":{"x":108.89176745863854,"y":34.20350472895968},"tag":"金融;银行","tel":"","uid":"40c452b906142635343a7108","zip":"","parent_poi":{"name":"","tag":"","addr":"","point":{"x":0.0,"y":0.0},"direction":"","distance":"","uid":""}}],"roads":[],"poiRegions":[],"sematic_description":"汇鑫IBC附近1米","cityCode":233}})
	}
}
