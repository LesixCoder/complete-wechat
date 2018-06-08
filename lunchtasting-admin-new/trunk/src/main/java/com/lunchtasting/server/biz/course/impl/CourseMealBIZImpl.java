package com.lunchtasting.server.biz.course.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.course.CourseMealBIZ;
import com.lunchtasting.server.dao.course.CourseMealDAO;
import com.lunchtasting.server.po.lt.CourseMeal;
import com.lunchtasting.server.util.IdWorker;

@Service
public class CourseMealBIZImpl implements CourseMealBIZ {

	@Autowired
	private CourseMealDAO courseMealDAO;
	
	@Override
	public CourseMeal getById(Long id) {
		return courseMealDAO.getById(id);
	}

	@Override
	public List queryCourseMealList(Long courseId, Integer page,
			Integer pageSize) {
		return courseMealDAO.queryCourseMealList(courseId, page, pageSize);
	}

	@Override
	public Boolean addCourseMeal(Long courseId, Long gymId, Long coachId,
			String name, Double price, Double marketPrice,
			Integer peopleNumber, Integer courseNumber, Integer type,
			String strTime, Date beginTime, Date endTime) {
		
		CourseMeal meal = new CourseMeal();
		meal.setId(IdWorker.getId());
		meal.setCourseId(courseId);
		meal.setGymId(gymId);
		meal.setCoachId(coachId);
		meal.setName(name);
		meal.setPrice(price);
		meal.setMarketPrice(marketPrice);
		meal.setPeopleNumber(peopleNumber);
		meal.setCourseNumber(courseNumber);
		meal.setType(type);
		meal.setStrTime(strTime);
		meal.setBeginTime(beginTime);
		meal.setEndTime(endTime);
		courseMealDAO.create(meal);

		return true;
	}

	@Override
	public Map getEditCourseMeal(Long courseMealId) {
		return courseMealDAO.getEditCourseMeal(courseMealId);
	}

	@Override
	public Boolean editCourseMeal(Long courseMealId, Long courseId, Long gymId,
			Long coachId, String name, Double price, Double marketPrice,
			Integer peopleNumber, Integer courseNumber, Integer type,
			String strTime, Date beginTime, Date endTime) {

		CourseMeal meal = new CourseMeal();
		meal.setId(courseMealId);
		meal.setCourseId(courseId);
		meal.setGymId(gymId);
		meal.setCoachId(coachId);
		meal.setName(name);
		meal.setPrice(price);
		meal.setMarketPrice(marketPrice);
		meal.setPeopleNumber(peopleNumber);
		meal.setCourseNumber(courseNumber);
		meal.setType(type);
		meal.setStrTime(strTime);
		meal.setBeginTime(beginTime);
		meal.setEndTime(endTime);
		
		return true;
	}

	@Override
	public Boolean updateFlag(Long courseMealId, Integer flag) {
		Integer result = courseMealDAO.updateFlag(courseMealId, flag);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

}
