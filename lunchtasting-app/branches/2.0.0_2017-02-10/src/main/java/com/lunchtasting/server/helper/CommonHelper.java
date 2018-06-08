package com.lunchtasting.server.helper;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.DateUtils;
import com.lunchtasting.server.util.StringEncrypt;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 通用帮助类
 * @author xq
 *
 */
public class CommonHelper {

	/**
	 * 获得距离
	 * @param distance
	 * @return
	 */
	public static String getDistance(Double distance){
		StringBuilder sb = new StringBuilder();
		if(ValidatorHelper.isNotEmpty(distance)){
			DecimalFormat df = new DecimalFormat("0");
			if(distance <= 1000){
				sb.append(df.format(distance)+"m");
			}else{
				sb.append(Math.rint(distance/100)/10+"km");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据时间判断活动状态status
	 * 1即将开始  2进行中  3已结束
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static int getActivityStatus(String beginTime,String endTime) throws ParseException{
		
		Date bTime = DateUtil.convertStringTODate(beginTime,DateUtil.YYYY_MM_DD_HH_MM_SS);
		Date eTime = DateUtil.convertStringTODate(endTime,DateUtil.YYYY_MM_DD_HH_MM_SS);
		
		int status = 1;
		Calendar beginCal = Calendar.getInstance();  
		beginCal.setTimeInMillis(bTime.getTime());  
		
		Calendar endCal = Calendar.getInstance();  
		endCal.setTimeInMillis(eTime.getTime());  
		
		Calendar nowCal = Calendar.getInstance();  
		nowCal.setTimeInMillis(new Date().getTime());  
		
		if(nowCal.before(beginCal)){
			status =  1;
		}
		if(nowCal.after(beginCal) && nowCal.before(endCal)){
			status =  2;
		}
		if( nowCal.after(endCal)){
			status =  3;
		}
		return status;
	}
	
	/**
	 * 获得活动还差几天开始,小于等于三天开始显示
	 * @param beginTime
	 * @return
	 * @throws ParseException 
	 */
	public static String getActivityDifferDay(String beginTime) throws ParseException{
		StringBuilder sb = new StringBuilder();
		Date fromDate = new Date();
		Date toDate = DateUtil.convertStringTODate(beginTime,DateUtil.YYYY_MM_DD_HH_MM_SS);
		int differDay = DateUtils.caculate2Days(fromDate,toDate);
		if(differDay <= 3){
			if(differDay == 0){
				sb.append("即将开始");
			}else{
				sb.append("还有"+differDay+"天");
			}
		}
		return sb.toString();
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
			if(beginDate.getDate() == endDate.getDate()){
				
				
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
	
	/**
	 * 格式化double 保留两位小数
	 * @return
	 */
	public static String getDobule(double d){
		DecimalFormat df = new DecimalFormat("######0.00"); 
		return df.format(d); 
	}
	
	/**
	 * 获得请求所有参数并放进map
	 * @param request
	 * @return
	 */
	public static Map getRequestMap(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		Map map = new HashMap();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			map.put(paraName, request.getParameter(paraName));
		}
		return map;
	}
	
	/**
	 * map 转json
	 * @param map
	 * @return
	 */
	public static String getParameterJson(Map map) {
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 获得第三方唯一交易码
	 * 例如：微信、支付宝付款和退款码
	 * 24位唯一码
	 * @return
	 */
	public static String getOutNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println( df.format(new Date()).length());
		return  df.format(new Date()) + "" + Utils.getRandomNumber(10);
	}
		
	
}
