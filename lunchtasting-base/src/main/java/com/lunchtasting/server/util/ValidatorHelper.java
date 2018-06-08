package com.lunchtasting.server.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author xuqian
 * 
 */
public class ValidatorHelper {

	public static void main(String[] args) {
		System.out.println(isMobileNumber("13900033323"));
		System.out.println(isMobileNumber("15900033323"));
		System.out.println(isMobileNumber("15800033323"));
		System.out.println(isMobileNumber("18700033323"));
		System.out.println(isMobileNumber("14700033323"));
	}

	public static boolean equals(Integer l1, Integer l2) {
		if (l1 == null || l2 == null) {
			return false;
		}
		return l1.intValue() == l2.intValue();
	}

	public static boolean equals(Long l1, Long l2) {
		if (l1 == null || l2 == null) {
			return false;
		}
		return l1.longValue() == l2.longValue();
	}

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * 检测是否为合法的电话号码
	 * 
	 * @param num
	 * @return boolean
	 */
	public static boolean isPhoneNumber(String num) {
		if (ValidatorHelper.isEmpty(num)) {
			return false;
		}
		return Pattern.matches("(\\(\\d{3}\\)|\\d{3,4}-)?\\d{7,8}$", num);
	}
	
	/**
	 * 给网址字符打头的加上<a>标签
	 * @param content
	 * @return
	 */
	public static String addLinkToUrl(String content) {
		String p0 = "(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&amp;%\\$\\-]+)*@)*((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|localhost|([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(\\:[0-9]+)*(/($|[a-zA-Z0-9\\.\\,\\?\'\\\\\\+&amp;%\\$#\\=~_\\-]+))*";
		String regex = "http://([\\w-]+\\.)+([\\w-]+\\.)+(/[\\w-./?%&=]*)?|([\\w-]+\\.)+([\\w-]+\\.)+(/[\\w-./?%&=]*)?";
		if (StringUtils.isBlank(content)) {
			return content;
		}
		String contentCopy = content;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		while (m.find()) {
			contentCopy = contentCopy.replaceAll(m.group(), "http://" + m.group());
		}
		contentCopy = contentCopy.replaceAll("(http\\://)+", "http://");
		contentCopy = contentCopy.replaceAll("(https\\://)+", "https://");
		contentCopy = contentCopy.replaceAll("(ftp\\://)+", "ftp://");
		p = Pattern.compile(p0);
		m = p.matcher(contentCopy);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "<a href=\"" + m.group() + "\">" + m.group() + "</a>");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 检测是否为合法的手机号码
	 * 
	 * @param num
	 * @return boolean
	 */
	public static boolean isMobileNumber(String num) {
		return Pattern.matches("0?1[3584]\\d{9}", num);
	}

	/**
	 * 判断是否为数字组成的字串
	 * 
	 * @param validString
	 *            要判断的字符串
	 * @return boolen值，true或false
	 */
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
	 * 判断字符串是否只包括字母
	 * 
	 * @param validString
	 *            要判断的字符串
	 * @return boolen值，true或false
	 */
	public static boolean isLetter(String validString) {
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if ((tempbyte[i] < 65) || (tempbyte[i] > 122)
					|| ((tempbyte[i] > 90) & (tempbyte[i] < 97))) {
				return false;
			}
		}
		return true;
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

	public static final boolean isNotNull(Object obj) {
		return obj != null;
	}

	public static final boolean isNull(Object obj) {
		return obj == null;
	}

	public static final boolean isNotEmpty(List<?> list) {
		return (list != null && list.size() > 0);
	}

	public static final boolean isNotEmpty(HashMap<?, ?> value) {
		if (value == null) {
			return false;
		}
		return !value.isEmpty();
	}
	
	public static final boolean isMapNotEmpty(Object value) {
		if (value == null) {
			return false;
		}
		if(value.toString() == null){
			return false;
		}
		if(value.equals("")){
			return false;
		}
		return true;
	}

	public static final boolean isNotEmpty(Hashtable<?, ?> value) {
		return value != null && !value.isEmpty();
	}

	public static final boolean isNotEmpty(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String[]) {
			return isNotEmpty((String[]) value);
		} else if (value instanceof List<?>) {
			return isNotEmpty((List<?>) value);
		} else if (value instanceof HashMap<?, ?>) {
			return isNotEmpty((HashMap<?, ?>) value);
		} else if (value instanceof Hashtable<?, ?>) {
			return isNotEmpty((Hashtable<?, ?>) value);
		}
		return value != null;
	}

	public static final boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static final boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static final boolean isEmpty(Set<?> set) {
		return set == null || set.isEmpty();
	}

	public static final boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			return (obj == null || String.valueOf(obj).length() == 0);
		} else {
			return (obj == null);
		}
	}

	public static final boolean isEmpty(List<?> list) {
		return (list == null || list.size() == 0);
	}

	public static boolean isNotEmpty(String[] strs) {
		return !isEmpty(strs);
	}

	public static boolean isEmpty(String[] strs) {
		return strs == null || strs.length == 0;
	}

	/**
	 * >
	 * 
	 * @param i
	 * @return
	 */
	public static final boolean gtZero(Long i) {
		return (i != null && i.longValue() > 0);
	}

	/**
	 * <=
	 * 
	 * @param i
	 * @return
	 */
	public static final boolean leZero(Long i) {
		return (i == null || i.longValue() <= 0);
	}

	public static boolean isNotEmpty(Set<?> set) {
		return (set != null && !set.isEmpty());
	}

	public static boolean isNotEmpty(Collection<?> c) {
		return c != null && !c.isEmpty();
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.size() <= 0;
	}

	public static boolean isEmpty(Object[] objs) {
		return objs == null || objs.length <= 0;
	}

	public static boolean isEmpty(byte[] bytes) {
		return (bytes == null || bytes.length == 0);
	}

	public static boolean isTrue(Boolean bea) {
		if (bea == null) {
			return false;
		} else {
			return bea.booleanValue();
		}
	}

	public static boolean isFalse(Boolean bea) {
		if (bea == null) {
			return true;
		} else {
			return !bea.booleanValue();
		}
	}

	public static boolean leZero(Integer i) {
		return i == null || i.intValue() <= 0;
	}

	public static String url(String param) {
		Pattern p = Pattern.compile(
				"[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(param);
		matcher.find();
		return matcher.group();
	}

}
