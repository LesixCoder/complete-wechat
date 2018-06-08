package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.topic.TopicBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	private TopicBIZ topicBIZ;
	/**
	 * 得到所需话题个数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request){
		String page = request.getParameter("page");
		String pageSizeString = request.getParameter("con");
/*		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}*/
		if(!ValidatorHelper.isNumber(pageSizeString)){
			pageSizeString="10";
		}
		try {
			Map map = new HashMap();
			Integer pageSize = Integer.parseInt(pageSizeString);
			map.put("topicList",topicBIZ.queryTopicList(0, pageSize));
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, map, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
	/**
	 * 新增标签
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public Object create(HttpServletRequest request){
		String name = request.getParameter("name");
		String typeString = request.getParameter("type");
		Integer type = 1;
		if(ValidatorHelper.isNull(name)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		if(ValidatorHelper.isNumber(typeString)){
			type=Integer.parseInt(typeString);
		}
		try {
			topicBIZ.createTopic(name, type);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, null, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
