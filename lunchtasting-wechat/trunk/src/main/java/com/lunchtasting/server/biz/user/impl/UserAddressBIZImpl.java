package com.lunchtasting.server.biz.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.user.UserAddressBIZ;
import com.lunchtasting.server.dao.user.UserAddressDAO;
import com.lunchtasting.server.po.lt.UserAddress;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class UserAddressBIZImpl implements UserAddressBIZ {

	@Autowired
	private UserAddressDAO addressDAO;

	@Override
	public UserAddress getById(Long id) {
		return addressDAO.getById(id);
	}
	
	@Override
	public List queryUserAddressList(Long userId, Integer page, Integer pageSize) {
		return addressDAO.queryUserAddressList(userId, page, pageSize);
	}

	@Override
	public Boolean doAdd(Long userId, String province, String city,
			String town, String detail, String name, String phone, Integer sex,
			String postalCode,String isFrequently, Double lng, Double lat) {
		
		UserAddress address = new UserAddress();
		address.setId(IdWorker.getId());
		address.setProvince(province);
		address.setCity(city);
		address.setTown(town);
		address.setDetail(detail);
		address.setName(name);
		address.setPhone(phone);
		address.setUserId(userId);
		address.setLongitude(lng);
		address.setLatitude(lat);
		address.setSex(sex);
		address.setPostalCode(postalCode);
		if(ValidatorHelper.isNotEmpty(isFrequently) && "1".equals(isFrequently)){
			address.setIsFrequently(1);
		}else{
			address.setIsFrequently(0);
		}
		addressDAO.create(address);
		return true;
	}

	@Override
	public Boolean doEdit(Long addressId,Long userId,String province, String city,
			String town, String detail, String name, String phone, Integer sex,String postalCode,
			String isFrequently, Double lng, Double lat) {
		
		UserAddress ad = addressDAO.getById(addressId);
		if(ad == null || ad.getUserId().longValue() != userId.longValue()){
			return false;
		}
		ad.setProvince(province);
		ad.setCity(city);
		ad.setTown(town);
		ad.setDetail(detail);
		ad.setName(name);
		ad.setPhone(phone);
		ad.setLongitude(lng);
		ad.setLatitude(lat);
		ad.setSex(sex);
		ad.setPostalCode(postalCode);
		if(ValidatorHelper.isNotEmpty(isFrequently) && "1".equals(isFrequently)){
			/**
			 * 如果这个地址不是默认地址，就清除以前的默认地址
			 */
			if(ad.getIsFrequently().intValue() == 0){
				addressDAO.clearFrequently(userId);
			}
			ad.setIsFrequently(1);
		}else{
			ad.setIsFrequently(0);
		}
		addressDAO.update(ad);
		return true;
	}

	@Override
	public Map getFrequentlyAddress(Long userId) {
		return addressDAO.getFrequentlyAddress(userId);
	}

	@Override
	public Map getByAddressId(Long addressId) {
		return addressDAO.getByAddressId(addressId);
	}

	@Override
	public void updateFrequently(Long addressId, Long userId) throws Exception{
		
		/**
		 * 清除原先默认地址
		 */
		addressDAO.clearFrequently(userId);
		
		/**
		 * 修改当前为默认
		 */
		addressDAO.updateFrequently(addressId, userId);
		
	}

	@Override
	public Boolean doRemove(Long addressId, Long userId) {
		Integer result = addressDAO.removeAddress(addressId, userId);
		if(result != null && result > 0){
			return true;
		}
		
		return false;
	}

	@Override
	public List queryProvinceList() {
		return addressDAO.queryProvinceList();
	}

	@Override
	public List queryCityList(String provinceCode) {
		return addressDAO.queryCityList(provinceCode);
	}

	@Override
	public List queryTownList(String cityCode) {
		return addressDAO.queryTownList(cityCode);
	}

	@Override
	public Map getAllCityMap() {
		List list = addressDAO.queryAllCityList();
		if(ValidatorHelper.isNotEmpty(list)){
			
			Map resultMap = new HashMap();
			
			Map provinceMap = null;
			List provinceList = new ArrayList();
			
			Map cityMap = new HashMap();
			List cityList = new ArrayList();
			
			Map townMap = new HashMap();
			List townList = new ArrayList();
			
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				String provinceName = map.get("province_name").toString();
				String provinceCode = map.get("province_code").toString();
				
				String cityName = "";
				String cityCode = "";
				if(ValidatorHelper.isMapNotEmpty(map.get("city_name"))){
					cityName = map.get("city_name").toString();
					cityCode = map.get("city_code").toString();
				}
				
				String townName = "";
				String townCode = "";
				if(ValidatorHelper.isMapNotEmpty(map.get("town_name"))){
					townName = map.get("town_name").toString();
					townCode = map.get("town_code").toString();

				}
				
				/**
				 * 省
				 */
				boolean check = false;
				for(int i1 = 0 ;i1<provinceList.size();i1++){
					HashMap map1 = (HashMap) provinceList.get(i1);
					String provinceValue = map1.get("value").toString();
					if(provinceValue.equals(provinceCode)){
						check = true;
					}
				}
				if(check == false){
					provinceMap = new HashMap();
					provinceMap.put("text", provinceName);
					provinceMap.put("value", provinceCode);
					provinceList.add(provinceMap);
				}
				
				/**
				 * 市
				 */
				if(!cityMap.containsKey(provinceCode)){
					
					Map cMap = new HashMap();
					cMap.put("text", cityName);
					cMap.put("value", cityCode);
					
					List cList = new ArrayList();
					cList.add(cMap);
					cityMap.put(provinceCode, cList);
					
				}else{
					List cList = (List)cityMap.get(provinceCode);
					if(ValidatorHelper.isNotEmpty(cList)){
						boolean check2 = false;
						for(int i2 =0 ;i2<cList.size();i2++){
							HashMap map2 = (HashMap) cList.get(i2);
							String cityValue = map2.get("value").toString();
							if(cityValue.equals(cityCode)){
								check2 = true;
							}
						}
						if(check2 == false){
							Map cMap = new HashMap();
							cMap.put("text", cityName);
							cMap.put("value", cityCode);							
							cList.add(cMap);
							cityMap.put(provinceCode, cList);
						}
						
					}
				}
				
				/**
				 * 区县
				 */
				if(!townMap.containsKey(cityCode)){
					
					Map tMap = new HashMap();
					tMap.put("text", townName);
					tMap.put("value", townCode);
					
					List tList = new ArrayList();
					tList.add(tMap);
					townMap.put(cityCode, tList);
					
				}else{
					List tList = (List)townMap.get(cityCode);
					if(ValidatorHelper.isNotEmpty(tList)){
						boolean check3 = false;
						for(int i3 =0 ;i3<tList.size();i3++){
							HashMap map3 = (HashMap) tList.get(i3);
							String cityValue = map3.get("value").toString();
							if(cityValue.equals(townCode)){
								check3 = true;
							}
						}
						if(check3 == false){
							Map tMap = new HashMap();
							tMap.put("text", townName);
							tMap.put("value", townCode);							
							tList.add(tMap);
							townMap.put(cityCode, tList);
						}
						
					}
				}
				
			}
			cityList.add(cityMap);
			townList.add(townMap);
			
			resultMap.put("province", provinceList);
			resultMap.put("city", cityList);
			resultMap.put("town", townList);
			return resultMap;
		}
		return null;
	}

}
