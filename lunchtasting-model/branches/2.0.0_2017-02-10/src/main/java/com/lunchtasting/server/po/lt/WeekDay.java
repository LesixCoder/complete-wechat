package com.lunchtasting.server.po.lt;

import java.util.Date;

import com.lunchtasting.server.model.BasicPOModel;

public class WeekDay extends BasicPOModel{
	private Long id;
	private String wYear;
	private String wMonth;
	private String wDay;
	private Date wDate;
	private Integer wFlag;
	private Integer wWeek;
	private Date wLastDay;
	private Date wNextDay;
	private Date createTime;
	private Integer flag;
	private String newDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getwYear() {
		return wYear;
	}
	public void setwYear(String wYear) {
		this.wYear = wYear;
	}
	public String getwMonth() {
		return wMonth;
	}
	public void setwMonth(String wMonth) {
		this.wMonth = wMonth;
	}
	public String getwDay() {
		return wDay;
	}
	public void setwDay(String wDay) {
		this.wDay = wDay;
	}
	public Date getwDate() {
		return wDate;
	}
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}
	public Integer getwFlag() {
		return wFlag;
	}
	public void setwFlag(Integer wFlag) {
		this.wFlag = wFlag;
	}
	public Integer getwWeek() {
		return wWeek;
	}
	public void setwWeek(Integer wWeek) {
		this.wWeek = wWeek;
	}
	public Date getwLastDay() {
		return wLastDay;
	}
	public void setwLastDay(Date wLastDay) {
		this.wLastDay = wLastDay;
	}
	public Date getwNextDay() {
		return wNextDay;
	}
	public void setwNextDay(Date wNextDay) {
		this.wNextDay = wNextDay;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getNewDate() {
		return newDate;
	}
	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}
	
}
