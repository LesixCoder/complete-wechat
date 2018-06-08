package com.lunchtasting.server.po.lt;


import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;


/**
 * 手机绑定验证码
 * @author xuqian
 *
 */
public class UserSms extends BasicPOModel {

	private Long id;
	
	private String phone;
	
	private String code;
	
	private Integer type;
	
	private String content;
	
	private Integer status;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
