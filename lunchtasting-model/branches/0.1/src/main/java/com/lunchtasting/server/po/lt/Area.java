package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

/** area地域信息表
*/
public class Area extends BasicPOModel{
	private Integer areaId;			//区域编号
	private Integer pId;			//上级编号
	private Integer areaType;		//区域类型
	private Integer state;			//区域状态
	private Integer createdUser;	//创建人
	private Integer updatedUser;	//修改人
	private String areaName;	//区域名称
	private String createdTime;	//创建时间
	private String updatedTime;	//修改时间
	public Area(){
		
	}
	public Area(Integer areaId, Integer pId, Integer areaType, Integer state,
			Integer createdUser, Integer updatedUser, String areaName,
			String createdTime, String updatedTime) {
		this.areaId = areaId;
		this.pId = pId;
		this.areaType = areaType;
		this.state = state;
		this.createdUser = createdUser;
		this.updatedUser = updatedUser;
		
		this.areaName = areaName;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}

	public Integer getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(Integer updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

}

