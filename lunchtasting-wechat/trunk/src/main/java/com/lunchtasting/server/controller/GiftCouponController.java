package com.lunchtasting.server.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.gift.GiftCouponBIZ;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/gift_coupon")
public class GiftCouponController {

	@Autowired
	private GiftCouponBIZ giftCouponBIZ;
	
	/**
	 * 批量产生礼品劵码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/batch")
	@ResponseBody
	public Object batch(HttpServletRequest request, HttpServletResponse response){
		long gcId = 1L;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		for(int i = 0; i < 10;i++){
			String code = Utils.getRandomNumber(16);
			giftCouponBIZ.createGiftCouponCode(gcId, code);
			System.out.println(code);
		}
	
		
		return 100;
	}
	
	/**
	 * 礼品优惠劵列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value =  "/list")
	public String giftCouponList(Model model, HttpServletRequest request) throws IOException{
		
		List list = giftCouponBIZ.queryGiftCouponList(null, null);
		model.addAttribute("list", list);
		
		return "/gift/gift_coupon_list";
	}
	
	/**
	 * 礼品优惠劵详情
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value =  "/detail")
	public String giftCouponDetail(Model model, HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		if(!ValidatorHelper.isNumber(id)){
			return VariableHelper.ERROR2_JSP;
		}
		long gcId = Long.parseLong(id);
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);

		
		Map giftCouponMap = giftCouponBIZ.getGiftCouponDetail(gcId);
		if(giftCouponMap == null){
			return VariableHelper.ERROR2_JSP;
		}
		model.addAttribute("giftCoupon", giftCouponMap);
		
		/**
		 * 判断是否可以兑换
		 */
		Map codeMap = giftCouponBIZ.getUserCode(gcId, userId);
		if(codeMap != null){
			model.addAttribute("isCode", "1");
		}
		int status = Integer.parseInt(giftCouponMap.get("status").toString());
		if(status == 2){
			model.addAttribute("isCode", "1");
		}
		
		return "/gift/gift_coupon_detail";
	}
	
	/**
	 * 礼品劵兑换
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doExchange")
	@ResponseBody
	public Object doExchange(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		if(!ValidatorHelper.isNumber(id)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		long gcId = Long.parseLong(id);
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		Map giftCouponMap = giftCouponBIZ.getGiftCouponDetail(gcId);
		if(giftCouponMap == null){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		int status = Integer.parseInt(giftCouponMap.get("status").toString());
		
		/**
		 * 卡劵已失效（兑换数已满/已过有效期）
		 */
		if(status == 2){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		/**
		 * 判断用户是否已经兑换过当前礼品劵
		 */
		Map codeMap = giftCouponBIZ.getUserCode(gcId, userId);
		if(codeMap != null){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		boolean result = giftCouponBIZ.exchangeGiftCoupon(gcId, userId);
		if(result){
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
		}
		
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);

	}
	
	/**
	 * 已兑换的礼物劵页面
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value =  "/exchange/list")
	public String giftCouponExchangeList(Model model, HttpServletRequest request) throws IOException{
		
		long userId = (Long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		List list = giftCouponBIZ.queryUserGiftCouponCodeList(userId, null, null);
		model.addAttribute("list", list);
		
		return "/gift/gift_coupon_exchange";
	}
	
}
