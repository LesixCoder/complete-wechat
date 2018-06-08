package com.lunchtasting.server.biz.gym.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.gym.GymDAO;
import com.lunchtasting.server.po.lt.gym.Gym;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class GymBIZImpl implements GymBIZ {

	@Autowired
	private GymDAO gymDAO;
	
	@Override
	public Gym getById(Long id) {
		return gymDAO.getById(id);
	}

	@Override
	public List queryGymList(Integer flag,Integer page, Integer pageSize) {
		return gymDAO.queryGymList(flag,page, pageSize);
	}

	@Override
	public List querySimpleGymList() {
		return gymDAO.querySimpleGymList();
	}

	@Override
	public Boolean addGym(String name, String phone,
			String address, String simpleAddress, String introduce,
			String imgUrl, String imgArray, Double longitude, Double latitude) {
		
		Gym gym = new Gym();
		gym.setId(IdWorker.getId());
		gym.setName(name);
		gym.setPhone(phone);
		gym.setAddress(address);
		gym.setSimpleAddress(simpleAddress);
		gym.setIntroduce(introduce);
		gym.setImgUrl(imgUrl);
		gym.setImgArray(imgArray);
		gym.setLongitude(longitude);
		gym.setLatitude(latitude);
		gymDAO.create(gym);
		return true;
	}

	@Override
	public Map getEditGym(Long gymId) {
		Map map = gymDAO.getEditGym(gymId);
		if(map != null){
			
			/**
			 * 图片
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("img_url"))){
				map.put("img_name", map.get("img_url").toString());
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/750/h/540");
			}
			
			/**
			 * 多张图片
			 */
			if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
				List imgList = imgList = new ArrayList();
				Map imageMap = null;
				String imgArray = map.get("img_array").toString();
				String [] spilt = imgArray.split(",");
				for(String img : spilt){
					imageMap = new HashMap();
					imageMap.put("img_name", img);
					imageMap.put("img_url", SysConfig.IMG_VISIT_URL+img+QiNiuStorageHelper.MODEL0+"w/750/h/540");
					imgList.add(imageMap);
				}
				map.put("img_list", imgList);
			}
		}
		return map;
	}

	@Override
	public Boolean editGym(Long gymId, String name, String phone,
			String address, String simpleAddress, String introduce,
			String imgUrl, String imgArray, Double longitude, Double latitude) {
		
		Gym gym = new Gym();
		gym.setId(gymId);
		gym.setName(name);
		gym.setPhone(phone);
		gym.setAddress(address);
		gym.setSimpleAddress(simpleAddress);
		gym.setIntroduce(introduce);
		gym.setImgUrl(imgUrl);
		gym.setImgArray(imgArray);
		gym.setLongitude(longitude);
		gym.setLatitude(latitude);
		gymDAO.update(gym);
		return true;
	}

	@Override
	public Boolean updateFlag(Long gymId, Integer flag) {
		Integer result = gymDAO.updateFlag(gymId, flag);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

}
