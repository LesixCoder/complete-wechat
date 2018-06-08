package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class SysRole extends BasicPOModel{
	private Long id; 
	private int roleId;
	private String menuId;
	private String menuName;
	private int isOpen;
	private int isAdd;
	private int isEdit;
	private int isDelete;
	private int isCheck;
	private int isPrint;
	private int isUncheck;
	private int flag;
	private String action;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public int getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}
	public int getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	public int getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
	}
	public int getIsUncheck() {
		return isUncheck;
	}
	public void setIsUncheck(int isUncheck) {
		this.isUncheck = isUncheck;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
