package com.lunchtasting.server.po.lt;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class OrdersSettlement extends BasicPOModel{
	private Long id;
	private Long orderId;
	private Long sellerId;
	private Double payPrice;
	private Double originalPrice;
	private Double subsidyPrice;
	private Double servicePrice;
	private Double channelPrice;
	private Double playPrice;
	private Double profitPrice;
	private Date beginTime;
	private Date endTime;
	private Date playTime;
	private String remark;
	private Integer isReceipt;
	private Integer status;
	private Date createTime;
	private String newId;
	private String sellerName;
	private String newSellerId;
	private Date settlementDate;
	private Date settlementEndDate;
	private Date nextDay;
	private Long sId;
	public Long getsId() {
		return sId;
	}
	public void setsId(Long sId) {
		this.sId = sId;
	}
	public Date getNextDay() {
		return nextDay;
	}
	public void setNextDay(Date nextDay) {
		this.nextDay = nextDay;
	}
	public Date getSettlementEndDate() {
		return settlementEndDate;
	}
	public void setSettlementEndDate(Date settlementEndDate) {
		this.settlementEndDate = settlementEndDate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getNewSellerId() {
		return newSellerId;
	}
	public void setNewSellerId(String newSellerId) {
		this.newSellerId = newSellerId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Double getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getSubsidyPrice() {
		return subsidyPrice;
	}
	public void setSubsidyPrice(Double subsidyPrice) {
		this.subsidyPrice = subsidyPrice;
	}
	public Double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Double getChannelPrice() {
		return channelPrice;
	}
	public void setChannelPrice(Double channelPrice) {
		this.channelPrice = channelPrice;
	}
	public Double getPlayPrice() {
		return playPrice;
	}
	public void setPlayPrice(Double playPrice) {
		this.playPrice = playPrice;
	}
	public Double getProfitPrice() {
		return profitPrice;
	}
	public void setProfitPrice(Double profitPrice) {
		this.profitPrice = profitPrice;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsReceipt() {
		return isReceipt;
	}
	public void setIsReceipt(Integer isReceipt) {
		this.isReceipt = isReceipt;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
