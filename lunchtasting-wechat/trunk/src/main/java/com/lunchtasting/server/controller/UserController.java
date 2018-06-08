package com.lunchtasting.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.user.CookieHandlingBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.biz.user.UserDepositBIZ;
import com.lunchtasting.server.biz.user.UserDepositDrawBIZ;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserDeposit;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private UserDepositBIZ userDepositBIZ;
	@Autowired
	private UserDepositDrawBIZ userDepositDrawBIZ;
	
	private int pageSize = 20;
	private double MIN_DAY_MONEY = 20;
	
	/**
	 * 用户个人中心
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/center")
	public String center(Model model, HttpServletRequest request, HttpServletResponse response){
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		Map user = userBIZ.getUserDetail(userId);
		if(user == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("user", user);
		
		return "/user/user_center_new";
	}
	
	/**
	 * 用户钱包页
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wallet")
	public String wallet(Model model, HttpServletRequest request, HttpServletResponse response){
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		Map userMap = userBIZ.getUserDetail(userId);
		if(userMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		double deposit = 0; 
		if(ValidatorHelper.isNotEmpty(userMap.get("deposit"))){
			deposit = Double.parseDouble(userMap.get("deposit").toString());
		}
		
		/**
		 * 提现次数
		 */
		Integer count = userDepositDrawBIZ.getNowDrawCount(userId, 3);
		if(count == null){
			count = 0;
		}
		
		model.addAttribute("user", userMap);
		model.addAttribute("count", 1-count);
		if(deposit == 0){
			model.addAttribute("deposit", 0);
		}else{
			model.addAttribute("deposit", CommonHelper.getDobule(deposit));
		}
		/**
		 * 是否可以提现
		 */
		if(deposit >= MIN_DAY_MONEY && count == 0){
			model.addAttribute("is_draw", "1");
		}
		return "/user/user_wallet";
	}
	
	
	@RequestMapping(value = "/pull_my")
	public void pullMy(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{
		long userId = (Long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		String url = "http://wchat.mperfit.com/user/pull_new?user_id="+userId;
		response.sendRedirect(url);
	}
	
	/**
	 * 用户拉新下线
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/pull_new")
	public String pullNew(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException{	
		String uId = request.getParameter("user_id");
		if(!ValidatorHelper.isNumber(uId)){
			return VariableHelper.ERROR2_JSP;
		}
		long userId = Long.parseLong(uId);
		
		/**
		 * 当前登录用户
		 */
		long loginUserId = (Long)request.getSession().getAttribute(
					VariableHelper.LOGIN_SESSION_USER);
		
		/**
		 * 判断是自己访问 还是别人访问
		 */
		if(loginUserId == userId){
			Map userMap = userBIZ.getUserDetail(userId);
			if(userMap == null){
				return VariableHelper.ERROR2_JSP;
			}
			model.addAttribute("user", userMap);
			
			/**
			 * 微信分享
			 */
			String ticket = WeChatHelper.getTicket(request);
			String timestamp = WeChatHelper.getTimeStamp();
			String nonceStr = WeChatHelper.getNonceStr();
			String url = "http://wchat.mperfit.com/user/pull_new?user_id="+userId;
			model.addAttribute("url", url);
			model.addAttribute("appId", WeChatConfig.APP_ID);
			model.addAttribute("timestamp", timestamp);
			model.addAttribute("nonceStr", nonceStr);
			SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
			String sign = WeChatHelper.sign(params);
			model.addAttribute("signature", sign);
			
			return "/user/user_pullnew";
		}else{
			
			/**
			 * 修改用户邀请
			 */
			userBIZ.updateInviteUserId(loginUserId, userId);
			
			/**
			 * 跳转到首页或课程页(默认第一堂课)
			 */
			response.sendRedirect(CommonHelper.gotoWebRoot(request)+"course/921633867612291072");
			return null;
		}
				
	}
	
//	/**
//	 * 用户存款记录列表
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/deposit/list")
//	public String depositList(Model model, HttpServletRequest request, HttpServletResponse response){
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		List list = userDepositBIZ.queryUserDepositList(userId, 0, pageSize);
//		int totalPage = Utils.getTotalPage(userDepositBIZ.getUserDepositCount(userId),20);
//		model.addAttribute("list", list);
//		model.addAttribute("totalPage", totalPage);
//		return "";
//	}
//	
//	/**
//	 * 用户存款记录列表更多
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/deposit/listMore")
//	@ResponseBody
//	public Object depositListMore(HttpServletRequest request) throws IOException{
//		String page = request.getParameter("page");
//		
//		int intPage = 1;
//		if(ValidatorHelper.isNumber(page)){
//			intPage = Integer.parseInt(page);
//		}
//		
//		try {
//			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			
//			int totalPage = Utils.getTotalPage(userDepositBIZ.getUserDepositCount(userId),pageSize);
//			if(intPage > totalPage){
//				intPage = totalPage;
//			}
//			
//			List list = userDepositBIZ.queryUserDepositList(userId,(intPage-1)*pageSize, pageSize);
//			Map map = new HashMap();
//			map.put("list", list);
//			map.put("total_page", totalPage);
//			map.put("current_page", intPage);	 
//			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_ERROR);
//		}
//	}
	
	/**
	 * 用户提现规则说明
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/withdraw/rule")
	public String drawRule(Model model, HttpServletRequest request, HttpServletResponse response){
		
		
		return "/user/user_withdraw_rule";
	}
	
	
	/**
	 * 用户提现一次
	 */
	@RequestMapping(value = "/withdraw/add")
	@ResponseBody
	public Object drawAdd(Model model, HttpServletRequest request, HttpServletResponse response){
		String account = request.getParameter("account");
		String money = request.getParameter("money");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		if(ValidatorHelper.isEmpty(account) || ValidatorHelper.isEmpty(money)
				|| ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(name)){
			return MapResult.build(111,MapResult.MESSAGE_FAILURE);
		}
		
		try {
			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			//long userId = 892295621438865408L;
			double dMoney = Double.parseDouble(money);
			
			/**
			 * 判断用户三天内是否提现
			 */
			if(userDepositDrawBIZ.checkDraw(userId)){
				return MapResult.build(110,MapResult.MESSAGE_FAILURE);
			}
			
			/**
			 * 判断用户钱包存款是否有提现的钱
			 */
			Map userMap = userBIZ.getUserDetail(userId);
			if(userMap == null || ValidatorHelper.isEmpty(userMap.get("deposit"))){
				return MapResult.build(110,MapResult.MESSAGE_FAILURE);
			}
			
			/**
			 * 提现是否大于存款  是否小于最低提现金额
			 */
			double deposit = Double.parseDouble(userMap.get("deposit").toString());
			if(deposit == 0 || dMoney > deposit || dMoney < MIN_DAY_MONEY){
				return MapResult.build(111,MapResult.MESSAGE_FAILURE);
			}
			
			
			/**
			 * 创建一条用户提现
			 */
			boolean result = userDepositDrawBIZ.createUserDepositDraw(userId, dMoney, 
					account, phone, name);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_ERROR);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
	/**
	 * 用户提现记录列表
	 * @param model
	 * @param re　quest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/withdraw/list")
	public String drawList(Model model, HttpServletRequest request, HttpServletResponse response){
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		//long userId = 921558320634920960L;
		List list = userDepositDrawBIZ.queryDrawList(userId, 0, pageSize);
		//int totalPage = Utils.getTotalPage(userDepositDrawBIZ.getDrawCount(userId),pageSize);
		model.addAttribute("list", list);
		//model.addAttribute("totalPage", totalPage);
		return "/user/user_withdraw_list";
	}
	
	/**
	 * 用户提现记录列表更多
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/withdraw/listMore")
	@ResponseBody
	public Object depositListMore(HttpServletRequest request) throws IOException{
		String page = request.getParameter("page");
		
		int intPage = 1;
		if(ValidatorHelper.isNumber(page)){
			intPage = Integer.parseInt(page);
		}
		
		try {
			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			
			int totalPage = Utils.getTotalPage(userDepositDrawBIZ.getDrawCount(userId),pageSize);
			if(intPage > totalPage){
				intPage = totalPage;
			}
			
			List list = userDepositDrawBIZ.queryDrawList(userId,(intPage-1)*pageSize, pageSize);
			Map map = new HashMap();
			map.put("list", list);
			map.put("total_page", totalPage);
			map.put("current_page", intPage);	 
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_ERROR);
		}
	}

	
//	/**
//	 * 用户个人中心
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/center")
//	public String center(Model model, HttpServletRequest request, HttpServletResponse response){
//		String dUserId = request.getParameter("user_id");
//		
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		/**
//		 * 判断查看的用户是否是自己
//		 */
//		int isMe = 1;
//		if(ValidatorHelper.isNumber(dUserId) 
//				&& !dUserId.equals(userId+"")){
//			userId = Long.parseLong(dUserId);
//			isMe = 0;
//		}
//		model.addAttribute("isMe", isMe);
//		model.addAttribute("userId", userId);
//		
//		/**
//		 * 获得用户信息
//		 */
//		Map userMap = userBIZ.getUserDetail(userId);
//		if(userMap == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		model.addAttribute("user", userMap);
//		
//		/**
//		 * 评论信息
//		 */
//		List commentList = userBIZ.queryUserCommentList(userId, 0, 10);
//		model.addAttribute("commentList", commentList);
//		
////		/**
////		 * 最近访客
////		 */
////		List visitList = userBIZ.queryUserVisitorList(userId, 0, 6);
////		model.addAttribute("visitList", visitList);
////		
//		
//		return "/user/user_center";
//	}
	
	
//	/**
//	 * 用户个人中心
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/center")
//	public String center(Model model, HttpServletRequest request, HttpServletResponse response){
//		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		Map userMap = userBIZ.getUserDetail(userId);
//		if(userMap == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		model.addAttribute("user", userMap);
//		
//		return "/user/user_center";
//	}
//	
//	/**
//	 * 用户信息修改
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/info/update")
//	@ResponseBody
//	public Object update(Model model, HttpServletRequest request, HttpServletResponse response){
//		String signature = request.getParameter("signature");
//		String hobby = request.getParameter("hobby");
//		String feeling = request.getParameter("feeling");
//		String tags = request.getParameter("tags");
//		if(ValidatorHelper.isEmpty(signature) && ValidatorHelper.isEmpty(hobby) 
//				&& ValidatorHelper.isEmpty(feeling) && ValidatorHelper.isEmpty(tags)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,"必须修改一项哦！");
//		}
//		
//		try {
//			long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			
//			boolean result = userBIZ.updateUserInfo(userId, null, feeling, signature, hobby, tags);
//			if(result){
//				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
//			}
//		}catch(Exception e){
//			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
//		}
//		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
//	}
//	
//	/**
//	 * 用户添加留言
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/comment/add")
//	@ResponseBody
//	public Object commentAdd(Model model, HttpServletRequest request) throws IOException{
//		String userId = request.getParameter("user_id");
//		String content = request.getParameter("content");
//		String pUserId = request.getParameter("parent_user_id");
//		String pId = request.getParameter("parent_id");
//		
//		if(ValidatorHelper.isNumber(userId) == false 
//				|| ValidatorHelper.isEmpty(content)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		
//		try {
//			long loginUserId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			
//			Long parentId = null;
//			if(ValidatorHelper.isNumber(pId)){
//				parentId = Long.parseLong(pId);
//			}
//			
//			/**
//			 * 登录用户
//			 */
//			User srcUser = userBIZ.getById(loginUserId);
//			if(srcUser == null){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//			}
//			
//			User parentUser = null;
//			Long parentUserId = null;
//			if(ValidatorHelper.isNumber(pUserId)){
//				parentUserId = Long.parseLong(pUserId);
//				parentUser = userBIZ.getById(parentUserId);
//				if(parentUser == null){
//					return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//				}
//			}
//			
//			/**
//			 * 创建留言
//			 */
//			long id = userBIZ.createUserComment(Long.parseLong(userId)
//						, loginUserId, content, parentId, parentUserId);
//			
//			Map data = new HashMap();
//			data.put("comment_id", id);
//			data.put("content", content);
//			data.put("src_user_id", srcUser.getId());
//			data.put("src_name", srcUser.getName());
//			if(parentUser != null){
//				data.put("parent_user_id", parentUser.getId());
//				data.put("parent_name", parentUser.getName());
//			}
//			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,data);
//		}catch(Exception e){
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
//		}
//	}
	
}

