package com.lunchtasting.server.po.lt;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class MatchTicket extends BasicPOModel {

	private Long id;
	
	private Long matchId;
	
	private String name;
	
	private String content;
	
	private Double price;
	
	private Double earlyBirdPrice;
	
	private Date earlyBirdTime;
	
	private Integer type;
	
	private Integer kind;
	
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getEarlyBirdPrice() {
		return earlyBirdPrice;
	}

	public void setEarlyBirdPrice(Double earlyBirdPrice) {
		this.earlyBirdPrice = earlyBirdPrice;
	}

	public Date getEarlyBirdTime() {
		return earlyBirdTime;
	}

	public void setEarlyBirdTime(Date earlyBirdTime) {
		this.earlyBirdTime = earlyBirdTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
