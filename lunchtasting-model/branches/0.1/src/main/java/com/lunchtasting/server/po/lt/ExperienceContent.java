package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class ExperienceContent extends BasicPOModel{
	private Long ueId;//关联的UE用户表
	private String advantage;//满意的地方
	private String insufficient;//不足的地方
	private Integer faceValue;//颜值
	private String faceText;//颜值评论
	private Integer flavourValue;//味道
	private String flavourText;//味道评论
	private Integer nutritionValue;//营养价值
	private String nutritionText;//营养价值
	private Integer logisticsValue;//物流体验
	private String logisticsText;//物流体验
	private Integer mealId;
	private String creationTime;
	private String imgSrc;//图片路径
	private String mealName;//菜品名字
	private Integer mealCalorie;//卡路里
	private String mealDescription;//配料信息
	private String mealNutrition;//菜品营养价值
	private Integer mealPrice;//价格
	private String ueName;//评论用户名
	private String ueSex;//用户性别
	private String touimgSrc;//用户头像

	public Long getUeId() {
		return ueId;
	}
	public void setUeId(Long ueId) {
		this.ueId = ueId;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getInsufficient() {
		return insufficient;
	}
	public void setInsufficient(String insufficient) {
		this.insufficient = insufficient;
	}
	public Integer getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}
	public String getFaceText() {
		return faceText;
	}
	public void setFaceText(String faceText) {
		this.faceText = faceText;
	}
	public Integer getFlavourValue() {
		return flavourValue;
	}
	public void setFlavourValue(Integer flavourValue) {
		this.flavourValue = flavourValue;
	}
	public String getFlavourText() {
		return flavourText;
	}
	public void setFlavourText(String flavourText) {
		this.flavourText = flavourText;
	}
	public Integer getNutritionValue() {
		return nutritionValue;
	}
	public void setNutritionValue(Integer nutritionValue) {
		this.nutritionValue = nutritionValue;
	}
	public String getNutritionText() {
		return nutritionText;
	}
	public void setNutritionText(String nutritionText) {
		this.nutritionText = nutritionText;
	}
	public Integer getLogisticsValue() {
		return logisticsValue;
	}
	public void setLogisticsValue(Integer logisticsValue) {
		this.logisticsValue = logisticsValue;
	}
	public String getLogisticsText() {
		return logisticsText;
	}
	public void setLogisticsText(String logisticsText) {
		this.logisticsText = logisticsText;
	}
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public Integer getMealCalorie() {
		return mealCalorie;
	}
	public void setMealCalorie(Integer mealCalorie) {
		this.mealCalorie = mealCalorie;
	}
	public String getMealDescription() {
		return mealDescription;
	}
	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}
	public String getMealNutrition() {
		return mealNutrition;
	}
	public void setMealNutrition(String mealNutrition) {
		this.mealNutrition = mealNutrition;
	}
	public Integer getMealPrice() {
		return mealPrice;
	}
	public void setMealPrice(Integer mealPrice) {
		this.mealPrice = mealPrice;
	}
	public String getUeName() {
		return ueName;
	}
	public void setUeName(String ueName) {
		this.ueName = ueName;
	}
	public String getUeSex() {
		return ueSex;
	}
	public void setUeSex(String ueSex) {
		this.ueSex = ueSex;
	}
	public String getTouimgSrc() {
		return touimgSrc;
	}
	public void setTouimgSrc(String touimgSrc) {
		this.touimgSrc = touimgSrc;
	}
	
	
}
