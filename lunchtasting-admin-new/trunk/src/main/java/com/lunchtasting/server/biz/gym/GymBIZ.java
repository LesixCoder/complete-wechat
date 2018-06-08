package com.lunchtasting.server.biz.gym;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.gym.Gym;

public interface GymBIZ {
	
	Gym getById(Long id);
	
	/**
	 * 查询场馆列表
	 * @param flag
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryGymList(Integer flag,Integer page,Integer pageSize);
	
	/**
	 * 查询简单所有健身房信息	
	 * @return
	 */
	List querySimpleGymList();
	
	/**
	 * 添加一个健身房
	 * @param gymId
	 * @param name
	 * @param phone
	 * @param address
	 * @param simpleAddress
	 * @param introduce
	 * @param imgUrl
	 * @param imgArray
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	Boolean addGym(String name,String phone,String address
			,String simpleAddress,String introduce,String imgUrl
			,String imgArray,Double longitude,Double latitude);
	
	/**
	 * 获得需要编辑的健身房信息
	 * @param gymId
	 * @return
	 */
	Map getEditGym(Long gymId);
	
	/**
	 * 健身房编辑
	 * @param gymId
	 * @param name
	 * @param phone
	 * @param address
	 * @param simpleAddress
	 * @param introduce
	 * @param imgUrl
	 * @param imgArray
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	Boolean editGym(Long gymId,String name,String phone,String address,String simpleAddress
			,String introduce,String imgUrl,String imgArray
			,Double longitude,Double latitude);
	
	/**
	 * 修改状态
	 * @param gymId
	 * @param flag
	 * @return
	 */
	Boolean updateFlag(Long gymId,Integer flag);
	
}
