package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class MealsPictures extends BasicPOModel{
	private Long id;				//图片编号
	private Integer mealId;			//菜品编号
	private Integer sequence;		//排序
	private Integer createdUser;	//创建人
	private Integer updatedUser;	//修改人
	private String title;		//图片标题
	private String image;		//图片路径
	private String createdTime;	//创建时间
	private String updatedTime;	//修改时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}
	public Integer getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(Integer updatedUser) {
		this.updatedUser = updatedUser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}
