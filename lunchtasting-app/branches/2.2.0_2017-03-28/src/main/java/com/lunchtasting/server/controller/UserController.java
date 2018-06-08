package com.lunchtasting.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.activity.ActivityOrderBIZ;
import com.lunchtasting.server.biz.common.CommonCollectBIZ;
import com.lunchtasting.server.biz.friend.FriendBIZ;
import com.lunchtasting.server.biz.match.MatchOrderBIZ;
import com.lunchtasting.server.biz.note.NoteBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.biz.user.UserScoreBIZ;
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
	private FriendBIZ friendBIZ;
	@Autowired
	private NoteBIZ noteBIZ;
	@Autowired
	private UserScoreBIZ userScoreBIZ;
	@Autowired
	private ActivityOrderBIZ activityOrderBIZ;
	@Autowired
	private MatchOrderBIZ matchOrderBIZ;
	
	/**
	 * 用户中心首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center" , method=RequestMethod.POST)
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
	 * 用户中心首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center_v2_1_0" , method=RequestMethod.POST)
	@ResponseBody
	public Object centerV2_1_0(HttpServletRequest request) throws Exception {
		
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
			dataMap.put("user", userMap);
			
			/**
			 * 发布的帖子
			 */
			dataMap.put("note_list", noteBIZ.queryUserNoteList(userId, 0, 12));
			dataMap.put("note_total_page",Utils.getTotalPage(noteBIZ.getUserNoteCount(userId),12));
			
			/**
			 * 喜欢的帖子
			 */
			dataMap.put("like_list", noteBIZ.queryUserLikeNoteList(userId,0, 12));
			dataMap.put("like_total_page", Utils.getTotalPage(noteBIZ.getUserLikeNoteCount(userId),12));
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 用户中心首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/center_v2_2_0" , method=RequestMethod.POST)
	@ResponseBody
	public Object centerV2_2_0(HttpServletRequest request) throws Exception {
		
		try {
			long userId = EncryptAuth.getUserId(request);
			
			Map userMap = userBIZ.getUserDetail(userId);
			if(userMap == null){
				return MapResult.build(MapResult.CODE_AGAIN_LOGIN, MapResult.MESSAGE_NOTLOGIN, request);
			}
			
			/**
			 * 发布帖子+喜欢帖子
			 */
			int noteCount = noteBIZ.getUserNoteCount(userId);
			int likeCount = noteBIZ.getUserLikeNoteCount(userId);
			
			Map dataMap = new HashMap();
			userMap.put("note_count", noteCount+likeCount);
			dataMap.put("user", userMap);
			
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 看他人个人中心
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/another" , method=RequestMethod.POST)
	@ResponseBody
	public Object another(HttpServletRequest request) throws Exception {
		String dId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
		}
		
		try {
			Long userId = EncryptAuth.getUserId(request);
			long desUserId = Long.parseLong(dId);
			
			if(userId != null && userId == desUserId){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 查询他人用户信息
			 */
			Map userMap = userBIZ.getUserDetail(desUserId);
			if(userMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR, MapResult.MESSAGE_PARAMETER, request);
			}
			
			/**
			 * 是否已经关注过
			 */
			boolean isFollow = friendBIZ.checkFollow(userId, desUserId);
			
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("user", userMap);
			if(isFollow){
				dataMap.put("is_follow", 1);
			}else{
				dataMap.put("is_follow", 0);
			}
			
			/**
			 * 发布的帖子
			 */
			dataMap.put("note_list", noteBIZ.queryUserNoteList(desUserId, 0, 12));
			dataMap.put("note_total_page",Utils.getTotalPage(noteBIZ.getUserNoteCount(desUserId),12));
			
			/**
			 * 喜欢的帖子
			 */
			dataMap.put("like_list", noteBIZ.queryUserLikeNoteList(desUserId,0,12));
			dataMap.put("like_total_page", Utils.getTotalPage(noteBIZ.getUserLikeNoteCount(desUserId),12));
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 个人信息修改详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/info/detail" , method=RequestMethod.POST)
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
	@RequestMapping(value = "/info/update" , method=RequestMethod.POST)
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
	@RequestMapping(value = "/suggest" , method=RequestMethod.POST)
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
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 用户报名活动列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activity/enroll/list" , method=RequestMethod.POST)
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
			
			dataMap.put("total_page",Utils.getTotalPage
					(activityOrderBIZ.getUserActivityOrderCount(userId),VariableConfig.PAGE_SIZE));
			
			dataMap.put("list", activityOrderBIZ.queryUserActivityOrderList
						(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	
	/**
	 * 用户报名赛事列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/match/enroll/list" )
	@ResponseBody
	public Object matchEnrollList(HttpServletRequest request) throws Exception {
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
			//long userId = 10019;
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			
			dataMap.put("total_page",Utils.getTotalPage
					(matchOrderBIZ.getUserMatchOrderCount(userId),VariableConfig.PAGE_SIZE));
			
			dataMap.put("list", matchOrderBIZ.queryUserMatchOrderList
						(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 用户发布的帖子列表页
	 * @param request
	 * @return
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/note/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object noteList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String dId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dId)){
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
			long desUserId = Long.parseLong(dId);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", noteBIZ.queryUserNoteList
						(desUserId,(pPage-1)*15,15));
			
			dataMap.put("total_page",Utils.getTotalPage
						(noteBIZ.getUserNoteCount(desUserId),15));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 用户喜欢的帖子列表页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/note/like/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object noteLikeList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String dId = request.getParameter("des_user_id");
		if(!ValidatorHelper.isNumber(dId)){
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
			long desUserId = Long.parseLong(dId);
			
			/**
			 * 报文返回
			 */
			Map dataMap = new HashMap();
			dataMap.put("list", noteBIZ.queryUserLikeNoteList
						(desUserId,(pPage-1)*15, 15));
			
			dataMap.put("total_page",Utils.getTotalPage
						(noteBIZ.getUserLikeNoteCount(desUserId),15));
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
	/**
	 * 用户积分列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/score/list" , method=RequestMethod.POST)
	@ResponseBody
	public Object scoreList(HttpServletRequest request) throws Exception {
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
			
			if(pPage == 1){
				dataMap.put("total_score", userScoreBIZ.getUserScoreTotal(userId));
			}
			
			dataMap.put("list", userScoreBIZ.queryUserScoreList
						(userId,(pPage-1)*VariableConfig.PAGE_SIZE, VariableConfig.PAGE_SIZE));
			
			dataMap.put("total_page",Utils.getTotalPage
						(userScoreBIZ.getUserScoreCount(userId),VariableConfig.PAGE_SIZE));
			
			
			dataMap.put("current_page", pPage);	
			return MapResult.build(MapResult.CODE_SUCCESS, MapResult.MESSAGE_SUCCESS, dataMap, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR, MapResult.MESSAGE_ERROR, request);
		}
	}
	
}
