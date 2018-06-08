package com.lunchtasting.server.po.temporaryEnroll;

import com.lunchtasting.server.model.BasicPOModel;

public class TempooraryInvitationCode extends BasicPOModel{
	private Long id;
	private Long userId;
	private String code;
	private String employTime;
	private Integer state;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEmployTime() {
		return employTime;
	}
	public void setEmployTime(String employTime) {
		this.employTime = employTime;
	}

	
}
