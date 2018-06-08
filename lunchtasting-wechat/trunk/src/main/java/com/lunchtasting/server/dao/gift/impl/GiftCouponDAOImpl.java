package com.lunchtasting.server.dao.gift.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.gift.GiftCouponDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.GiftCoupon;

@Repository
public class GiftCouponDAOImpl extends GenericDAOSupport<Long, GiftCoupon> implements GiftCouponDAO {

	@Override
	protected Class<?> getObjectClass() {
		return GiftCoupon.class;
	}

	@Override
	protected String getTableName() {
		return "gift_coupon";
	}

	@Override
	public List queryGiftCouponList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGiftCouponList", map);			
	}

	@Override
	public Map getGiftCouponDetail(Long id) {
		if(id == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("id", id);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGiftCouponDetail", map);		
	}

	@Override
	public List queryUserGiftCouponCodeList(Long userId, Integer page,
			Integer pageSize) {
		if(userId == null){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserGiftCouponCodeList", map);			
	}

	@Override
	public Integer exchangeGiftCoupon(Long giftCouponId, Long userId) {
		if(giftCouponId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("giftCouponId", giftCouponId);
		map.put("userId", userId);
		return getSqlMapClientTemplate().update(getNamespace() + ".exchangeGiftCoupon", map);			
	}

	@Override
	public Map getUserCode(Long giftCouponId, Long userId) {
		if(giftCouponId == null || userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("giftCouponId", giftCouponId);
		map.put("userId", userId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserCode", map);			
	}

	@Override
	public void createGiftCouponCode(Map map) {
		if(map == null){
			return;
		}
		getSqlMapClientTemplate().insert(getNamespace() + ".createGiftCouponCode", map);			
	}

}
