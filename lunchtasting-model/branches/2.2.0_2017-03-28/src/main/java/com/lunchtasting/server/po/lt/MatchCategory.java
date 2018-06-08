package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class MatchCategory extends BasicPOModel{
	private Long id;
	private String name;
	private String imgUrl;
	private String content;
	private String unlockContent;
	private Integer score;
	private Long medalId;
	private Integer sort;
	private Integer flag;
	private String newId;
	private String medalName;
	public String getMedalName() {
		return medalName;
	}
	public void setMedalName(String medalName) {
		this.medalName = medalName;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUnlockContent() {
		return unlockContent;
	}
	public void setUnlockContent(String unlockContent) {
		this.unlockContent = unlockContent;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getMedalId() {
		return medalId;
	}
	public void setMedalId(Long medalId) {
		this.medalId = medalId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
