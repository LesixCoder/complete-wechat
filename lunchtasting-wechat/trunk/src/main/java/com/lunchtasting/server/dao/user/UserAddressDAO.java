package com.lunchtasting.server.dao.user;

import java.util.List;
import java.util.Map;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.UserAddress;

public interface UserAddressDAO extends GenericDAO<UserAddress> {

	/**
	 * 查看用户地址
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List queryUserAddressList(Long userId,Integer page,Integer pageSize);
	
	/**
	 * 清除常用地址
	 * @param userId
	 */
	void clearFrequently(Long userId);
	
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
	Integer updateFrequently(Long addressId,Long userId);
	
	/**
	 * 删除一个地址
	 * @param addressId
	 * @param userId
	 * @return
	 */
	Integer removeAddress(Long addressId,Long userId);
	
	/**
	 * 获得省会列表
	 * @return
	 */
	List queryProvinceList();
	
	/**
	 * 获得省会下的城市列表
	 * @param provinceCode
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
	List queryAllCityList();
}
