package com.lunchtasting.server.po.lt;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class Friend extends BasicPOModel {

	private Long id;
	
	private Long srcUserId;
	
	private Long desUserId;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSrcUserId() {
		return srcUserId;
	}

	public void setSrcUserId(Long srcUserId) {
		this.srcUserId = srcUserId;
	}

	public Long getDesUserId() {
		return desUserId;
	}

	public void setDesUserId(Long desUserId) {
		this.desUserId = desUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
