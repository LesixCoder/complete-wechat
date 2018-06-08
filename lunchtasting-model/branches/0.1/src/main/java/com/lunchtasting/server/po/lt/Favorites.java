package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class Favorites extends BasicPOModel{
	private Integer userId;
	private Integer FollowUserId;
	private String addTime;
	private Integer state;//1增加 2删除
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFollowUserId() {
		return FollowUserId;
	}
	public void setFollowUserId(Integer followUserId) {
		FollowUserId = followUserId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}
