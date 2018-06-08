package com.lunchtasting.server.po.temporaryEnroll;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class TemporaryCinema extends BasicPOModel{
	private Long id;
	private Long userId;
	private String random;
	private Date createTime;
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
