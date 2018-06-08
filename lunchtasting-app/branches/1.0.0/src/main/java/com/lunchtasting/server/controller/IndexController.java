package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 首页模块
 * @author xq
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private ActivityBIZ activityBIZ;

	/**
	 * 活动列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public Object index(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		try {
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", activityBIZ.queryIndexList((pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(activityBIZ.getIndexCount(),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, null, request);
		}
	}
}
