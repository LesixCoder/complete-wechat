package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.message.MessageNotificationBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.po.lt.Activity;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/message/notification")
public class MessageNotificationController {
	
	
	@Autowired
	private MessageNotificationBIZ nofiticationBIZ;

	/**
	 * 消息列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", nofiticationBIZ.queryNotificationList(userId, (pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(nofiticationBIZ.getNotificationCount(userId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE,dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
