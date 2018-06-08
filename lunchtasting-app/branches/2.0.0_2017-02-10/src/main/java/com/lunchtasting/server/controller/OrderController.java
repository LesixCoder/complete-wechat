package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.course.CourseBIZ;
import com.lunchtasting.server.biz.orders.OrdersBIZ;
import com.lunchtasting.server.biz.orders.OrdersListBIZ;
import com.lunchtasting.server.biz.orders.OrdersRefundBIZ;
import com.lunchtasting.server.config.TenpayConfig;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.payment.AlipayHelper;
import com.lunchtasting.server.payment.TenpayHelper;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 订单模块
 * Created on 2016-10-9
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrdersBIZ ordersBIZ;
	@Autowired
	private OrdersListBIZ ordersListBIZ;
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private OrdersRefundBIZ ordersRefundBIZ;

	/**
	 * 订单列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String sta = request.getParameter("status");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		Integer status = null;
		if(ValidatorHelper.isNumber(sta)){
			status = Integer.parseInt(sta);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", ordersBIZ.queryOrderList(userId, status,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(ordersBIZ.getOrderCount(userId, status),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
	}
	
	
	/**
	 * 订单详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Object detail(HttpServletRequest request) throws Exception {
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		
		try {
			long orderId = Long.parseLong(oId);
			long userId = EncryptAuth.getUserId(request);

			Map orderMap = ordersBIZ.getOrderDetail(orderId, userId);
			if(orderMap == null){
				return MapResult.build(20001, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 清单列表
			 */
			List qdList = null;
			int stauts = Integer.parseInt(orderMap.get("status").toString());
			if(stauts > 1){
				qdList = ordersListBIZ.queryOrderListList(orderId,null,null,null);
			}
			
			Map dataMap = new HashMap();
			dataMap.put("order", orderMap);
			dataMap.put("qd_list", qdList);
			
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 确认订单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/confirm")
	@ResponseBody
	public Object confirm(HttpServletRequest request) throws Exception {
		String cId = request.getParameter("course_id");
		String num = request.getParameter("number");
		
		if(!ValidatorHelper.isNumber(cId) || !ValidatorHelper.isNumber(num)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long courseId = Long.parseLong(cId);
			long userId = EncryptAuth.getUserId(request);
			int number = Integer.parseInt(num);

			/**
			 * 课程不存在或已经下架
			 */
			Map courseMap = courseBIZ.getConfirmCourse(courseId);
			if(courseMap == null){
				return MapResult.build(20001, MapResult.MESSAGE_PARAMETER, request);
			}
			long sellerId = Long.parseLong(courseMap.get("seller_id").toString());
			double price = Double.parseDouble(courseMap.get("price").toString());
			String courseName = courseMap.get("course_name").toString();
			
			/**
			 * 创建订单
			 */
			Orders order = ordersBIZ.createOrder(userId, courseId, sellerId, number, price);
			if(order != null){
				Map dataMap = new HashMap();
				dataMap.put("course_id", courseId);
				dataMap.put("course_name", courseName);	
				dataMap.put("order_id", order.getId());
				dataMap.put("number", number);
				dataMap.put("price", order.getPrice());
				dataMap.put("pay_price", order.getPayPrice());
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	
	
	/**
	 * 支付完成
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay/complete")
	@ResponseBody
	public Object payComplete(HttpServletRequest request) throws Exception {
		String oId = request.getParameter("order_id");
//		String pType = request.getParameter("pay_type");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}

		
		try {
			long orderId = Long.parseLong(oId);
			long userId = EncryptAuth.getUserId(request);
//			int payType = Integer.parseInt(pType);
			
			/**
			 * 查看本地订单不存在
			 */
			Map orderMap = ordersBIZ.getByOrderIdAndUserId(orderId, userId);
			if(orderMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			int status = Integer.parseInt(orderMap.get("status").toString());

			
//			/**
//			 * 判断订单是否支付成功
//			 */
//			if(status == StateEnum.ORDER_NOPAY.getValue().intValue()){
//				
//				String outTradeNo = orderMap.get("code").toString();
//				
//				/**
//				 * 查看支付宝/微信支付记录
//				 */
//				if(payType == StateEnum.Alipay.getValue()){
//					
//					AlipayHelper alipayHeler = new AlipayHelper();
//					boolean result = alipayHeler.checkPaySuccess(outTradeNo);
//					if(result == false){
//						return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
//					}
//					
//				}else{
//					
//					TenpayHelper tenpayHelper = new TenpayHelper();
//					boolean result = tenpayHelper.checkPaySuccess(tenpayHelper.createQueryPayOrderParam(outTradeNo));
//					if(result == false){
//						return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
//					}
//				}
//			}
			
			//订单的每个单价/还没优惠劵
			double price = Double.parseDouble(orderMap.get("price").toString());
			String courseName = orderMap.get("course_name").toString();
			long courseId = Long.parseLong(orderMap.get("course_id").toString());
			int number = Integer.parseInt(orderMap.get("number").toString());
			
			Map dataMap = new HashMap();
			dataMap.put("qd_list", ordersListBIZ.queryOrderListList(orderId,null,null,null));
			dataMap.put("course_name", courseName);
			dataMap.put("course_id", courseId);
			dataMap.put("order_id", orderId);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
			
//			/**
//			 * 修改订单状态/创建订单清单
//			 */
//			boolean result = ordersBIZ.completePayOrder(orderId, userId, price, number);
//			if(result){
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		

	}
	
	/**
	 * 用户确认退款
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/refund/confirm")
	@ResponseBody
	public Object refundConfirm(HttpServletRequest request) throws Exception {
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long orderId = Long.parseLong(oId);
			long userId = EncryptAuth.getUserId(request);

			
			Map orderMap = ordersBIZ.getRefundOrder(orderId, userId);
			if(orderMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"订单无法退款！", request);
			}
			
			Map dataMap = new HashMap();
			dataMap.put("order", orderMap);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 用户完成退款
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/refund/complete")
	@ResponseBody
	public Object refundComplete(HttpServletRequest request) throws Exception {
		String oId = request.getParameter("order_id");
		String num = request.getParameter("number");
		if(!ValidatorHelper.isNumber(oId) || !ValidatorHelper.isNumber(num)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long orderId = Long.parseLong(oId);
			int number = Integer.parseInt(num); //需要退款的数量
			long userId = EncryptAuth.getUserId(request);
			
			/**
			 * 查询退款订单是否存在
			 */
			Map orderMap = ordersBIZ.getRefundOrder(orderId, userId);
			if(orderMap == null){
				return MapResult.build(20001,"支付退款异常，请联系客服！", request);
			}
			int payType = Integer.parseInt(orderMap.get("pay_type").toString());
			String outTradeNo = orderMap.get("code").toString();
			int listCount = Integer.parseInt(orderMap.get("list_count").toString()); //付款没使用的数量
			int realityNumber = Integer.parseInt(orderMap.get("number").toString()); //实际购买数量
			
			if(listCount == 0 || listCount < number){
				return MapResult.build(20002,"支付退款异常，请联系客服！", request);
			}
			
			/**
			 * 是否整个订单退款
			 */
			boolean isOrder = false;
			if(realityNumber == number){
				isOrder = true;
			} 
			
			/**
			 * 计算需要退款的价格
			 */
			double payPrice = Double.parseDouble(orderMap.get("pay_price").toString());
			double refundPrice = payPrice;
			if(realityNumber != number){
				double unitPrice = payPrice/realityNumber;
				refundPrice = unitPrice*number;
			}
			
			/**
			 * 本地退款记录
			 */
			Map refundMap = ordersBIZ.refundOrder(orderId, number, userId, payType
						,outTradeNo, payPrice, refundPrice,isOrder);
			if(refundMap != null){
				Map dataMap = new HashMap();
				dataMap.put("refund", refundMap);	
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	@RequestMapping(value = "/course/refund/detail")
	@ResponseBody
	public Object refundDetail(HttpServletRequest request) throws Exception {
		String rId = request.getParameter("refund_id");
		if(!ValidatorHelper.isNumber(rId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long refundId = Long.parseLong(rId);
			//long userId = EncryptAuth.getUserId(request);
			
			Map refundMap = ordersRefundBIZ.getOrderRefundDetail(refundId);
			if(refundMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			Map dataMap = new HashMap();
			dataMap.put("refund", refundMap);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
}
