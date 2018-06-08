package com.lunchtasting.server.controller.payment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.orders.OrdersBIZ;
import com.lunchtasting.server.biz.payment.TenpayNotifityBIZ;
import com.lunchtasting.server.config.AlipayConfig;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.payment.TenpayHelper;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.util.HttpUtil;
import com.lunchtasting.server.util.MD5Util;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/wxpay")
public class TenpayController {
	
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TENPAY);


	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private OrdersBIZ ordersBIZ;
	@Autowired
	private TenpayNotifityBIZ tenpayNotifityBIZ;

//	/**
//	 * 课程订单支付签名,生产预支付订单
//	 * 
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/course/sign")
//	@ResponseBody
//	public Object courseSign(HttpServletRequest request) throws Exception {
//		String cId = request.getParameter("course_id");
//		String num = request.getParameter("number");
//		if (!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isNumber(num)) {
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,
//					MapResult.MESSAGE_PARAMETER, request);
//		}
//
//		int number = Integer.parseInt(num);
//		if (number < 0) {
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,
//					MapResult.MESSAGE_PARAMETER, request);
//		}
//
//		try {
//			long courseId = Long.parseLong(cId);
//			long userId = EncryptAuth.getUserId(request);
//
//			/**
//			 * 课程不存在或已经下架
//			 */
//			Map courseMap = courseBIZ.getConfirmCourse(courseId);
//			if (courseMap == null) {
//				return MapResult.build(20001, MapResult.MESSAGE_PARAMETER,request);
//			}
//
//			long sellerId = Long.parseLong(courseMap.get("seller_id").toString());
//			double price = Double.parseDouble(courseMap.get("price").toString());
//			String courseName = courseMap.get("course_name").toString();
//
//			/**
//			 * 创建订单
//			 */
//			Orders order = ordersBIZ.createOrder(userId, courseId, sellerId,
//					number, price, StateEnum.Tenpay.getValue());
//			if (order != null) {
//
//				/**
//				 * 支付签名，生成预支付订单
//				 */
//				TenpayHelper tenpayHelper = new TenpayHelper();
//				String nonceStr = tenpayHelper.getNonceStr();
//				SortedMap params = getSignMap(order.getCode(),tenpayHelper.getWxPrice(order.getPayPrice()),courseName,nonceStr);
//				String sign = tenpayHelper.createSign(params);
//				params.put("sign", sign);
//				String prepayId = tenpayHelper.getPrepayId(params);
//				if(ValidatorHelper.isNotEmpty(prepayId)){
//					
//					/**
//					 * 获得预支付再次签名
//					 */
//					//String payNonceStr = tenpayHelper.getNonceStr();
//					SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
//					String paySign = tenpayHelper.createSign(paySignMap);
//					
//					/**
//					 * 报文返回
//					 */
//					paySignMap.put("sign", paySign);
//					paySignMap.put("order_id", order.getId());
//					paySignMap.put("code", order.getCode());
//					return MapResult.build(MapResult.CODE_SUCCESS,
//							MapResult.MESSAGE_SUCCESS, paySignMap, request);
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR,
//					MapResult.MESSAGE_ERROR, request);
//		}
//
//		return MapResult.build(MapResult.CODE_FAILURE,
//				MapResult.MESSAGE_FAILURE, request);
//	}
	
	
	
	/**
	 * 支付签名,订单已生成
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/sign")
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
			 * 支付签名,预支付订单
			 */
			TenpayHelper tenpayHelper = new TenpayHelper();
			String nonceStr = tenpayHelper.getNonceStr();
			SortedMap params = getSignMap(code,tenpayHelper.getWxPrice(price),courseName,nonceStr);
			String sign = tenpayHelper.createSign(params);
			params.put("sign", sign);
			String prepayId = tenpayHelper.getPrepayId(params);
			if(ValidatorHelper.isNotEmpty(prepayId)){
				
				
				/**
				 * 获得预支付再次签名
				 */
				//String payNonceStr = tenpayHelper.getNonceStr();
				SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
				String paySign = tenpayHelper.createSign(paySignMap);
				
				/**
				 * 报文返回
				 */
				paySignMap.put("sign", paySign);
				paySignMap.put("order_id", orderId);
				paySignMap.put("code", code);
				
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, paySignMap, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);

	}
	
	/**
	 * 微信异步通知服务器
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/notify")
	public void courseNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("#TenpayController# courseNotify() URL=/tenpay/course/notify begin");
		
		TenpayHelper tenpayHelper = new TenpayHelper();
		try {
			String strxml = tenpayHelper.getResponseXmlString(request, response);
			logger.info("#TenpayController# courseNotify() URL=/tenpay/course/notify xml parameter = " + strxml);
			if(ValidatorHelper.isEmpty(strxml)){
				response.getWriter().write(tenpayHelper.printXML("FAIL", ""));  
				return;
			}

			Map map = tenpayHelper.doXMLParse(strxml);
			SortedMap parameters = new TreeMap();
			parameters.putAll(map);
			logger.info("#TenpayController# courseNotify() URL=/tenpay/course/notify json parameter = " + CommonHelper.getParameterJson(map));
			
			
			/**
			 * 判断是否成功
			 */
			if(!map.get("return_code").toString().equalsIgnoreCase("SUCCESS")
					|| !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
				//失败
				logger.info("#TenpayController# courseNotify() URL=/tenpay/course/notify fail");
				response.getWriter().write(tenpayHelper.printXML("FAIL", ""));  
				return;
			}
			
			
			/**
			 * 判断签名是否正确
			 */
			if(!tenpayHelper.isTenpaySign(parameters)){
				//失败
				logger.info("#TenpayController# courseNotify() URL=/tenpay/course/notify sign fail");
				response.getWriter().write(tenpayHelper.printXML("FAIL", "")); 
				return;
			}
			
			/**
			 * 成功
			 */
			boolean result = tenpayNotifityBIZ.checkNotifity(StateEnum.PAY_COURSE.getValue()
						, map.get("out_trade_no").toString());
			if(result == false){
				boolean isPay = ordersBIZ.notifityCompletePayOrder(map.get("out_trade_no").toString(),StateEnum.Tenpay.getValue(), map);
				if(isPay){
					response.getWriter().write(tenpayHelper.printXML("SUCCESS", ""));  
					return;
				}
			}
			response.getWriter().write(tenpayHelper.printXML("FAIL", ""));  
			return;
		} catch (Exception e) {
			response.getWriter().write(tenpayHelper.printXML("FAIL", ""));  
			e.printStackTrace();
		}
	}

	/**
	 * 获得签名参数 
	 * @param orderCode
	 * @param price
	 * @param name
	 * @param nonceStr
	 * @return
	 */
	private SortedMap getSignMap(String orderCode, int price, String name,String nonceStr) {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", TenpayConfig.APP_ID);
		parameters.put("mch_id", TenpayConfig.MCH_ID);
		parameters.put("nonce_str", nonceStr);
		parameters.put("body", "perfit(玩美)-"+name);
		parameters.put("out_trade_no",orderCode);
		parameters.put("total_fee",price);
		parameters.put("spbill_create_ip",TenpayConfig.SPBILL_CREATE_IP);
		parameters.put("notify_url",TenpayConfig.COURSE_NOFIFY_URL);
		parameters.put("trade_type","APP");
		return parameters;
	}
	
	/**
	 * 获得统一下单签名
	 * @param prepayId
	 * @param nonceStr
	 * @return
	 */
	private SortedMap getPaySignMap(String prepayId,String nonceStr){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", TenpayConfig.APP_ID);
		parameters.put("partnerid", TenpayConfig.MCH_ID);
		parameters.put("prepayid", prepayId);
		parameters.put("package", "Sign=WXPay");
		parameters.put("timestamp", System.currentTimeMillis() / 1000);
		parameters.put("noncestr", nonceStr);
		return parameters;
	}

		

	public static void main(String[] args) {
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", TenpayConfig.APP_ID);
		parameters.put("mch_id", TenpayConfig.MCH_ID);
		
		TenpayHelper helper = new TenpayHelper();
		System.out.println(helper.getParamXml(parameters));
		
	}
}
