package com.lunchtasting.server.dao.match.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.match.MatchOrderDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchOrder;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class MatchOrderDAOImpl extends GenericDAOSupport<Long,MatchOrder> implements MatchOrderDAO {

	@Override
	protected Class<?> getObjectClass() {
		return MatchOrder.class;
	}

	@Override
	protected String getTableName() {
		return "match_order";
	}

	@Override
	public Integer createMatchOrder(Map map) {
		if(map == null){
			return null;
		}
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".createMatchOrder",map);	
	}

	@Override
	public Integer createMatchOrderGoods(Map map) {
		if(map == null){
			return null;
		}
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".createMatchOrderGoods",map);		
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
	public List queryUserOrderList(Long matchId, Long userId, Integer status) {
		if(matchId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("matchId", matchId);
		map.put("userId", userId);
		map.put("status", status);
		return	getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserOrderList", map);	
	}
	

//	@Override
//	public Integer getMatchOrderCount(Long matchId) {
//		if(matchId == null){
//			return 0;
//		}
//		
//		Map map = new HashMap();
//		map.put("matchId", matchId);
//		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchOrderCount",map);	
//	}
//
//	@Override
//	public Integer getIsSignUp(Long matchId, Long userId,String phone,Integer type) {
//		if(matchId == null){
//			return 0;
//		}
//		Map map = new HashMap();
//		map.put("matchId", matchId);
//		map.put("userId", userId);
//		map.put("phone", phone);
//		map.put("type", type);
//		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getIsSignUp",map);	
//	}
//
//	@Override
//	public Integer getUserMatchOrderCount(Long userId) {
//		if(userId == null){
//			return 0;
//		}
//		Map map = new HashMap();
//		map.put("userId", userId);
//		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserMatchOrderCount",map);	
//	}
//
//	@Override
//	public List queryUserMatchOrderList(Long userId, Integer page,
//			Integer pageSize) {
//		if(userId == null){
//			return null;
//		}
//		
//		Map map = new HashMap();
//		map.put("userId", userId);
//		map.put("page", page);
//		map.put("pageSize", pageSize);
//        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserMatchOrderList", map);
//	}
//
//	@Override
//	public Map getOrderDetail(Long orderId,Long userId) {
//		if(orderId == null){
//			return null;
//		}
//		
//		Map map = new HashMap();
//		map.put("id", orderId);
//		map.put("userId", userId);
//		
//		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderDetail", map);
//	}
//
//	@Override
//	public Integer updateStatus(Long orderId, Integer status) {
//		if(orderId == null){
//			return null;
//		}
//		Map map = new HashMap();
//		map.put("id", orderId);
//		map.put("status", status);
//		return getSqlMapClientTemplate().update(getNamespace() + ".updateStatus", map);
//	}
//
//	@Override
//	public List queryMatchOrderUserList(Long matchId, Integer sex, Integer size) {
//		if(matchId == null || sex == null || size == null){
//			return null;
//		}
//		
//		Map map = new HashMap();
//		map.put("matchId", matchId);
//		map.put("sex", sex);
//		map.put("size", size);
//		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMatchOrderUserList", map);
//	}
//
//	@Override
//	public Integer getMatchCodeCount(Long matchId, Long matchCodeId) {
//		if(matchId == null || matchCodeId == null){
//			return 0;
//		}
//		Map map = new HashMap();
//		map.put("matchId", matchId);
//		map.put("matchCodeId", matchCodeId);
//		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchCodeCount", map);
//	}
//
//	@Override
//	public Map getUserMatchOrder(Long userId,Long matchId,Integer type) {
//		if(userId == null || matchId == null){
//			return null;
//		}
//		Map map = new HashMap();
//		map.put("userId", userId);
//		map.put("matchId", matchId);
//		map.put("type", type);
//		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserMatchOrder", map);
//	}
//
//	@Override
//	public List queryOrderPayUserList(Long matchId, Long userId, String name,
//			Integer sortType, Integer page, Integer pageSize) {
//		if(matchId == null){
//			return null;
//		}
//		Map map = new HashMap();
//		map.put("matchId", matchId);
//		map.put("userId", userId);
//		map.put("name", name);
//		map.put("sortType", sortType);
//		map.put("page", page);
//		map.put("pageSize", pageSize);
//		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryOrderPayUserList", map);
//	}
//
//	@Override
//	public Integer getOrderPayUserCount(Long matchId, String name) {
//		if(matchId == null){
//			return 0;
//		}
//		
//		Map map = new HashMap();
//		map.put("matchId", matchId);
//		map.put("name", name);
//		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOrderPayUserCount", map);
//	}
//
//	@Override
//	public Map getUserMatchOrderTest(Long userId, Long matchId, Integer type,
//			Integer start,Integer end) {
//		Map map = new HashMap();
//		map.put("userId", userId);
//		map.put("matchId", matchId);
//		map.put("type", type);
//		map.put("start", start);
//		map.put("end", end);
//		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserMatchOrderTest", map);	}
//
//	@Override
//	public Integer getMatchGongyi(Long matchId, Long userId) {
//		if(matchId == null || userId == null){
//			return 0;
//		}
//		Map map = new HashMap();
//		map.put("userId", userId);
//		map.put("matchId", matchId);
//		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchGongyi", map);	
//	}
//
//	@Override
//	public Integer getUserMatchCount(Long matchId, Long userId) {
//		if(matchId ==  null || userId == null){
//			return 0;
//		}
//		Map map = new HashMap();
//		map.put("userId", userId);
//		map.put("matchId", matchId);
//		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserMatchCount", map);		}

}
