package com.lunchtasting.server.biz.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.CourseOrder;

public interface CourseOrderBIZ {
	
	CourseOrder getById(Long id);

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
	 * 创建课程订单
	 * @param userId
	 * @param courseId
	 * @param courseMealId
	 * @param gymId
	 * @param price
	 * @param code
	 * @param phone
	 * @param sex
	 * @return
	 * @throws Exception
	 */
	Long createOrder(Long userId,Long courseId,Long courseMealId,Long gymId,
			Double price,String code,String phone,Integer sex,Integer status)throws Exception;
	
	/**
	 * 创建活动订单
	 * @param userId
	 * @param courseId
	 * @param courseMealId
	 * @param gymId
	 * @param price
	 * @param code
	 * @param phone
	 */
	void createActivityOrder(Long userId,Long courseId,Long courseMealId,Long gymId,
			Double price,String code,String phone);
	
	/**
	 * 判断订单是否已经支付
	 * @param orderId
	 * @return
	 */
	Boolean checkOrderPay(Long orderId);
	
	/**
	 * 修改订单已支付
	 * @param orderId
	 */
	Boolean updateOrderPay(Long orderId);
	
	/**
	 * 获得用户退款订单
	 * @param orderId
	 * @param userId
	 * @return
	 */
	Map getRefundOrder(Long orderId,Long userId); 
	
	/**
	 * 订单退款
	 * @param orderId
	 * @param userId
	 * @param price
	 * @param outTradeNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Boolean refundOrder(Long orderId,Long userId,Double payPrice
			,Double refundPrice,String outTradeNo,Integer type) throws Exception;
	
	/**
	 * 获得用户订单详情
	 * @param userId
	 * @param orderId
	 * @return
	 */
	Map getCourseOrderDetail(Long userId,Long orderId);
}
