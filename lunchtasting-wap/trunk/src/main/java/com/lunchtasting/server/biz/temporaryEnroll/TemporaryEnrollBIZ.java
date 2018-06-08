package com.lunchtasting.server.biz.temporaryEnroll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;


public interface TemporaryEnrollBIZ {
	/**
	 * 插入一条报名
	 * @param temporaryEnroll
	 * @return
	 */
	Boolean create(TemporaryEnroll temporaryEnroll);
	/**
	 * 随机抽取一条
	 * @param map
	 * @return
	 */
	TemporaryEnroll getRandomOneRandom(HashMap map);
	/**
	 * 配对报名
	 * @return 报名表id
	 */
	Long applyTo(Long otherId,Integer sex , String name,String tel,Long userId);
	/**
	 * 单人报名
	 * @param sex
	 * @param name
	 * @param tel
	 * @return
	 */
	public Long applyOne(Integer sex ,String name ,String tel,Long userId);
	/**
	 * 支付成功回调
	 * @param id
	 * @return
	 */
	boolean payUpdate(Long userId ,Map map);
	
	
	
	int checkUser(String phone);
	/**
	 * show
	 * @param uesrId
	 * @param groupId
	 * @return
	 */
	List show(Long uesrId,Long groupId);
	
	/**
	 * 查询
	 * @return
	 */
	TemporaryEnroll getTemporaryEnrollByUserId(Long userId);
	/**
	 * 判断性别是否相同
	 * @param sex
	 * @return
	 */
	boolean verifySex(Integer sex,Long userId);
	/**
	 * 报名2个人组队
	 * @param userId
	 * @param userId2
	 * @return
	 */
	boolean applyhaha(Long userId, Long userId2);
}
