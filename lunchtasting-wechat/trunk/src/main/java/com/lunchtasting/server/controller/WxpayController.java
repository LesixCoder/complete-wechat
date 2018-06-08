package com.lunchtasting.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.lunchtasting.server.biz.goods.GoodsBIZ;
import com.lunchtasting.server.biz.goods.GoodsOrderBIZ;
import com.lunchtasting.server.biz.gym.CourseBIZ;
import com.lunchtasting.server.biz.gym.CourseOrderBIZ;
import com.lunchtasting.server.biz.match.MatchAlbumBIZ;
import com.lunchtasting.server.biz.match.MatchAlbumImageOrderBIZ;
import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.biz.match.MatchGoodsBIZ;
import com.lunchtasting.server.biz.match.MatchOrderBIZ;
import com.lunchtasting.server.biz.match.MatchTicketBIZ;
import com.lunchtasting.server.biz.user.UserWeChatBIZ;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.po.lt.MatchTicket;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.util.ValidatorHelper;

/**
 * 微信支付
 * Created on 2017-7-14
 * @author xuqian
 *
 */
@Controller
@RequestMapping("/wxpay")
public class WxpayController {
	
	@Autowired
	private UserWeChatBIZ userWeChatBIZ;
	@Autowired
	private MatchOrderBIZ matchOrderBIZ;
	@Autowired
	private GoodsBIZ goodsBIZ;
	@Autowired
	private GoodsOrderBIZ goodsOrderBIZ;
	@Autowired
	private MatchAlbumBIZ matchAlbumBIZ;
	@Autowired
	private MatchAlbumImageOrderBIZ imageOrderBIZ;
	@Autowired
	private MatchBIZ matchBIZ;
	@Autowired
	private MatchTicketBIZ matchTicketBIZ;
	@Autowired
	private MatchGoodsBIZ matchGoodsBIZ;
	@Autowired
	private CourseBIZ courseBIZ;
	@Autowired
	private CourseOrderBIZ courseOrderBIZ;
	
	/**
	 * 课程支付
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/course")
	public String coursePay(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String cmId = request.getParameter("cm_id");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		if(!ValidatorHelper.isNumber(cmId)){
			return VariableHelper.ERROR2_JSP;
		}
		//long userId = 905306223132278784L;
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		long courseMealId = Long.parseLong(cmId);
		Integer intSex =  null;
		if(ValidatorHelper.isNumber(sex)){
			intSex = Integer.parseInt(sex);
		}
		
		try {
			/**
			 * 获得微信授权信息
			 */
			UserWeChat wechat = userWeChatBIZ.getByUserId(userId);
			if(wechat == null){ 
				return VariableHelper.ERROR2_JSP;
			}
			
			/**
			 * 课程套餐
			 */
			Map courseMealMap = courseBIZ.getCourseMealDetail(courseMealId);
			if(courseMealMap == null){
				return VariableHelper.ERROR2_JSP;
			}
			
			/**
			 * 判断报名人数是否超标
			 */
			int orderCount = Integer.parseInt(courseMealMap.get("order_count").toString());
			int peoplenNumber = Integer.parseInt(courseMealMap.get("people_number").toString());
			if(orderCount >= peoplenNumber){
				return VariableHelper.ERROR2_JSP;
			}
			
			/**
			 * 生成订单
			 */
			String openId = wechat.getOpenId();
			String orderNo = WeChatHelper.getOrderNo();
			String body = courseMealMap.get("course_name").toString();
			String nonceStr = WeChatHelper.getNonceStr();
			double price = Double.parseDouble(courseMealMap.get("price").toString());
			long gymId = Long.parseLong(courseMealMap.get("gym_id").toString());
			long courseId = Long.parseLong(courseMealMap.get("course_id").toString());
			
			/**
			 * 价格为0的话，状态直接为已支付
			 */
			int status = 1;
			if(price == 0){
				status = 2;
			}
			
			Long orderId = courseOrderBIZ.createOrder(userId, courseId, courseMealId, gymId, price
					, orderNo,phone,intSex,status);
			if(orderId == null){ 
				return VariableHelper.ERROR2_JSP;
			}
			
			/**
			 * 如果金额为0 不需要微信支付 直接跳转订单列表页面
			 */
			if(price == 0){
				response.sendRedirect("/course/order/list");
				return null;
			}
			
			/**
			 * 支付签名,生成预支付订单
			 */
			Map attachMap = new HashMap();
			attachMap.put("orderId", orderId);
			String attach = JSONObject.toJSONString(attachMap);
			
			SortedMap params = WeChatHelper.getSignMap(openId,orderNo,price,body,nonceStr,WeChatConfig.NOFIFY_COURSE_URL,attach,request);
			String prepayId = WeChatHelper.getPrepayId(params);
			if(ValidatorHelper.isEmpty(prepayId)){
				return VariableHelper.ERROR2_JSP;
			}
			
			SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
			String paySign = WeChatHelper.createSign(paySignMap);
			model.addAttribute("appId", paySignMap.get("appId"));
			model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
			model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
			model.addAttribute("package", paySignMap.get("package"));
			model.addAttribute("paySign", paySign);
			model.addAttribute("orderId", orderId);
			model.addAttribute("courseId", courseId);
			return "/course/course_order_pay";
		} catch (Exception e) {
			e.printStackTrace();
			return VariableHelper.ERROR2_JSP;
		}
	}
	
	/**
	 * 课程报名订单微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/course/notify")
	public void courseNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			
			System.out.println("wxpay course notify begin !!!!!!!!!11");
		
			String strxml = WeChatHelper.getResponseXmlString(request, response);
			Map map = WeChatHelper.doXMLParse(strxml);
			SortedMap parameters = new TreeMap();
			parameters.putAll(map);
			
			System.out.println("wxpay course notify map = " + map);
			
			if(!map.get("return_code").toString().equalsIgnoreCase("SUCCESS")
					|| !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			
			if(!WeChatHelper.createSign(parameters).equals(map.get("sign").toString())){
				response.getWriter().write(WeChatHelper.printXML("FAIL", "")); 
				return;
			}
			
			Map attachMap = (Map)JSON.parseObject(map.get("attach").toString());
			String oId = attachMap.get("orderId").toString();
			if(!ValidatorHelper.isNumber(oId)){
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			System.out.println("wxpay course notify attachMap = " + attachMap);
			Long orderId = Long.parseLong(oId);
			
			if(courseOrderBIZ.checkOrderPay(orderId) == false){
				courseOrderBIZ.updateOrderPay(orderId);
				response.getWriter().write(WeChatHelper.printXML("SUCCESS", ""));
				System.out.println("wxpay course notify end !!!!!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		}
	}
	
	/**
	 * 赛事支付
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/match")
	public String matchPay(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String mId = request.getParameter("match_id");
		String tId = request.getParameter("ticket_id");
		String number = request.getParameter("number");
		String goodsStr = request.getParameter("goods_str");
		String signupStr = request.getParameter("signup_str");
		if(!ValidatorHelper.isNumber(mId) || !ValidatorHelper.isNumber(tId)
				|| ValidatorHelper.isEmpty(signupStr)){
			return VariableHelper.ERROR_JSP;
		}
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		long matchId = Long.parseLong(mId);
		long ticketId = Long.parseLong(tId);
		
		try {
			
			/**
			 * 获得微信授权信息
			 */
			UserWeChat wechat = userWeChatBIZ.getByUserId(userId);
			if(wechat == null){ 
				return VariableHelper.ERROR_JSP;
			}
			String openId = wechat.getOpenId();
		
			/**
			 * 判断赛事是否存在
			 */
			Match match = matchBIZ.getById(matchId);
			if(match == null){
				return VariableHelper.ERROR_JSP;
			}
			
			/**
			 * 判断票是否存在
			 */
			MatchTicket ticket = matchTicketBIZ.getById(ticketId);
			if(ticket == null){
				return VariableHelper.ERROR_JSP;
			}
			
			if(match.getId().longValue() != ticket.getMatchId().longValue()){
				return VariableHelper.ERROR_JSP;
			}	
			
			/**
			 * 票价格
			 * 1普通票 2亲子票 有早鸟票
			 * 3团体票 有折扣  5人上9人下 8折   9人上7折
			 */
			double ticketPrice = ticket.getPrice();
			int ticketKind = ticket.getKind();
			if(ticketKind == 1 || ticketKind == 2){
				if(ValidatorHelper.isNotEmpty(ticket.getEarlyBirdPrice())){
					ticket.getEarlyBirdTime().after(new Date());
					ticketPrice = ticket.getEarlyBirdPrice();
				}
			}else{
				int ticketNumber = 1;
				if(ValidatorHelper.isNumber(number)){
					ticketNumber = Integer.parseInt(number);
				}
				ticketPrice = ticketPrice * ticketNumber;
				if(ticketNumber > 5 && ticketNumber < 9){
					ticketPrice = ticketPrice * 0.8;
				}
				if(ticketNumber > 9){
					ticketPrice = ticketPrice * 0.7;
				}
			}
			
			/**
			 * 商品价格
			 */
			double goodsPrice = 0;
			if(ValidatorHelper.isNotEmpty(goodsStr)){
				goodsPrice = matchGoodsBIZ.getUserSelectGoodsPrice(ticketId,goodsStr);
			}
			
			/**
			 * 订单信息
			 */
			String orderNo = WeChatHelper.getOrderNo();
			String body = ticket.getName();
			String nonceStr = WeChatHelper.getNonceStr();
			double price = ticketPrice + goodsPrice;
			
			/**
			 * 生成订单
			 */
			Long orderId = matchOrderBIZ.createMatchOrder(userId, matchId, ticketId, price, ticketPrice, goodsPrice, signupStr, goodsStr);
			if(orderId == null){ 
				return VariableHelper.ERROR_JSP;
			}
			
			
			/**
			 * 支付签名,生成预支付订单
			 */
			Map attachMap = new HashMap();
			attachMap.put("orderId", orderId);
			String attach = JSONObject.toJSONString(attachMap);
			
			SortedMap params = WeChatHelper.getSignMap(openId,orderNo,price,body,nonceStr,WeChatConfig.NOFIFY_MATCH_URL,attach,request);
			String prepayId = WeChatHelper.getPrepayId(params);
			if(ValidatorHelper.isEmpty(prepayId)){
				return VariableHelper.ERROR_JSP;
			}
			
			SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
			String paySign = WeChatHelper.createSign(paySignMap);
			model.addAttribute("appId", paySignMap.get("appId"));
			model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
			model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
			model.addAttribute("package", paySignMap.get("package"));
			model.addAttribute("paySign", paySign);
			model.addAttribute("orderId", orderId);
			model.addAttribute("matchId", matchId);
			return "/match2/match_order_pay";
		} catch (Exception e) {
			e.printStackTrace();
			return VariableHelper.ERROR_JSP;
		}
	}
	
	/**
	 * 赛事报名订单微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/match/notify")
	public void matchNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			
			System.out.println("wxpay match notify begin !!!!!!!!!11");
		
			String strxml = WeChatHelper.getResponseXmlString(request, response);
			Map map = WeChatHelper.doXMLParse(strxml);
			SortedMap parameters = new TreeMap();
			parameters.putAll(map);
			
			System.out.println("wxpay match notify map = " + map);
			
			if(!map.get("return_code").toString().equalsIgnoreCase("SUCCESS")
					|| !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			
			if(!WeChatHelper.createSign(parameters).equals(map.get("sign").toString())){
				response.getWriter().write(WeChatHelper.printXML("FAIL", "")); 
				return;
			}
			
			Map attachMap = (Map)JSON.parseObject(map.get("attach").toString());
			String oId = attachMap.get("orderId").toString();
			if(!ValidatorHelper.isNumber(oId)){
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			System.out.println("wxpay match notify attachMap = " + attachMap);
			Long orderId = Long.parseLong(oId);
			
			if(matchOrderBIZ.checkOrderPay(orderId) == false){
				matchOrderBIZ.updateOrderPay(orderId);
				response.getWriter().write(WeChatHelper.printXML("SUCCESS", ""));
				System.out.println("wxpay match notify end !!!!!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		}
	}
	
	
	/**
	 * 商品支付（订单未生成）
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goods_model")
	public String goodsModel(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String gsId = request.getParameter("gs_id");
		String aId = request.getParameter("address_id");
		String remark = request.getParameter("remark");
		String num = request.getParameter("number");
		
		if(!ValidatorHelper.isNumber(gsId) || !ValidatorHelper.isNumber(aId)
				|| !ValidatorHelper.isNumber(num)){
			return VariableHelper.ERROR_JSP;
		}
		long goodsSkuId = Long.parseLong(gsId);
		long addressId = Long.parseLong(aId);
		int number = Integer.parseInt(num);
		long userId = (Long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);

		
		try {
			
			/**
			 * 付款商品详情
			 */
			Map goodsMap = goodsBIZ.getGoodsPayMap(goodsSkuId);
			if(goodsMap == null){
				return VariableHelper.ERROR_JSP;
			}
			
			/**
			 * 购买数量最小1最大99
			 */
			if(number <= 0){
				number = 1;
			}
			if(number > 99){
				number = 99;
			}
			
			
			/**
			 * 订单信息
			 */
			String orderNo = WeChatHelper.getOrderNo();
			//String body = goodsMap.get("goods_name").toString();
			String body = "商城商品购买";
			String nonceStr = WeChatHelper.getNonceStr();
			double price = Double.parseDouble(goodsMap.get("price").toString());
			
			/**
			 * 生成订单
			 */
			Long orderId = goodsOrderBIZ.createGoodsOrder(userId, addressId, goodsSkuId,price, orderNo,remark);
			if(orderId == null){
				return VariableHelper.ERROR_JSP;
			}
			
			/**
			 * 获得微信授权信息
			 */
			UserWeChat wechat = userWeChatBIZ.getByUserId(userId);
			if(wechat == null){
				return VariableHelper.ERROR_JSP;
			}
			String openId = wechat.getOpenId();
			
			
			/**
			 * 支付签名,生成预支付订单
			 */
			Map attachMap = new HashMap();
			attachMap.put("orderId", orderId);
			String attach = JSONObject.toJSONString(attachMap);
			
			SortedMap params = WeChatHelper.getSignMap(openId,orderNo,price*number,body,nonceStr,WeChatConfig.NOFIFY_GOODS_URL,attach,request);
			String prepayId = WeChatHelper.getPrepayId(params);
			if(ValidatorHelper.isEmpty(prepayId)){
				return VariableHelper.ERROR_JSP;
			}
			
			SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
			String paySign = WeChatHelper.createSign(paySignMap);
			model.addAttribute("appId", paySignMap.get("appId"));
			model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
			model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
			model.addAttribute("package", paySignMap.get("package"));
			model.addAttribute("paySign", paySign);
			model.addAttribute("orderId", orderId);
			model.addAttribute("model", "1");
			return "/goods/order_pay";
		} catch (Exception e) {
			e.printStackTrace();
			return VariableHelper.ERROR_JSP;
		}
	}
	
	/**
	 * 商品支付（订单已生成）
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goods_model2")
	public String goodsModel2(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return VariableHelper.ERROR_JSP;
		}
		long orderId = Long.parseLong(oId);
		long userId = (Long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		try {
			
			/**
			 * 订单信息
			 */
			Map orderMap = goodsOrderBIZ.getOrderPayMap(orderId);
			if(orderMap == null){
				return VariableHelper.ERROR_JSP;
			}
			long orderUserId = Long.parseLong(orderMap.get("user_id").toString());
			if(userId != orderUserId){
				return VariableHelper.ERROR_JSP;
			}
			
			
			String orderNo = orderMap.get("code").toString();
			String body = "商城商品购买";
			String nonceStr = WeChatHelper.getNonceStr();
			double price = Double.parseDouble(orderMap.get("price").toString());
			
			/**
			 * 获得微信授权信息
			 */
			UserWeChat wechat = userWeChatBIZ.getByUserId(userId);
			if(wechat == null){
				return VariableHelper.ERROR_JSP;
			}
			String openId = wechat.getOpenId();
			
			
			/**
			 * 支付签名,生成预支付订单
			 */
			Map attachMap = new HashMap();
			attachMap.put("orderId", orderId);
			String attach = JSONObject.toJSONString(attachMap);
			
			SortedMap params = WeChatHelper.getSignMap(openId,orderNo,price,body,nonceStr,WeChatConfig.NOFIFY_GOODS_URL,attach,request);
			String prepayId = WeChatHelper.getPrepayId(params);
			if(ValidatorHelper.isEmpty(prepayId)){
				return VariableHelper.ERROR_JSP;
			}
			
			SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
			String paySign = WeChatHelper.createSign(paySignMap);
			model.addAttribute("appId", paySignMap.get("appId"));
			model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
			model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
			model.addAttribute("package", paySignMap.get("package"));
			model.addAttribute("paySign", paySign);
			model.addAttribute("orderId", orderId);
			model.addAttribute("model", "2");
			return "/goods/order_pay";
		} catch (Exception e) {
			e.printStackTrace();
			return VariableHelper.ERROR_JSP;
		}
	}
	
	
	/**
	 * 商品微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/goods/notify")
	public void goodsNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			System.out.println("wxpay goods notify begin !!!!!!!!!");
			/**
			 * 获得解析xml
			 */
			String strxml = WeChatHelper.getResponseXmlString(request, response);
			Map map = WeChatHelper.doXMLParse(strxml);
			SortedMap parameters = new TreeMap();
			parameters.putAll(map);
			
			System.out.println("wxpay notify map = " + map);
			
			/**
			 * 判断是否成功
			 */
			if(!map.get("return_code").toString().equalsIgnoreCase("SUCCESS")
					|| !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
				//失败
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			
			/**
			 * 判断签名是否正确
			 */
			if(!WeChatHelper.createSign(parameters).equals(map.get("sign").toString())){
				response.getWriter().write(WeChatHelper.printXML("FAIL", "")); 
				return;
			}
			
			/**
			 * 获取订单id
			 */
			Map attachMap = (Map)JSON.parseObject(map.get("attach").toString());
			System.out.println("wxpay goods notify attachMap = " + attachMap);
			
			String oId = attachMap.get("orderId").toString();
			if(!ValidatorHelper.isNumber(oId)){
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			Long orderId = Long.parseLong(oId);
			
			/**
			 * 防止异步多次通知判断
			 */
			if(goodsOrderBIZ.checkOrderPay(orderId) == false){
				boolean result = goodsOrderBIZ.updateOrderPay(orderId);
				if(result){
					response.getWriter().write(WeChatHelper.printXML("SUCCESS", ""));
					System.out.println("wxpay goods notify end !!!!!!!");
				}
			}
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		}
	}
	
	
	/**
	 * 商品支付（订单已生成）
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/album_image")
	public String albumImage(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String iId = request.getParameter("image_id");
		String price = request.getParameter("price");
		
		if(!ValidatorHelper.isNumber(iId) || ValidatorHelper.isEmpty(price)){
			return VariableHelper.ERROR_JSP;
		}
		long imageId = Long.parseLong(iId);
		long userId = (Long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		double dPrice = Double.parseDouble(price);
		
		try {
			
			/**
			 * 图片详情
			 */
			Map imageMap = matchAlbumBIZ.getAlbumImageMap(imageId);
			if(imageMap == null){
				return VariableHelper.ERROR_JSP;
			}
			
			/**
			 * 获得微信授权信息
			 */
			UserWeChat wechat = userWeChatBIZ.getByUserId(userId);
			if(wechat == null){
				return VariableHelper.ERROR_JSP;
			}
			String openId = wechat.getOpenId();	
			
			
			String orderNo = WeChatHelper.getOrderNo();
			String body = "照片打赏";
			String nonceStr = WeChatHelper.getNonceStr();
			
			/**
			 * 生成订单
			 */
			Long orderId = imageOrderBIZ.createImageOrder(userId, imageId, dPrice, orderNo);
			if(orderId == null){
				return VariableHelper.ERROR_JSP;
			}
			
			/**
			 * 支付签名,生成预支付订单
			 */
			Map attachMap = new HashMap();
			attachMap.put("orderId", orderId);
			String attach = JSONObject.toJSONString(attachMap);
			
			SortedMap params = WeChatHelper.getSignMap(openId,orderNo,dPrice,body,nonceStr,WeChatConfig.NOFIFY_ALBUM_IMAGE_URL,attach,request);
			String prepayId = WeChatHelper.getPrepayId(params);
			if(ValidatorHelper.isEmpty(prepayId)){
				return VariableHelper.ERROR_JSP;
			}
			
			SortedMap paySignMap = getPaySignMap(prepayId, nonceStr);
			String paySign = WeChatHelper.createSign(paySignMap);
			model.addAttribute("appId", paySignMap.get("appId"));
			model.addAttribute("timeStamp", paySignMap.get("timeStamp"));
			model.addAttribute("nonceStr", paySignMap.get("nonceStr"));
			model.addAttribute("package", paySignMap.get("package"));
			model.addAttribute("paySign", paySign);
			model.addAttribute("orderId", orderId);
			model.addAttribute("imageId", imageId);
			return "/match/match_album_image_order_pay";
		} catch (Exception e) {
			e.printStackTrace();
			return VariableHelper.ERROR_JSP;
		}
	}
	
	/**
	 * 商品微信支付回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/album_image/notify")
	public void albumImageNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			System.out.println("wxpay album_image notify begin !!!!!!!!!");
			/**
			 * 获得解析xml
			 */
			String strxml = WeChatHelper.getResponseXmlString(request, response);
			Map map = WeChatHelper.doXMLParse(strxml);
			SortedMap parameters = new TreeMap();
			parameters.putAll(map);
			
			System.out.println("wxpay album_image notify map = " + map);
			
			/**
			 * 判断是否成功
			 */
			if(!map.get("return_code").toString().equalsIgnoreCase("SUCCESS")
					|| !map.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
				//失败
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			
			/**
			 * 判断签名是否正确
			 */
			if(!WeChatHelper.createSign(parameters).equals(map.get("sign").toString())){
				response.getWriter().write(WeChatHelper.printXML("FAIL", "")); 
				return;
			}
			
			/**
			 * 获取订单id
			 */
			Map attachMap = (Map)JSON.parseObject(map.get("attach").toString());
			System.out.println("wxpay album_image notify attachMap = " + attachMap);
			
			String oId = attachMap.get("orderId").toString();
			if(!ValidatorHelper.isNumber(oId)){
				response.getWriter().write(WeChatHelper.printXML("FAIL", ""));  
				return;
			}
			Long orderId = Long.parseLong(oId);
			
			/**
			 * 防止异步多次通知判断
			 */
			if(imageOrderBIZ.checkOrderPay(orderId) == false){
				boolean result = imageOrderBIZ.updateOrderPay(orderId);
				if(result){
					response.getWriter().write(WeChatHelper.printXML("SUCCESS", ""));
					System.out.println("wxpay album_image notify end !!!!!!!");
				}
			}
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(WeChatHelper.printXML("FAIL", ""));
		}
	}
	
	
	/**
	 * 获得统一下单签名
	 * @param prepayId
	 * @param nonceStr
	 * @return
	 */
	private SortedMap getPaySignMap(String prepayId,String nonceStr){
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appId", WeChatConfig.APP_ID);
		parameters.put("timeStamp", System.currentTimeMillis() / 1000);
		parameters.put("nonceStr", nonceStr);
		parameters.put("package", "prepay_id="+prepayId);
		parameters.put("signType", "MD5");
		return parameters;
	}
	
}
