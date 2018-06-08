//
//package com.lunchtasting.server.controller;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.lunchtasting.server.biz.match.MatchBIZ;
//import com.lunchtasting.server.biz.match.MatchGroupBIZ;
//import com.lunchtasting.server.biz.match.MatchOrderBIZ;
//import com.lunchtasting.server.biz.match.MatchVoteBIZ;
//import com.lunchtasting.server.biz.user.UserBIZ;
//import com.lunchtasting.server.biz.user.UserSmsBIZ;
//import com.lunchtasting.server.biz.user.UserWeChatBIZ;
//import com.lunchtasting.server.enumeration.StateEnum;
//import com.lunchtasting.server.helper.WeChatConfig;
//import com.lunchtasting.server.helper.MapResult;
//import com.lunchtasting.server.helper.VariableHelper;
//import com.lunchtasting.server.helper.WeChatHelper;
//import com.lunchtasting.server.helper.WeChatMessageHelper;
//import com.lunchtasting.server.po.lt.Match;
//import com.lunchtasting.server.po.lt.MatchGroup;
//import com.lunchtasting.server.po.lt.User;
//import com.lunchtasting.server.po.lt.UserSms;
//import com.lunchtasting.server.po.lt.UserWeChat;
//import com.lunchtasting.server.util.Utils;
//import com.lunchtasting.server.util.ValidatorHelper;
//import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
//
//@Controller
//@RequestMapping("/match")
//public class MatchController {
//	
//	@Autowired
//	private MatchOrderBIZ matchOrderBIZ;
//	@Autowired 
//	private UserSmsBIZ userSmsBIZ;
//	@Autowired
//	private MatchBIZ matchBIZ;
//	@Autowired
//	private UserBIZ userBIZ;
//	@Autowired
//	private MatchGroupBIZ matchGroupBIZ;
//	@Autowired
//	
//	private UserWeChatBIZ userWeChatBIZ;
//	@Autowired
//	private MatchVoteBIZ matchVoteBIZ;
//	
//	
////	/**
////	 * 赛事详情
////	 * @param model
////	 * @param request
////	 * @return
////	 * @throws IOException
////	 * @throws ParseException 
////	 */
////	@RequestMapping(value = "/paoxiaogou")
////	public String paoxiaogou(Model model, HttpServletRequest request) throws IOException{
////		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////		long matchId = 840043772388573183L;
////		
////		/**
////		 * 参赛票
////		 */
////		Map csOrderMap = matchOrderBIZ.getUserMatchOrder(userId, matchId,1);
////		model.addAttribute("csOrder", csOrderMap);
////		
////		/**
////		 * 观赛票
////		 */
////		Map gsOrderMap = matchOrderBIZ.getUserMatchOrder(userId, matchId,2);
////		model.addAttribute("gsOrder", gsOrderMap);
////		
//	
////		/**
////		 * 微信分享信息
////		 */
////		String ticket = WeChatHelper.getTicket(request);
////		String timestamp = WeChatHelper.getTimeStamp();
////		String nonceStr = WeChatHelper.getNonceStr();
////		String url = "http://wchat.mperfit.com/match/paoxiaogou";
////		model.addAttribute("appId", WeChatConfig.APP_ID);
////		model.addAttribute("timestamp", timestamp);
////		model.addAttribute("nonceStr", nonceStr);
////		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
////		String sign = WeChatHelper.sign(params);
////		model.addAttribute("signature", sign);
////		
////		return "/match/match_intro";
////	}
//	
//	/**
//	 * 成绩
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/pxg/chengji")
//	public String pxgChengJi(Model model, HttpServletRequest request) throws IOException{
//		
//		/**
//		 * 男子组排名
//		 */
//		List manList = matchBIZ.queryPxgCjList("男");
//		model.addAttribute("manList", manList);
//		
//		/**
//		 * 女子组排名
//		 */
//		List womenList = matchBIZ.queryPxgCjList("女");
//		model.addAttribute("womenList", womenList);
//		
//		return "/match/match_score";
//	}
//	
//	
//	/**
//	 * 报名页面
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup")
//	public String signup(Model model,HttpServletRequest request){
//		//return VariableHelper.ERROR_JSP;
//		String matchId = request.getParameter("match_id");
//		String inviteCode = request.getParameter("invite_code");
//		String inviteUserId = request.getParameter("invite_id");
//		if(!ValidatorHelper.isNumber(matchId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		
//		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		User user = userBIZ.getById(userId);
//		model.addAttribute("user", user);
//		model.addAttribute("matchId",matchId);
//		model.addAttribute("inviteCode", inviteCode);
//		model.addAttribute("inviteId", inviteUserId);
//		return "/match/match_signUp";
//	}
//	
//	/**
//	 * 参赛票报名
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/do")
//	@ResponseBody
//	public Object signupDo(Model model, HttpServletRequest request){
//		String phone = request.getParameter("phone");
//		String code = request.getParameter("code");
//		String mId = request.getParameter("match_id");
//		String name = request.getParameter("name");
//		String sex = request.getParameter("sex");
//		String birth = request.getParameter("birth");
//		String realName = request.getParameter("real_name");
//		String certificate = request.getParameter("certificate");
//		String inviteCode = request.getParameter("invite_code");
//		String inviteId = request.getParameter("invite_id");
//		
//		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(code) 
//				|| !ValidatorHelper.isNumber(mId) || ValidatorHelper.isEmpty(name)
//				|| !ValidatorHelper.isNumber(sex) || ValidatorHelper.isEmpty(birth)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		
//		try {
//			long matchId = Long.parseLong(mId);
//			long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			Double price = 0d;
//			
//			/**
//			 * 判断存在赛事
//			 */
//			Match match = matchBIZ.getById(matchId);
//			if(match == null){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"未找到赛事信息。");
//			}
//			price = match.getPrice();
//			
//			/**
//			 * 判断验证码是否正确
//			 */
//			UserSms userSms = userSmsBIZ.getByPhoneAndType(phone, 201);
//			if(userSms == null || !userSms.getCode().equals(code)){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"验证码错误。");
//			}
//			
//			/**
//			 * 判断邀请码是否存在
//			 */
//			Long matchCodeId = null;
//			Integer isPay = null;
//			if(ValidatorHelper.isNotEmpty(inviteCode)){
//				Map matchCodeMap = matchBIZ.getMatchCodeByCode(inviteCode);
//				if(matchCodeMap != null){
//					/**
//					 * 选判断是否超过使用次数
//					 */
//					int number = Integer.parseInt(matchCodeMap.get("number").toString());
//					isPay = Integer.parseInt(matchCodeMap.get("is_pay").toString());
//					int orderCodeCount = matchOrderBIZ.getMatchCodeCount(matchId, matchCodeId);
//					if(number > orderCodeCount){
//						matchCodeId = Long.parseLong(matchCodeMap.get("id").toString());
//						price = Double.parseDouble(matchCodeMap.get("price").toString());
//					}
//				}
//			}
//			
//			/**
//			 * 判断是否被用户邀请
//			 */
//			Long inviteUserId = null;
//			if(ValidatorHelper.isNumber(inviteId)){
//				inviteUserId = Long.parseLong(inviteId);
//			}
//			
//			
//			/**
//			 * 验证是否手机号，用户是否报过名
//			 */
//			if(matchOrderBIZ.verify("", userId, matchId,1) == false){
//				Long orderId = matchOrderBIZ.createMatchOrder(userId,matchId, phone
//						, price,name,Integer.parseInt(sex),birth,realName,certificate,null,null
//						,userSms.getId(),matchCodeId,isPay,inviteUserId);
//				Map map = new HashMap();
//				
//				/**
//				 * 判断是否需要支付
//				 * 0 不需要支付
//				 * 1 需要支付
//				 */
//				if(isPay != null && isPay == 0){
//					map.put("url","/match/signup/info?match_id="+matchId+"&type=1");
//				}else{
//					map.put("url","/match/order/confirm?order_id="+orderId);
//				}
//				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS, map);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
//		}
//		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
//	}
//	
//	/**
//	 * 观赛票报名页面
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/watch")
//	public String signupWatch(Model model,HttpServletRequest request){
//        return VariableHelper.ERROR_JSP;
////		String matchId = request.getParameter("match_id");
////		String inviteUserId = request.getParameter("invite_id");
////		if(!ValidatorHelper.isNumber(matchId)){
////			return VariableHelper.ERROR_JSP;
////		}
////		
////		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////		User user = userBIZ.getById(userId);
////		model.addAttribute("user", user);
////		model.addAttribute("matchId",matchId);
////		model.addAttribute("inviteId", inviteUserId);
////		return "/match/match_signUp_watch";
//	}
//	
//	/**
//	 * 观赛票报名
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/watch/do")
//	@ResponseBody
//	public Object signupWatchDo(Model model,HttpServletRequest request){
//		String phone = request.getParameter("phone");
//		String code = request.getParameter("code");
//		String mId = request.getParameter("match_id");
//		String name = request.getParameter("name");
//		String sex = request.getParameter("sex");
//
//		
//		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(code) 
//				|| !ValidatorHelper.isNumber(mId) || ValidatorHelper.isEmpty(name)
//				|| !ValidatorHelper.isNumber(sex)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		
//
//		try {
//			long matchId = Long.parseLong(mId);
//			long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			Double price = 0d;
//			
//			/**
//			 * 判断存在赛事
//			 */
//			Match match = matchBIZ.getById(matchId);
//			if(match == null){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"未找到赛事信息。");
//			}
//			
//			/**
//			 * 判断验证码是否正确
//			 */
//			UserSms userSms = userSmsBIZ.getByPhoneAndType(phone, 202);
//			if(userSms == null || !userSms.getCode().equals(code)){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"验证码错误。");
//			}
//			
//			/**
//			 * 判断是否被用户邀请
//			 */
//			
//			Long inviteUserId = null;
//			if(request.getSession()
//					.getAttribute(VariableHelper.INVITE_SESSION_USER) != null){
//				inviteUserId = (long)request.getSession()
//						.getAttribute(VariableHelper.INVITE_SESSION_USER);
//			}
//			
//			/**
//			 * 邀请渠道
//			 */
//			String channel = "";
//			if(request.getSession()
//					.getAttribute(VariableHelper.INVITE_CHANNEL) != null){
//				channel = (String)request.getSession()
//						.getAttribute(VariableHelper.INVITE_CHANNEL);
//			}
//			
//			/**
//			 * 验证是否手机号，用户是否报过名
//			 */
//			if(matchOrderBIZ.verify(phone, userId, matchId,2) == false){
//				
//				matchOrderBIZ.createMatchWatchOrder(userId, matchId, phone, price, name
//						,Integer.parseInt(sex),userSms.getId(),inviteUserId,channel);
//				
//				request.getSession().removeAttribute(VariableHelper.INVITE_SESSION_USER);
//				
//				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
//		}
//		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
//	}
//	
//	
//	/**
//	 * 观赛票报名页面
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/gongyi")
//	public String signupGongyi(Model model,HttpServletRequest request){
//		String matchId = request.getParameter("match_id");
//		String inviteUserId = request.getParameter("invite_id");
//		if(!ValidatorHelper.isNumber(matchId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		
//		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		User user = userBIZ.getById(userId);
//		model.addAttribute("user", user);
//		model.addAttribute("matchId",matchId);
//		model.addAttribute("inviteId", inviteUserId);
//		return "/match/match_signUp_gongyi";
//	}
//	
//	/**
//	 * 观赛票报名
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/gongyi/do")
//	@ResponseBody
//	public Object signupGongyiDo(Model model,HttpServletRequest request){
//		String phone = request.getParameter("phone");
//		String code = request.getParameter("code");
//		String mId = request.getParameter("match_id");
//		String name = request.getParameter("name");
//		String sex = request.getParameter("sex");
//		String inviteId = request.getParameter("invite_id");
//		
//		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(code) 
//				|| !ValidatorHelper.isNumber(mId) || ValidatorHelper.isEmpty(name)
//				|| !ValidatorHelper.isNumber(sex)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		
//
//		try {
//			long matchId = Long.parseLong(mId);
//			long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//			Double price = 10d;
//			
//			/**
//			 * 判断存在赛事
//			 */
//			Match match = matchBIZ.getById(matchId);
//			if(match == null){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"未找到赛事信息。");
//			}
//			
//			/**
//			 * 判断验证码是否正确
//			 */
//			UserSms userSms = userSmsBIZ.getByPhoneAndType(phone, 202);
//			if(userSms == null || !userSms.getCode().equals(code)){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"验证码错误。");
//			}
//			
//			/**
//			 * 判断是否被用户邀请
//			 */
//			Long inviteUserId = null;
//			if(ValidatorHelper.isNumber(inviteId)){
//				inviteUserId = Long.parseLong(inviteId);
//			}
//			
//			/**
//			 * 验证是否手机号，用户是否报过名
//			 */
//			if(matchOrderBIZ.verify(phone, userId, matchId,3) == false){
//				Long orderId = matchOrderBIZ.createMatchGongyiOrder(userId, matchId, phone, price, name
//						,Integer.parseInt(sex),userSms.getId(),inviteUserId);
//				Map map = new HashMap();
//				map.put("url","/match/order/confirm?order_id="+orderId);
//				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS, map);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
//		}
//		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
//	}
//	
//	
//	/**
//	 * 报名信息
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/info")
//	public String signupInfo(Model model, HttpServletRequest request){
//		String mId = request.getParameter("match_id");
//		String type = request.getParameter("type");
//		if(!ValidatorHelper.isNumber(mId)){
//			return "/match/match_not_signup";
//		}
//		
//		
//		long matchId = Long.parseLong(mId);
//		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		Map orderMap = null;
//		if(ValidatorHelper.isNumber(type)){
//			orderMap = matchOrderBIZ.getUserMatchOrder(userId,matchId,Integer.parseInt(type));
//		}else{
//			/**
//			 * 参赛票
//			 */
//			orderMap = matchOrderBIZ.getUserMatchOrder(userId,matchId,1);
//			if(orderMap == null){
//				/**
//				 * 公益票
//				 */
//				orderMap = matchOrderBIZ.getUserMatchOrder(userId,matchId,3);
//				
//				/**
//				 * 观赛票
//				 */
//				if(orderMap == null){
//					orderMap = matchOrderBIZ.getUserMatchOrder(userId,matchId,2);
//				}
//			}
//
//		}
//		if(orderMap == null){
//			return "/match/match_not_signup";
//		}
//		model.addAttribute("order", orderMap);
//		
//		return "/match/match_seeSign";
//		
//	}
//	
//	/**
//	 * 用户发送验证码短信
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/smsCode")
//	@ResponseBody
//	public Object smsCode(Model model,HttpServletRequest request) throws Exception {
//		String phone = request.getParameter("phone");
//		String mId = request.getParameter("match_id");
//		String type = request.getParameter("type");
//		if(ValidatorHelper.isEmpty(phone) || !ValidatorHelper.isNumber(mId)
//				|| !ValidatorHelper.isNumber(type)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		
//		try {
//			Long matchId = Long.parseLong(mId);
//			
//			/**
//			 * 判断是否是手机号
//			 */
//			Pattern pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?1[3,4,5,6,7,8]{1}\\d{9}$"); 
//		    Matcher matcher = pattern.matcher(phone);
//		    if(!(matcher.matches())){
//				return MapResult.build(MapResult.CODE_PARAM_ERROR,"手机号格式不对");
//		    }
//			
////			/**
////			 * 报名短信，判断号码是否已经报名过
////			 */
////		    if(!matchOrderBIZ.verify(phone, null, matchId)){
////				model.addAttribute("msg", "您已报名！");
////				model.addAttribute("result", 4);
////				return model;
////		    }
//		    
//			/**
//			 * 创建并发送短信验证码
//			 */
//			Boolean result = userSmsBIZ.createUserSms(phone, Integer.parseInt(type));
//			if(result){
//				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
//		}
//		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
//	}
//	
//	/**
//	 * 跳转支付确认页面
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/order/confirm")
//	public String orderConfirm(Model model,HttpServletRequest request){
//		String orderId = request.getParameter("order_id");
//		if(!ValidatorHelper.isNumber(orderId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		Map map = matchOrderBIZ.getOrderDetail(Long.parseLong(orderId),userId);
//		if(map == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		
//		model.addAttribute("order", map);
//		return "/match/match_order_confirm";
//	}
//	
//	/**
//	 * 参赛用户付款投票列表
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/order/vote/list")
//	public String orderVoteList(Model model, HttpServletRequest request){
//		String mId = request.getParameter("match_id");
//		String name = request.getParameter("name");
//		String sort = request.getParameter("sort");
//		String page = request.getParameter("page");
//		if(!ValidatorHelper.isNumber(mId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		StringBuilder parameter = new StringBuilder();
//		long matchId = Long.parseLong(mId);
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		int pPage = 1;
//		if(ValidatorHelper.isNumber(page)){
//			pPage = Integer.parseInt(page);
//			if(pPage < 1){
//				pPage = 1;
//			}
//		}
//		
//		int totalPage = Utils.getTotalPage(matchOrderBIZ.getOrderPayUserCount(matchId, name),10);
//		if(pPage >= totalPage){
//			pPage = totalPage;
//		}
//		
//		int previousPage = pPage - 1;
//		if(previousPage <= 0){
//			previousPage = 1;
//		}
//		
//		int nextPage = pPage + 1;
//		if(nextPage >= totalPage){
//			nextPage = totalPage;
//		}
//		
//		parameter.append("match_id="+matchId);
//		
//		int sortType = 1;
//		if(ValidatorHelper.isNumber(sort)){
//			sortType = Integer.parseInt(sort);
//			parameter.append("&sort="+sortType);
//		}
//		
//		if(ValidatorHelper.isNotEmpty(name)){
//			parameter.append("&name="+name);
//		}
//		
//		/**
//		 * 获取当前用户是否报名
//		 */
//		Map orderMap = matchOrderBIZ.getUserMatchOrder(userId,matchId,1);
//		if(orderMap != null){
//			model.addAttribute("orderId", orderMap.get("order_id").toString());
//		}
//
//		
//		List list = matchOrderBIZ.queryOrderPayUserList(matchId, userId, name, sortType,(pPage-1)*10, 10);
//		model.addAttribute("list", list);
//		model.addAttribute("currentPage", pPage);
//		model.addAttribute("totalPage", totalPage);
//		model.addAttribute("nextPage", nextPage);
//		model.addAttribute("previousPage", previousPage);
//		model.addAttribute("parameter", parameter.toString());
//		model.addAttribute("matchId", matchId);
//		model.addAttribute("name", name);
//		model.addAttribute("sort", sortType);
//
//		return "/match/match_vote_list";
//	}
//	
//	/**
//	 * 参数用户投票详情
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/order/vote/detail")
//	public String orderVoteDetail(Model model, HttpServletRequest request){
//		String oId = request.getParameter("order_id");
//		if(!ValidatorHelper.isNumber(oId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		long orderId = Long.parseLong(oId);
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		Map voteMap = matchVoteBIZ.getMatchVoteDetail(orderId, userId);
//		if(voteMap == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		
//		model.addAttribute("vote", voteMap);
//		
//		
//		/**
//		 * 微信分享信息
//		 */
//		String ticket = WeChatHelper.getTicket(request);
//		String timestamp = WeChatHelper.getTimeStamp();
//		String nonceStr = WeChatHelper.getNonceStr();
//		String url = "http://wchat.mperfit.com/match/order/vote/detail?order_id="+orderId;
//		model.addAttribute("appId", WeChatConfig.APP_ID);
//		model.addAttribute("timestamp", timestamp);
//		model.addAttribute("nonceStr", nonceStr);
//		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
//		String sign = WeChatHelper.sign(params);
//		model.addAttribute("signature", sign);
//		return "/match/match_vote_detail";
//	}
//	
//	/**
//	 * 参赛用户投票
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/order/vote/do")
//	@ResponseBody
//	public Object orderVoteDo(Model model, HttpServletRequest request){
//		String oId = request.getParameter("order_id");
//		if(ValidatorHelper.isEmpty(oId)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		
//		
//		long orderId = Long.parseLong(oId);
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		/**
//		 * 判断是否投票
//		 */
//		boolean isVote = matchVoteBIZ.checkIsVote(orderId, userId);
//		if(isVote){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,"你今天已经投过票了，明天可以继续投哦！");
//		}
//		
//		matchVoteBIZ.createMatchVote(orderId, userId);
//		return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
//	}
//
//	/**
//	 * 直接获得某个用户的订单信息(测试，临时使用)
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/signup/get")
//	public String signupGet(Model model, HttpServletRequest request){
//		String uId = request.getParameter("user_id");
//		String type = request.getParameter("type");
//		String mId = request.getParameter("match_id");
//		String p = request.getParameter("page");
//		
//		long matchId = Long.parseLong(mId);
//		long userId = Long.parseLong(uId);
//		int page = Integer.parseInt(p);
//		
//		Map orderMap = matchOrderBIZ.getUserMatchOrderTest(userId,matchId
//				,Integer.parseInt(type),(page-1)*1, 1);
//		if(orderMap == null){
//			return "/match/match_not_signup";
//		}
//		model.addAttribute("order", orderMap);
//		return "/match/match_seeSign";
//		
//	}
//	
//	/**
//	 * 中转页面
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/gongyi/skip")
//	public void gongyiSkip(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		String channel = request.getParameter("channel");
//		long userId = (long)request.getSession()
//				.getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		request.getSession()
//		.setAttribute(VariableHelper.INVITE_CHANNEL,channel);
//		
//		response.sendRedirect("http://wchat.mperfit.com/match/gongyi?user_id="+userId);
//		return;
//	}
//	
//	/**
//	 * 公益页面
//	 * 
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/gongyi")
//	public String gongyi(Model model,HttpServletRequest request){
//		String uId = request.getParameter("user_id");
//		if(!ValidatorHelper.isNumber(uId)){
//			return VariableHelper.ERROR_JSP;
//		}
//		long userId = Long.parseLong(uId);
//		long matchId = 840043772388573183L;
//		long loginUserId = (long)request.getSession()
//				.getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
////		Long userId = null;
////		if(ValidatorHelper.isNumber(uId)){
////			userId = Long.parseLong(uId);
////		}else{
////			userId = loginUserId;
////		}
//		
//		User user = userBIZ.getById(userId);
//		if(user == null){
//			return VariableHelper.ERROR_JSP;
//		}
//		model.addAttribute("user", user);
//		
//		int count = matchOrderBIZ.getMatchGongyi(matchId, userId);
//		model.addAttribute("count", count);
//		model.addAttribute("money", count*2);
//		
//		/**
//		 * 判断登录用户是否报过名
//		 */
//		Integer isSignup = matchOrderBIZ.getUserMatchCount(matchId, loginUserId);
//		if(isSignup > 0){
//			model.addAttribute("isSignup", "1");
//			
//			/**
//			 * 判断是否是自己
//			 */
//			if(userId == loginUserId){
//				model.addAttribute("isMe", "1");
//			}
//		}else{
//			/**
//			 * 判断是否是自己
//			 */
//			if(userId == loginUserId){
//				model.addAttribute("isMe", "1");
//			}else{
//				
//				/**
//				 * 他人用户没报名进来，保存当前邀请用户id
//				 */
//				request.getSession()
//				.setAttribute(VariableHelper.INVITE_SESSION_USER,userId);
//			}
//		}
//		
//		/**
//		 * 微信分享信息
//		 */
//		String ticket = WeChatHelper.getTicket(request);
//		String timestamp = WeChatHelper.getTimeStamp();
//		String nonceStr = WeChatHelper.getNonceStr();
//		String url = "http://wchat.mperfit.com/match/gongyi?user_id="+userId;
//		model.addAttribute("url", url);
//		model.addAttribute("appId", WeChatConfig.APP_ID);
//		model.addAttribute("timestamp", timestamp);
//		model.addAttribute("nonceStr", nonceStr);
//		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
//		String sign = WeChatHelper.sign(params);
//		model.addAttribute("signature", sign);
//		model.addAttribute("userId", userId);
//		return "/match/match_publicGood";
//	}
//	
////	/**
////	 * 赛事用户招募页
////	 * @param model
////	 * @param request
////	 * @return
////	 * @throws IOException 
////	 */
////	@RequestMapping(value = "/user")
////	public String matchUser(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
////		String mId = request.getParameter("match_id");
////		if(!ValidatorHelper.isNumber(mId)){
////			return VariableHelper.ERROR_JSP;		
////		}
////		
////		long matchId = Long.parseLong(mId);
////		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////		
////		Map userMap = userBIZ.getMatchUser(userId, matchId);
////		if(userMap == null 
////				|| !ValidatorHelper.isMapNotEmpty(userMap.get("order_id"))){
////			response.sendRedirect("/match/paoxiaogou");
////			return null;
////		}
////		model.addAttribute("user", userMap);
////		
////		/**
////		 * 赛事信息
////		 */
////		Match match = matchBIZ.getById(matchId);
////		if(match == null){
////			model.addAttribute("msg", "赛事找不到哦！");
////			return VariableHelper.ERROR_JSP;	
////		}
////
////		model.addAttribute("match", match);
////		
////		/**
////		 * 判断当前是否已经有队伍
////		 */
////		if(ValidatorHelper.isMapNotEmpty(userMap.get("match_group_id"))){
////			
////			/**
////			 * 查询自己的队友
////			 */
////			Map groupUserMap = matchGroupBIZ.getGroupUserOther(
////					Long.parseLong(userMap.get("match_group_id").toString()), userId);
////			if(groupUserMap != null){
////				model.addAttribute("isGroup", 1);
////				model.addAttribute("groupUser", groupUserMap);
////			}
////		}
////		
////		
////		/**
////		 * 被邀请的信息
////		 */
////		List inviteList = matchBIZ.queryMatchInviteList(userId, 0, 3);
////		model.addAttribute("invteList", inviteList);
////
////		
////		/**
////		 * 微信分享信息
////		 */
////		String ticket = WeChatHelper.getTicket(request);
////		String timestamp = WeChatHelper.getTimeStamp();
////		String nonceStr = WeChatHelper.getNonceStr();
////		String url = "http://wchat.mperfit.com/match/user?match_id="+matchId;
////		model.addAttribute("appId", WeChatConfig.APP_ID);
////		model.addAttribute("timestamp", timestamp);
////		model.addAttribute("nonceStr", nonceStr);
////		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
////		String sign = WeChatHelper.sign(params);
////		model.addAttribute("signature", sign);
////		return "/match/match_user";
////	}
////	
////	
////	/**
////	 * 赛事匹配邀请用户列表
////	 * @param model
////	 * @param request
////	 * @return
////	 */
////	@RequestMapping(value = "/mate/list")
////	public String mateList(Model model,HttpServletRequest request){
////		String mId = request.getParameter("match_id");
////		if(!ValidatorHelper.isNumber(mId)){
////			return "";
////		}
////		long matchId = Long.parseLong(mId);
////		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////		
////		User user = userBIZ.getById(userId);
////		if(user == null){
////			return VariableHelper.ERROR_JSP;
////		}
////		
////		List userList = matchBIZ.getRecommendUser(userId, matchId, user.getSex(), 3);
////		model.addAttribute("userList", userList);
////		model.addAttribute("matchId", matchId);
////		
////		return "/match/match_mate_list";
////	}
////	
////	
////	
////	
////	/**
////	 * 赛事匹配邀请
////	 * @param model
////	 * @param request
////	 * @return
////	 */
////	@RequestMapping(value = "/mate/invite")
////	@ResponseBody
////	public Object mateInvite(Model model,HttpServletRequest request){
////		String mId = request.getParameter("match_id");
////		String iId = request.getParameter("invite_id");
////		String content = request.getParameter("content");
////		
////		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(iId)){
////			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
////		}
////		
////		try {
////			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////			long matchId = Long.parseLong(mId);
////			long inviteUserId = Long.parseLong(iId);
////			
////			if(userId == inviteUserId){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,"不能邀请自己哦！");
////			}
////			
////			
////			/**
////			 * 判断今天是否邀请过
////			 */
////			boolean inviteResult = matchBIZ.checkIsInvite(userId, inviteUserId, matchId);
////			if(inviteResult){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,"用户今天已经被邀请过了，明天才能继续邀请哦！");
////			}
////			
////			/**
////			 * 判断赛事是否存在
////			 */
////			Match match = matchBIZ.getById(matchId);
////			if(match == null){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
////			}
////			
////			/**
////			 * 判断邀请对象是否已经组队
////			 */
////			Map groupUser = matchGroupBIZ.getMatchGroupUser(matchId, inviteUserId);
////			if(groupUser != null){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,"对方已经有队伍啦！");
////			}
////			
//////			UserWeChat userWechat = userWeChatBIZ.getByUserId(inviteUserId);
//////			if(userWechat == null){
//////				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//////			}
//////			
//////			/**
//////			 * 创建邀请
//////			 */
//////			String openId = userWechat.getOpenId();
//////			String url = "http://wchat.mperfit.com/match/group/mate?match_id="+matchId+"&invite_id="+userId;
////			Boolean result = matchBIZ.inviteGroupUser(userId, inviteUserId, matchId
////					, content);
////			if(result){
////				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
////		}
////		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);	
////	}
////	
//////	/**
//////	 * 赛事分享连接邀请
//////	 * @param model
//////	 * @param request
//////	 * @return
//////	 */
//////	@RequestMapping(value = "/share/invite")
//////	public String shareInvite(Model model,HttpServletRequest request){
//////		return "";
//////	}
////	
////	/**
////	 * 赛事组队页面
////	 * @param model
////	 * @param request
////	 * @return
////	 */
////	@RequestMapping(value = "/group/mate")
////	public String groupMate(Model model,HttpServletRequest request){
////		String mId = request.getParameter("match_id");
////		String iId = request.getParameter("invite_id");		
////		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(iId)){
////			return VariableHelper.ERROR_JSP;
////		}
////		
////		try {
////			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////			long matchId = Long.parseLong(mId);
////			long inviteUserId = Long.parseLong(iId);
////			
////			
////			/**
////			 * 判断邀请对象是否存在 并且对方是否报名
////			 */
////			Map inviteUserMap = userBIZ.getMatchUser(inviteUserId, matchId);
////			if(inviteUserMap == null
////					&& !ValidatorHelper.isMapNotEmpty(inviteUserMap.get("order_id"))){
////				return VariableHelper.ERROR_JSP;
////			}
////			
////			/**
////			 * 当前用户信息
////			 */
////			Map userMap = userBIZ.getMatchUser(userId, matchId);
////			if(userMap == null){
////				return VariableHelper.ERROR_JSP;
////			}
////			
////			/**
////			 * 判断赛事是否存在
////			 */
////			Match match = matchBIZ.getById(matchId);
////			if(match == null){
////				return VariableHelper.ERROR_JSP;
////			}
////			
////			/**
////			 * 如果当前用户没有报名和组队,记录邀请用户session,付款时匹配组队
////			 */
////			if(userId != inviteUserId){
////				if(!ValidatorHelper.isMapNotEmpty(userMap.get("order_id"))
////						|| ValidatorHelper.isMapNotEmpty(userMap.get("group_user_id"))){
////					request.getSession().setAttribute(
////							VariableHelper.INVITE_SESSION_USER, inviteUserId);
////				}
////			}
////			
////			
////			model.addAttribute("user", userMap);
////			model.addAttribute("inviteUser", inviteUserMap);
////			model.addAttribute("match", match);
////			return "/match/match_group_mate";
////			
////		} catch (Exception e) {
////			return VariableHelper.ERROR_JSP;
////		}
////		
////	}
////	
////	/**
////	 * （已经报名的）被邀请的人同意组队，队伍成立
////	 * @param model
////	 * @param request
////	 * @return
////	 */
////	@RequestMapping(value = "/group/add")
////	@ResponseBody
////	public Object groupAdd(Model model,HttpServletRequest request){
////		String mId = request.getParameter("match_id");
////		String iId = request.getParameter("invite_id");
////		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(iId)){
////			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
////		}
////		
////		try {
////			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////			long matchId = Long.parseLong(mId);
////			long inviteUserId = Long.parseLong(iId);
////			
////			if(userId == inviteUserId){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,"亲！不能和自己组队哦！");
////			}
////			
////			/**
////			 * 判断赛事是否存在
////			 */
////			Match match = matchBIZ.getById(matchId);
////			if(match == null){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
////			}
////			
////			/**
////			 * 判断当前用户是否为空，是否报名，是否已经组队
////			 */
////			Map userMap = userBIZ.getMatchUser(userId, matchId);
////			if(userMap == null || !ValidatorHelper.isMapNotEmpty(userMap.get("order_id"))){
////				return MapResult.build(1,"您尚未报名赛事！");
////			}
////			
////			if(ValidatorHelper.isMapNotEmpty(userMap.get("group_user_id"))){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,"你已经有队伍了！");
////			}
////			
////			
////			/**
////			 * 判断邀请用户是否为空，是否报名，是否已经组队
////			 */
////			Map inviteUserMap = userBIZ.getMatchUser(inviteUserId, matchId);
////			if(inviteUserMap == null || !ValidatorHelper.isMapNotEmpty(inviteUserMap.get("order_id"))
////					|| ValidatorHelper.isMapNotEmpty(inviteUserMap.get("group_user_id"))){
////				return MapResult.build(MapResult.CODE_PARAM_ERROR,"邀请用户尚未报名赛事或已和他人组队成功");
////			}
////			
////			/**
////			 * 组队
////			 */
////			Boolean result = matchGroupBIZ.addTeam(matchId, userId, inviteUserId,
////					inviteUserMap.get("open_id").toString());
////			if(result){
////				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
////			}
////			
////		} catch (Exception e) {
////			e.printStackTrace();
////			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
////		}
////		
////		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
////	}
////	
////	/**
////	 * 赛事小组也没
////	 * @param model
////	 * @param request
////	 * @return
////	 */
////	@RequestMapping(value = "/group")
////	public String group(Model model,HttpServletRequest request){
////		String gId = request.getParameter("group_id");
////		if(!ValidatorHelper.isNumber(gId)){
////			return VariableHelper.ERROR_JSP;
////		}
////		
////		try {
////			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
////			long groupId = Long.parseLong(gId);
////			
////			/**
////			 * 查询小组
////			 */
////			
////			
////			return "/match/match_group";
////			
////		} catch (Exception e) {
////			return VariableHelper.ERROR_JSP;
////		}
////		
////	}
//	
//}
