package com.lunchtasting.server.dao.orders;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.OrdersList;

public interface OrdersListDAO extends GenericDAO<OrdersList> {

	/**
	 * 查询订单清单总数
	 * @param orderId
	 * @param status
	 * @return
	 */
	Integer getOrderListCount(Long orderId,Integer status);
	
	/**
	 * 查询订单清单列表
	 * @param orderId
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderListList(Long orderId,Integer status,Integer page,Integer pageSize);
	
	/**
	 * 订单申请退款
	 * @param ids（1,2,3）
	 * @param refundId
	 * @param status
	 * @return
	 */
	Integer updateApplyRefund(String ids,Long refundId,Integer status);
	
	/**
	 * 订单退款成功
	 * @param ids
	 * @param status
	 * @return
	 */
	Integer updateIsRefund(String ids,Integer status);
	
}
