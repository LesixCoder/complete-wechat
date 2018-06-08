package com.lunchtasting.server.dao.gift;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.GiftCoupon;

public interface GiftCouponDAO extends GenericDAO<GiftCoupon> {

	/**
	 * 查询礼品劵列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryGiftCouponList(Integer page,Integer pageSize);
	
	/**
	 * 查询礼品劵详情
	 * @param id
	 * @return
	 */
	Map getGiftCouponDetail(Long id);
	
	/**
	 * 查询用户已兑换的礼品劵列表
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserGiftCouponCodeList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 礼品劵兑换
	 * @param giftCouponId
	 * @param userId
	 * @return
	 */
	Integer exchangeGiftCoupon(Long giftCouponId,Long userId);
	
	/**
	 * 获得用户兑换的某张礼品劵
	 * @param giftCouponId
	 * @param userId
	 * @return
	 */
	Map getUserCode(Long giftCouponId,Long userId);
	
	/**
	 * 创建礼品劵码
	 * @param map
	 */
	void createGiftCouponCode(Map map);
}
