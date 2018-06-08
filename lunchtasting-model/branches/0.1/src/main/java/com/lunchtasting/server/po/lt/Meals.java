package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;


public class Meals extends BasicPOModel{
	private Integer mealId;				//菜品编号
	private Integer chefId;				//厨师编号
	private Integer state;				//状态
	private Integer categoryId;			//分类编号
	private Integer spicy;				//辣度
	private Integer vegetables;			//荤素
	private Integer clitocybine;		//荤素
	private Integer supplyWeek;			//供应周期
	private Integer stock;				//总数量
	private Integer buyNum;				//已订数量
	private Integer createdUser;		//创建人
	private Integer updatedUser;		//修改人
	private double price;			//价格
	private String mealName;		//菜品名称
	private String tag;				//标签
	private String nutritionValue;	//营养成分
	private String description;		//配料信息
	private String createdTime;		//创建时间
	private String updatedTime;		//修改时间
	
	private Chefs chefs;			//厨师类
	private Integer concessionPrice;
	
	private MealsPictures mealsPictures;//菜品图片类

	public Meals() {
	}

	public Meals(Integer mealId, Integer chefId, Integer state, Integer categoryId, Integer spicy,
			Integer vegetables, Integer clitocybine,Integer supplyWeek, Integer stock, Integer buyNum,
			Integer createdUser, Integer updatedUser, double price, String mealName,String tag,
			String nutritionValue, String description, String createdTime, String updatedTime,
			Chefs chefs,MealsPictures mealsPictures) {
		this.mealId = mealId;
		this.chefId = chefId;
		this.state = state;
		this.categoryId = categoryId;
		this.spicy = spicy;
		this.vegetables = vegetables;
		this.clitocybine = clitocybine;
		this.supplyWeek = supplyWeek;
		this.stock = stock;
		this.buyNum = buyNum;
		this.createdUser = createdUser;
		this.updatedUser = updatedUser;
		this.price = price;
		this.mealName = mealName;
		this.tag = tag;
		this.nutritionValue = nutritionValue;
		this.description = description;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.chefs = chefs;
		this.mealsPictures = mealsPictures;
	}

	
	public Integer getConcessionPrice() {
		return concessionPrice;
	}

	public void setConcessionPrice(Integer concessionPrice) {
		this.concessionPrice = concessionPrice;
	}

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public Integer getChefId() {
		return chefId;
	}

	public void setChefId(Integer chefId) {
		this.chefId = chefId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSpicy() {
		return spicy;
	}

	public void setSpicy(Integer spicy) {
		this.spicy = spicy;
	}

	public Integer getVegetables() {
		return vegetables;
	}

	public void setVegetables(Integer vegetables) {
		this.vegetables = vegetables;
	}

	public Integer getClitocybine() {
		return clitocybine;
	}

	public void setClitocybine(Integer clitocybine) {
		this.clitocybine = clitocybine;
	}

	public Integer getSupplyWeek() {
		return supplyWeek;
	}

	public void setSupplyWeek(Integer supplyWeek) {
		this.supplyWeek = supplyWeek;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNutritionValue() {
		return nutritionValue;
	}

	public void setNutritionValue(String nutritionValue) {
		this.nutritionValue = nutritionValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public Chefs getChefs() {
		return chefs;
	}

	public void setChefs(Chefs chefs) {
		this.chefs = chefs;
	}

	public MealsPictures getMealsPictures() {
		return mealsPictures;
	}

	public void setMealsPictures(MealsPictures mealsPictures) {
		this.mealsPictures = mealsPictures;
	}

	public String toString() {
		return "Meals [mealId=" + mealId + ", chefId=" + chefId + ", state=" + state + ", categoryId=" + categoryId
				+ ", spicy=" + spicy + ", vegetables=" + vegetables + ", clitocybine=" + clitocybine + ", supplyWeek="
				+ supplyWeek + ", stock=" + stock + ", buyNum=" + buyNum + ", createdUser=" + createdUser
				+ ", updatedUser=" + updatedUser + ", price=" + price + ", mealName=" + mealName + ", tag=" + tag
				+ ", nutritionValue=" + nutritionValue + ", description=" + description + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", chefs=" + chefs + ", mealsPictures=" + mealsPictures + "]";
	}

}
