package com.lunchtasting.server.po.lt;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class Agents extends BasicPOModel {
	private Integer agentId;
	private String agentName;
	private Integer category;
	private Integer level;
	private Integer state;
	private Integer areaId;
	private String address;
	private String coordinateX;
	private String coordinateY;
	private String description;
	private Date createdTime;
	private Integer createdUser;
	private Date updatedTime;
	private Integer updatedUser;
	private String stateName;
	private String linkTelephone;
	private String linkName;
	private String agentAddrId;
	public Agents(){
		
	}
	public Agents(Integer agentId, String agentName, Integer category, Integer level, Integer state, Integer areaId, String address,
			String coordinateX, String coordinateY, String description, Date createdTime, Integer createdUser,
			Date updatedTime, Integer updatedUser, String stateName, String linkTelephone, String linkName,String agentAddrId) {
		this.agentId = agentId;
		this.agentName = agentName;
		this.category = category;
		this.level = level;
		this.state = state;
		this.areaId = areaId;
		this.address = address;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.description = description;
		this.createdTime = createdTime;
		this.createdUser = createdUser;
		this.updatedTime = updatedTime;
		this.updatedUser = updatedUser;
		this.stateName = stateName;
		this.linkTelephone = linkTelephone;
		this.linkName = linkName;
		this.agentAddrId = agentAddrId;
	}

	/**
	 * @return the agentAddrId
	 */
	public String getAgentAddrId() {
		return agentAddrId;
	}


	/**
	 * @param agentAddrId the agentAddrId to set
	 */
	public void setAgentAddrId(String agentAddrId) {
		this.agentAddrId = agentAddrId;
	}


	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Integer getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(Integer updatedUser) {
		this.updatedUser = updatedUser;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getLinkTelephone() {
		return linkTelephone;
	}
	public void setLinkTelephone(String linkTelephone) {
		this.linkTelephone = linkTelephone;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agents [agentId=" + agentId + ", agentName=" + agentName + ", category=" + category + ", level=" + level
				+ ", state=" + state + ", areaId=" + areaId + ", address=" + address + ", coordinateX=" + coordinateX
				+ ", coordinateY=" + coordinateY + ", description=" + description + ", createdTime=" + createdTime
				+ ", createdUser=" + createdUser + ", updatedTime=" + updatedTime + ", updatedUser=" + updatedUser
				+ ", stateName=" + stateName + ", linkTelephone=" + linkTelephone + ", linkName=" + linkName
				+ ", agentAddrId=" + agentAddrId + "]";
	}
	
	
}
