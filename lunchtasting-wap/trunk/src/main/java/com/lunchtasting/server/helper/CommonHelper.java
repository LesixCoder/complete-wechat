package com.lunchtasting.server.helper;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.DateUtils;
import com.lunchtasting.server.util.ValidatorHelper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



/**
 * 公共帮助类
 * 
 * @author xuqian
 * 
 */
public class CommonHelper {
	
	
	public static String gotoWebRoot(HttpServletRequest request) {
		return "http://" + request.getServerName() + request.getContextPath() + "/";
	}
	/**
	 * 微信登录
	 * @param request
	 * @return
	 */
	public static String gotoWebLogin(HttpServletRequest request) {
		return "http://" + request.getServerName() + request.getContextPath() + "/wechat/wechatInvoking";
	}
	
	/**
	 * 获得活动具体时间
	 * 1例：2016-8-26 to 2016-8-27  显示： 8.26-8.27
	 * 2例：2016-8-26 15:30 to 2016-8-26 17:30  显示 : 8.26 15:30-17:30
	 * 3例：2016-8-26 15:30 to 2016-8-27 17:30  显示: 8.26-8.27
	 * 4例：活动详情 2016-8-26 15:30 to 2016-8-27 17:30  显示: 8.26 15:30 - 8.27 17:30
	 * @param beginTime
	 * @param endTime
	 * @param index 1列表页显示  2详情页显示
	 * @return
	 * @throws ParseException 
	 */
	public static String getActivityTime(String beginTime,String endTime,int index) throws ParseException{
		
		StringBuilder sb = new StringBuilder();
		Date beginDate = DateUtil.convertStringTODate(beginTime,DateUtil.YYYY_MM_DD_HH_MM_SS);
		Date endDate = DateUtil.convertStringTODate(endTime,DateUtil.YYYY_MM_DD_HH_MM_SS);
		if(beginDate.getMonth() == endDate.getMonth()){
			if(beginDate.getDay() == endDate.getDay()){
				
				if(beginDate.getHours() != 0){
					sb.append(DateUtil.convertDateToString(beginDate,"M.d")+" "
							+DateUtil.convertDateToString(beginDate,"HH:mm")+"-"
							+DateUtil.convertDateToString(endDate,"HH:mm"));
				}else{
					sb.append(DateUtil.convertDateToString(beginDate,"M.d")+"-"
							+DateUtil.convertDateToString(endDate,"M.d"));
				}
				
			}else{
				if(index == 1){
					sb.append(DateUtil.convertDateToString(beginDate,"M.d")+"-"
							+DateUtil.convertDateToString(endDate,"M.d"));
				}
				if(index == 2){
					if(beginDate.getHours() != 0){
						sb.append(DateUtil.convertDateToString(beginDate,"M.d")+" "
								+DateUtil.convertDateToString(beginDate,"HH:mm")+" - "
								+DateUtil.convertDateToString(endDate,"M.d")+" "
								+DateUtil.convertDateToString(endDate,"HH:mm"));
					}else{
						sb.append(DateUtil.convertDateToString(beginDate,"M.d")+"-"
								+DateUtil.convertDateToString(endDate,"M.d"));
					}
				}
			}
			
		}else{
			if(index == 1){
				sb.append(DateUtil.convertDateToString(beginDate,"M.d")+"-"
						+DateUtil.convertDateToString(endDate,"M.d"));
			}
			if(index == 2){
				if(beginDate.getHours() != 0){
					sb.append(DateUtil.convertDateToString(beginDate,"M.d")+" "
							+DateUtil.convertDateToString(beginDate,"HH:mm")+" - "
							+DateUtil.convertDateToString(endDate,"M.d")+" "
							+DateUtil.convertDateToString(endDate,"HH:mm"));
				}else{
					sb.append(DateUtil.convertDateToString(beginDate,"M.d")+"-"
							+DateUtil.convertDateToString(endDate,"M.d"));
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 获得自己换算时间
	 * 今天：
		60秒及以内：刚刚
		60分钟及以内：XX分钟前，去秒只取分钟；
		60分钟以外：今天HH:MM
		昨天：
		昨天 HH:MM
		昨天之前：
		MM-DD HH:MM
		超过365天后
		YYYY-MM-DD HH:MM
	 * 
	 * @param date
	 * @return
	 */
	public static String getPerfitTime(Date date) {
		if (date == null) {
			return null;
		}
		int oldDay = date.getDate();
		int oldMonth = date.getMonth() + 1;
		int oldYers = date.getYear() + 1900;
		int oldHours = date.getHours();
		int oldMinutes = date.getMinutes();
		int oldSeconds  = date.getSeconds();
		Date currentDate = new Date();
		int newDay = currentDate.getDate();
		int newMonth = currentDate.getMonth() + 1;
		int newYers = currentDate.getYear() + 1900;
		int newHours = currentDate.getHours();
		int newMinutes = currentDate.getMinutes();
		int newSeconds = currentDate.getSeconds();


		if (oldYers == newYers) {
			if (oldMonth != newMonth || oldDay != newDay) {
				if(DateUtils.caculate2Days(date,currentDate) == 1){
					return "昨天 "+DateUtil.convertDateToString(date,"HH:mm");
				}
				
				return DateUtil.convertDateToString(date,"MM-dd HH:mm");
			} else {
				if(oldHours == newHours && oldMinutes == newMinutes){
					return "刚刚";
				}
				
				if(DateUtil.differHour(date, currentDate) == 0){
					long minutes = DateUtil.differMin(date, currentDate);
					if(minutes == 0){
						minutes = 1;
					}
					return minutes+"分钟前";
				}
				
				return "今天 " + DateUtil.convertDateToString(date, "HH:mm");
			}

		}
		return DateUtil.convertDateToString(date, "yyyy-MM-dd HH:mm");
	}
	
	/**
	 * 获得随机颜色
	 * @param id
	 * @return
	 */
	public static int getRandomColor(String id) {
		if(ValidatorHelper.isEmpty(id)){
			return 1;
		}
		id = id.substring(id.length()-1,id.length());
		
		int a  = Integer.parseInt(id.substring(id.length()-1, id.length()));
		if(a == 2  || a == 5){
			return 1;
		}
		if(a == 1  || a == 3 || a == 6){
			return 2;
		}
		if(a == 4  || a == 7){
			return 3;
		}
		if(a == 0  || a == 8 || a == 9){
			return 4;
		}
		return 1;
	}
	
}
