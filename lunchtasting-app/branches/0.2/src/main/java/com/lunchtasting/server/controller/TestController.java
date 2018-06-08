package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping(value = "/test/list")
	@ResponseBody
	public Object test(HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		map.put("user_id", "10000");
		map.put("sex", 1);
		map.put("name", "张三");
		return map;
	}
}
