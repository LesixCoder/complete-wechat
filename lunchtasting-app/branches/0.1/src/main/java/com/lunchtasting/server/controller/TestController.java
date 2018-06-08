package com.lunchtasting.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.grammar.v3.ANTLRv3Parser.throwsSpec_return;
import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.helper.CommonHelper;

@Controller
public class TestController {

	@RequestMapping(value = "/test")
	@ResponseBody
	public Object test(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		
		
		Map map = new HashedMap();
		map.put("name", "zhangsan");
		map.put("sex", 1);
		
		request.setAttribute("msg", JSONObject.toJSONString(map));
		
//		throw new Exception();
		
		return map;
	}
}
