package com.lunchtasting.server.dao.orders;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.OrdersRefund;

public interface OrdersRefundDAO extends GenericDAO<OrdersRefund> {

	/**
	 * 获得退款详情
	 * @param refundId
	 * @return
	 */
	Map getOrderRefundDetail(Long refundId);
	
	/**
	 * 查询正在支付宝/微信审核的订单退款
	 * @return
	 */
	List queryAuditOrderRefundList();
	
	/**
	 * 修改退款查询后的状态
	 * @param refundId
	 * @param status
	 * @return
	 */
	Integer updateRefundStatus(Long refundId,Integer status);
	
	/**
	 * 获得订单退款总数
	 * @param userId
	 * @return
	 */
	Integer getOrderRefundCount(Long userId);
	
	/**
	 * 获得订单退款列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderRefundList(Long userId,Integer page,Integer pageSize);
}
