package com.perfit.server.helper;

import java.util.List;
import java.util.Map;

/**
 * 分页支持
 * @author 
 *
 */
public class PageBean {
	private List list; //返回的数据
	private int curPage; //当前页
	private int pageSize=10; //页大小
	private int totalCount; //总记录数
	private int totalPage; //总页数
	
	public PageBean() {
		
	}
	
	public PageBean(List list, int curPage,int totalCount,
			int pageSize) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.totalCount = totalCount;
		this.totalPage = (totalCount - 1)/pageSize +1; 
		this.pageSize = pageSize;
	}
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getcurPage() {
		return curPage;
	}
	public void setcurPage(int curPage) {
		this.curPage = curPage;
	}
	public int gettotalCount() {
		return totalCount;
	}
	public void settotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int gettotalPage() {
		return totalPage;
	}
	public void settotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
