package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("name", "xx");
		String name = map.get("name").toString();
		System.out.println(map.get("name")+"xxx");
	}
}
