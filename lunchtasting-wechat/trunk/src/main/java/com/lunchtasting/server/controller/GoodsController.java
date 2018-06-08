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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.biz.goods.GoodsBIZ;
import com.lunchtasting.server.biz.goods.GoodsOrderBIZ;
import com.lunchtasting.server.biz.user.UserAddressBIZ;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.MapResult;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatConfig;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.UserAddress;
import com.lunchtasting.server.po.lt.goods.GoodsOrder;
import com.lunchtasting.server.util.Utils;
import com.lunchtasting.server.util.ValidatorHelper;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsBIZ goodsBIZ;
	@Autowired
	private GoodsOrderBIZ goodsOrderBIZ;
	@Autowired
	private UserAddressBIZ addressBIZ;
	
	/**
	 * 商品首页
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "")
	public String goodsIndex(Model model, HttpServletRequest request) throws IOException{
		
		List list = goodsBIZ.queryGoodsList(0,2);
		model.addAttribute("list", list);
		
		return "/goods/goods_index";
	}
	
	/**
	 * 商品列表（分页）
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/listMore")
	@ResponseBody
	public Object goodsListMore(Model model, HttpServletRequest request) throws IOException{
		String page = request.getParameter("page");
		
		int intPage = 1;
		if(ValidatorHelper.isNumber(page)){
			intPage = Integer.parseInt(page);
		}
		
		try {
			
			int totalPage = Utils.getTotalPage(goodsBIZ.getGoodsCount(),2);
			if(intPage > totalPage){
				intPage = totalPage;
			}
			
			List list = goodsBIZ.queryGoodsList((intPage-1)*2, 2);
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
	
	/**
	 * 商品详情
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */ 
	@RequestMapping(value =  "/detail")
	public String goodsDetail(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException{
		String gId = request.getParameter("goods_id");
		if(!ValidatorHelper.isNumber(gId)){
			response.sendRedirect("/goods");
			return null;		
		}
		long goodsId = Long.parseLong(gId);
		
		/**
		 * 商品详情
		 */
		Map goodsMap = goodsBIZ.getGoodsDetail(goodsId);
		if(goodsMap == null){
			response.sendRedirect("/goods");
			return null;
		}
		model.addAttribute("goods", goodsMap);
		
		/**
		 * 商品规格属性
		 */
		List guigeList = goodsBIZ.queryGoodsPropertyList(goodsId);
		model.addAttribute("guigeList",guigeList);
		
		
		/**
		 * 商品SKU属性
		 */
		String propertyStr = goodsBIZ.getGoodsSkuPropertStr(goodsId);
		model.addAttribute("propertyStr", propertyStr);
		if(ValidatorHelper.isNotEmpty(propertyStr)){
			model.addAttribute("propertyStr", propertyStr);
			
			/**
			 * 商品sku属性列表
			 */
			List propertyList = goodsBIZ.queryGoodsSkuPropertyList(goodsId);
			model.addAttribute("propertyList", propertyList);
			
//			/**
//			 * 商品sku列表
//			 */
//			List goodsSkuList = goodsBIZ.queryGoodsSkuList(goodsId);
//			model.addAttribute("goodsSkuList", goodsSkuList);

		}else{
			model.addAttribute("goodsSkuId", goodsMap.get("goods_sku_id"));
		}
		
		/**
		 * 微信分享
		 */
		String ticket = WeChatHelper.getTicket(request);
		String timestamp = WeChatHelper.getTimeStamp();
		String nonceStr = WeChatHelper.getNonceStr();
		String url = "http://wchat.mperfit.com/goods/detail?goods_id="+goodsId;
		model.addAttribute("appId", WeChatConfig.APP_ID);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonceStr", nonceStr);
		SortedMap params = WeChatHelper.getSign(nonceStr,ticket,timestamp,url);
		String sign = WeChatHelper.sign(params);
		model.addAttribute("signature", sign);
		
		return "/goods/goods_details";
	}
	
	/**
	 * 通过属性定位到SKU商品
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getSkuGoods")
	@ResponseBody
	public Object getSkuGoods(HttpServletRequest request) throws IOException{
		String optionIds = request.getParameter("option_ids");
		if(ValidatorHelper.isEmpty(optionIds)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		try{
			
			Map goodsSkuMap = goodsBIZ.getGoodsSkuByOptionIds(optionIds);
			if(goodsSkuMap == null){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
		
			Map map = new HashMap();
			map.put("goodsSku", goodsSkuMap);
			return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS,map);
		}catch(Exception e){
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
	}
	
	
	/**
	 * 订单确认支付
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order/confirm")
	public String orderConfirm(Model model,HttpServletRequest request,HttpServletResponse response){
		String addressId = request.getParameter("address_id");
		String gsId = request.getParameter("gs_id");
		String num = request.getParameter("number");
		if(!ValidatorHelper.isNumber(gsId) || !ValidatorHelper.isNumber(num)){
			return VariableHelper.ERROR_JSP;
		}
		
		long goodsSkuId = Long.parseLong(gsId);
		int number = Integer.parseInt(num);
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		/**
		 * 获得当前商品
		 */
		Map goodsMap = goodsBIZ.getGoodsPayMap(goodsSkuId);
		if(goodsMap == null){
			return VariableHelper.ERROR_JSP;
		}
		model.addAttribute("goods", goodsMap);
		
		/**
		 * 购买数量最小1最大99
		 */
		if(number <= 0){
			number = 1;
		}
		if(number > 99){
			number = 99;
		}
		model.addAttribute("number", number);
		model.addAttribute("price", CommonHelper.getDobule(Double.parseDouble(
					goodsMap.get("price").toString())*number));
		
		
		/**
		 * 用户地址
		 */
		Map addressMap = null;
		if(ValidatorHelper.isNumber(addressId)){
			addressMap = addressBIZ.getByAddressId(Long.parseLong(addressId));
		}else{
			addressMap = addressBIZ.getFrequentlyAddress(userId);
		}
		model.addAttribute("address", addressMap);
		
		return "/goods/order_confirm";
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
		String status = request.getParameter("status");
		
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		Integer intStatus = null;
		if(ValidatorHelper.isNumber(status)){
			intStatus = Integer.parseInt(status);
		}
		
		int totalPage = Utils.getTotalPage(goodsOrderBIZ.getOrderCount(userId, intStatus),10);
		List list = goodsOrderBIZ.queryOrderList(userId, intStatus,null,null); 
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("totalPage", totalPage);
		return "/goods/order_list";
	}
	
	/**
	 * 订单分页更多
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/order/listMore")
	@ResponseBody
	public Object orderListMore(HttpServletRequest request) throws IOException{
		String status = request.getParameter("status");
		String page = request.getParameter("page");
		
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		Integer intStatus = null;
		if(ValidatorHelper.isNumber(status)){
			intStatus = Integer.parseInt(status);
		}
		
		Integer intPage = 1;
		if(ValidatorHelper.isNumber(page)){
			intPage = Integer.parseInt(page);
		}
		
		try {
			int totalPage = Utils.getTotalPage(goodsOrderBIZ.getOrderCount(userId, intStatus),10);
			if(intPage > totalPage){
				intPage = totalPage;
			}
			
			List list = goodsOrderBIZ.queryOrderList(userId, intStatus,(intPage-1)*10, 10);
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
	
	/**
	 * 取消订单
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/order/doCancel")
	@ResponseBody
	public Object doCancel(HttpServletRequest request) throws IOException{
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		long orderId = Long.parseLong(oId);
		long userId = (long)request.getSession().getAttribute(VariableHelper.LOGIN_SESSION_USER);
		
		try {
			
			GoodsOrder goodsOrder = goodsOrderBIZ.getById(orderId);
			if(goodsOrder == null || goodsOrder.getUserId().longValue() != userId){
				return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
			}
			
			boolean result = goodsOrderBIZ.updateOrderCancel(orderId);
			if(result){
				return MapResult.build(MapResult.CODE_SUCCESS,MapResult.MESSAGE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MapResult.build(MapResult.CODE_SYS_ERROR,MapResult.MESSAGE_ERROR);
		}
		return MapResult.build(MapResult.CODE_FAILURE,MapResult.MESSAGE_FAILURE);
		
	}
	
	
	/**
	 * 
	 * 订单详情
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/order/detail")
	public String orderDetail(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException{
		String oId = request.getParameter("order_id");
		if(!ValidatorHelper.isNumber(oId)){
			//return MapResult.build(MapResult.CODE_PARAM_ERROR,MapResult.MESSAGE_PARAMETER);
		}
		
		long orderId = Long.parseLong(oId);
		long userId = (long)request.getSession().getAttribute(
				VariableHelper.LOGIN_SESSION_USER);
		
		
		return "";
	}
}
