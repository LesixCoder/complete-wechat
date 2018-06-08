package com.lunchtasting.server.util;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author xuqian
 *
 */
public class TypeChange {
	
	private static final Log LOG = LogFactory.getLog(TypeChange.class);
	
	public static final Long stringToLong(String val, Long defaultVal) {
		if(val == null) {
			return defaultVal;
		}
		try {
			return Long.parseLong(val);
		} catch (Exception e) {
			LOG.error(e);
			return defaultVal;
		}
	}
	
	public static final Integer StringToInteger(String val, Integer defaultVal) {
		if(val == null) {
			return defaultVal;
		}
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			LOG.error(e);
			return defaultVal;
		}
	}

	public static int doubleToInt(Double d) {
		try {
			// double转换成long前要过滤掉double类型小数点后数据
			// return Integer.parseInt(String.valueOf(d).substring(0,
			// String.valueOf(d).lastIndexOf(".")));
			return d.intValue();
		} catch (Exception e) {
			LOG.error(e);
		}
		return 0;
	}

	public static Integer objectToInteger(Object obj, Integer defaultValue) {
		if (obj != null && Utils.isNumber(String.valueOf(obj))) {
			try {
				return new Integer(String.valueOf(obj));
			} catch (RuntimeException e) {
				LOG.error(e);
			}
		}
		return defaultValue;
	}

	public static Boolean stringToBoolean(String str) {
		if(ValidatorHelper.isEmpty(str)) {
			return false;
		} else if(str.equalsIgnoreCase("true") || str.equalsIgnoreCase("1")) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean objectToBoolean(Object obj, Boolean val) {
		if(obj == null) {
			return val;
		} else {
			return stringToBoolean(String.valueOf(obj));
		}
	}
}
