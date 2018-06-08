package com.lunchtasting.server.dao.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CourseOrder;

public interface CourseOrderDAO extends GenericDAO<CourseOrder> {

	/**
	 * 查询课程订单列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 查询课程订单总数
	 * @param userId
	 * @return
	 */
	Integer getOrderCount(Long userId);
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 * @return
	 */
	Integer updateOrderPay(Long orderId);
	
	/**
	 * 修改订单已退款
	 * @param orderId
	 * @return
	 */
	Integer updateOrderRefund(Long orderId);
	
	/**
	 * 获得用户退款订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getRefundOrder(Long orderId,Long userId);
	
	/**
	 * 创建订单退款记录
	 * @param map
	 */
	Integer createOrderRefund(Map map);
	
	/**
	 * 定时任务批量修改
	 * 修改当前时间大于开课时间的订单状态为已开课（进行中）
	 */
	void timerNotifyOrderUp();
	
	/**
	 * 定时任务批量修改
	 * 修改当前时间大于结课时间的订单状态为已结束
	 */
	void timerNotifyOrderFinish();
	
	/**
	 * 获得用户订单详情
	 * @param userId
	 * @param orderId
	 * @return
	 */
	Map getCourseOrderDetail(Long userId,Long orderId);
	
	/**
	 * 定时任务使用
	 * 用于查询尚未分红的订单进行分红处理
	 * @return
	 */
	List queryNoBonusCourseOrderList();
	
	/**
	 * 修改订单已经分红
	 * @param orderId
	 * @return
	 */
	Integer updateOrderIsBonus(Long orderId);
	
}
