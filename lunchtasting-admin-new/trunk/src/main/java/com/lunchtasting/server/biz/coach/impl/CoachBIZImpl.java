package com.lunchtasting.server.biz.coach.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.coach.CoachBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.coach.CoachDAO;
import com.lunchtasting.server.po.lt.gym.Coach;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CoachBIZImpl implements CoachBIZ {

	@Autowired
	private CoachDAO coachDAO;
	

	@Override
	public Coach getById(Long id) {
		return coachDAO.getById(id);
	}

	@Override
	public List queryCoachList(Integer page, Integer pageSize) {
		return coachDAO.queryCoachList(page, pageSize);
	}
	
	@Override
	public List querySimpleCoachList() {
		return coachDAO.querySimpleCoachList();
	}

	@Override
	public Boolean addCoach(String name, Long gymId, Integer sex, String phone,
			Integer age, String birth, Integer coachYear, String certificate,
			String imgUrl) {
		
		Coach coach = new Coach();
		coach.setId(IdWorker.getId());
		coach.setName(name);
		coach.setGymId(gymId);
		coach.setSex(sex);
		coach.setPhone(phone);
		coach.setAge(age);
		coach.setBirth(birth);
		coach.setCoachYear(coachYear);
		coach.setCertificate(certificate);
		coach.setImgUrl(imgUrl);
		coachDAO.create(coach);
		return true;
	}

	@Override
	public Map getEditCoach(Long coachId) {
		return coachDAO.getEditCoach(coachId);
	}

	@Override
	public Boolean editCoach(Long coachId, String name, Long gymId,
			Integer sex, String phone, Integer age, String birth,
			Integer coachYear, String certificate, String imgUrl) {
		
		Coach coach = new Coach();
		coach.setId(coachId);
		coach.setName(name);
		coach.setGymId(gymId);
		coach.setSex(sex);
		coach.setPhone(phone);
		coach.setAge(age);
		coach.setBirth(birth);
		coach.setCoachYear(coachYear);
		coach.setCertificate(certificate);
		coach.setImgUrl(imgUrl);
		coachDAO.update(coach);
		return true;
	}
	
	@Override
	public Boolean updateFlag(Long coachId, Integer flag) {
		Integer result = coachDAO.updateFlag(coachId, flag);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public List queryCoachAlbumList(Long coachId, Integer page, Integer pageSize) {
		List list = coachDAO.queryCoachAlbumList(coachId, page, pageSize);
		if(list != null){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("img_url"))){
					map.put("img_url",SysConfig.IMG_VISIT_URL+map.get("img_url").toString()
							+QiNiuStorageHelper.MODEL0+"w/80/h/80");
				}
			}
		}
		return list;
	}

	@Override
	public Boolean addCoachAlbum(Long coachId, String imgUrl) {
		
		Map map = new HashMap();
		map.put("id", IdWorker.getId());
		map.put("coachId", coachId);
		map.put("imgUrl", imgUrl);
		return true;
	}

	@Override
	public Boolean removeCoachAlbum(Long coachAlbumId) {
		coachDAO.removeCoachAlbum(coachAlbumId);
		return true;
	}
}
