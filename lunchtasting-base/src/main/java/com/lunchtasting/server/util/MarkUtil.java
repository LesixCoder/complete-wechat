package com.lunchtasting.server.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MarkUtil {

	private void init() {

	}
	
	/**
	 * 获取字符串里所有的标签
	 * @param list所有标签
	 * @param str 字符串
	 * @param size 个数
	 * @return
	 */
	public String getMarks(List list, String str,int size) {
		if (ValidatorHelper.isEmpty(list) || ValidatorHelper.isEmpty(str)) {
			return null;
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			HashMap m = (HashMap) list.get(i);
			String id = m.get("id").toString();
			String name = m.get("name").toString();
			String isOpen = m.get("is_open").toString();
			String synonym = null;
			int num = getStrCount(name, str);
			/**
			 * 同义词不为空 还需要把同义词的个数加进去
			 */
			if(ValidatorHelper.isNotEmpty(m.get("synonym"))){
				synonym = m.get("synonym").toString();
				String [] spilt = synonym.split(",");
				if(ValidatorHelper.isNotEmpty(spilt)){
					for(String s : spilt){
						int addNum = getStrCount(s, str);
						if (addNum > 0) {
							num += addNum;
						}
					}
					if(num > 0){
						map.put(id, num);
					}
				}
			}else{
				if(num > 0){
					map.put(id, num);
				}
			}
		}
		
		if(ValidatorHelper.isEmpty(map)){
			return null;
		}
		
		map = sortMap(map,"desc",size);
		
		String result = null;
		StringBuffer sb = new StringBuffer();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			sb.append(m.getKey().toString()).append(",");
		}
		
		result = sb.toString();
		if (ValidatorHelper.isNotEmpty(result)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * 获取字符在字符串里的个数
	 * 
	 * @param subStr
	 * @param str
	 * @return
	 */
	private static int getStrCount(String subStr, String str) {
		if (ValidatorHelper.isEmpty(str) || ValidatorHelper.isEmpty(subStr)) {
			return 0;
		}
		int count = 0;
		int index = 0;

		while ((index = str.indexOf(subStr, index)) != -1) {
			count++;
			index += subStr.length();
		}
		return count;

	}

	/**
	 * map 冒泡排序
	 * @param Map
	 * @param String,sequence 排序方式 desc 、asc
	 * @param size(为null则全部)
	 * @throws Exception
	 * @return String
	 */
	private static Map sortMap(Map map, String sequence,Integer size) {

		Object[] keyArray = (Object[]) map.keySet().toArray();
		Object[] valueArray = (Object[]) map.values().toArray();
		int keyArrayLength = keyArray.length;
		int tmp;
		String tmpString = null;
		for (int i = 0; i < keyArrayLength; i++) {
			for (int j = 0; j < keyArrayLength - i - 1; j++) {
				int value1 = Integer.parseInt(valueArray[j].toString());
				int value2 = Integer.parseInt(valueArray[j + 1].toString());

				if (sequence.equals("desc")) {
					// 降序
					if (value2 > value1) {
						tmp = (Integer) valueArray[j + 1];
						valueArray[j + 1] = valueArray[j];
						valueArray[j] = tmp;

						tmpString = (String) keyArray[j + 1];
						keyArray[j + 1] = keyArray[j];
						keyArray[j] = tmpString;
					}
				} else if ("asc".equals(sequence)) {
					if (value2 < value1) {
						tmp = (Integer) valueArray[j + 1];
						valueArray[j + 1] = valueArray[j];
						valueArray[j] = tmp;

						tmpString = (String) keyArray[j + 1];
						keyArray[j + 1] = keyArray[j];
						keyArray[j] = tmpString;
					}
				} else {
					if (value2 < value1) {
						tmp = (Integer) valueArray[j + 1];
						valueArray[j + 1] = valueArray[j];
						valueArray[j] = tmp;

						tmpString = (String) keyArray[j + 1];
						keyArray[j + 1] = keyArray[j];
						keyArray[j] = tmpString;
					}
				}
			}
		}
		
		Map mapReturn = new LinkedHashMap();
		for (int i = 0; i < keyArrayLength; i++) {
			if(ValidatorHelper.isNotEmpty(size)){
				if(i < size){
					mapReturn.put(keyArray[i], valueArray[i]);
				}else{
					break;
				}
			}else{
				mapReturn.put(keyArray[i], valueArray[i]);
			}
			//System.out.println(keyArray[i] + "===" + valueArray[i]);
		}
		return mapReturn;

	}

	public static void main(String[] args) {
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("哈哈", 2);
//		map.put("呵呵", 4);
//		map.put("嘿嘿", 3);
//		map.put("嚯嚯", 1);
//		map.put("啾啾", 5);
//		map.put("ZZ", 6);
//		map.put("XX", 7);
//		sortMap(map,"desc",5);
		String str = "xas基金dasd阿萨德撒sadas萨达s基金ad萨达";
		String subStr = "基金";
		System.out.println(getStrCount(subStr,str));
	}
}
