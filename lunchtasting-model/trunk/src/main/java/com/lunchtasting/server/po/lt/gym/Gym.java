package com.lunchtasting.server.po.lt.gym;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class Gym extends BasicPOModel {

	private Long id;
	
	private Long areaId;
	
	private String name;
	
	private String introduce;
	
	private String phone;
	
	private String address;
	
	private String simpleAddress;
	
	private String businessHours;
	
	private String imgText;
	
	private String imgUrl;
	
	private String imgArray;
	
	private Double longitude;
	
	private Double latitude;
	
	private Date createTime;
	
	private Integer isOpen;
	
	private Integer flag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSimpleAddress() {
		return simpleAddress;
	}

	public void setSimpleAddress(String simpleAddress) {
		this.simpleAddress = simpleAddress;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getImgText() {
		return imgText;
	}

	public void setImgText(String imgText) {
		this.imgText = imgText;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgArray() {
		return imgArray;
	}

	public void setImgArray(String imgArray) {
		this.imgArray = imgArray;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
