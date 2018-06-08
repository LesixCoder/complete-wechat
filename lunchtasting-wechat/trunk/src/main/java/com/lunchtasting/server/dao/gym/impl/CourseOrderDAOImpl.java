package com.lunchtasting.server.dao.gym.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.gym.CourseOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.CourseOrder;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class CourseOrderDAOImpl extends GenericDAOSupport<Long, CourseOrder> implements CourseOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return CourseOrder.class;
	}

	@Override
	protected String getTableName() {
		return "course_order";
	}

	@Override
	public List queryOrderList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderList", map);			
	}

	@Override
	public Integer getOrderCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderCount", map);	
	}
	
	@Override
	public Integer updateOrderPay(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateOrderPay", map);
	}
	
	@Override
	public Integer updateOrderRefund(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return (Integer)getSqlMapClientTemplate().update(getNamespace() + ".updateOrderRefund", map);
	}

	@Override
	public Map getRefundOrder(Long orderId, Long userId) {
		if(orderId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getRefundOrder", map);		
	}

	@Override
	public Integer createOrderRefund(Map map) {
		if(map == null){
			return null;
		}
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".createOrderRefund",map);	
	}

	@Override
	public void timerNotifyOrderUp() {
		getSqlMapClientTemplate().update(getNamespace() + ".timerNotifyOrderUp");	
	}

	@Override
	public void timerNotifyOrderFinish() {
		getSqlMapClientTemplate().update(getNamespace() + ".timerNotifyOrderFinish");	

	}

	@Override
	public Map getCourseOrderDetail(Long userId, Long orderId) {
		if(userId == null || orderId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getCourseOrderDetail", map);		
	}

	@Override
	public List queryNoBonusCourseOrderList() {
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryNoBonusCourseOrderList");			
	}

	@Override
	public Integer updateOrderIsBonus(Long orderId) {
		if(orderId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("orderId", orderId);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateOrderIsBonus",map);			
	}

}
