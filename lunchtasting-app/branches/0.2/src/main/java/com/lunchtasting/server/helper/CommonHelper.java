package com.lunchtasting.server.helper;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lunchtasting.server.util.MD5;
import com.lunchtasting.server.util.StringEncrypt;
import com.lunchtasting.server.util.Utils;

/**
 * 通用帮助类
 * @author xq
 *
 */
public class CommonHelper {

	/**
	 * 生成指定位随机数
	 * 
	 * @param size
	 * @return
	 */
	public static String getRandomNumber(int size) {
		String result = "";
		for (int i = 0; i < size; i++) {
			result += Utils.getRandomNumber(0, 9).toString();
		}
		return result;
	}
}
