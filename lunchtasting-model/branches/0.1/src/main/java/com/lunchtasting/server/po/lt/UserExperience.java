package com.lunchtasting.server.po.lt;

public class UserExperience {
	private int id; //id
	private int userId;//用户id
	private String name;//昵称
	private int sex;//性别
	private String sexName;//性别
	private String manifesto;//美食宣言
	private String	merit;//特长
	private String occupation;//职业
	private int state;//0 正常 1 禁用 2删除
	private String wechat;//微信号
	private String touimgSrc;//头像路径
	private String telephone;//手机号
	private String creationTime;
	
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTouimgSrc() {
		return touimgSrc;
	}
	public void setTouimgSrc(String touimgSrc) {
		this.touimgSrc = touimgSrc;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getManifesto() {
		return manifesto;
	}
	public void setManifesto(String manifesto) {
		this.manifesto = manifesto;
	}
	public String getMerit() {
		return merit;
	}
	public void setMerit(String merit) {
		this.merit = merit;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
