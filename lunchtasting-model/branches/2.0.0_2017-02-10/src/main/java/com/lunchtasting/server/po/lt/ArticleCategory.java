package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class ArticleCategory extends BasicPOModel {

	private Long id;
	
	private String name;
	
	private Integer sort;
	
	private Integer flag;

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
