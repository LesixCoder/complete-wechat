package com.lunchtasting.server.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {
	
	public double getProPrice(int value){
		return value/100.0;
	}
	public String getRefPrice(double value){
		double result =  value/100.0;
		NumberFormat objFormat =new DecimalFormat("#.##"); 
		return objFormat.format(result);
	}
	
	public String getOrignalImg(String photos){
		if(photos != null){
			return photos.split("\\|")[0];
		}
		return null;
	}

	/**
	 * 生成某一范围之内的随机数字
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getRandomNumber(int start,int end){
		return (int)(Math.random()*end)+start;
	}
	
	public static void main(String aggs[]){
		while(true){
		System.out.println(getRandomNumber(1,20));
		}
	}
}
