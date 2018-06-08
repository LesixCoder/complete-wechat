package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityEnrollBIZ;
import com.lunchtasting.server.biz.common.CommonCollectBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableConfig;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 用户基本信息模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private CommonCollectBIZ collectBIZ;
	@Autowired
	private ActivityEnrollBIZ activityEnrollBIZ;
	
	/**
	 * 用户中心首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center")
	@ResponseBody
	public Object center(HttpServletRequest request) throws Exception {
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			Map userMap = userBIZ.getUserDetail(userId);
			if(userMap == null){
				return MapResult.build(MapResult.CODE_AGAIN_LOGIN, MapResult.MESSAGE_NOTLOGIN, request);
			}
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("userMap", userMap);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 个人信息修改详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info/detail")
	@ResponseBody
	public Object infoDetail(HttpServletRequest request) throws Exception {
		try {
			long userId = EncryptAuth.getUserId(request);
			
			Map userMap = userBIZ.getEditInfo(userId);
			if(userMap == null){
				return MapResult.build(MapResult.CODE_AGAIN_LOGIN, MapResult.MESSAGE_NOTLOGIN, request);
			}
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("user", userMap);
			dataMap.put("token", QiNiuStorageHelper.getUpToken());
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 个人信息修改
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info/update")
	@ResponseBody
	public Object infoUpdate(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String imgUrl = request.getParameter("img_url");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String profession = request.getParameter("profession");
		String feeling = request.getParameter("feeling");
		String signature = request.getParameter("signature");
		
		if(	ValidatorHelper.isEmpty(imgUrl)
				&& ValidatorHelper.isEmpty(sex) && ValidatorHelper.isEmpty(birth)
				&& ValidatorHelper.isEmpty(profession) && ValidatorHelper.isEmpty(feeling)
				&& ValidatorHelper.isEmpty(signature)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		if(ValidatorHelper.isEmpty(name)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, "名字不可为空！", request);
		}
		
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			boolean result = userBIZ.updateUserInfo(userId, name, imgUrl, sex, birth, profession, feeling, signature);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
	/**
	 * 用户建议
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/suggest")
	@ResponseBody
	public Object suggest(HttpServletRequest request) throws Exception {
		String content = request.getParameter("content");
		if(ValidatorHelper.isEmpty(content)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			userBIZ.createSuggest(userId, content);
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 用户报名活动列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activity/enroll/list")
	@ResponseBody
	public Object activityEnrollList(HttpServletRequest request) throws Exception {
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
			dataMap.put("list", activityEnrollBIZ.queryUserEnrollerList
						(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			
			dataMap.put("total_page",Utils.getTotalPage
						(activityEnrollBIZ.getUserEnrollerCount(userId),VariableConfig.PAGE_SIZE));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
}
