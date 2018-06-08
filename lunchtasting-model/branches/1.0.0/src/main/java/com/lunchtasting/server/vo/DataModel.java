package com.lunchtasting.server.vo;

import com.lunchtasting.server.model.BasicPOModel;

public class DataModel extends BasicPOModel{
	private String value;	//前端autocomplete属性
	private String label;	//前端autocomplete属性
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public DataModel(){
		
	}

	public DataModel(String value, String label) {
		this.value = value;
		this.label = label;
	}

	@Override
	public String toString() {
		return "DataModel [value=" + value + ", label=" + label + "]";
	}
	
}
