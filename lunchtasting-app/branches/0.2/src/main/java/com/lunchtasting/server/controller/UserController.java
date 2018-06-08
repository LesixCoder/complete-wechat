package com.lunchtasting.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.common.CommonCollectBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.helper.EncryptAuth;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 用户基本信息模块
 * @author xq
 *
 */
@Controller
@RequestMapping("/user/center")
public class UserController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private CommonCollectBIZ userCollectBIZ;
	
	/**
	 * 用户中心首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "")
	@ResponseBody
	public Object center(HttpServletRequest request) throws Exception {
		
		return null;
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
		String hobby = request.getParameter("hobby");
		String feeling = request.getParameter("feeling");
		
		if(ValidatorHelper.isEmpty(name) && ValidatorHelper.isEmpty(imgUrl)
				&& ValidatorHelper.isEmpty(sex) && ValidatorHelper.isEmpty(hobby)
				&& ValidatorHelper.isEmpty(feeling)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			Boolean result = true;
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
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
	
	@RequestMapping(value = "/collect/add")
	@ResponseBody
	public Object collectAdd(HttpServletRequest request) throws Exception {
		String bId = request.getParameter("biz_id");
		String bType = request.getParameter("biz_type");
		if(!ValidatorHelper.isNumber(bId) || !ValidatorHelper.isNumber(bType)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			long userId = EncryptAuth.getUserId(request);
			long bizId = Long.parseLong(bId);
			int bizType = Integer.parseInt(bType);
			
			/**
			 * 判断用户是否收藏过
			 */
			Boolean checkCollect = userCollectBIZ.checkUserCollect(userId, bizId, bizType);
			if(checkCollect){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, "亲，你已经收藏过了！", request);
			}
			
			/**
			 * 收藏
			 */
			Boolean result = userCollectBIZ.createCollect(userId, bizId, bizType);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, request);
			}
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
		return MapResult.build(MapResult.CODE_FAILURE, MapResult.MESSAGE_FAILURE, request);
	}
	
}
