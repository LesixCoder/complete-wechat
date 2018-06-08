package com.perfit.server.biz.orders;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.OrdersList;
import com.lunchtasting.server.vo.OrdersListExhibition;

public interface OrdersListBIZ {
	/**
	 * 查询子订单列表
	 * @param map
	 * @return
	 */
	List<OrdersList> queryOrdersListList(HashMap map);
	/**
	 * 查询子订单列表
	 * @param map
	 * @return
	 */
	OrdersListExhibition queryOrdersListOne(HashMap map);
	/**
	 * 根据已经查信息 使用券息
	 * @param code
	 * @param id
	 * @return
	 */
	boolean employCode(String code,Long id);
	/**
	 * 根据code查询子订单
	 * @param code
	 * @param sellerId
	 * @return
	 */
	OrdersListExhibition queryOrdersListByCode(String code,Long sellerId);
	/**
	 * 验证是否可以消费(code he id只传一个)
	 * @param code
	 * @param sellerId
	 * @param id
	 * @return
	 */
	boolean verifyOrdersList(String code,Long sellerId,Long id);
	/**
	 * 根据Id查询子订单
	 * @param id
	 * @param sellerId
	 * @return
	 */
	public OrdersListExhibition queryOrdersListById(Long id, Long sellerId);
}
