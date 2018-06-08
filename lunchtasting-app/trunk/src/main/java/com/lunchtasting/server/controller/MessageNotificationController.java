package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.message.MessageNotificationBIZ;
import com.lunchtasting.server.enumeration.SysEnum.MessageNotificatioType;
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
			 * 修改消息已读
			 */
			if(pPage == 1){
				nofiticationBIZ.updateNotifyIsRead(userId);
			}
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", nofiticationBIZ.queryNotificationList(userId, (pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(nofiticationBIZ.getNotificationCount(userId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 评论通知列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment/list")
	@ResponseBody
	public Object commentList(HttpServletRequest request) throws Exception {
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
			 * 修改消息已读
			 */
			if(pPage == 1){
				nofiticationBIZ.updateIsRead(userId,MessageNotificatioType.NOTE_ADD_COMMENT.getType());
			}
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", nofiticationBIZ.queryCommentNotificationList(userId, (pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(nofiticationBIZ.getCommentNotificationCount(userId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 点赞通知列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/like/list")
	@ResponseBody
	public Object likeList(HttpServletRequest request) throws Exception {
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
			 * 修改消息已读
			 */
			if(pPage == 1){
				nofiticationBIZ.updateIsRead(userId,MessageNotificatioType.NOTE_LIKE.getType());
			}
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", nofiticationBIZ.queryLikeNotificationList(userId, (pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(nofiticationBIZ.getLikeNotificationCount(userId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
