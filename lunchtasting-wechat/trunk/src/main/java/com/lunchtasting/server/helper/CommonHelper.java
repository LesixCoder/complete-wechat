package com.lunchtasting.server.helper;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 获得request的所有参数 
	 * @param request
	 * @return
	 */
	public static String getRequestParameter(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		int i = 1;
		StringBuilder strBuilder = new StringBuilder();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			if (!paraName.equals("filename")) {
				if(i == 1){
					strBuilder.append("?" + paraName + "=" + 
							request.getParameter(paraName));
				}else{
					strBuilder.append("&" + paraName + "=" + 
							request.getParameter(paraName));
				}
				i++;
			}
		}
		return strBuilder.toString();
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
	
	/**
	 * 计算星座
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getConstellation(int month, int day) {
		String star = "";
		if (month == 1 && day >= 20 || month == 2 && day <= 18) {
			star = "水瓶座";
		}
		if (month == 2 && day >= 19 || month == 3 && day <= 20) {
			star = "双鱼座";
		}
		if (month == 3 && day >= 21 || month == 4 && day <= 19) {
			star = "白羊座";
		}
		if (month == 4 && day >= 20 || month == 5 && day <= 20) {
			star = "金牛座";
		}
		if (month == 5 && day >= 21 || month == 6 && day <= 21) {
			star = "双子座";
		}
		if (month == 6 && day >= 22 || month == 7 && day <= 22) {
			star = "巨蟹座";
		}
		if (month == 7 && day >= 23 || month == 8 && day <= 22) {
			star = "狮子座";
		}
		if (month == 8 && day >= 23 || month == 9 && day <= 22) {
			star = "处女座";
		}
		if (month == 9 && day >= 23 || month == 10 && day <= 22) {
			star = "天秤座";
		}
		if (month == 10 && day >= 23 || month == 11 && day <= 21) {
			star = "天蝎座";
		}
		if (month == 11 && day >= 22 || month == 12 && day <= 21) {
			star = "射手座";
		}
		if (month == 12 && day >= 22 || month == 1 && day <= 19) {
			star = "摩羯座";
		}
		return star;
	}
	
	/**
	 * 计算年龄
	 * @param dateOfBirth
	 * @return
	 */
	public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException("年龄不能超过当前日期");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            int nowDayOfYear = now.get(Calendar.DAY_OF_YEAR);
            int bornDayOfYear = born.get(Calendar.DAY_OF_YEAR);
            if (nowDayOfYear < bornDayOfYear) {
                age -= 1;
            }
        }
        return age;
    }
	
	public static String filterEmoji(String source) {  
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find()) 
            {
                source = emojiMatcher.replaceAll("*");
                return source ; 
            }
        return source;
       }
       return source;  
    }
	
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
	
}
