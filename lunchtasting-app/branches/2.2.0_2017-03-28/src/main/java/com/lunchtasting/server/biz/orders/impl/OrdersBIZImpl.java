package com.lunchtasting.server.biz.orders.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.orders.OrdersBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.orders.OrdersDAO;
import com.lunchtasting.server.dao.orders.OrdersListDAO;
import com.lunchtasting.server.dao.orders.OrdersRefundDAO;
import com.lunchtasting.server.dao.payment.AlipayNotifityDAO;
import com.lunchtasting.server.dao.payment.TenpayNotifityDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.payment.AlipayHelper;
import com.lunchtasting.server.payment.TenpayHelper;
import com.lunchtasting.server.po.lt.Orders;
import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.po.lt.OrdersRefund;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class OrdersBIZImpl implements OrdersBIZ {

	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private OrdersListDAO ordersListDAO;
	@Autowired
	private OrdersRefundDAO ordersRefundDAO;
	@Autowired
	private AlipayNotifityDAO alipayNotifityDAO;
	@Autowired
	private TenpayNotifityDAO tenpayNotifityDAO;
	
	@Override
	public Orders createOrder(Long userId, Long courseId, Long sellerId,
			Integer number, Double price){
		
		
		/**
		 * 订单
		 */
		Orders order = new Orders();
		order.setId(IdWorker.getId());
		order.setCourseId(courseId);
		order.setCode(CommonHelper.getOutNo());
		order.setUserId(userId);
		order.setSellerId(sellerId);
		order.setNumber(number);
		order.setPrice(price);
		order.setOriginalPrice(price*number);
		order.setPayPrice(price*number);
		order.setStatus(StateEnum.ORDER_NOPAY.getValue());
		ordersDAO.create(order);
		
		return order;
	}

	@Override
	public Map getOrderDetail(Long orderId, Long userId) {
		
		Map map = ordersDAO.getOrderDetail(orderId, userId);
		if(map != null){
			
			/**
			 * 课程图片
			 */
			map.put("course_img_url", SysConfig.IMG_VISIT_URL+map.get("course_img_url")
					+ QiNiuStorageHelper.MODEL0+"w/750/h/540");
			
			
		}
		
		return map;
	}

	@Override
	public Integer getOrderCount(Long userId, Integer status) {
//		int result = 0;
//		if(status == null || status != StateEnum.ORDER_ISREFUND.getValue().intValue()){
//			result = ordersDAO.getOrderCount(userId, status);
//		}else{
//			result = ordersRefundDAO.getOrderRefundCount(userId);
//		}
		return ordersDAO.getOrderCount(userId, status);
	}

	@Override
	public List queryOrderList(Long userId,Integer status, Integer page,Integer pageSize) {
		
//		/**
//		 * 如果是查询退款列表 直接查询退款表
//		 */
//		List list = null;
//		if(status == null || status != StateEnum.ORDER_ISREFUND.getValue().intValue()){
//			list = ordersDAO.queryOrderList(userId, status, page, pageSize);
//		}else{
//			list = ordersRefundDAO.queryOrderRefundList(userId, page, pageSize);
//		}
		List list = ordersDAO.queryOrderList(userId, status, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				
				/**
				 * 图片
				 */
				map.put("course_img_url", SysConfig.IMG_VISIT_URL+map.get("course_img_url")
						+ QiNiuStorageHelper.MODEL1+"w/750/h/460");
				

				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Map getNoPayOrder(Long orderId, Long userId) {
		return ordersDAO.getNoPayOrder(orderId, userId);
	}

	@Override
	public Boolean synchroCompletePayOrder(Long orderId, Long userId, Double price,
			Integer number) throws Exception {
		
		/**
		 * 修改订单状态已支付
		 */
		int isUpdate = ordersDAO.updateStatus(orderId, StateEnum.ORDER_ISPAY.getValue());
		if(isUpdate == 0){
			return false;
		}
		
		/**
		 * 创建清单清单
		 */
		for(int i = 0 ; i < number; i ++){
			OrdersList qd = new OrdersList();
			qd.setId(IdWorker.getId());
			qd.setOrderId(orderId);
			qd.setCode(Utils.getRandomNumber(12));
			qd.setPrice(price);
			qd.setStatus(1);
			ordersListDAO.create(qd);
		}
		
		return true;
	}
	
	@Override
	public Boolean notifityCompletePayOrder(String outTradeNo, Integer payType,
			Map payMap) throws Exception {
		
		IdWorker idWorker = new IdWorker(0, 0);
		Map orderMap = ordersDAO.getOrderByOutTradeNo(outTradeNo);
		if(orderMap == null){
			return false;
		}
		

		/**
		 * 创建支付记录
		 */
		payMap.put("id", idWorker.nextId());
		payMap.put("biz_type",payType);
		if(payType.intValue() == StateEnum.Alipay.getValue().intValue()){
			alipayNotifityDAO.createAlipayNotifity(payMap);
		}else{
			tenpayNotifityDAO.createTenpayNotifity(payMap);
		}
		
		/**
		 * 判断订单是否修改状态，没修改的话修改状态创建清单
		 */
		int status = Integer.parseInt(orderMap.get("status").toString());
		if(status == StateEnum.ORDER_NOPAY.getValue()){
			
			long orderId = Long.parseLong(orderMap.get("order_id").toString());
			int number = Integer.parseInt(orderMap.get("number").toString());
			double price = Double.parseDouble(orderMap.get("price").toString());
			
			/**
			 * 修改订单状态已支付
			 */
			int isUpdate = ordersDAO.updateIsPay(orderId,payType,StateEnum.ORDER_ISPAY.getValue());
			if(isUpdate == 0){
				return false;
			}
			
			/**
			 * 创建清单清单
			 */
			for(int i = 0 ; i < number; i ++){
				OrdersList qd = new OrdersList();
				qd.setId(idWorker.nextId());
				qd.setOrderId(orderId);
				qd.setCode(Utils.getRandomNumber(12));
				qd.setPrice(price);
				qd.setStatus(1);
				ordersListDAO.create(qd);
			}
		}
		
		return true;
	} 

	@Override
	public Boolean checkPayOrderIsExist(String code, Double payPrice) {
		
		Long id = ordersDAO.getByCodeAndPayPrice(code, payPrice);
		if(id != null){
			return true;
		}
		return false;
	}

	@Override
	public Map getRefundOrder(Long orderId, Long userId) {
		return ordersDAO.getRefundOrder(orderId, userId);
	}

	@Override
	public Map getCommentOrder(Long orderId, Long userId) {
		return ordersDAO.getCommentOrder(orderId, userId);
	}

	@Override
	public Map refundOrder(Long orderId,Integer number,Long userId,Integer payType
			,String outTradeNo,Double payPrice,Double refundPrice,Boolean isOrder) throws Exception {
		
		/**
		 * 获取订单清单信息
		 */
		List qdList = ordersListDAO.queryOrderListList(orderId,StateEnum.ORDER_LIST_NOT_USED.getValue(),0,number);
        StringBuffer idSb = new StringBuffer();
		if(qdList != null){
			for(int i =0 ;i<qdList.size();i++){
				HashMap map = (HashMap) qdList.get(i);
                idSb.append(map.get("id").toString()+",");
			}
			idSb.deleteCharAt(idSb.length()-1);
		}
		
		/**
		 * 增加订单退款记录
		 */
		OrdersRefund refund = new OrdersRefund();
		
		//退款id
		long refundId = IdWorker.getId();
		//退款唯一凭证号
		String outRefundNo = CommonHelper.getOutNo();
		//退款类型  1整个订单退款  2分开退款
		if(isOrder){
			refund.setRefundType(1);
		}else{
			refund.setRefundType(2);
		}
		
		refund.setId(refundId);
		refund.setUserId(userId);
		refund.setOrderId(orderId);
		refund.setOrderListIds(idSb.toString());
		refund.setPayType(payType);
		refund.setPrice(refundPrice);
		refund.setOutRefundNo(outRefundNo);
		refund.setNumber(number);
		refund.setStatus(2);
		refund.setCreateTime(new Date());
		refund.setAuditTime(new Date());
		ordersRefundDAO.create(refund);
		
		/**
		 * 修改清单状态退款中
		 */
		Integer up1 = ordersListDAO.updateApplyRefund(idSb.toString(),refundId, StateEnum.ORDER_LIST_REFUNDING.getValue());
		if(up1 == null || up1 == 0){
			throw new Exception();
		}
		
		/**
		 * 第三方退款
		 */
		if(payType == StateEnum.Tenpay.getValue()){
			TenpayHelper tenpayHelper = new TenpayHelper();
			SortedMap params = tenpayHelper.createRefundParam(outTradeNo, outRefundNo, payPrice, refundPrice);
			Map map = tenpayHelper.refund(params);
			if(map == null){
				throw new Exception();
			}
			
		}else{
			AlipayHelper alipayHelper = new AlipayHelper();
			boolean result = alipayHelper.refund(outTradeNo, refundPrice,outRefundNo);
			if(result == false){
				throw new Exception();
			}
		}
		
		Map map = new HashMap();
		map.put("refund_id", refundId);
		return map;
	}

	@Override
	public Map getByOrderIdAndUserId(Long orderId, Long userId) {
		return ordersDAO.getByOrderIdAndUserId(orderId, userId);
	}

	
}
