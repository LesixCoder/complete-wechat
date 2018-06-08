package com.lunchtasting.server.payment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
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
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.MD5Util;
import com.lunchtasting.server.util.ValidatorHelper;


/**
 * 财富通支付帮助类
 * Created on 2016-11-9
 * @author xuqian
 *
 */
public class TenpayHelper {
	
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TENPAY);

	
	/**
	 * 获得微信的预支付订单
	 * @param map
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public String getPrepayId(SortedMap parameters) throws JDOMException, IOException {
		if(parameters == null){
			return null;
		}
		
		String xml = getParamXml(parameters);
        StringEntity entity = new StringEntity(xml, TenpayConfig.CHARSET); 
        HttpPost request = new HttpPost(TenpayConfig.PREPAY_ID_URL);  
        request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		logger.info("#TenpayHelper# getPrepayId() parameters = " + xml);
		
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			logger.info("#TenpayHelper# getPrepayId() return = " + new String(result.getBytes("iso-8859-1"), "utf-8"));

			
			if (ValidatorHelper.isNotEmpty(result)) {
				Map xmlMap = doXMLParse(result);
				if(xmlMap.get("return_code").equals("SUCCESS") 
						&& xmlMap.get("result_code").equals("SUCCESS")){
					
//					Map map = new HashMap();
//					map.put("prepay_id", xmlMap.get("prepay_id").toString());
//					map.put("sign", xmlMap.get("sign").toString());
//					map.put("nonce_str", xmlMap.get("nonce_str").toString());
					return xmlMap.get("prepay_id").toString();
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 检查订单是否支付成功
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public Boolean checkPaySuccess(SortedMap parameters) throws JDOMException, IOException{
		if(parameters == null){
			return false;
		}
		
		String xml = getParamXml(parameters);
        StringEntity entity = new StringEntity(xml, TenpayConfig.CHARSET); 
        HttpPost request = new HttpPost(TenpayConfig.ORDER_QUERY_URL);  
        request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		logger.info("#TenpayHelper# checkPaySuccess() parameters = " + xml);
		
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			logger.info("#TenpayHelper# checkPaySuccess() return = " + new String(result.getBytes("iso-8859-1"), "utf-8"));
			if (ValidatorHelper.isNotEmpty(result)) {
				Map xmlMap = doXMLParse(result);
				if(xmlMap.get("return_code").equals("SUCCESS") 
						&& xmlMap.get("result_code").equals("SUCCESS")
						&& xmlMap.get("trade_state").equals("SUCCESS")){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 订单退款
	 * @param parameters
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public Map refund(SortedMap parameters) throws Exception{
		if(parameters == null){
			return null;
		}
		
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	    FileInputStream instream = new FileInputStream(new File(TenpayConfig.ZHENGSHU));
	    try {
	    	keyStore.load(instream, TenpayConfig.MCH_ID.toCharArray());
	    } finally {
	    	instream.close();
	    }
	    
	    SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, TenpayConfig.MCH_ID.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        
        try {
			String xml = getParamXml(parameters);
	        StringEntity requestEntity = new StringEntity(xml, TenpayConfig.CHARSET); 
			logger.info("#TenpayHelper# orderRefund() parameters = " + xml);
	        
	        HttpPost request = new HttpPost(TenpayConfig.ORDER_REFUND_URL);  
	        request.setEntity(requestEntity);
	        CloseableHttpResponse response = httpclient.execute(request);
	        
	        try {
	    		if(response.getStatusLine().getStatusCode()==200){
					String result = EntityUtils.toString(response.getEntity());
					logger.info("#TenpayHelper# orderRefund() return = " + new String(result.getBytes("iso-8859-1"), "utf-8"));
					if (ValidatorHelper.isNotEmpty(result)) {
						Map xmlMap = doXMLParse(result);
						if(xmlMap.get("return_code").equals("SUCCESS") 
								&& xmlMap.get("result_code").equals("SUCCESS")){
							return xmlMap;
						}
					}
	    		}
	        	
	        	
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    System.out.println("Response content length: " + entity.getContentLength());
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
//                    String text;
//                    while ((text = bufferedReader.readLine()) != null) {
//                        System.out.println(text);
//                    }
                   
//                }
                //EntityUtils.consume(entity);
//                consume(entity);
            } finally {
                response.close();
            }
	        
        }finally{
        	httpclient.close();
		}
		return null;
	}
	
	/**
	 * 退款订单查询
	 * @param outTradeNo
	 * @param outRequestNo
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public Map refundQuery(SortedMap parameters) throws JDOMException, IOException{
		if(parameters == null){
			return null;
		}
		
		String xml = getParamXml(parameters);
        StringEntity entity = new StringEntity(xml, TenpayConfig.CHARSET); 
        HttpPost request = new HttpPost(TenpayConfig.ORDER_REFUND_QUERY_URL);  
        request.setEntity(entity);
		HttpResponse response = new DefaultHttpClient().execute(request);
		logger.info("#TenpayHelper# refundQuery() parameters = " + xml);
		
		if(response.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(response.getEntity());
			logger.info("#TenpayHelper# refundQuery() return = " + new String(result.getBytes("iso-8859-1"), "utf-8"));
			if (ValidatorHelper.isNotEmpty(result)) {
				Map xmlMap = doXMLParse(result);
				if(xmlMap.get("return_code").equals("SUCCESS") 
						&& xmlMap.get("result_code").equals("SUCCESS")){
					return xmlMap;
				}
			}
		}
		return null;
	}
	
	/**
	 * 创建订单查询签名参数
	 * @param outTradeNo
	 * @return
	 */
	public SortedMap createQueryPayOrderParam(String outTradeNo){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", TenpayConfig.APP_ID);
		parameters.put("mch_id", TenpayConfig.MCH_ID);
		parameters.put("out_trade_no", outTradeNo);
		parameters.put("nonce_str", getNonceStr());
		
		String sign = createSign(parameters);
		parameters.put("sign", sign);
		return parameters;
		
	}
	
	/**
	 * 创建订单退款签名参数
	 * @param outTradeNo
	 * @return
	 */
	public SortedMap createRefundParam(String outTradeNo,String outRefundNo,double totalFee,double refundFee){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", TenpayConfig.APP_ID);
		parameters.put("mch_id", TenpayConfig.MCH_ID);
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
	 *  创建订单退款查询签名参数
	 * @param outTradeNo
	 * @param outRefundNo
	 * @return
	 */
	public SortedMap createRefundQueryParam(String outTradeNo,String outRefundNo){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", TenpayConfig.APP_ID);
		parameters.put("mch_id", TenpayConfig.MCH_ID);
		parameters.put("nonce_str", getNonceStr());
		//parameters.put("out_trade_no", outTradeNo);  //查询所有的退款
		parameters.put("out_refund_no", outRefundNo); //查询当前一次的退款
		
		String sign = createSign(parameters);
		parameters.put("sign", sign);
		return parameters;
	}

	
	/**
	 * 获得请求参数 带参数的
	 * @param map
	 * @param baseUrl
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String getRequestURL(Map map,String baseUrl) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			
			if("spbill_create_ip".equals(k)) {
				sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
			} else {
				sb.append(k + "=" + URLEncoder.encode(v, "UTF-8") + "&");
			}
		}
		
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		return baseUrl + "?" + reqPars;
	}
	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	private String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 创建财付通签名
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	public String createSign(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + TenpayConfig.APP_KEY);
		System.out.println("key = " + sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), TenpayConfig.CHARSET)
				.toUpperCase();
		return sign;
	}
	
	/**
	 * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @return boolean
	 */
	public boolean isTenpaySign(SortedMap parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}
		
		sb.append("key=" + TenpayConfig.APP_KEY);
		
		//算出摘要
		String enc = TenpayConfig.CHARSET;
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
		
		String tenpaySign = parameters.get("sign").toString().toLowerCase();
		
		return tenpaySign.equals(sign);
	}
	
	/**
     * 将请求参数转换为xml格式的string
     * @param parameters  请求参数
     * @return
     */
	public String getParamXml(SortedMap parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = String.valueOf(entry.getValue().toString());
            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
	
	/**
	 * 获得返回的xml数据
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public String getResponseXmlString(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
	 * 随机数
	 * @return
	 */
	public String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
	
	/**
	 * 返回输出方法
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public String printXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";

	}
	
	/**
	 * 关闭entity流
	 * @param entity
	 * @throws IOException
	 */
	private void consume(final HttpEntity entity) throws IOException {  
		if (entity == null) {  
			return;  
		}  
		if (entity.isStreaming()) {  
			final InputStream instream = entity.getContent();  
		    if (instream != null) {  
		    	instream.close();  
		    }  
		}  
	}  
	
	/**
	 * 微信支付价格 单位分
	 * @param price
	 * @return
	 */
	public int getWxPrice(double price){	
		price = price*100;
		String result = "";
		if(price % 1.0 == 0){	
			 result =  String.valueOf((long)price);		   
		}else{
			 result = String.valueOf(price);
		}		   
		return Integer.parseInt(result);
	}

	
	public static void main(String[] args) throws JDOMException, IOException {
//		String str = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg>" +
//				"<appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id>" +
//				"<nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>" +
//				"<sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>" +
//				"<result_code><![CDATA[SUCCESS]]></result_code>" +
//				"<prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
//				"<trade_type><![CDATA[APP]]></trade_type></xml>";
		
		TenpayHelper he = new TenpayHelper();
//		System.out.println(he.getNonceStr());
		//System.out.println(doXMLParse(str));
		System.out.println(he.refundQuery(he.createRefundQueryParam("201611281710363872373041", "201611281711131505171236")));
		
	}
}
