package com.lunchtasting.server.po.temporaryEnroll;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class EnrollGroup extends BasicPOModel {
	private Long id;
	private String name;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
