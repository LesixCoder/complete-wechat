package com.lunchtasting.server.dao.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CourseOrderVote;

public interface CourseOrderVoteDAO extends GenericDAO<CourseOrderVote> {

	/**
	 * 查询课程套餐用户列表
	 * @param courseMealId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryOrderVoteList(Long courseMealId,Integer page,Integer pageSize);
	
	/**
	 * 获得课程套餐用户总数
	 * @param courseMealId
	 * @return
	 */
	Integer getOrderVoteCount(Long courseMealId);
	
	
	/**
	 * 查询用户的被投票列表
	 * @param orderId
	 * @param desUserId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserVoteList(Long orderId,Long desUserId,Integer page,Integer pageSize);
	
	/**
	 * 获得用户被投票的总数
	 * @param orderId
	 * @param desUserId
	 * @return
	 */
	Integer getUserVoteCount(Long orderId,Long desUserId);
	
	/**
	 * 获得用户是否已经投过票
	 * @param courseMealId
	 * @param userId
	 * @return
	 */
	Integer getVote(Long courseMealId,Long userId);
	
	/**
	 * 课程订单投票详情
	 * @param orderId
	 * @return
	 */
	Map getCourseOrderVoteDetail(Long orderId);
}
