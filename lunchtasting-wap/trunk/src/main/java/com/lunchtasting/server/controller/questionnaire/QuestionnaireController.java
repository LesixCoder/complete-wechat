package com.lunchtasting.server.controller.questionnaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.questionnaire.QuestionnaireBIZ;
import com.lunchtasting.server.util.ValidatorHelper;
/**
 * 问查调卷模块
 * Created on 2017-4-20
 * @author chenchen
 *
 */
@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {
	@Autowired
	private QuestionnaireBIZ questionnaireBIZ;

	@RequestMapping(value = "/entering")
	@ResponseBody
	public Object entering(HttpServletRequest request){
		String json = request.getParameter("date");
		System.out.println(json);
		if(questionnaireBIZ.entering(json)){
			return getMap(0, "成功");
		}
		return getMap(1, "失败");
	}
	
	@RequestMapping(value = "/wenjuan")
	public String wenjuan(HttpServletRequest request){
		return "/wenjuan/wenjuan";
	}
	
	
	public Map getMap(Integer code,String hint){
		Map map = new HashMap();
		map.put("code", code);
		map.put("hint", hint);
		return map;
	}
}
