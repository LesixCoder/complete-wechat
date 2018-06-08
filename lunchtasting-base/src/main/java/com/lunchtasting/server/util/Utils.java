package com.lunchtasting.server.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


public class Utils {
	
	/**
	 * 判断字符串是否时间类型
	 * @param str
	 * @param dateStr
	 * @return
	 */
	public static boolean isDate(String str,String dateStr){
		SimpleDateFormat format = new SimpleDateFormat(dateStr);
		try{
			format.parse(str);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 *  md5密码加密
	 * @param code
	 * @param delimiter
	 * @return
	 */
	public static String encodeCode(String code,String delimiter) {

		if (ValidatorHelper.isEmpty(code)) {
			return null;
		}
		MD5 md5 = new MD5();
		String result1 = md5.getMD5ofStr(code);
		String result2 = md5.getMD5ofStr(result1 + "//");
		String houResult = result2.substring(result2.length() / 2, result2.length());
		String qianReulst = result2.substring(0, houResult.length());
		return md5.getMD5ofStr(houResult + qianReulst);
	}

	/**
	 * 指定范围的随机数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static Long getRandomNumber(int begin, int end) {
		return Math.round(Math.random() * (end - begin) + begin);
	}
	
	/**
	 * 生成指定位随机数
	 * 
	 * @param size
	 * @return
	 */
	public static String getRandomNumber(int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(getRandomNumber(0, 9).toString());
		}
		return sb.toString();
	}

	/**
	 * 生成随机汉字
	 */
	public static String getRandomWord() {
		String[] array = new String[] { "咽", "喉", "叨", "口", "吻", "呵", "哈", "啥",
				"啊", "嗡", "吒", "呼", "吁", "吗", "呜", "鸣", "吧", "吃", "响", "吭",
				"吱", "唔", "咯", "呢", "咋", "吓", "吐", "哇", "咀", "咩", "哗", "咤",
				"哑", "哼", "唧", "哦", "味", "咪", "呀", "哩", "唱", "喝", "哎", "哟",
				"呗", "咚", "嗦", "啊", "吵", "呆", "呃", "噶", "喊", "啦", "咧", "哪",
				"嗄", "咕", "喔", "嘛", "噢", "吖" };
		StringBuffer sb = new StringBuffer();
		int num = 0;
		for (int i = 1; i <= 10; i++) {
			num = getRandomNumber(0, 61).intValue();
			sb.append(array[num]);
		}
		String str = sb.toString();
		return str;
	}
	
	/**
	 * 生成自定义随机码
	 * @param array
	 * @param size
	 * @return
	 */
	public static String getRandomWord(String [] array ,int size) {
		StringBuffer sb = new StringBuffer();
		int num = 0;
		for (int i = 1; i <= size; i++) {
			num = getRandomNumber(0, array.length-1).intValue();
			sb.append(array[num]);
		}
		String str = sb.toString();
		return str;
	}

	/**
	 * 获得字符串长度
	 * 
	 * @param value
	 * @return
	 */
	public static int stringLength(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}
	
	/**
	 * 获取字符串长度
	 * @param value
	 * return int;
	 */
	
    public static int getStringLength(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

	/**
	 * 截取字符串长度
	 * 
	 * @param value,size
	 * @return
	 */

	public static String subString(String value, int size) {
		int valueLength = 0;
		int num = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
				if (valueLength > size) {
					num = i;
					break;
				}
			} else {
				valueLength += 1;
				if (valueLength > size) {
					num = i;
					break;
				}
			}
		}
		return new StringBuilder().append(value.substring(0, num)).toString();
	}

	public static String getEncoding(String str) {
		String dStr = null;
		try {
			dStr = new String(str.getBytes(), "UTF-8");// windows
			// dStr = java.net.URLDecoder.decode(str); //linux
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dStr;

	}

	/**
	 * 指定类型list的copy
	 * 
	 * @param src
	 *            源
	 * @param dest
	 *            copy的对象
	 * @return
	 */
	public static List listCopy(List src, List dest) {
		for (int i = 0; i < src.size(); i++) {
			Object obj = src.get(i);
			if (obj instanceof List) {
				dest.add(new ArrayList());
				listCopy((List) obj, (List) ((List) dest).get(i));
			} else {
				dest.add(obj);
			}
		}
		return dest;

	}

	public static int getTotalPage(int pageCount, int pageSize) {
		int num = 0;
		if(pageCount == 0){
			return 1;
		}
		int a = pageCount / pageSize;
		int b = pageCount % pageSize;
		if (a != 0) {
			if (b != 0) {
				num = a + 1;
			} else {
				num = a;
			}
		} else {
			num = 1;
		}
		return num;
	}

	/*
	 * public static Object ss(Object src , Object dest){ return
	 * BeanUtils.copyProperties(src, dest); }
	 */

	public static boolean isAndroid(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		if (agent != null && agent.toLowerCase().indexOf("android") != -1) {
			return true;
		}
		return false;
	}

	public static boolean isIphone(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		if (agent != null && agent.toLowerCase().indexOf("iphone") != -1) {
			return true;
		}
		return false;
	}

	public static boolean isSymbian(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		if (agent != null && agent.toLowerCase().indexOf("symbian") != -1) {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String validString) {
		if (validString == null || validString.length() < 1) {
			return false;
		} else {
			byte[] tempbyte = validString.getBytes();
			for (int i = 0; i < validString.length(); i++) {
				// by=tempbyte;
				if ((tempbyte[i] == 45) && (i == 0)) {
					continue;
				}
				if ((tempbyte[i] < 48) || (tempbyte[i] > 57)) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * <p>check the source string is a numeric</p>
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNumericString(String source){
		if (StringUtils.isEmpty(source)){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(source).matches();  
	}
	
	/**
	 * <p>convert float into percentage string</p>
	 * 
	 * @param source
	 * @return
	 */
	public static String convertFloatPercentage(Float source){
		if (Float.isNaN(source)){
			return "0%";
		}
		return (int)(Math.round(source * 100)) + "%";
	}
}
