package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class Chefs extends BasicPOModel{
	private Integer chefId;		//厨师编号
	private Integer agentId;		//商家编号
	private Integer category;		//厨师分类
	private Integer state;			//状态
	private Integer createdUser;	//创建人
	private Integer updatedUser;	//修改人
	
	private String chefName;	//厨师名称
	private String avatar;		//厨师头像
	private String createdTime;	//创建时间
	private String updatedAt;	//修改时间
	
	private Agents agents;		//商家類
	
	public Chefs(){
		
	}

	public Chefs(Integer chefId, Integer agentId, Integer category, Integer state,
			Integer createdUser, Integer updatedUser, String chefName,
			String avatar, String createdTime, String updatedAt, Agents agents) {
		this.chefId = chefId;
		this.agentId = agentId;
		this.category = category;
		this.state = state;
		this.createdUser = createdUser;
		this.updatedUser = updatedUser;
		
		this.chefName = chefName;
		this.avatar = avatar;
		this.createdTime = createdTime;
		this.updatedAt = updatedAt;
		this.agents = agents;
	}


	public Integer getChefId() {
		return chefId;
	}
	
	public void setChefId(Integer chefId) {
		this.chefId = chefId;
	}
	
	public Integer getAgentId() {
		return agentId;
	}
	
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	
	public Integer getCategory() {
		return category;
	}
	
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
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
	
	public String getChefName() {
		return chefName;
	}
	
	public void setChefName(String chefName) {
		this.chefName = chefName;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Agents getAgents() {
		return agents;
	}

	public void setAgents(Agents agents) {
		this.agents = agents;
	}

	public String toString() {
		return "Chefs [chefId=" + chefId + ", agentId=" + agentId + ", category=" + category + ", state=" + state
				+ ", createdUser=" + createdUser + ", updatedUser=" + updatedUser + ", chefName=" + chefName
				+ ", avatar=" + avatar + ", createdTime=" + createdTime + ", updatedAt=" + updatedAt + ", agents="
				+ agents + "]";
	}


}
