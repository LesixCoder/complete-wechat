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
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class GymBIZImpl implements GymBIZ {
	
	@Autowired
	private GymDAO gymDAO;
	
	@Override
	public List queryGymList() {
		return gymDAO.queryGymList();
	}

	@Override
	public Map getGymDetail(Long gymId) {
		return gymDAO.getGymDetail(gymId);
	}

	@Override
	public List queryCourseGymList(Long courseId, Integer page, Integer pageSize) {
		List list = gymDAO.queryCourseGymList(courseId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 健身房图片
				 */
				map.put("gym_img_url", VariableHelper.IMAGE_VISIT_URL
						+map.get("gym_img_url"));

				/**
				 * 拆分课程信息
				 */
				List mealList = null;
				if(ValidatorHelper.isMapNotEmpty(map.get("course_meal_str"))){
					mealList = new ArrayList();
					String mealStr =  map.get("course_meal_str").toString();
					String [] aSpilt = mealStr.split("\\|");
					if(ValidatorHelper.isNotEmpty(aSpilt)){
						Map mealMap = null;
						for(String s : aSpilt){
							String [] bSpilt = s.split("\\@");
							if(ValidatorHelper.isNotEmpty(bSpilt)){
								mealMap = new HashMap();
								int peopleNumber = Integer.parseInt(bSpilt[4]);
								int orderCount = Integer.parseInt(bSpilt[8]);
								int type = Integer.parseInt(bSpilt[12]);
								
								mealMap.put("id", bSpilt[0]);
								mealMap.put("name", bSpilt[1]);
								mealMap.put("price", CommonHelper.getDobule(
												Double.parseDouble(bSpilt[2])));
//								mealMap.put("str_time", bSpilt[3]);
								mealMap.put("people_number", bSpilt[4]);
								mealMap.put("course_number", bSpilt[5]);
//								mealMap.put("begin_time", bSpilt[6]);
//								mealMap.put("end_time", bSpilt[7]);
								mealMap.put("order_count", bSpilt[8]);
								mealMap.put("coach_id", bSpilt[9]);
								mealMap.put("coach_img_url", VariableHelper.IMAGE_VISIT_URL+bSpilt[10] 
										+ QiNiuStorageHelper.MODEL1+"w/140/h/140");
								mealMap.put("coach_name", bSpilt[11]);
								mealMap.put("market_price", CommonHelper.getDobule(
										Double.parseDouble(bSpilt[13])));
								mealMap.put("type", type);
								/**
								 * 判断时间显示
								 * 1 普通课  2团课
								 */
								if(type == 1){
									mealMap.put("str_time", bSpilt[6] + " " + bSpilt[3]);
								}else{
									mealMap.put("str_time", bSpilt[6] + " - " 
											+ bSpilt[7] + " " +bSpilt[3]);
								}
								
								/**
								 * 判断当前报名人数是否超过上课人数
								 * status 1可以报名  2报名已满
								 */
								if(orderCount >= peopleNumber){
									mealMap.put("status", 2);
								}else{
									mealMap.put("status", 1);
								}
								mealList.add(mealMap);

							}
						}
					}
				}
				map.remove("course_meal_str");
				map.put("course_meal_list", mealList);
				newList.add(map);
			}
			return newList;
			
		}
		
		return null;
	}

}
