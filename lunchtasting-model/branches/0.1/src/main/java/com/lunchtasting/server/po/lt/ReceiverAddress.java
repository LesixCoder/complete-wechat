package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class ReceiverAddress extends BasicPOModel{
	
	
	private Integer addrId;		//地址编号
	private Integer userId;		//用户编号
	private Integer areaId;		//大厦编号
	private Integer addressDefault;//是否默认
	private Integer createdUser;	//创建人
	private Integer updatedUser;	//修改人
	private Integer flag;			//状态
	
	private String receiverName;//收货人
	private String telephone;	//手机号
	private String createdTime;	//创建时间
	private String updatedTime;	//修改时间
	private String addressDetail;//详细地址
	private String addDe;
	
	public String getAddDe() {
		return addDe;
	}

	public void setAddDe(String addDe) {
		this.addDe = addDe;
	}

	private Area area;			//区域类
	
	public ReceiverAddress(){
		
	}

	/**
	 * @return the addressDetail
	 */
	public String getAddressDetail() {
		return addressDetail;
	}

	/**
	 * @param addressDetail the addressDetail to set
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public ReceiverAddress(Integer addrId, Integer userId, Integer areaId, Integer addressDefault, Integer createdUser, Integer updatedUser,
			Integer flag, String receiverName, String telephone, String createdTime, String updatedTime, Area area,String addressDetail) {
		this.addrId = addrId;
		this.userId = userId;
		this.areaId = areaId;
		this.addressDefault = addressDefault;
		this.createdUser = createdUser;
		this.updatedUser = updatedUser;
		this.flag = flag;
		this.receiverName = receiverName;
		this.telephone = telephone;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.area = area;
		this.addressDetail = addressDetail;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getAddressDefault() {
		return addressDefault;
	}

	public void setAddressDefault(Integer addressDefault) {
		this.addressDefault = addressDefault;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReceiverAddress [addrId=" + addrId + ", userId=" + userId + ", areaId=" + areaId + ", addressDefault="
				+ addressDefault + ", createdUser=" + createdUser + ", updatedUser=" + updatedUser + ", flag=" + flag
				+ ", receiverName=" + receiverName + ", telephone=" + telephone + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", addressDetail=" + addressDetail + ", area=" + area + "]";
	}

}
