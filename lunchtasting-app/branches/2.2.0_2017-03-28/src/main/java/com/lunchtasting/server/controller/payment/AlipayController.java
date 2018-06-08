package com.lunchtasting.server.controller.payment;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.internal.util.WebUtils;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.orders.OrdersBIZ;
import com.lunchtasting.server.biz.payment.AlipayNotifityBIZ;
import com.lunchtasting.server.config.AlipayConfig;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
	
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TOOL);

	
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private OrdersBIZ ordersBIZ;
	@Autowired
	private AlipayNotifityBIZ alipayBIZ;

//	/**
//	 * 课程订单支付签名,生产预支付订单
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/course/sign")
//	@ResponseBody
//	public Object courseSign(HttpServletRequest request) throws Exception {
//		String cId = request.getParameter("course_id");
//		String num =  request.getParameter("number");
//		if(!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isNumber(num)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
//		}
//		
//		int number = Integer.parseInt(num);
//		if(number < 0){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
//		}
//
//		
//		try {
//			long courseId = Long.parseLong(cId);
//			long userId = EncryptAuth.getUserId(request);
//			
//			/**
//			 * 课程不存在或已经下架
//			 */
//			Map courseMap = courseBIZ.getConfirmCourse(courseId);
//			if(courseMap == null){
//				return MapResult.build(20001, MapResult.MESSAGE_PARAMETER, request);
//			}
//			
//			long sellerId = Long.parseLong(courseMap.get("seller_id").toString());
//			double price = Double.parseDouble(courseMap.get("price").toString());
//			String courseName = courseMap.get("course_name").toString();
//			
//			/**
//			 * 创建订单
//			 */
//			Orders order = ordersBIZ.createOrder(userId, courseId, sellerId, number, price, StateEnum.Alipay.getValue()) ;
//			if(order != null){
//				
//				/**
//				 * 支付签名
//				 */
//				Map params = getSignMap(order.getCode(),order.getPayPrice(),courseName);
//				String sign = AlipaySignature.rsaSign(params, AlipayConfig.PRIVATE_KEY, AlipayConfig.CHARSET);
//				params.put("sign", sign);
//				String signResult = encode(params);
//				
//				/**
//				 * 报文返回
//				 */
//				Map dataMap = new HashMap();
//				dataMap.put("sign", signResult);
//				dataMap.put("order_id", order.getId());
//				dataMap.put("code", order.getCode());
//				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
//			}
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
//		}
//		
//		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
//	}
	
	/**
	 * 支付签名,订单已生成
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/sign" , method=RequestMethod.POST)
	@ResponseBody
	public Object orderSign(HttpServletRequest request) throws Exception {
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}

		
		try {
			long orderId = Long.parseLong(oId);
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 订单不存在
			 */
			Map orderMap = ordersBIZ.getNoPayOrder(orderId, userId);
			if(orderMap == null){
				return MapResult.build(20001, MapResult.MESSAGE_PARAMETER, request);
			}
			
			double price = Double.parseDouble(orderMap.get("pay_price").toString());
			String code = orderMap.get("code").toString();
			String courseName = orderMap.get("course_name").toString();
			
			/**
			 * 支付签名
			 */
			Map params = getSignMap(code,price,courseName);
			String sign = AlipaySignature.rsaSign(params, AlipayConfig.PRIVATE_KEY, AlipayConfig.CHARSET);
			params.put("sign", sign);
			String signResult = encode(params);
			
			if(ValidatorHelper.isNotEmpty(signResult)){
				
				/**
				 * 报文返回
				 */
				Map dataMap = new HashMap();
				dataMap.put("sign", signResult);
				dataMap.put("order_id", orderId);
				dataMap.put("code", code);
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);

	}
	

	
//	/**
//	 * 交易退款
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/course/refund")
//	@ResponseBody
//	public Object tradeRefund(HttpServletRequest request) throws Exception {
//		String oId = request.getParameter("order_id");
//		String num = request.getParameter("number");
//		if(!ValidatorHelper.isNumber(oId) || !ValidatorHelper.isNumber(num)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
//		}
//		
//		try {
//			long orderId = Long.parseLong(oId);
//			int number = Integer.parseInt(num);
//			long userId = EncryptAuth.getUserId(request);
//			
//			/**
//			 * 查询退款订单是否存在
//			 */
//			Map orderMap = ordersBIZ.getRefundOrder(orderId, userId);
//			if(orderMap == null){
//				return MapResult.build(20001,"支付退款异常，请联系客服！", request);
//			}
//			
//			/**
//			 * 判断是否有支付记录
//			 */
//			int payType = Integer.parseInt(orderMap.get("pay_type").toString());
//			if(payType != StateEnum.Alipay.getValue()){
//				return MapResult.build(20002,"支付退款异常，请联系客服！", request);
//			}
//			
//			String code = orderMap.get("code").toString();
//			Map alipayMap = alipayBIZ.getAlipay(1, code, "TRADE_SUCCESS");
//			if(alipayMap == null ){
//				return MapResult.build(20003,"支付退款异常，请联系客服！", request);
//			}
//			
//			double payPrice = Double.parseDouble(orderMap.get("pay_price").toString());
//			double alipayPrice =  Double.parseDouble(alipayMap.get("total_amount").toString());
//			if(payPrice != alipayPrice){
//				return MapResult.build(20004,"支付退款异常，请联系客服！", request);
//			}
//			
//			/**
//			 * 本地退款记录
//			 */
//			
//			
//			/**
//			 * 支付宝退款
//			 */
//			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APP_ID
//						,AlipayConfig.PRIVATE_KEY,"json",AlipayConfig.CHARSET,AlipayConfig.PUBLIC_KEY);
//			AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
//			refundRequest.setBizContent("");
//			AlipayTradeRefundResponse response = alipayClient.execute(refundRequest);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
//		}
//		
//		return null;
//	}
	
	/**
	 * 支付宝异步通知服务器
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/notify")
	public void courseNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		String appId = request.getParameter("app_id");
		String outTradeNo = request.getParameter("out_trade_no");
		String sellerId = request.getParameter("seller_id");
		String totalAmount = request.getParameter("total_amount");
		String tradeStatus = request.getParameter("trade_status");
		
		Map paramsMap = CommonHelper.getRequestMap(request);
		logger.info("#AlipayController# courseNotify() URL=/alipay/course/notify parameter = "+ CommonHelper.getParameterJson(paramsMap));
		
		
		boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.PUBLIC_KEY, AlipayConfig.CHARSET);
		if(signVerified){
			
			//判断out_trade_no和total_amount 是否是订单号和对应的金额
			boolean orderVerified = ordersBIZ.checkPayOrderIsExist(outTradeNo, Double.parseDouble(totalAmount));
			if(orderVerified == false){
				writer.print("failure");
				return;
			}
			
			//校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方
			if(!AlipayConfig.SELLER_ID.equals(sellerId)){
				writer.print("failure");
				return;
			}
			
			// 验证app_id是否为该商户本身
			if(!AlipayConfig.APP_ID.equals(appId)){
				writer.print("failure");
				return;
			}
			
			/**
			 * 交易状态判断 
			 * 枚举名称                          枚举说明                                                                  触发条件描述                触发条件默认值
			 * WAIT_BUYER_PAY  交易创建，等待买家付款                                          交易创建	       false（不触发通知）
			 * TRADE_CLOSED    未付款交易超时关闭，或支付完成后全额退款        交易关闭                      true（触发通知）
			 * TRADE_SUCCESS   交易支付成功                                                            支付成功                       true（触发通知）
			 * TRADE_FINISHED  交易结束，不可退款                                                 交易完成                       false（不触发通知）
			 */
//			if(tradeStatus.equals("TRADE_CLOSED")){
//				alipayBIZ.createAlipayNotifity(paramsMap);
//			}
			if(tradeStatus.equals("TRADE_SUCCESS")){
				boolean result = alipayBIZ.checkNotifity(StateEnum.PAY_COURSE.getValue(), "TRADE_SUCCESS", outTradeNo);
				if(result == false){
					//alipayBIZ.createAlipayNotifity(paramsMap);
					boolean isPay = ordersBIZ.notifityCompletePayOrder(outTradeNo,StateEnum.Alipay.getValue(), paramsMap);
					if(isPay){
						writer.print("success");
						return;
					}
				}
			}
		}
		writer.print("failure");
		return;
	}
	
	/**
	 * 签名参数生成
	 * @param orderCode
	 * @param price
	 * @param name
	 * Anna的fellow
	 * @return
	 */
	private Map getSignMap(String orderCode,double price,String name){
		Map map = new HashMap();
		
		map.put("app_id", AlipayConfig.APP_ID);
		map.put("method", "alipay.trade.app.pay");
		map.put("charset", AlipayConfig.CHARSET);
		map.put("sign_type", "RSA");
		map.put("timestamp", DateUtil.convertDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
		map.put("version", "1.0");
		map.put("notify_url", AlipayConfig.COURSE_NOFIFY_URL);
		
		Map contentMap = new HashMap();
		contentMap.put("out_trade_no", orderCode);
		//contentMap.put("seller_id", AlipayConfig.SELLER_ID);
		contentMap.put("product_code", "QUICK_MSECURITY_PAY");
		contentMap.put("subject", name);
		contentMap.put("total_amount", price+"");
		
		String bizContent = JSONObject.toJSONString(contentMap);
		map.put("biz_content", bizContent);
		return map;
	}
	
	/**
	 * 获得退款json
	 * @param outTradeNo
	 * @param tradeNo
	 * @param outRequestNo
	 * @param refundAmount
	 * @return
	 */
	private String getRefundJson(String outTradeNo,String tradeNo
			,String outRequestNo,Double refundAmount){
		Map map = new HashMap();
		map.put("out_trade_no", outTradeNo);
		map.put("trade_no", tradeNo);
		map.put("out_request_no", outRequestNo);
		map.put("refund_amount", refundAmount);
		
		return JSONObject.toJSONString(map);
	}
	

	
	private String encode(Map<String, String> sortedParams){
		StringBuffer content = new StringBuffer();
      	List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + WebUtils.encode(value));
                index++;
            }
        }
        return content.toString();
	}
	
	
}
