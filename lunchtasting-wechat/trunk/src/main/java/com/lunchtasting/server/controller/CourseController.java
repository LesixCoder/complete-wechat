package com.lunchtasting.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.gym.CourseBIZ;
import com.lunchtasting.server.biz.gym.CourseOrderBIZ;
import com.lunchtasting.server.biz.gym.CourseOrderVoteBIZ;
import com.lunchtasting.server.biz.gym.GymBIZ;
import com.lunchtasting.server.biz.user.UserBIZ;
import com.lunchtasting.server.biz.user.UserWeChatBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.helper.WeChatMessageTemplet;
import com.lunchtasting.server.po.lt.Course;
import com.lunchtasting.server.po.lt.CourseOrder;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.DateUtils;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;


/**
 * 课程 
 * @author xq
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private CourseOrderBIZ courseOrderBIZ;
	@Autowired
	private CourseOrderVoteBIZ courseOrderVoteBIZ;
	@Autowired
	private GymBIZ gymBIZ;
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private UserWeChatBIZ userWeChatBIZ;
	
	/**
	 * 119活动成绩表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/score/119")
	public String courseMark119(Model model, HttpServletRequest request){
		return "/course/course_score_119";
	}
	
	/**
	 * 课程活动和课程详情有区别
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity")
	public String courseActivity(Model model, HttpServletRequest request){
		String cId = request.getParameter("course_id");
		if(!ValidatorHelper.isNumber(cId)){
			return VariableHelper.ERROR2_JSP;
		}
		long courseId = Long.parseLong(cId);
		
		Map courseMap = courseBIZ.getCourseDtail(courseId);
		if(courseMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("course", courseMap);
		
		/**
		 * 课程下的健身房
		 */
		List gymList = gymBIZ.queryCourseGymList(courseId, null, null);
		model.addAttribute("gymList", gymList);	
		
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		model.addAttribute("userId", userId);
		
		
		
		/**
		 * 微信分享信息
		 */
		String ticket = WeChatHelper.getTicket(request);
		String timestamp = WeChatHelper.getTimeStamp();
		String nonceStr = WeChatHelper.getNonceStr();
		String url = "http://wchat.mperfit.com/course/activity?course_id="+courseId;
		model.addAttribute("url", url);
		model.addAttribute("appId", WeChatConfig.APP_ID);
		model.addAttribute("timestamp", timestamp);
		
		model.addAttribute("nonceStr", nonceStr);
		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
		String sign = WeChatHelper.sign(params);
		model.addAttribute("signature", sign);
		
		return "/course/course_detail2";
	}
	
	/**
	 * 课程上课确认
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activity/confirm")
	public String activityConfirm(Model model, HttpServletRequest request){
		String cMId = request.getParameter("cm_id");
		if(!ValidatorHelper.isNumber(cMId)){
			return VariableHelper.ERROR2_JSP;
			
		}
		long courseMealId = Long.parseLong(cMId);
		
		try {
			Map courseMeal = courseBIZ.getCourseMealDetail(courseMealId);
			if(courseMeal == null){
				return VariableHelper.ERROR2_JSP;
			}
			
			/**
			 * 判断上课人数是否已满
			 */
			int peopleNumber = Integer.parseInt(courseMeal.get("people_number").toString());
			int orderCount = Integer.parseInt(courseMeal.get("order_count").toString());
			if(orderCount >= peopleNumber){
				return VariableHelper.ERROR2_JSP;
			}
			
			model.addAttribute("courseMeal", courseMeal);
			return "/course/course_order_confirm2";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return VariableHelper.ERROR2_JSP;
		}
	}
	
	/**
	 * 课程详情
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}")
	public String courseDetail(@PathVariable("id") String id,Model model, HttpServletRequest request){
		if(!ValidatorHelper.isNumber(id)){
			return VariableHelper.ERROR2_JSP;
		}
		long courseId = Long.parseLong(id);
		
		Map courseMap = courseBIZ.getCourseDtail(courseId);
		if(courseMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("course", courseMap);
		
		/**
		 * 课程下的健身房
		 */
		List gymList = gymBIZ.queryCourseGymList(courseId, null, null);
		model.addAttribute("gymList", gymList);
		
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		model.addAttribute("userId", userId);
		
		
		return "/course/course_detail";
	}
	
	
	/**
	 * 课程上课确认
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/up/confirm")
	public String upConfirm(Model model, HttpServletRequest request){
		String cMId = request.getParameter("cm_id");
		if(!ValidatorHelper.isNumber(cMId)){
			return VariableHelper.ERROR2_JSP;
			
		}
		long courseMealId = Long.parseLong(cMId);
		
		try {
			Map courseMeal = courseBIZ.getCourseMealDetail(courseMealId);
			if(courseMeal == null){
				return VariableHelper.ERROR2_JSP;
			}
			
			/**
			 * 判断上课人数是否已满
			 */
			int peopleNumber = Integer.parseInt(courseMeal.get("people_number").toString());
			int orderCount = Integer.parseInt(courseMeal.get("order_count").toString());
			if(orderCount >= peopleNumber){
				return VariableHelper.ERROR2_JSP;
			}
			
			model.addAttribute("courseMeal", courseMeal);
			return "/course/course_order_confirm";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return VariableHelper.ERROR2_JSP;
		}
	}
	
	
	/**
	 * 订单列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order/list")
	public String orderList(Model model, HttpServletRequest request, HttpServletResponse response){
		
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		
		int totalPage = Utils.getTotalPage(courseOrderBIZ.getOrderCount(userId),10);
		List list = courseOrderBIZ.queryOrderList(userId,null,null);
		model.addAttribute("list", list);
		model.addAttribute("totalPage", totalPage);
		return "/course/course_order_list";
	}
	
	/**
	 * 没开课，用户主动退款
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/order/refund")
	@ResponseBody
	public Object orderRefund(Model model, HttpServletRequest request) throws Exception{
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try {
			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			long orderId = Long.parseLong(oId);
			
			/**
			 * 查询退款订单是否存在
			 */
			Map orderMap = courseOrderBIZ.getRefundOrder(orderId, userId);
			if(orderMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
		
			String outTradeNo = orderMap.get("code").toString();
			double payPrice = Double.parseDouble(orderMap.get("pay_price").toString());
			
			/**
			 * 判断是否已经开课，开课的订单不能退款
			 */
			Date beginTime = DateUtil.convertStringTODate(orderMap.get("begin_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
			Date nowTime = new Date();
			if(nowTime.after(beginTime)){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			/**
			 * 计算退款价格
			 * 距离开始超过3天不扣款
			 * 少于3天，扣除30%手续费
			 */
			double refundPrice = payPrice;
			if(DateUtils.caculate2Days(nowTime,beginTime) < 3){
				refundPrice = refundPrice * 0.3;
			}
			
			boolean result = courseOrderBIZ.refundOrder(orderId, userId, payPrice, refundPrice, outTradeNo, 1);
			if(result){
				
				/**
				 * 退款微信通知
				 */
				String openId = userWeChatBIZ.getOpenIdByUserId(userId);
				if(ValidatorHelper.isEmpty(openId)){
					String title = "您好，您购买的课程已经退款成功";
					String reason = "退款原因：主动退款";
					String remark = "备注：如有疑问，请致电18518481875联系我们，或回复M来了解详情。";
					WeChatMessageTemplet.courseOrderRefund(openId, "", 
							title, reason, refundPrice+"", remark);
				}
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
	}
	
	@RequestMapping(value = "/order/detail")
	public String orderDetail(Model model, HttpServletRequest request, HttpServletResponse response){
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return VariableHelper.ERROR2_JSP;
		}
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		long orderId = Long.parseLong(oId);
		
		Map orderMap = courseOrderBIZ.getCourseOrderDetail(userId, orderId);
		if(orderMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("order", orderMap);
		
		return "/course/course_order_detail";
	}
	
	
	/**
	 * 课程订单投票相关列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order_vote/list")
	public String courseOrderVoteList(Model model, HttpServletRequest request, HttpServletResponse response){
		String cmId = request.getParameter("cm_id");
		String page = request.getParameter("page");
		if(ValidatorHelper.isEmpty(cmId)){
			return VariableHelper.ERROR2_JSP;
		}
		long courseMealId = Long.parseLong(cmId);
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		StringBuilder parameter = new StringBuilder();
		parameter.append("cm_id="+cmId);
		
		int pPage = 1;
		if(ValidatorHelper.isNumber(page)){
			pPage = Integer.parseInt(page);
			if(pPage < 1){
				pPage = 1;
			}
		}
		
		/**
		 * 总数页
		 */
		int totalPage = Utils.getTotalPage(courseOrderVoteBIZ.getOrderVoteCount(courseMealId),10);
		int previousPage = pPage - 1;
		if(previousPage <= 0){
			previousPage = 1;
		}
		
		int nextPage = pPage + 1;
		if(nextPage >= totalPage){
			nextPage = totalPage;
		}
		model.addAttribute("totalPage", totalPage);
		
		/**
		 * 判断是否已经投过票了
		 */
		boolean checkVote = courseOrderVoteBIZ.checkVote(courseMealId, userId);
		if(checkVote){
			model.addAttribute("is_vote", 1);
		}else{
			model.addAttribute("is_vote", 0);
		}
		
		
		/**
		 * 查询课程套餐的报名列表
		 */
		List list = courseOrderVoteBIZ.queryOrderVoteList(courseMealId, (pPage-1)*20, 20);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", pPage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("previousPage", previousPage);
		model.addAttribute("cmId", cmId);
		model.addAttribute("parameter", parameter.toString());
		
		
		return "/course/course_order_vote_list";
	}

	/**
	 * 课程投票相关列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/vote/list")
	public String courseVoteList(Model model, HttpServletRequest request, HttpServletResponse response){
		String oId = request.getParameter("order_id");
		String dUserId = request.getParameter("user_id");
		String page = request.getParameter("page");
		if(!ValidatorHelper.isNumber(oId) || !ValidatorHelper.isNumber(dUserId)){
			return VariableHelper.ERROR2_JSP;
		}
		long orderId = Long.parseLong(oId);
		long desUserId = Long.parseLong(dUserId);
		
		List list = courseOrderVoteBIZ.queryUserVoteList(orderId, desUserId, null, null);
		model.addAttribute("list", list);
		
		return "/course/course_vote_list";
	}
	
	
	/**
	 * 课程订单投票
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vote/add")
	@ResponseBody
	public Object courseVoteAdd(Model model, HttpServletRequest request) throws Exception{
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		
		try {
			long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
			long orderId = Long.parseLong(oId);
			
			/**
			 * 判断当前投票的订单用户是否已经付款
			 */
			CourseOrder order = courseOrderBIZ.getById(orderId);
			if(order == null || order.getStatus().intValue() == 1 || 
					order.getStatus().intValue() == 3){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			/**
			 * 判断是否已经投过票
			 */
			boolean checkVote = courseOrderVoteBIZ.checkVote(order.getCourseMealId(), userId);
			if(checkVote){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"亲！已经投过票了！");
			}
			
			/**
			 * 自己不能给自己投票
			 */
			if(userId == order.getUserId().longValue()){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,"亲！不能给自己投票哦！");
			}
			
			
			User user = userBIZ.getById(userId);
			if(user == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			Map map = new HashMap();
			map.put("user_id", user.getId());
			map.put("user_img_url", VariableHelper.IMAGE_VISIT_URL+user.getImgUrl()
					+ QiNiuStorageHelper.MODEL0+"w/200/h/200");
			
			courseOrderVoteBIZ.createCourseOrderVote(userId, order.getUserId(), orderId,order.getCourseMealId());
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
	}
	
	/**
	 * 投票详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/vote/detail")
	public String courseVoteDetail(Model model, HttpServletRequest request, HttpServletResponse response){
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return VariableHelper.ERROR2_JSP;
		}
		
		long orderId = Long.parseLong(oId);
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		Map voteMap = courseOrderVoteBIZ.getCourseOrderVoteDetail(orderId);
		if(voteMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		long courseMealId = Long.parseLong(voteMap.get("course_meal_id").toString());
		model.addAttribute("vote", voteMap);
		
		
		/**
		 * 判断是否已经投过票了
		 */
		boolean checkVote = courseOrderVoteBIZ.checkVote(courseMealId, userId);
		if(checkVote){
			model.addAttribute("is_vote", 1);
		}else{
			model.addAttribute("is_vote", 0);
		}
		
		return "/course/course_vote_detail";
	}
	
	/**
	 * 体验课活动
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/experience")
	public String courseExperience(Model model, HttpServletRequest request, HttpServletResponse response){
		
		
		return "";
	}
	
//	/**
//	 * 课程活动
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/activity")
//	public String courseActivity(Model model, HttpServletRequest request, HttpServletResponse response){
//		
//		return "";
//	}
//	
//	/**
//	 * 课程活动报名
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/activity/query")
//	@ResponseBody
//	public Object orderQuery(Model model, HttpServletRequest request) throws Exception{
//		String phone = request.getParameter("phone");
//		String cmId = request.getParameter("cm_id");
//		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(cmId)){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		long courseMealId = Long.parseLong(cmId);
//		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		Map courseMealMap = courseBIZ.getCourseMealDetail(courseMealId);
//		if(courseMealMap == null){
//			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
//		}
//		long courseId = Long.parseLong(courseMealMap.get("course_id").toString());
//		long gymId = Long.parseLong(courseMealMap.get("gym_id").toString());
//		long coachId = Long.parseLong(courseMealMap.get("coach_id").toString());
//		double price = Double.parseDouble(courseMealMap.get("price").toString());
//		String orderNo = WeChatHelper.getOrderNo();
//		
//		courseOrderBIZ.createActivityOrder(userId, courseId, courseMealId, gymId, price, orderNo,phone);
//		return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
//	}
	
	
//	/**
//	 * 训练营第一期课程
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/xly/first")
//	public String xly1(Model model, HttpServletRequest request){
//		
//		/**
//		 * 查询训练营第一期课程可以上课的健身房列表
//		 */
//		List list = gymBIZ.queryGymList();
//		model.addAttribute("list", list);
//		
//		return "/gym/xly_first";
//	}
	
//	
//	/**
//	 * 课程列表
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "")
//	public String courseList(Model model, HttpServletRequest request){
//		
//		List list = courseBIZ.queryCourseList(null, null, null, null);
//		model.addAttribute("list", list);
//		
//		return "/gym/course_list";
//	}
//	

//	
//	/**
//	 * 课程确认支付
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/pay_confirm")
//	public String coursePayConfrim(Model model, HttpServletRequest request){
//		String cId = request.getParameter("course_id");
//		if(!ValidatorHelper.isNumber(cId)){
//			return VariableHelper.ERROR2_JSP;
//		}
//		long courseId = Long.parseLong(cId);
//		long userId = (Long)request.getSession()
//				.getAttribute(VariableHelper.LOGIN_SESSION_USER);
//		
//		Map courseMap = courseBIZ.getCourseDtail(courseId);
//		if(courseMap == null){
//			return VariableHelper.ERROR2_JSP;
//		}
//		model.addAttribute("course", courseMap);
//		
//		/**
//		 *  查看当前报名人数
//		 */
//		
//		
//		return "/gym/course_pay_confirm";
//	} 
	
//	
//	/**
//	 * 折扣退款
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/order/discount_refund")
//	@ResponseBody
//	public Object orderDiscountRefund(Model model, HttpServletRequest request){
//		return 1;
//	}
	
}
