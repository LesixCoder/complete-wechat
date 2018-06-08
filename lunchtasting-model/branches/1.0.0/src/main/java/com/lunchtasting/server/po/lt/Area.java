package com.lunchtasting.server.po.lt;

import com.lunchtasting.server.model.BasicPOModel;

public class Area extends BasicPOModel{
	
    private Long id;
    
    private String name;
    
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
     
}
