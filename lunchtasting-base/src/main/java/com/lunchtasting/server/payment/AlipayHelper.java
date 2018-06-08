package com.lunchtasting.server.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lunchtasting.server.config.AlipayConfig;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 支付宝支付帮助类
 * Created on 2016-11-9
 * @author xuqian
 *
 */
public class AlipayHelper {
	
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_ALIPAY);

	private AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.REQUEST_URL,AlipayConfig.APP_ID
			,AlipayConfig.PRIVATE_KEY,"json",AlipayConfig.CHARSET,AlipayConfig.PUBLIC_KEY);
  
	
	/**
	 * 检查支付是否成功
	 * @param outTradeNo
	 * @throws Exception
	 */
	public boolean checkPaySuccess(String outTradeNo) throws Exception{
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.REQUEST_URL,AlipayConfig.APP_ID
		,AlipayConfig.PRIVATE_KEY,"json",AlipayConfig.CHARSET,AlipayConfig.PUBLIC_KEY);	
		if(ValidatorHelper.isEmpty(outTradeNo)){
			return false;
		}
		
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" +
		"    \"out_trade_no\":\""+outTradeNo+"\"" +
		"}");
		logger.info("#AlipayHelper# checkPaySuccess() parameters = " +objectToJson(request));

		AlipayTradeQueryResponse response = alipayClient.execute(request);
		logger.info("#AlipayHelper# checkPaySuccess() return = " + objectToJson(response));
		if(response != null){
			if(response.isSuccess()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 支付宝退款
	 * @return
	 * @throws Exception 
	 */
	public Boolean refund(String outTradeNo,double price,String outRefundNo) throws Exception{
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.REQUEST_URL,AlipayConfig.APP_ID
//		,AlipayConfig.PRIVATE_KEY,"json",AlipayConfig.CHARSET,AlipayConfig.PUBLIC_KEY);	
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
		"    \"out_trade_no\":\""+outTradeNo+"\"," +
		"    \"refund_amount\":"+price+"," +
		"    \"refund_reason\":\"正常退款\"," +
		"    \"out_request_no\":\""+outRefundNo+"\"" +
		"}");
		logger.info("#AlipayHelper# refund() parameters = " +objectToJson(request));
		
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		logger.info("#AlipayHelper# refund() return = " + objectToJson(response));
		if(response.isSuccess()){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 退款订单查询
	 * @param outTradeNo
	 * @param outRequestNo
	 * @return
	 * @throws Exception
	 */
	public Map refundQuery(String outTradeNo,String outRefundNo) throws Exception{
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.REQUEST_URL,AlipayConfig.APP_ID
//				,AlipayConfig.PRIVATE_KEY,"json",AlipayConfig.CHARSET,AlipayConfig.PUBLIC_KEY);		
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizContent("{" +
		"    \"out_trade_no\":\""+outTradeNo+"\"," +
		"    \"out_request_no\":\""+outRefundNo+"\"" +
		"}");
		logger.info("#AlipayHelper# refundQuery() parameters = " +objectToJson(request));

		
		AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
		logger.info("#AlipayHelper# refundQuery() return = " + objectToJson(response));
		if(response.isSuccess()){
			Map map = new HashMap();
			map.put("body", response.getBody());
			map.put("sub_msg", response.getSubMsg());
			map.put("sub_code", response.getSubCode());
			map.put("trade_no", response.getTradeNo());
			map.put("out_trade_no", response.getOutTradeNo());
			map.put("out_request_no", response.getOutRequestNo());
			map.put("code", response.getCode());
			map.put("params", response.getParams());
			map.put("msg", response.getMsg());
			map.put("refund_reason", response.getRefundReason());
			map.put("refund_amount", response.getRefundAmount());
			map.put("total_amount", response.getTotalAmount());
			return map;
		} 
		return null;
	}
	
	public static JSONObject objectToJson(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();  
	    String jsonStr =  mapper.writeValueAsString(obj);
	    return new JSONObject(jsonStr);
	}
	
	public static void main(String[] args) throws Exception {
//		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key");
//		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//		request.setBizContent("{" +
//		"    \"out_trade_no\":\"11111\"," +
//		"  }");
//		System.out.println(objectToJson(request));
//		
//		AlipayTradeQueryResponse response = alipayClient.execute(request);
//		System.out.println(objectToJson(response));
		AlipayHelper helper = new AlipayHelper();
//		helper.refund("201611281000557126398518", 0.01d, "201611281024116529756088");
//		helper.checkPaySuccess("201611281148122963253787");'
//		Map map = helper.refundQuery("201611281203087865192362", "201611281204464340557820");
//		System.out.println(map.get("params"));
		
		helper.checkPaySuccess("201612191533144362953384");
	}
}
