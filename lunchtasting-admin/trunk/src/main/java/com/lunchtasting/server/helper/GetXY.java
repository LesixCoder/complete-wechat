package com.lunchtasting.server.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mortbay.util.UrlEncoded;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.util.HttpUtil;
/**
 * 获取地址经纬度(高德地图,百度地图)
 * @author ly
 *
 */
public class GetXY {
	private static String KEY = "d44588a85d744cd73f1844fe972873a8";
	private static Pattern pattern = Pattern
			.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");
	/**
	 * 高德(main方法没问题,程序里却获取不到。。。尼玛)
	 * @param address
	 * @return
	 */
	public static double[] ToGaoDeGPS(String address) {
		try {

			String url = String.format(
					"http://restapi.amap.com/v3/geocode/geo?address=%s&key=%s",
					address, KEY);
			URL myURL = null;
			URLConnection httpsConn = null;
			try {
				myURL = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			InputStreamReader insr = null;
			BufferedReader br = null;
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(),
						"UTF-8");
				br = new BufferedReader(insr);
				String data = "";
				String line = null;
				while ((line = br.readLine()) != null) {
					data += line;
				}
				System.out.println(data);
				Matcher matcher = pattern.matcher(data);
				double[] gps = new double[2];
				if (matcher.find() && matcher.groupCount() == 2) {
					gps[0] = Double.valueOf(matcher.group(1));
					gps[1] = Double.valueOf(matcher.group(2));
					return gps;
				} else {
					return gps;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * 百度
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public static String ToBaiDuGPS(String address) throws IOException {
		/**
		 * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using
		 * java.net.URL and //java.net.URLConnection
		 */
		String url_str = "http://api.map.baidu.com/geocoder?address=" + address + "&output=json";
		URL url = new URL(url_str);
		URLConnection connection = url.openConnection();

		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String sCurrentLine;
		String sTotalString;
		sCurrentLine = "";
		sTotalString = "";
		InputStream l_urlStream;
		l_urlStream = connection.getInputStream();
		// 传说中的三层包装阿！
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sTotalString += sCurrentLine + "/r/n";

		}
		JSONObject josn = JSONObject.parseObject(sTotalString.replace("/r/n", ""));
		if (josn.get("result").toString().length() > 2) {
			JSONObject josn1 = (JSONObject) JSONObject.toJSON(josn.get("result"));
			JSONObject josn2 = (JSONObject) JSONObject.toJSON(josn1.get("location"));
			return josn2.get("lng") + "," + josn2.get("lat");
		} else {
			// System.out.println("无数据返回！");
			return "0,0";
		}
	}
	
	/**
	 * 高德(没问题)
	 * @param address
	 * @return
	 */
	public static String getGaoDeXY(String address){
		 String addressStr = "http://restapi.amap.com/v3/geocode/geo?key=d44588a85d744cd73f1844fe972873a8&address=";
	     String getAddress = HttpUtil.queryStringForGet(addressStr + address);
	     JSONObject object = JSONObject.parseObject(getAddress);
	     JSONArray geocodes = object.getJSONArray("geocodes");
	     if (geocodes.size() == 1) {
	    	 JSONObject trueAddress = geocodes.getJSONObject(0);
	         String location = trueAddress.getString("location");
	         String lngX = location.split(",")[0];
	         String latY = location.split(",")[1];
	         return lngX + "," + latY;
	     } else {
	    	 return "0,0";
	     }
	}
	
	public static void main(String[] args) throws IOException {
		double [] data = ToGaoDeGPS("北京市昌平区老牛湾村");
        System.out.println("经度:"+data[0]);
        System.out.println("纬度:"+data[1]);
	}
}
