package com.lunchtasting.server.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.DigestException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.MD5Util;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;
import com.squareup.okhttp.Request;

public class WeChatHelper {
	
	private static String characterEncoding = "UTF-8";
	
	/**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     *
     * @param request
     * @return
     */
    public static boolean isWeiXin(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
            Matcher m = p.matcher(userAgent);
            String version = null;
            if (m.find()) {
                version = m.group(1);
            }
            return (null != version && NumberUtils.toInt(version) >= 5);
        }
        return false;
    }
    
	
	/**
	 * map转换为xml
	 * @param arr
	 * @return
	 */
	public static String arrayToXml(Map<String, Object> arr) {
		String xml = "<xml>";

		Iterator<Entry<String, Object>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = (String)entry.getKey();
			Object val = (Object)entry.getValue();
			xml += "<" + key + ">" + val + "</" + key + ">";
		}

		xml += "</xml>";
		return xml;
	}
	
	/**
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Map<String, String> doXMLParse(String xml)
			throws XmlPullParserException, IOException {
		xml = xml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());

		Map<String, String> map = null;

		XmlPullParser pullParser = XmlPullParserFactory.newInstance()
				.newPullParser();
		pullParser.setInput(inputStream, "UTF-8"); // 为xml设置要解析的xml数据
		int eventType = pullParser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				map = new HashMap<String, String>();
				break;

			case XmlPullParser.START_TAG:
				String key = pullParser.getName();
				if (key.equals("xml"))
					break;

				String value = pullParser.nextText();
				map.put(key, value);

				break;

			case XmlPullParser.END_TAG:
				break;

			}

			eventType = pullParser.next();
		}

		return map;
	}
	/**
	 * 返回输出方法
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public static String printXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";

	}
	/**
	 * 获得返回的xml数据
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public static String getResponseXmlString(HttpServletRequest request, HttpServletResponse response) throws IOException{
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return new String(outSteam.toByteArray(),TenpayConfig.CHARSET);
	}
	
	/**
	 * 生成签名
	 * @param parameters
	 * @return sign
	 */
	public static String createSign(SortedMap<Object, Object> parameters){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) { 
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + WeChatConfig.APP_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
	/**
	 * 订单号随机获取
	 * @return
	 */
	public static String getOrderNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return  df.format(new Date()) + "" + Utils.getRandomNumber(5);
	}
	
	/**
	 * 获得支付签名参数 
	 * @param orderCode
	 * @param price
	 * @param name
	 * @param nonceStr
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static SortedMap getSignMap(String openid,String orderCode, double price, String body,String nonceStr,String notifyUrl,String attach,HttpServletRequest request) throws UnsupportedEncodingException {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WeChatConfig.APP_ID);
		parameters.put("mch_id", WeChatConfig.MCH_ID);
		parameters.put("openid", openid);
		parameters.put("nonce_str", nonceStr);
		parameters.put("body", WeChatConfig.MCH_NAME+"-"+body);
		parameters.put("out_trade_no",orderCode);
		parameters.put("total_fee",getWxPrice(price));
		parameters.put("spbill_create_ip",getIp(request));
		parameters.put("notify_url",notifyUrl);
		parameters.put("trade_type","JSAPI");
		if(ValidatorHelper.isNotEmpty(attach)){
			parameters.put("attach", attach);
		}
		
		String sign = createSign(parameters);
		parameters.put("sign", sign);
		return parameters;
	}
	
	/**
	 * 获得退款签名参数 
	 * @param outTradeNo
	 * @param outRefundNo
	 * @param totalFee
	 * @param refundFee
	 * @return
	 */
	public static SortedMap getRefundSignMap(String outTradeNo,String outRefundNo,double totalFee,double refundFee){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WeChatConfig.APP_ID);
		parameters.put("mch_id", WeChatConfig.MCH_ID);
		parameters.put("nonce_str", getNonceStr());
		parameters.put("out_trade_no", outTradeNo);
		parameters.put("out_refund_no", outRefundNo);
		parameters.put("total_fee", getWxPrice(totalFee));
		parameters.put("refund_fee", getWxPrice(refundFee));
		parameters.put("op_user_id", TenpayConfig.MCH_ID);
		
		String sign = createSign(parameters);
		parameters.put("sign", sign);
		return parameters;
	}
	
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取prepay_id
	 * @return
	 * @throws Exception
	 */
	public static String getPrepayId(Map<String,Object> mapCondition) throws Exception{
		SortedMap<String,Object> map = new TreeMap<String,Object>();
		map.putAll(mapCondition);

		String xml = arrayToXml(map);
		System.out.println("======================传递=========================="+"\n"+xml);
		StringEntity entity = new StringEntity(xml, TenpayConfig.CHARSET);
		HttpPost request = new HttpPost(TenpayConfig.PREPAY_ID_URL);
		request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			System.out.println("======================收到=========================="+"\n"+new String(result.getBytes("iso-8859-1"), "utf-8"));
			if (ValidatorHelper.isNotEmpty(result)) {
				Map xmlMap = doXMLParse(result);
				if(xmlMap.get("return_code").equals("SUCCESS") && xmlMap.get("result_code").equals("SUCCESS")){
					return xmlMap.get("prepay_id").toString();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 订单退款
	 * @param parameters
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map refund(SortedMap parameters) throws Exception{
		if(parameters == null){
			return null;
		}
		
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	    FileInputStream instream = new FileInputStream(new File(WeChatConfig.APICLIENT_CERT));
	    try {
	    	keyStore.load(instream, WeChatConfig.MCH_ID.toCharArray());
	    } finally {
	    	instream.close();
	    }
	    
	    SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WeChatConfig.MCH_ID.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        
        try {
			String xml = arrayToXml(parameters);
	        StringEntity requestEntity = new StringEntity(xml, characterEncoding); 
			System.out.println("#WeChatHelper# orderRefund() parameters = " + xml);
	        
	        HttpPost request = new HttpPost(TenpayConfig.ORDER_REFUND_URL);  
	        request.setEntity(requestEntity);
	        CloseableHttpResponse response = httpclient.execute(request);
	        
	        try {
	    		if(response.getStatusLine().getStatusCode()==200){
					String result = EntityUtils.toString(response.getEntity());
					System.out.println("#WeChatHelper# orderRefund() return = " + new String(result.getBytes("iso-8859-1"), "utf-8"));
					if (ValidatorHelper.isNotEmpty(result)) {
						Map xmlMap = doXMLParse(result);
						if(xmlMap.get("return_code").equals("SUCCESS") 
								&& xmlMap.get("result_code").equals("SUCCESS")){
							return xmlMap;
						}
					}
	    		}
	        	
            } finally {
                response.close();
            }
	        
        }finally{
        	httpclient.close();
		}
		return null;
	}
	
	/**
	 * 得到OpenId
	 * @param code
	 * @return
	 */
	public static JSONObject getOpenID(String code){
		String appSecret=WeChatConfig.APP_KEY;
		String appid=WeChatConfig.APP_ID;
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
				"appid=" + appid +
				"&secret=" + WeChatConfig.APP_SECRET +
				"&code=" + code +
				"&grant_type=authorization_code";
		String result = HttpUtil.queryStringForGet(url);
		if(result.length()==0||result==null||result.equals("")){
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(result);
		if(jsonObject.getString("openid")==null){
			return null;
		}
		return jsonObject;
	}
	
	/**
	 * 得到用户信息
	 * @param code
	 * @return
	 */
	public static JSONObject getWebchatUser (String ACCESS_TOKEN,String OPENID){
		String appSecret=WeChatConfig.APP_SECRET;
		String appid=WeChatConfig.APP_ID;
		String url = "https://api.weixin.qq.com/sns/userinfo?" +
				"access_token="+ACCESS_TOKEN +
				"&openid="+OPENID +
				"&lang=zh_CN";
		String str = HttpUtil.queryStringForGet(url);
		if(str.length()==0 || str==null || str.equals("")){
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(str);
		return jsonObject;
	}
	
	/**
	 * 得到AccessToken
	 * 
	 * @return
	 */
	public static String getAccessToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WeChatConfig.APP_ID+"&secret="+WeChatConfig.APP_SECRET+"";
		String result = HttpUtil.queryStringForGet(url);
		if(result.length()==0||result==null||result.equals("")){
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(result);
		if(jsonObject.getString("access_token")==null){
			return null;
		}
		return jsonObject.getString("access_token");
	}
	
	/**
	 * 得到Ticket
	 * 
	 * @return
	 */
	public static String getTicket(HttpServletRequest request){
		String accessToken;
//		if(request.getSession().getAttribute("accessToken") == "null" || request.getSession().getAttribute("accessToken") == null){
			accessToken = getAccessToken();
//			request.getSession().setAttribute("accessToken", accessToken);
//		}else{
//			accessToken = (String) request.getSession().getAttribute("accessToken");
//		}
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		String result = HttpUtil.queryStringForGet(url);
		if(result.length()==0||result==null||result.equals("")){
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(result);
		if(!"ok".equals(jsonObject.getString("errmsg"))){
			return null;
		}
		return jsonObject.getString("ticket");
	}
	
    /** 
     * @description： SHA、SHA1加密
     * @parameter：   str：待加密字符串
     * @return：  加密串
    **/
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String sign(SortedMap<String,Object> parameters){
		StringBuffer sb = new StringBuffer();
		//所有参与传参的参数按照accsii排序（升序）
		Set<Entry<String, Object>> es = parameters.entrySet();
		Iterator<Entry<String, Object>> it = es.iterator();
		while(it.hasNext()) { 
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();
			String k = entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");       
			}
		}
		String sign = SHA1(sb.toString().substring(0,sb.toString().length()-1));
		return sign;
	}
	    
    /**
	 * 获得签名参数 
	 * @param orderCode
	 * @param price
	 * @param name
	 * @param nonceStr
	 * @return
	 */
	public static SortedMap getSign(String nonceStr, String ticket,String timestamp,String url) {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("noncestr", nonceStr);
		parameters.put("jsapi_ticket", ticket);
		parameters.put("timestamp", timestamp);
		parameters.put("url", url);
		return parameters;
	}
	
    /**
	 * 随机字符串，不长于32位
	 * @return nonceStr
	 */
	public static String getNonceStr(){
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), characterEncoding);
	}
	
	/**
	 * 时间戳，当前的时间
	 * @return timeStamp
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 微信支付价格 单位分
	 * @param price
	 * @return
	 */
	public static int getWxPrice(double price){	
		price = price*100;
		String result = "";
		if(price % 1.0 == 0){	
			 result =  String.valueOf((long)price);		   
		}else{
			 result = String.valueOf(price);
		}		   
		return Integer.parseInt(result);
	}
	
	/**
	 * 获得第三方唯一交易码
	 * 例如：微信、支付宝付款和退款码
	 * 24位唯一码
	 * @return
	 */
	public static String getRefundNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println( df.format(new Date()).length());
		return  df.format(new Date()) + "" + Utils.getRandomNumber(10);
	}
		
}
