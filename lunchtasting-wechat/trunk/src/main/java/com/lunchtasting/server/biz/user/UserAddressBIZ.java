package com.lunchtasting.server.biz.user;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.po.lt.UserAddress;

public interface UserAddressBIZ {
	
	UserAddress getById(Long id);
	
	/**
	 * 查看用户地址
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserAddressList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 用户创建一个地址
	 * @param userId
	 * @param province
	 * @param city
	 * @param town
	 * @param detail
	 * @param name
	 * @param phone
	 * @param sex
	 * @param postalCode
	 * @param isFrequently
	 * @param lng
	 * @param lat
	 * @return
	 */
	Boolean doAdd(Long userId,String province, String city, String town, String detail,
			String name, String phone, Integer sex, String postalCode,String isFrequently, Double lng, Double lat);
	
	/**
	 * 修改地址
	 * @param addressId
	 * @param userId
	 * @param province
	 * @param city
	 * @param town
	 * @param detail
	 * @param name
	 * @param phone
	 * @param sex
	 * @param isFrequently
	 * @param lng
	 * @param lat
	 * @return
	 */
	Boolean doEdit(Long addressId,Long userId,String province, String city, String town,
			String detail, String name, String phone,
			Integer sex, String postalCode,String isFrequently, Double lng, Double lat);
	
	/**
	 * 获得用户常用地址
	 * @param userId
	 * @return
	 */
	Map getFrequentlyAddress(Long userId);
	
	/**
	 * 获得用户选择的地址
	 * @param addressId
	 * @return
	 */
	Map getByAddressId(Long addressId);
	
	/**
	 * 修改默认地址
	 * @param addressId
	 * @param userId
	 * @return
	 */
	void updateFrequently(Long addressId,Long userId) throws Exception;
	
	/**
	 * 删除一个地址
	 * @param addressId
	 * @param userId
	 * @return
	 */
	Boolean doRemove(Long addressId,Long userId);
	
	/**
	 * 获得省会列表
	 * @return
	 */
	List queryProvinceList();
	
	/**
	 * 获得省会下的城市列表
	 * @param ProvinceCode
	 * @return
	 */
	List queryCityList(String provinceCode);
	
	/**
	 * 获得城市下的县区列表
	 * @param cityCode
	 * @return
	 */
	List queryTownList(String cityCode);
	
	/**
	 * 查询所有省会、市、区县列表
	 * @return
	 */
	Map getAllCityMap();
}
