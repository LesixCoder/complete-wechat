package com.lunchtasting.server.biz.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.common.TimerBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.orders.OrdersDAO;
import com.lunchtasting.server.dao.orders.OrdersListDAO;
import com.lunchtasting.server.dao.orders.OrdersRefundDAO;
import com.lunchtasting.server.dao.payment.AlipayRefundDAO;
import com.lunchtasting.server.dao.payment.TenpayRefundDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.payment.AlipayHelper;
import com.lunchtasting.server.payment.TenpayHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class TimerBIZImpl implements TimerBIZ {
	
	private Logger logger = Logger.getLogger(SysConfig.LOGGER_TOOL);
	
	@Autowired
	private AlipayRefundDAO alipayRefundDAO;
	@Autowired
	private TenpayRefundDAO tenpayRefundDAO;
	@Autowired
	private OrdersRefundDAO ordersRefundDAO;
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private OrdersListDAO ordersListDAO;

	@Override
	public void doCourseRefundQuery() throws Exception {
		
		/**
		 * 查询所有退款未完成的订单清单
		 */
		List list = ordersRefundDAO.queryAuditOrderRefundList();
		if(ValidatorHelper.isNotEmpty(list)){
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				int payType = Integer.parseInt(map.get("pay_type").toString());
				int refundType = Integer.parseInt(map.get("refund_type").toString());
				long orderId = Long.parseLong(map.get("order_id").toString());
				String outRefundNo = map.get("out_refund_no").toString();
				String outTradeNo = map.get("out_trade_no").toString();
				String listIds = map.get("order_list_ids").toString();
				long refundId = Long.parseLong(map.get("refund_id").toString());
				
				/**
				 * 判断是支付宝还是微信
				 */
				if(payType == StateEnum.Alipay.getValue()){
					AlipayHelper alipayHelper = new AlipayHelper();
					Map createMap = alipayHelper.refundQuery(outTradeNo, outRefundNo);
					if(createMap == null){
						return;
					}
					createMap.put("id", IdWorker.getId());
					createMap.put("biz_type", StateEnum.PAY_COURSE.getValue());
					alipayRefundDAO.createAlipayRefund(createMap);
				}else{
					TenpayHelper tenpayHelper = new TenpayHelper();
					Map createMap = tenpayHelper.refundQuery(
							tenpayHelper.createRefundQueryParam(outTradeNo, outRefundNo));
					if(createMap == null){
						throw new Exception();
					}
					createMap.put("id", IdWorker.getId());
					createMap.put("biz_type", StateEnum.PAY_COURSE.getValue());
					tenpayRefundDAO.createTenpayRefund(createMap);
				}
				
				/**
				 * 修改订单清单状态
				 */
				Integer up1 = ordersListDAO.updateIsRefund(listIds, StateEnum.ORDER_LIST_IS_REFUND.getValue());
				if(up1 == null || up1 == 0){
					throw new Exception();
				}
				
				/**
				 * 修改退款记录已到账
				 */
				ordersRefundDAO.updateRefundStatus(refundId, 3);
				
				
				/**
				 * 修改订单状态已退款
				 * refundType=1 整个订单退款， 直接修改订单状态为已退款
				 * refundType=2 单个订单退款，判断是否全部已退款，是则修改订单状态
				 */
				if(refundType == 1){
					Integer up2 = ordersDAO.updateOrderRefund(orderId, StateEnum.ORDER_ISREFUND.getValue());
					if(up2 == null || up2 == 0){
						throw new Exception();
					}
				}else{
					Map refundMap = ordersDAO.getOrderRefundNumber(orderId);
					if(refundMap != null){
						if(refundMap.get("number").toString().equals(refundMap.get("refund_number").toString())){
							Integer up2 = ordersDAO.updateOrderRefund(orderId, StateEnum.ORDER_ISREFUND.getValue());
							if(up2 == null || up2 == 0){
								throw new Exception();
							}
						}
					}
				}
			}
		}
	}

	
}
