package com.lunchtasting.server.po.lt;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class CourseOrderVote extends BasicPOModel {

	private Long id;
	
	private Long userId;
	
	private Long desUserId;
	
	private Long courseOrderId;
	
	private Long courseMealId;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDesUserId() {
		return desUserId;
	}

	public void setDesUserId(Long desUserId) {
		this.desUserId = desUserId;
	}

	public Long getCourseOrderId() {
		return courseOrderId;
	}

	public void setCourseOrderId(Long courseOrderId) {
		this.courseOrderId = courseOrderId;
	}

	public Long getCourseMealId() {
		return courseMealId;
	}

	public void setCourseMealId(Long courseMealId) {
		this.courseMealId = courseMealId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
