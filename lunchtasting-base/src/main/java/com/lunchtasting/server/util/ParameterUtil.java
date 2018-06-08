package com.lunchtasting.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class ParameterUtil {
	
	private static final Log log = LogFactory.getLog(ParameterUtil.class);
	
	public static String staticImg;
	public static String staticStyle;
	public static String dynamicImg;
	public static boolean isLoad;

	public static void getConfig() {
		if (!isLoad) {
			try {
				String _staticImg = System
						.getProperty(ParameterUtil.ParameterSerivce.staticImg);
				String _staticStyle = System
						.getProperty(ParameterUtil.ParameterSerivce.staticStyle);
				String _dynamicImg = System
						.getProperty(ParameterUtil.ParameterSerivce.dynamicImg);
				if (_staticImg == null || _staticStyle == null
						|| _dynamicImg == null) {
					InputStream inputStream = ParameterUtil.class
							.getClassLoader().getResourceAsStream(
									"param.properties");
					if (inputStream != null) {
						Properties p = new Properties();
						p.load(inputStream);
						staticImg = (_staticImg == null) ? p.getProperty(ParameterUtil.ParameterSerivce.staticImg) : _staticImg;
						staticStyle = (_staticStyle == null) ? p.getProperty(ParameterUtil.ParameterSerivce.staticStyle) : _staticStyle;
						dynamicImg = (_dynamicImg == null) ? p.getProperty(ParameterUtil.ParameterSerivce.dynamicImg) : _dynamicImg;

					}
				}else{
					staticImg = _staticImg;
					staticStyle = _staticStyle;
					dynamicImg = _dynamicImg;
				}
			} catch (IOException e) {
				log.error("加载param.properties参数失败：" + e);
			}
		}
	}
	
	public static String getStaticImg(){
		if(!isLoad){
			getConfig();
		}
		return staticImg;
	}
	
	public static String getStaticStyle(){
		if(!isLoad){
			getConfig();
		}
		return staticStyle;
	}
	
	public static String getDynamicImg(){
		if(!isLoad){
			getConfig();
		}
		return dynamicImg;
	}
	
	
	
	public static class ParameterSerivce{
		public static String staticImg = "static.image.url";
		public static String staticStyle = "static.style.url";
		public static String dynamicImg = "dynamic.img.url";
	}

}
