package com.lunchtasting.server.po.lt;
import com.lunchtasting.server.model.BasicPOModel;

public class OrderLines extends BasicPOModel{
	private Long id;				//逐渐ID
	private Integer state;				//配送状态
	private Integer quantity;			//数量
	private Integer orderId;			//订单编号
	private Integer mealId;			//菜品编号
	private Integer createdUser;		//购买用户
	private Integer updatedUser;		//修改人
	private double packageFee;		//餐盒费
	private double price;			//单价
	private double subtotal;		//小计
	private String comment;			//备注
	private String createdTime;		//购买时间
	private String updatedTime;		//修改时间
	private String deliveredTime;	//送达时间
	private String deliveryDate;	//收货日期
	private Meals meals;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMealId() {
		return mealId;
	}
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
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
	public double getPackageFee() {
		return packageFee;
	}
	public void setPackageFee(double packageFee) {
		this.packageFee = packageFee;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public String getDeliveredTime() {
		return deliveredTime;
	}
	public void setDeliveredTime(String deliveredTime) {
		this.deliveredTime = deliveredTime;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Meals getMeals() {
		return meals;
	}
	public void setMeals(Meals meals) {
		this.meals = meals;
	}
	
}
