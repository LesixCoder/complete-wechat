package com.lunchtasting.server.po.lt;

import java.io.Serializable;
import java.util.List;

import com.lunchtasting.server.model.BasicPOModel;

public class Orders extends BasicPOModel{
	private Long orderId;			//订单编号
	private Integer paymentType;		//支付类型
	private Integer userId;			//下单用户编号
	private Integer couponId;			//优惠券编号
	private Integer addrId;			//收货地址编号
	private Integer updatedUser;		//修改人
	
	private double total;			//总计
	private double couponAmount;	//优惠金额
	private double actualAmount;	//actual_amount
	
	private String state;			//状态
	private String onlyId;			//订单加密ID
	private String invoiceTitle;	//发票抬头
	private String orderTime;		//下单时间
	private String payTime;			//支付时间
	private String preparedTime;	//确认时间
	private String deliveredTime;	//送达时间
	private String expectedDelivery;//预计送达时间
	private String updatedTime;		//修改时间
	private String deliveryDate;		//配送日期
	
	private List<OrderLines> orderLines;
	
	private ReceiverAddress receiverAddress;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(Integer updatedUser) {
		this.updatedUser = updatedUser;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(double couponAmount) {
		this.couponAmount = couponAmount;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOnlyId() {
		return onlyId;
	}

	public void setOnlyId(String onlyId) {
		this.onlyId = onlyId;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPreparedTime() {
		return preparedTime;
	}

	public void setPreparedTime(String preparedTime) {
		this.preparedTime = preparedTime;
	}

	public String getDeliveredTime() {
		return deliveredTime;
	}

	public void setDeliveredTime(String deliveredTime) {
		this.deliveredTime = deliveredTime;
	}

	public String getExpectedDelivery() {
		return expectedDelivery;
	}

	public void setExpectedDelivery(String expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<OrderLines> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLines> orderLines) {
		this.orderLines = orderLines;
	}

	public ReceiverAddress getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(ReceiverAddress receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
}
