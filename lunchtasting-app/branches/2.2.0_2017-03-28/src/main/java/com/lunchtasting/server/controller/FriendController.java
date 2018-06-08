package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.friend.FriendBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;


@Controller
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private FriendBIZ friendBIZ;
	
	/**
	 * 用户关注好友
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add" , method=RequestMethod.POST)
	@ResponseBody
	public Object add(HttpServletRequest request) throws Exception {
		String dId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long desUserId = Long.parseLong(dId);
			
			if(userId == desUserId){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"亲！不能关注自己哦！", request);
			}
			
			/**
			 * 是否已经关注过
			 */
			boolean isFollow = friendBIZ.checkFollow(userId, desUserId);
			if(isFollow){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "亲，不能重复关注哦！", request);
			}
			
			/**
			 * 添加关注
			 */
			boolean result = friendBIZ.addFriend(userId, desUserId);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
			
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 用户取消关注好友
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/remove" , method=RequestMethod.POST)
	@ResponseBody
	public Object remove(HttpServletRequest request) throws Exception {
		String dId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long desUserId = Long.parseLong(dId);
			
			if(userId == desUserId){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 添加关注
			 */
			boolean result = friendBIZ.removeFriend(userId, desUserId);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
			
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	
	/**
	 * 关注好友列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/follow/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object followList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String dUserId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dUserId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			Long desUserId = Long.parseLong(dUserId);
			Long loginUserId = userId;
			
			/**
			 * 看自己关注的人就不需要再查询是否关注过了
			 */
			if(userId != null){
				if(userId.longValue() == desUserId){
					userId = null;
				}
			}
			
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", friendBIZ.queryFollowList(userId,desUserId,loginUserId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(friendBIZ.getFollowCount(desUserId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 粉丝好友列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fans/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object fansList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String dUserId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dUserId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			long desUserId = Long.parseLong(dUserId);

			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", friendBIZ.queryFansList(userId,desUserId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			dataMap.put("total_page",Utils.getTotalPage(friendBIZ.getFansCount(desUserId),VariableConfig.PAGE_SIZE));
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
}
